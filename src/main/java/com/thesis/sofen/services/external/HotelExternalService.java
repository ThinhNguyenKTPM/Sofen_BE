package com.thesis.sofen.services.external;

import com.thesis.sofen.dto.Interface.IRoomFilter;
import com.thesis.sofen.dto.Interface.IServiceResponse;
import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.HotelImage;
import com.thesis.sofen.entities.Room;
import com.thesis.sofen.entities.RoomType.RoomType;
import com.thesis.sofen.entities.RoomType.RoomTypeDetail;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.HotelAddressRepository;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.repositories.RoomFurnitureRepository;
import com.thesis.sofen.repositories.RoomRepository;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import com.thesis.sofen.response.External.Hotel.HotelDetailResponse;
import com.thesis.sofen.response.External.RoomDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelExternalService {
//    @Value("${app.expiration_time_booking}")
//    private Integer expiredTime;
    private final HotelRepository hotelRepo;
    private final RoomRepository roomRepo;
    private final RoomFurnitureRepository roomFurnitureRepo;
    private final HotelAddressRepository hotelAddressRepo;

    public List<RoomDetailResponse> filter(SearchHotelRequest query, String lang) throws ResourceNotFoundException {
        List<IRoomFilter> roomFilters = hotelRepo.filterHotel1(query);
        List<RoomDetailResponse> response = new ArrayList<>();
        //filter room
        if (!roomFilters.isEmpty()) {
            roomFilters.removeIf(room ->
                    ((query.getAdult() != null && room.getAdult() * room.getRemain() < query.getAdult()))
                            || (query.getRoomAmount() != null && room.getRemain() < query.getRoomAmount())
            );
            response = getRoomsDetail(roomFilters, lang);
        }
        if (response.isEmpty()) {
            throw new ResourceNotFoundException("room_not_found", "Không tìm thấy phòng phù hợp");
        }

        return response;
    }

    /**
     * Get hotel detail
     *
     * @param hotelId UUID
     * @param lang    String
     */
    public HotelDetailResponse getHotelDetailByID(UUID hotelId, String lang) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("hotel_not_found", "Hotel not found with id " + hotelId.toString())
        );
        HotelDetailResponse response = convertToResponse(hotel, lang, true);
        return response;
    }

    /**
     * Convert hotel to response
     *
     * @param hotel Hotel
     * @param lang  String
     */
    private HotelDetailResponse convertToResponse(Hotel hotel, String lang, Boolean isDetail) {
        HotelDetailResponse response = new HotelDetailResponse();
        response.setHotelName(hotel.getName());
        String address = hotel.getAddressDetail() + ", "
                + hotel.getHotelAddress().getWard() + ", " + hotel.getHotelAddress().getDistrict()
                + ", " + hotel.getHotelAddress().getProvince() + ", " + hotel.getHotelAddress().getNation();
        response.setHotelAddress(address);
        List<IServiceResponse> services = hotelRepo.getServicesByHotelIdAndLang(hotel.getId(), lang);
        response.setServices(hotelRepo.getServicesByHotelIdAndLang(hotel.getId(), lang));
        response.setFromPrice(hotelRepo.getMinPriceByHotelId(hotel.getId()));
        if (isDetail) {
            response.setImages(hotel.getImages());
            response.setPolicies(hotelRepo.getPoliciesByHotelIdAndLang(hotel.getId(), lang));

            String description = Objects.requireNonNull(hotel.getHotelInfos().stream()
                    .filter(x -> x.getLanguage().getCode().equals(lang)).findFirst().orElse(null)).getDescription();
            if(description != null)
                response.setHotelDescription(description);
        } else {
            response.setImages(hotel.getImages().stream().filter(HotelImage::getIsMain).collect(Collectors.toList()));
        }

        return response;
    }

    /**a
     * Get room detail
     *
     * @param list List<IRoomFilter>
     * @param lang String
     */
    public List<RoomDetailResponse> getRoomsDetail(List<IRoomFilter> list, String lang) {
        List<RoomDetailResponse> result = new ArrayList<>();
        for (var item : list) {
            RoomDetailResponse roomResponse = new RoomDetailResponse();

            Room room = roomRepo.findById(item.getRoomId()).orElse(null);
            RoomType roomType = room.getRoomType();
            RoomTypeDetail roomTypeDetail = roomType.getRoomTypeDetails().stream().filter(x -> x.getLanguage().getCode().equals(lang)).findFirst().orElse(null);
            roomResponse.setId(room.getId());
            roomResponse.setArea(roomType.getArea());
            roomResponse.setBed(roomTypeDetail.getBed());
            roomResponse.setRoomTypeName(roomTypeDetail.getName());
            roomResponse.setDescription(roomTypeDetail.getDescription());
            roomResponse.setPrice(roomType.getPrice());
            roomResponse.setImages(room.getRoomImages());
            roomResponse.setMaxAdult(roomType.getMaxAdults());
            roomResponse.setMaxChildren(roomType.getMaxChildren());
            roomResponse.setFurnitures(roomFurnitureRepo.getDetailByRoomIdAndLang(room.getId(), lang));
            roomResponse.setRemain(item.getRemain());
            result.add(roomResponse);
        }
        result.sort(Comparator.comparing(RoomDetailResponse::getPrice).reversed());
        return result;
    }

    //    {
//        "Việt Nam": {
    //        "Bà Rịa - Vũng Tàu": [],
    //        "Khánh Hòa": [],
    //        "Cần Thơ": []
//          }
//    }
    public Object getNationality(String lang) {
        List<String> nations = hotelAddressRepo.findAllNations();

        HashMap<String, Object> response = new HashMap<>();
        for (var nation : nations) {
            List<String> province = hotelAddressRepo.findProvincesByNation(nation);
            HashMap<String, Object> provinces = new HashMap<>();
            for (var p : province) {
                List<String> district = hotelAddressRepo.findDistrictsByNationAndProvince(nation, p);
                if (district.size() > 1)
                    provinces.put(p, district);
                else provinces.put(p, new ArrayList<>());
            }
            response.put(nation, provinces);
        }
        return response;
    }

    public List<HotelDetailResponse> getHotelByDestination(String nation, String province, String lang) {
        List<Hotel> hotels = hotelRepo.getHotelByDestination(nation, province);
        List<HotelDetailResponse> response = new ArrayList<>();
        for (var hotel : hotels) {
            response.add(convertToResponse(hotel, lang, false));
        }
        return response;
    }

    public Object getListHotelName(String lang) {
        return hotelRepo.getListHotelName();
    }
}
