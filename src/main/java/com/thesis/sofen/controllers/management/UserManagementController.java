package com.thesis.sofen.controllers.management;

import com.thesis.sofen.entities.User;
import com.thesis.sofen.request.filter.UserFilterRequest;
import com.thesis.sofen.services.management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class UserManagementController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<?> getUserCreated(@RequestParam(value = "time") String time){
        return ResponseEntity.ok(userService.getUserCreated(time));
    }
    @GetMapping("/users/count")
    public ResponseEntity<?> getUserCount(
            @RequestParam(value = "role")String role
            ){
        return ResponseEntity.ok(userService.getUserCount(role));
    }
    @GetMapping("/hotels/count")
    public ResponseEntity<?> getHotelCount(){
        return ResponseEntity.ok(userService.getHotelCount());
    }

    @GetMapping("/user/customer")
    public ResponseEntity<Page<User>> getCustomer(@ModelAttribute UserFilterRequest query){
        Pageable pageable = query.getPageAndSort();
        return ResponseEntity.ok(userService.getCustomer(pageable, query));
    }

}
