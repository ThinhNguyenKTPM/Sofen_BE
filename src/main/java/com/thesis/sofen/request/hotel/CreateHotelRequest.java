package com.thesis.sofen.request.hotel;

import com.thesis.sofen.request.HotelAddressRequest;
import com.thesis.sofen.request.HotelContactRequest;
import com.thesis.sofen.dto.ImageDTO;
import lombok.Data;

import java.util.List;

@Data
public class CreateHotelRequest {
    private String name;
    private String addressDetail;
    private String description;
    private HotelAddressRequest hotelAddress;
    private HotelContactRequest hotelContact;
    private List<ImageDTO> images;
    private List<HotelInfoDTO> hotelInfo;

    @Data
    public static class HotelInfoDTO {
        private Integer langId;
        private String description;
    }
}

