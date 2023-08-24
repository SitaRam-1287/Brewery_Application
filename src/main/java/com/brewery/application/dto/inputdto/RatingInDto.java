package com.brewery.application.dto.inputdto;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@Validated
public class RatingInDto {

    private String comment;

    private Integer rating;

    private UUID itemId;

    private UUID orderId;

    private UUID userId;
}
