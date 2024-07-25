package com.thesis.sofen.services;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.DetailDTO;
import com.thesis.sofen.entities.HotelService.HotelService;
import com.thesis.sofen.entities.HotelService.HotelServiceDetail;
import com.thesis.sofen.entities.Language;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.mapper.HotelServiceDetailMapper;
import com.thesis.sofen.repositories.HotelServiceDetailRepository;
import com.thesis.sofen.repositories.HotelServiceRepository;
import com.thesis.sofen.repositories.LanguageRepository;
import com.thesis.sofen.request.hotelService.HotelServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceServices {
    private final HotelServiceRepository hotelServiceRepo;
    private final HotelServiceDetailRepository hotelServiceDetailRepo;
    private final LanguageRepository languageRepo;

    public HotelService createService(HotelServiceRequest request)
            throws ResourceNotFoundException, DuplicateFieldException {
        if (hotelServiceRepo.existsByName(request.getName())) {
            throw new DuplicateFieldException("service_name_duplicate", "Dịch vụ đã tồn tại với tên " + request.getName());
        }
        HotelService hotelService = new HotelService();
        hotelService.setName(request.getName());
        hotelService.setPrice(request.getPrice());
        hotelService.setStatus(EUserStatus.ACTIVE);
        hotelServiceRepo.save(hotelService);

        createServiceDetails(request.getServiceDetails(), hotelService);
        return hotelService;
    }

    private void createServiceDetails(List<DetailDTO> serviceDetails, HotelService hotelService) throws ResourceNotFoundException, DuplicateFieldException {
        for (DetailDTO detailDTO : serviceDetails) {
            Language lang = languageRepo.findByCode(detailDTO.getLangCode());
            if (lang == null) {
                hotelServiceRepo.delete(hotelService);
                throw new ResourceNotFoundException("language_not_found", "Ngôn ngữ không tồn tại");
            }
            if (hotelServiceDetailRepo.existsByNameAndLangCode(detailDTO.getName(), detailDTO.getLangCode())) {
                hotelServiceRepo.delete(hotelService);
                throw new DuplicateFieldException("service_name_duplicate", "Dịch vụ đã tồn tại với tên " + detailDTO.getName());
            }
        }

        for (DetailDTO detailDTO : serviceDetails) {
            Language lang = languageRepo.findByCode(detailDTO.getLangCode());
            HotelServiceDetail hotelServiceDetail = HotelServiceDetailMapper.INSTANCE.toEntity(detailDTO, lang, hotelService);
            hotelServiceDetailRepo.save(hotelServiceDetail);
        }

    }

    public Object updateService(HotelServiceRequest request, Long id) {
        return null;
    }

    public HotelService updateServiceStatus(Long id, EUserStatus status) throws ResourceNotFoundException {
        HotelService hotelService = hotelServiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("service_not_found", "Dịch vụ không tồn tại"));
        hotelService.setStatus(status);
        return hotelServiceRepo.save(hotelService);

    }

    public List<HotelService> getAllHotelService(UUID hotelId) {
        if (hotelId != null)
            return hotelServiceRepo.findAllByHotelId(hotelId);
        return hotelServiceRepo.findAll();
    }
}
