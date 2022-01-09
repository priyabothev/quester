package com.test.quest.service;


import com.test.quest.client.VegetableProvider;
import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Vegetable;
import com.test.quest.repository.VegetablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service is used for vegetable related operations
 */
@Service
public class VegetableService {
    @Autowired
    private VegetablesRepository vegetablesRepository;

    /**
     * This method is used to search for vegetables for given quantity
     * @param quantity used to fetch list of vegetables
     * @return list of vegetables for given quantity
     */
    public List<FoodDTO> searchForVegetables(int quantity) {
        List<Vegetable> vegetableList = vegetablesRepository.findByQuantityLessThanEqual(quantity);
        List<FoodDTO> foodList = new ArrayList<>();
        if (!vegetableList.isEmpty()) {
            foodList = vegetableList.stream().map(f -> new FoodDTO(f.getName(), f.getQuantity())).collect(Collectors.toList());
        } else {
            VegetableProvider vegetableProvider = new VegetableProvider();
            try {
                foodList = vegetableProvider.getVegetableListFromProvider();
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
            }
        }
        return foodList;
    }
}
