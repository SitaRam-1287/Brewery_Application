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
    List<Item> findByFoodType(FoodType foodType);

    @Query("SELECT i FROM Item i ORDER BY i.quantityOrdered DESC limit 8")
    List<Item> findTopItemsByOrderedQuantity();

    @Query("SELECT i FROM Item i ORDER BY i.rating DESC limit 8")
    List<Item> findTopItemsByRating();

    List<Item> findByFoodTypeOrderByRatingDesc(FoodType foodType);

    List<Item> findByFoodTypeOrderByQuantityOrderedDesc(FoodType foodType);

    Item findByName(String name);

}
