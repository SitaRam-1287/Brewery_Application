package com.brewery.application.repository;

import com.brewery.application.dto.outputdto.RatingOutDto;
import com.brewery.application.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    public List<Rating> findRatingByItemId(UUID id);

    public List<Rating> findRatingByOrderId(UUID id);

    public List<Rating> findRatingByUserId(UUID id);

}
