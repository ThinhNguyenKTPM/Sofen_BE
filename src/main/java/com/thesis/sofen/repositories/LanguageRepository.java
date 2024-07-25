package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByCode(String code);
    Language findByCode(String code);
}
