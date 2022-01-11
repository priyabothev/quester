package com.test.quest.client;

import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Fruit;
import com.test.quest.service.FruitsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  This is client interface for getting food list from fruit provider
 */
public class FruitProvider {
    private static final Logger log = LogManager.getLogger(FruitProvider.class);

    RestTemplate restTemplate = new RestTemplateInitializer().getRestTemplate();
    final String baseUrl = "https://942384f8-3bde-4c28-afc1-d0d3b7045786.mock.pstmn.io/fruits";

    /**
     * This method is used to get fruit list from provider
     * @return - this statement will return fruit list
     * @throws URISyntaxException
     */
    public List<FoodDTO> getFruitListFromProvider() throws URISyntaxException, Exception {
    	 List<FoodDTO> fruitList = new ArrayList<>();
    	try {
	        ResponseEntity<FoodDTO[]> result = restTemplate.getForEntity(new URI(baseUrl), FoodDTO[].class);
	       
	        if (result.getStatusCode().equals(HttpStatus.OK)) {
	            log.info("Fruit provider api response: {}",result.getBody());
	            fruitList = Arrays.asList(result.getBody());
	        }
    	}catch (Exception e) {
			// TODO: handle exception
    		log.error("Error occured while calling Fruit Provider API : {}", e);
    		throw e;
		}
        return fruitList;
    }
}
