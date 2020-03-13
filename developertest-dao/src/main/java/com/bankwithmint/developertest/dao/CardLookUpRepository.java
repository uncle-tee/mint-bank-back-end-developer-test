package com.bankwithmint.developertest.dao;

import com.bankwithmint.developertest.domain.CardLookUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public interface CardLookUpRepository extends JpaRepository<CardLookUp, Long> {
    Optional<CardLookUp> findByNumber(String number);

    Page<CardLookUp> findCardLookUpBy(Pageable pageable);
}
