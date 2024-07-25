package com.thesis.sofen.services;

import com.thesis.sofen.entities.Language;
import com.thesis.sofen.entities.RoomType.RoomType;
import com.thesis.sofen.entities.RoomType.RoomTypeDetail;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.LanguageRepository;
import com.thesis.sofen.repositories.RoomTypeDetailRepository;
import com.thesis.sofen.repositories.RoomTypeRepository;
import com.thesis.sofen.request.roomType.RoomTypeDetailRequest;
import com.thesis.sofen.request.roomType.RoomTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepo;
    private final RoomTypeDetailRepository roomTypeDetailRepo;

    private final LanguageRepository languageRepo;
    public RoomType create(RoomTypeRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {
        if(roomTypeRepo.existsByName(request.getName())){
            throw new DuplicateFieldException("room_type_duplicate","Room type already exists");
        }
        RoomType roomType = new RoomType();
        roomType.setName(request.getName());
        roomType.setArea(request.getArea());
        roomType.setMaxAdults(request.getMaxAdults());
        roomType.setMaxChildren(request.getMaxChildren());
        roomType.setPrice(request.getPrice());
        roomTypeRepo.save(roomType);

        createRoomTypeDetails(request.getRoomTypeDetailRequests(), roomType);


        return roomType;
    }

    /**
     * Create room type details
     * @param roomTypeDetailRequests List<RoomTypeDetailRequest>
     * @param roomType RoomType
     * @throws DuplicateFieldException
     * @throws ResourceNotFoundException
     */
    private void createRoomTypeDetails(List<RoomTypeDetailRequest> roomTypeDetailRequests, RoomType roomType) throws DuplicateFieldException, ResourceNotFoundException {
        for(RoomTypeDetailRequest detail : roomTypeDetailRequests){
            Language language = languageRepo.findByCode(detail.getLangCode());
            if(language == null){
                roomTypeRepo.delete(roomType);
                throw new ResourceNotFoundException("language_not_found", "Không thể tìm thấy ngôn ngữ với mã: " + detail.getLangCode());
            }
            if(roomTypeDetailRepo.existsByNameAndLanguageId(roomType.getName(), language.getId())){
                roomTypeRepo.delete(roomType);
                throw new DuplicateFieldException("room_type_name_duplicate", "Tên loại phòng đã tồn tại với ngôn ngữ: " + language.getName());
            }
        }
        for(RoomTypeDetailRequest detail : roomTypeDetailRequests){
            RoomTypeDetail roomTypeDetail = new RoomTypeDetail();
            roomTypeDetail.setRoomType(roomType);
            roomTypeDetail.setLanguage(languageRepo.findByCode(detail.getLangCode()));
            roomTypeDetail.setName(detail.getName());
            roomTypeDetail.setDescription(detail.getDescription());
            roomTypeDetailRepo.save(roomTypeDetail);
        }
    }


    public Object update(RoomTypeRequest request, Long id) throws ResourceNotFoundException {
        RoomType roomType = roomTypeRepo.findById(id).orElse(null);
        if(roomType == null){
            throw new ResourceNotFoundException("room_type_not_found", "Không tìm thấy phòng với id: "+id);
        }
        roomType.setName(request.getName());
        roomType.setArea(request.getArea());
        roomType.setMaxAdults(request.getMaxAdults());
        roomType.setMaxChildren(request.getMaxChildren());
        roomType.setPrice(request.getPrice());
        roomTypeRepo.save(roomType);

        return null;
    }

    public List<RoomType> getAllRoomType() {
        return roomTypeRepo.findAll();
    }
}
