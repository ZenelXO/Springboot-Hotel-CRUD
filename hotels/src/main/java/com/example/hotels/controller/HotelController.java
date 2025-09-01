package com.example.hotels.controller;

import com.example.hotels.dto.HotelDTO;
import com.example.hotels.model.Address;
import com.example.hotels.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService service;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@Validated @RequestBody HotelDTO dto) {
        return ResponseEntity.ok(service.createHotel(dto));
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(service.getAllHotels());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<HotelDTO>> getHotelsByCity(@PathVariable String city) {
        return ResponseEntity.ok(service.getHotelsByCity(city));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<HotelDTO> updateAddress(@PathVariable Long id,@Validated @RequestBody Address address) {
        return ResponseEntity.ok(service.updateAddress(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        service.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}