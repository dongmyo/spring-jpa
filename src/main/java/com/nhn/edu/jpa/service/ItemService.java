package com.nhn.edu.jpa.service;

import com.nhn.edu.jpa.dto.ItemName;
import com.nhn.edu.jpa.entity.Item;
import com.nhn.edu.jpa.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void setUp() {
        Item item1 = new Item();
        item1.setItemName("apple");
        item1.setItemPrice(100L);

        Item item2 = new Item();
        item2.setItemName("orange");
        item2.setItemPrice(200L);

        Item item3 = new Item();
        item3.setItemName("banana");
        item3.setItemPrice(300L);

        itemRepository.saveAll(Arrays.asList(item1, item2, item3));
    }

    public List<String> getItemNamesByPriceMoreThan(long price) {
        return itemRepository.findByItemPriceGreaterThan(price)
                             .stream()
                             .map(ItemName::getItemName)
                             .collect(Collectors.toList());
    }


}
