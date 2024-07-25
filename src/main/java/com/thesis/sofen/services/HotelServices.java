package com.thesis.sofen.services;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.entities.*;
import com.thesis.sofen.entities.HotelInfo.HotelInfo;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import com.thesis.sofen.entities.HotelService.HotelService;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.mapper.HotelAddressMapper;
import com.thesis.sofen.mapper.HotelContactMapper;
import com.thesis.sofen.mapper.HotelImageMapper;
import com.thesis.sofen.mapper.HotelMapper;
import com.thesis.sofen.repositories.*;
import com.thesis.sofen.request.filter.FilterHotelRequest;
import com.thesis.sofen.request.hotel.CreateHotelRequest;
import com.thesis.sofen.dto.ImageDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HotelServices {
    private final HotelRepository hotelRepo;
    private final HotelAddressRepository hotelAddressRepo;
    private final HotelContactRepository hotelContactRepo;
    private final HotelImageRepository hotelImageRepo;
    private final HotelPolicyRepository hotelPolicyRepo;
    private final HotelServiceRepository hotelServiceRepo;
    private final LanguageRepository langRepo;
    private final HotelInfoRepository hotelInfoRepo;
    /**
     * Create hotel
     *
     * @param request CreateHotelRequest
     * @return Hotel
     */
    @Transactional
    public Hotel createHotel(CreateHotelRequest request) throws DuplicateFieldException {
        if (hotelRepo.existsByName(request.getName())) { // check if hotel name already exists
            throw new DuplicateFieldException("hotel_name_duplicate", "Hotel name already exists with name: " + request.getName() + "!");
        }
        // save hotel contact and address
        HotelContact hotelContactEntity = HotelContactMapper.INSTANCE.toEntity(request.getHotelContact());
        hotelContactRepo.save(hotelContactEntity);

        HotelAddress hotelAddressEntity = HotelAddressMapper.INSTANCE.toEntity(request.getHotelAddress());
        hotelAddressRepo.save(hotelAddressEntity);

        List<HotelImage> listImage = new ArrayList<>();
        for (ImageDTO imageRequest : request.getImages()) {
            HotelImage hotelImageEntity = HotelImageMapper.INSTANCE.toEntity(imageRequest);
            hotelImageRepo.save(hotelImageEntity);
            listImage.add(hotelImageEntity);
        }
        List<HotelInfo> hotelInfos = new ArrayList<>();

        Hotel hotelEntity = HotelMapper.INSTANCE.toEntity(request);
        hotelEntity.setHotelContact(hotelContactEntity);
        hotelEntity.setHotelAddress(hotelAddressEntity);
        hotelEntity.setImages(listImage);
        hotelEntity.setStatus(EUserStatus.ACTIVE);
        hotelEntity.setHotelInfos(hotelInfos);
        hotelRepo.save(hotelEntity);
        for(CreateHotelRequest.HotelInfoDTO hotelInfoDTO : request.getHotelInfo()) {
            Language lang = langRepo.findById(hotelInfoDTO.getLangId()).orElse(null);
            if (lang == null) {
                throw new ResourceNotFoundException("lang_not_found", "Language not found with id: " + hotelInfoDTO.getLangId() + "!");
            }
            HotelInfo hotelInfo = new HotelInfo();
            hotelInfo.setHotel(hotelEntity);
            hotelInfo.setLanguage(lang);
            hotelInfo.setDescription(hotelInfoDTO.getDescription());
            hotelInfoRepo.save(hotelInfo);
            hotelInfos.add(hotelInfo);
        }
        hotelRepo.save(hotelEntity);
        return hotelEntity;
    }

    /**
     * Add policy to hotel
     *
     * @param hotelId
     * @param policyId
     * @return ResponseEntity<?>
     */
    public Hotel addPolicy(UUID hotelId, Long policyId) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            throw new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        HotelPolicy hotelPolicy = hotelPolicyRepo.findById(policyId).orElse(null);
        if (hotelPolicy == null) {
            throw new ResourceNotFoundException("policy_not_found", "Policy.json not found with id: " + policyId + "!");
        }
        List<HotelPolicy> hotelPolicies = hotel.getPolicies();
        if(hotelPolicies == null) {
            hotelPolicies = new ArrayList<>();
        }
        hotelPolicies.add(hotelPolicy);
        hotel.setPolicies(hotelPolicies);
        return hotelRepo.save(hotel);
    }

    /**
     * Remove policy from hotel
     *
     * @param hotelId  UUID
     * @param policyId Long
     * @return Object
     */
    public Object removePolicy(UUID hotelId, Long policyId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            return new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        List<HotelPolicy> policyList =  hotel.getPolicies();
        for (HotelPolicy policy : policyList) {
            if (policy.getId().equals(policyId)) {
                policyList.remove(policy);
                hotel.setPolicies(policyList);
                hotelRepo.save(hotel);
                break;
            }
        }
        return new HashMap<String, String>(){{
            put("message", "Policy removed successfully!");
        }};
    }

    /**
     * Add service to hotel
     *
     * @param hotelId   UUID
     * @param serviceId Long
     * @return Hotel
     */
    public Hotel addService(UUID hotelId, Long serviceId) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            throw new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        HotelService hotelService = hotelServiceRepo.findById(serviceId).orElse(null);
        if (hotelService == null) {
            throw new ResourceNotFoundException("service_not_found", "Service not found with id: " + serviceId + "!");
        }
        List<HotelService> hotelServices = hotel.getServices();
        if (hotelServices == null) {
            hotelServices = new ArrayList<>();
        }
        hotelServices.add(hotelService);
        hotel.setServices(hotelServices);

        return hotelRepo.save(hotel);
    }

    /**
     * Remove service from hotel
     *
     * @param hotelId   UUID
     * @param serviceId Long
     * @return Object
     */
    public Object removeService(UUID hotelId, Long serviceId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            return new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        List<HotelService> serviceList =  hotel.getServices();
        for (HotelService service : serviceList) {
            if (service.getId().equals(serviceId)) {
                serviceList.remove(service);
                hotel.setServices(serviceList);
                hotelRepo.save(hotel);
                break;
            }
        }
        return new HashMap<String, String>(){{
            put("message", "Service removed successfully!");
        }};
    }

    public Object getAllHotelName() {
        Object hotel = hotelRepo.getListHotelName();
        return hotel;
    }

    public   Page<Hotel> filterHotel(Pageable pageable, FilterHotelRequest request) {
        EUserStatus status = null;
        try {
            status = EUserStatus.valueOf(request.getStatus());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + request.getStatus());
        }
        var result = hotelRepo.filterHotel(status, pageable);

        return  result;
    }

    private Page<HashMap<String,Object>> mappingToResponse(Page<Hotel> result) {
        Page<HashMap<String,Object>> res = result.map(hotel -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", hotel.getId());
            map.put("name", hotel.getName());
            map.put("status", hotel.getStatus().toString());
            map.put("address", hotel.getHotelAddress());
            map.put("contact", hotel.getHotelContact());
            List<HotelImage> images = hotel.getImages();
            map.put("images", images);
            map.put("policies", hotel.getPolicies());
            map.put("services", hotel.getServices());
//            map.put("rooms", hotel.getRooms().stream().map(room -> {
//                HashMap<String, Object> roomMap = new HashMap<>();
//                roomMap.put("id", room.getId());
//                roomMap.put("amount", room.getAmount());
//                roomMap.put("image", room.getRoomImages());
//                roomMap.put("furnitures", room.getRoomFurnitures());
//                roomMap.put("status", room.getStatus().toString());
////                roomMap.put("bookingDetails", room.getBookingDetails());
//                return roomMap;
//            }).collect(Collectors.toList()));
//            var booking = hotel.getRooms().stream().map( (room) ->
//                room.getBookingDetails()
//            ).collect(Collectors.toList());
            return map;
        });
        return res;
    }

    public Hotel getHotelById(UUID id) {
        return hotelRepo.findById(id).orElse(null);
    }

    public Object addImage(UUID hotelId, HotelImage image) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            return new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        List<HotelImage> images = hotel.getImages();
        if(images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        hotel.setImages(images);
        hotelRepo.save(hotel);
        return new HashMap<String, String>(){{
            put("message", "Image added successfully!");
        }};
    }

    public Object removeImage(UUID hotelId, Long imageId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            return new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        List<HotelImage> images = hotel.getImages();
        for (HotelImage image : images) {
            if (image.getId().equals(imageId)) {
                images.remove(image);
                hotel.setImages(images);
                hotelRepo.save(hotel);
                break;
            }
        }
        return new HashMap<String, String>(){{
            put("message", "Image removed successfully!");
        }};
    }

    public Object updateImage(UUID hotelId, HotelImage image) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if (hotel == null) {
            return new ResourceNotFoundException("hotel_not_found", "Hotel not found with id: " + hotelId + "!");
        }
        List<HotelImage> images = hotel.getImages();
        for (HotelImage img : images) {
            if (img.getId().equals(image.getId())) {
                img.setUrl(image.getUrl());
                img.setIsMain(image.getIsMain());
                hotel.setImages(images);
                hotelRepo.save(hotel);
                break;
            }
        }
        return new HashMap<String, String>(){{
            put("message", "Image updated successfully!");
        }};
    }
}
