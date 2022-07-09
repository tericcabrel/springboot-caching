package com.tericcabrel.springbootcaching.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class SearchProductDto {
    @NotBlank(message = "This field is required")
    private String name;

    private String category;

    private float minPrice;

    private float maxPrice;

    private String available; // yes or no
}
