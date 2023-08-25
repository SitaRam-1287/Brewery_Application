package com.brewery.application.repository;

import com.brewery.application.entity.Item;
import com.brewery.application.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    public List<Item> findByFoodType(FoodType foodType);

    @Query("SELECT p FROM Item p ORDER BY p.quantityOrdered DESC limit 9")
    public Item findByItemCount();

    @Query("SELECT p FROM Item p ORDER BY p.rating DESC limit 9")
    public Item findByMoreRating();

    @Query("SELECT p FROM Item p where p.foodType = :foodType ORDER BY p.rating DESC")
    public List<Item> findByItemRatingAndFoodType(@Param("foodType") FoodType foodType);

    @Query("SELECT p FROM Item p where p.foodType = :foodType ORDER BY p.quantityOrdered DESC")
    public List<Item> findByItemOrderedAndFoodType(@Param("foodType") FoodType foodType);

    public Item findByName(String name);

}
