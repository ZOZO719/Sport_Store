package com.store.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.store.entities.HeroBanner;

@Repository
public interface HeroRepo extends JpaRepository<HeroBanner,Long>{
    List<HeroBanner> findByIsActiveTrue();
}
