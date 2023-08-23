package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.RatingInDto;
import com.brewery.application.dto.outputdto.RatingOutDto;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.Order;
import com.brewery.application.entity.Rating;
import com.brewery.application.entity.User;
import com.brewery.application.repository.ItemRepository;
import com.brewery.application.repository.OrderRepository;
import com.brewery.application.repository.RatingRepository;
import com.brewery.application.repository.UserRepository;
import com.brewery.application.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RatingOutDto createRating(RatingInDto input) {
        Rating rating = convertDtoToEntity(input);
        rating = ratingRepository.save(rating);
        User user = userRepository.findById(input.getUserId()).orElseThrow(()->new RuntimeException());
        Item item = itemRepository.findById(input.getItemId()).orElseThrow(()->new RuntimeException());
        Order order = orderRepository.findById(input.getOrderId()).orElseThrow(()->new RuntimeException());
        rating.setUser(user);
        rating.setItem(item);
        rating.setOrder(order);
        return convertEntityToDto(rating);
    }

    @Override
    public RatingOutDto getRating(UUID id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating with given id is not found"));
        return convertEntityToDto(rating);
    }

    @Override
    public RatingOutDto updateRating(RatingInDto input) {
        Rating rating = convertDtoToEntity(input);
        Rating existingRating = ratingRepository.findById(rating.getId()).orElseThrow(()->new RuntimeException("Rating with given id is not found"));
        modelMapper.map(rating,existingRating);
        Rating currentRating = ratingRepository.save(existingRating);
        return convertEntityToDto(currentRating);
    }

    @Override
    public RatingOutDto partialUpdateRating(RatingInDto input) {
        return null;
    }

    @Override
    public List<RatingOutDto> getAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        List<RatingOutDto> ratingList = ratings.stream().map(rating -> convertEntityToDto(rating)).collect(Collectors.toList());
        return ratingList;
    }

    @Override
    public RatingOutDto deleteRating(UUID id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with given Id"));
        ratingRepository.delete(rating);
        return convertEntityToDto(rating);
    }

    @Override
    public List<RatingOutDto> getRatingByItemId(UUID id) {
        List<Rating> ratings = ratingRepository.findRatingByItemId(id);
        return ratings.stream().map(rating->convertEntityToDto(rating)).collect(Collectors.toList());
    }

    @Override
    public List<RatingOutDto> getRatingByOrderId(UUID id) {
        List<Rating> ratings = ratingRepository.findRatingByOrderId(id);
        return ratings.stream().map(rating->convertEntityToDto(rating)).collect(Collectors.toList());
    }

    @Override
    public List<RatingOutDto> getRatingByUserId(UUID id) {
        List<Rating> ratings = ratingRepository.findRatingByUserId(id);
        return ratings.stream().map(rating->convertEntityToDto(rating)).collect(Collectors.toList());
    }


    public Rating convertDtoToEntity(RatingInDto input){
        return modelMapper.map(input,Rating.class);
    }

    public RatingOutDto convertEntityToDto(Rating rating){
        return modelMapper.map(rating,RatingOutDto.class);
    }
}
