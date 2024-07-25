package com.thesis.sofen.services.management;

import com.thesis.sofen.common.ERole;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.Interface.create.ICreateMonth;
import com.thesis.sofen.dto.Interface.create.ICreateWeek;
import com.thesis.sofen.dto.Interface.create.ICreateYear;
import com.thesis.sofen.entities.User;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.repositories.UserRepository;
import com.thesis.sofen.request.filter.UserFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final HotelRepository hotelRepo;
    public Object getUserCreated(String time){
        return switch (time) {
            case "date" -> this.getCreatedCountByDate("date");
            case "week" -> this.getCreatedCountByWeek("week");
            case "month" -> this.getCreatedCountByMonth("month");
            case "year" -> this.getCreatedCountByYear("year");
            default -> null;
        };
    }

    private Object getCreatedCountByYear(String year) {
        List<ICreateYear> list = userRepo.countUserCreatedByYear();
        List<Object> response = new ArrayList<>();
        for (ICreateYear item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }

    private Object getCreatedCountByMonth(String month) {
        List<ICreateMonth> list = userRepo.countUserCreatedByMonth();
        List<Object> response = new ArrayList<>();
        for (ICreateMonth item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getMonth() + "-" + item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }

    private Object getCreatedCountByWeek(String week) {
        List<ICreateWeek> list = userRepo.countUserCreatedByWeek();
        List<Object> response = new ArrayList<>();
        for (ICreateWeek item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getWeek() + "-" + item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }

    private Object getCreatedCountByDate(String date) {
        return userRepo.countUserCreatedByDate();
    }

    public Object getUserCount(String role) {
        ERole role1 = ERole.valueOf(role);
        int count = userRepo.countByRole(role1);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("count", count);
        return map;
    }

    public Object getHotelCount() {
        int count = hotelRepo.countHotels();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("count", count);
        return map;
    }


    public Page<User> getCustomer(Pageable pageable, UserFilterRequest query) {
        Page<User> res = null;
        if(query.getStatus() != null){
            EUserStatus status = EUserStatus.valueOf(query.getStatus());
            res =  userRepo.getUsersByRoleAndStatus(ERole.ROLE_USER, status, pageable, query.getSearch());
        }else {
            res = userRepo.getUsersByRoleAndStatus(ERole.ROLE_USER, null, pageable, query.getSearch());
        }
        return res;
    }
}
