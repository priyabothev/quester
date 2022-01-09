package com.test.quest.service;

import com.test.quest.client.GrainProvider;
import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Grain;
import com.test.quest.repository.GrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service is used for grain related operations
 */
@Service
public class GrainService {
    @Autowired
    private GrainsRepository grainsRepository;

    /**
     * This method is used to search for grains for given quantity
     * @param quantity used to fetch list of grains
     * @return list of grains for given quantity
     */
    public List<FoodDTO> searchForGrains(int quantity) {
        List<Grain> grainList = grainsRepository.findByQuantityLessThanEqual(quantity);
        List<FoodDTO> foodList = new ArrayList<>();
        if (!grainList.isEmpty()) {
            foodList = grainList.stream().map(f -> new FoodDTO(f.getName(), f.getQuantity())).collect(Collectors.toList());
        } else {
            GrainProvider grainProvider = new GrainProvider();
            try {
                foodList = grainProvider.getGrainListFromProvider();
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
            }
        }

        return foodList;
    }
}
