package com.thesis.sofen.services;

import com.thesis.sofen.dto.DetailDTO;
import com.thesis.sofen.entities.Furniture.FurnitureDetail;
import com.thesis.sofen.entities.Furniture.RoomFurniture;
import com.thesis.sofen.entities.Language;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.LanguageRepository;
import com.thesis.sofen.repositories.RoomFurnitureDetailRepository;
import com.thesis.sofen.repositories.RoomFurnitureRepository;
import com.thesis.sofen.request.RoomFurnitureRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomFurnitureService {
    private final RoomFurnitureDetailRepository roomFurnitureDetailRepo;
    private final RoomFurnitureRepository roomFurnitureRepo;
    private final LanguageRepository languageRepo;

    public List<RoomFurniture> createList(List<RoomFurnitureRequest> request) {
        List<RoomFurniture> list = new ArrayList<>();
        for(RoomFurnitureRequest roomFurnitureRequest : request) {
            try {
                list.add(create(roomFurnitureRequest));
            } catch (DuplicateFieldException | ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public RoomFurniture create(RoomFurnitureRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {
        if (roomFurnitureRepo.existsByName(request.getName())) {
            throw new DuplicateFieldException("room_furniture_name_duplicate", "Tên nội thất phòng đã tồn tại");
        }
        RoomFurniture roomFurniture = new RoomFurniture();
        roomFurniture.setName(request.getName());
        roomFurnitureRepo.save(roomFurniture);

        createFurnitureDetail(request.getFurnitureDetails(), roomFurniture);

        return roomFurniture;
    }

    /**
     * Create furniture details.
     *      If language is not found or furniture name already exists with the language
     *      then delete the room furniture created before
     */
    private void createFurnitureDetail(List<DetailDTO> furnitureDetails, RoomFurniture roomFurniture)
            throws ResourceNotFoundException, DuplicateFieldException {
        for (DetailDTO detail : furnitureDetails) {
            Language language = languageRepo.findByCode(detail.getLangCode());
            if (language == null) {
                roomFurnitureRepo.delete(roomFurniture);
                throw new ResourceNotFoundException("language_not_found", "Không thể tìm thấy ngôn ngữ với mã: " + detail.getLangCode());
            }
            if(roomFurnitureDetailRepo.existsByNameAndLanguageId(roomFurniture.getName(), language.getId())) {
                roomFurnitureRepo.delete(roomFurniture);
                throw new DuplicateFieldException("room_furniture_name_duplicate", "Tên nội thất phòng đã tồn tại với ngôn ngữ: " + language.getName());
            }
        }
        for(DetailDTO detail : furnitureDetails) {
            FurnitureDetail furnitureDetail = new FurnitureDetail();
            furnitureDetail.setRoomFurniture(roomFurniture);
            furnitureDetail.setLanguage(languageRepo.findByCode(detail.getLangCode()));
            furnitureDetail.setName(detail.getName());
            furnitureDetail.setDescription(detail.getDescription());
            roomFurnitureDetailRepo.save(furnitureDetail);
        }
    }


    public Object getAllRoomFurniture(Long id) {
        if (id == null) {
            return roomFurnitureRepo.findAll();
        }
        return roomFurnitureRepo.findByRoomId(id);
    }
}
