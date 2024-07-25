package com.thesis.sofen.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilterRequest extends PageAndFilter{
    @JsonProperty("status")
    private String status;
    @JsonProperty("search")
    private String search;
}
