package com.thesis.sofen.request.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAndFilter implements Serializable {
    private final static Integer DEFAULT_SIZE = 10;
    private final static String ASC = "ascend";
    private final static String DESC = "descend";

    private Integer page;
    private Integer size;
    private String sort;
    private String direction;

//    private T filter;
    public Pageable getPageAndSort() {
        Integer pageIndex = page != null && page > 0 ? page - 1 : 0;
        Integer pageSize = size == null ? DEFAULT_SIZE : size;

        if (StringUtils.isNotBlank(sort)) {

            if (direction.equalsIgnoreCase(DESC)) {
                return PageRequest.of(pageIndex, pageSize, Sort.by(sort).descending());
            } else {
                return PageRequest.of(pageIndex, pageSize, Sort.by(sort).ascending());
            }
        } else {
            return PageRequest.of(pageIndex, pageSize);
        }
    }

}
