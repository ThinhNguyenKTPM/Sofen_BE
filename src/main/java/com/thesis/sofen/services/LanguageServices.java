package com.thesis.sofen.services;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.language.CreateLanguageRequest;
import com.thesis.sofen.entities.Language;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.mapper.LanguageMapper;
import com.thesis.sofen.repositories.LanguageRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.thesis.sofen.constant.LanguageConstant.DEFAULT_LANGUAGE;

@Service
@RequiredArgsConstructor
public class LanguageServices {
    private final LanguageRepository languageRepo;
    public ResponseEntity<?> create(CreateLanguageRequest request) throws DuplicateFieldException {
        Language lang = languageRepo.findByCode(request.getCode());
        if(lang != null){
            throw new DuplicateFieldException("lang_duplicate","Language code already exists");
        }
        Language entity = LanguageMapper.INSTANCE.toEntity(request);

        return ResponseEntity.ok(languageRepo.save(entity));
    }

    public List<Language> getAll(){
        return languageRepo.findAll();
    }

    public String getCurrentLanguage(HttpServletRequest request) {
        try {
            String lang = request.getHeaders("Accept-Language").nextElement();
            System.out.println(lang);
            if(!languageRepo.existsByCode(lang))
                throw new ResourceNotFoundException("language_not_found", "Language not found");
            return lang != null ? lang : DEFAULT_LANGUAGE;
        } catch (Exception e) {
            return DEFAULT_LANGUAGE;
        }
    }

}
