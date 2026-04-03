package com.store.store.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.dtos.HomeDto;
import com.store.store.serviceImplementation.HomeSerImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomePageController {
    private final HomeSerImpl homeSerImpl;

    @GetMapping("/api/home")
    public ResponseEntity<HomeDto> getHome(){
        HomeDto homeDto = homeSerImpl.getHomeData();
        return ResponseEntity.ok(homeDto);
    }
}
