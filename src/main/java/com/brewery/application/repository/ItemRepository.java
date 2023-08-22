package com.brewery.application.repository;

import com.brewery.application.entity.Item;
import com.brewery.application.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    public List<Item> findByFoodType(FoodType foodType);
}
