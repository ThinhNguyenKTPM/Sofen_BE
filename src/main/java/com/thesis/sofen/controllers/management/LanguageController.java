package com.thesis.sofen.controllers.management;

import com.thesis.sofen.request.language.CreateLanguageRequest;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.services.LanguageServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/management/language")
@Slf4j
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageServices languageService;
    @GetMapping(value = "")
    public ResponseEntity<String> find(){
        return ResponseEntity.ok("Get Language");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findById(){
        return ResponseEntity.ok("Get Language by id");
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid CreateLanguageRequest request) throws DuplicateFieldException {

        return new ResponseEntity<>(languageService.create(request), HttpStatus.CREATED);
    }
    @PutMapping(value = "")
    public ResponseEntity<String> update(){
        return ResponseEntity.ok("Update Language");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Delete Language");
    }


}
