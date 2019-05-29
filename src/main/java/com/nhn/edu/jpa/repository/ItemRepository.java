package com.nhn.edu.jpa.repository;

import com.nhn.edu.jpa.dto.ItemName;
import com.nhn.edu.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<ItemName> findByItemPriceGreaterThan(long price);
    
}
