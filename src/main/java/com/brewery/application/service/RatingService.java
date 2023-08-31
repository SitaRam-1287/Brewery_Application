package com.brewery.application.service;

import com.brewery.application.dto.inputdto.RatingInDto;
import com.brewery.application.dto.outputdto.RatingOutDto;
import java.util.List;
import java.util.UUID;

public interface RatingService{

    public RatingOutDto createRating(UUID userId,RatingInDto input);

    public RatingOutDto getRating(UUID id);

    public RatingOutDto updateRating(RatingInDto input);

    public RatingOutDto partialUpdateRating(RatingInDto input);

    public List<RatingOutDto> getAllRatings();

    public RatingOutDto deleteRating(UUID id);

    public List<RatingOutDto> getRatingByItemId(UUID id);

    public List<RatingOutDto> getRatingByOrderId(UUID id);

    public List<RatingOutDto> getRatingByUserId(UUID id);
}
