package com.test.quest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.quest.client.FoodProvider;
import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Fruit;
import com.test.quest.repository.FruitsRepository;

/**
 * This service is used for fruit related operations
 */
@Service
public class FruitsService {
	private static final Logger log = LogManager.getLogger(FruitsService.class);
	@Autowired
	private FruitsRepository fruitsRepository;

	/**
	 * This method is used to search for fruits for given quantity
	 *
	 * @param quantity
	 *            used to fetch list of fruits
	 * @return list of fruits for given quantity
	 */
	public List<FoodDTO> searchForFruits(int quantity) throws Exception {
		log.info("Fetching fruit list for quantity: {}", quantity);
		List<Fruit> listOfFruits = fruitsRepository.findByQuantityLessThanEqual(quantity);
		List<FoodDTO> foodList = new ArrayList<>();
		if (!listOfFruits.isEmpty()) {
			log.info("Found fruits in database");
			foodList = listOfFruits.stream().map(f -> new FoodDTO(f.getName(), f.getQuantity()))
					.collect(Collectors.toList());
		} else {
			log.info("We did not find fruit items in database, calling fruit provider");
			FoodProvider fruitProvider = new FoodProvider();
			try {
				foodList = fruitProvider.getFoodListFromProvider("fruit");
			} catch (Exception e) {
				log.error("Error while calling Fruit client : {}", e);
			}
		}

		return foodList;
	}
}
