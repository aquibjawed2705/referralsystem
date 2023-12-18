package com.suggest.referal.ReferalSystem.repository;

import com.suggest.referal.ReferalSystem.entity.UrlFinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlFinder,Long> {
    List<UrlFinder> findByName(String name);
}
