package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.RatingInDto;
import com.brewery.application.dto.outputdto.RatingOutDto;
import com.brewery.application.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public RatingOutDto createRating(@RequestHeader UUID userId,@RequestBody RatingInDto input){

        return ratingService.createRating(userId,input);
    }

    @GetMapping("{id}")
    public RatingOutDto getRating(@PathVariable UUID id){

        return ratingService.getRating(id);
    }

    @PutMapping
    public RatingOutDto updateRating(RatingInDto input){

        return ratingService.updateRating(input);
    }


    @PatchMapping
    public RatingOutDto partialUpdateRating(RatingInDto input){

        return ratingService.partialUpdateRating(input);
    }

    @GetMapping("/getAll")
    public List<RatingOutDto> getAllRatings(){
        return ratingService.getAllRatings();
    }

    @DeleteMapping("{id}")
    public RatingOutDto deleteRating(@PathVariable UUID id){

        return ratingService.deleteRating(id);
    }

    @GetMapping("/user")
    public List<RatingOutDto> getRatingByUser(@RequestHeader UUID id){

        return ratingService.getRatingByUserId(id);

    }

    @GetMapping("/item/{id}")
    public List<RatingOutDto> getRatingByItem(@PathVariable UUID id){

        return ratingService.getRatingByItemId(id);

    }

    @GetMapping("/order/{id}")
    public List<RatingOutDto> getRatingByOrder(@PathVariable UUID id){

        return ratingService.getRatingByOrderId(id);

    }

}
