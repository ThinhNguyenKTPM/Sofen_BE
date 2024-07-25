package com.thesis.sofen.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConstant {
    @Value("${app.validate_time_limit}")
    private Integer validateTimeLimit;

    @Value("${app.default.avatar}")
    private String defaultAvatar;
}
