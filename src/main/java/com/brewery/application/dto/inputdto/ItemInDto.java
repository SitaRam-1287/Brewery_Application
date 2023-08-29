package com.brewery.application.dto.inputdto;

import com.brewery.application.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemInDto {

    private String name;

    private String details;

    private Double price;

    private Boolean inStock;

    private FoodType foodType;
}
