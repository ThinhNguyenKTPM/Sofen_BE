package com.thesis.sofen.controllers.Auth;

import com.thesis.sofen.exception.*;
import com.thesis.sofen.request.auth.*;
import com.thesis.sofen.response.Auth.LoginResponse;
import com.thesis.sofen.response.SuccessResponse;
import com.thesis.sofen.services.JwtService;
import com.thesis.sofen.services.auth.AuthService;
import com.thesis.sofen.util.ArgumentNotValid;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtUtils;
    /**
     *  Login a user
     */
    @PostMapping("/token")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bind)
            throws NotAcceptableException, MethodArgumentNotValidException, AccountLoginException {
        ArgumentNotValid.handleInvalidArgument(bind);
        return ResponseEntity.ok( authService.login(request));
    }


    /**
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request)
            throws DuplicateFieldException, NotAcceptableException {

        return ResponseEntity.ok(authService.register(request));
    }

    /**
     *  Validate a user
     */
    @PostMapping("/validate")
    public ResponseEntity<SuccessResponse> validate(@Valid @RequestBody ValidateRequest request)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(authService.validate(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ValidateRequest request)
            throws UnAuthorizedException, NotAcceptableException {
        var response = authService.forgotPassword(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request)
            throws DuplicateFieldException, NotAcceptableException {
        var response = authService.changePassword(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request)
            throws UnAuthorizedException {
        LoginResponse response = authService.generateRefreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest request
    ){
        return ResponseEntity.ok(authService.logout(request));
    }
}
