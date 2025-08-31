package com.example.hotels.service;

import com.example.hotels.dto.HotelDTO;
import com.example.hotels.model.Hotel;
import com.example.hotels.model.Address;
import com.example.hotels.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository repository;

    public HotelDTO createHotel(HotelDTO dto) {
        Hotel hotel = Hotel.builder().name(dto.getName()).stars(dto.getStars()).address(dto.getAddress()).build();
        hotel = repository.save(hotel);
        dto.setId(hotel.getId());
        return dto;
    }

    public List<HotelDTO> getAllHotels() {
        return repository.findAll().stream()
                .map(h -> HotelDTO.builder().id(h.getId()).name(h.getName()).stars(h.getStars()).address(h.getAddress()).build()).collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelsByCity(String city) {
        return repository.findByAddressCity(city).stream()
                .map(h -> HotelDTO.builder().id(h.getId()).name(h.getName()).stars(h.getStars()).address(h.getAddress()).build()).collect(Collectors.toList());
    }

    public HotelDTO updateAddress(Long id, Address newAddress) {
        Hotel hotel = repository.findById(id).orElseThrow(() -> new RuntimeException("Hotel no encontrado!"));
        hotel.setAddress(newAddress);
        repository.save(hotel);
        return HotelDTO.builder().id(hotel.getId()).name(hotel.getName()).stars(hotel.getStars()).address(hotel.getAddress()).build();
    }

    public void deleteHotel(Long id) {
        repository.deleteById(id);
    }
}