package com.test.quest.client;

import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Fruit;

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
 *  This is client interface for getting food list from Vegetable provider
 */
public class VegetableProvider {
	private static final Logger log = LogManager.getLogger(FruitProvider.class);
    RestTemplate restTemplate = new RestTemplateInitializer().getRestTemplate();
    final String baseUrl = "https://6f09336b-9208-4385-ab15-61362072f2d1.mock.pstmn.io/vegetables";

    /**
     * This method is used to get list of vegetables from vegetable provider
     * @return
     * @throws URISyntaxException
     */
    public List<FoodDTO> getVegetableListFromProvider() throws URISyntaxException {
    	List<FoodDTO> vegetableList = new ArrayList<>();
    	try {    	
        ResponseEntity<FoodDTO[]> result = restTemplate.getForEntity(new URI(baseUrl), FoodDTO[].class);
        
        if (result.getStatusCode().equals(HttpStatus.OK)) {
        	log.info("Vegetable provider api response: {}",result.getBody());
            vegetableList = Arrays.asList(result.getBody());
        }
    	}
    	catch(Exception e) {
    		log.error("Error occured while calling Vegetable Provider API : {}", e);
    		throw e;
    	}
        return vegetableList;
    }
}
