package com.thesis.sofen.dto.Interface;

import com.thesis.sofen.common.EUserStatus;

import java.util.UUID;

public interface IHotelFilter {
    UUID getId();
    String getName();
    EUserStatus getStatus();
}
