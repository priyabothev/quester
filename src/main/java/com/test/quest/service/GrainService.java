package com.test.quest.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.quest.client.FoodProvider;
import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Grain;
import com.test.quest.repository.GrainsRepository;

/**
 * This service is used for grain related operations
 */
@Service
public class GrainService {
	private static final Logger log = LogManager.getLogger(FruitsService.class);
    @Autowired
    private GrainsRepository grainsRepository;

    /**
     * This method is used to search for grains for given quantity
     * @param quantity used to fetch list of grains
     * @return list of grains for given quantity
     */
    public List<FoodDTO> searchForGrains(int quantity) throws Exception {
    	log.info("Fetching grain list for quantity: {}", quantity);
        List<Grain> grainList = grainsRepository.findByQuantityLessThanEqual(quantity);
        List<FoodDTO> foodList = new ArrayList<>();
        if (!grainList.isEmpty()) {
            foodList = grainList.stream().map(f -> new FoodDTO(f.getName(), f.getQuantity())).collect(Collectors.toList());
        } else {
        	FoodProvider grainProvider=new FoodProvider();
            try {
                foodList = grainProvider.getFoodListFromProvider("grain");
            } catch (URISyntaxException e) {
            	log.error("Error while calling grain client : {}", e);
            }
        }

        return foodList;
    }
}
