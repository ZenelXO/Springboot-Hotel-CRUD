package com.example.hotels.dto;

import com.example.hotels.model.Address;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    @Max(5)
    private int stars;

    @NotNull
    private Address address;
}