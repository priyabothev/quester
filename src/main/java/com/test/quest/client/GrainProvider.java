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
 *  This is client interface for getting food list from Grain provider
 */
public class GrainProvider {
	private static final Logger log = LogManager.getLogger(FruitProvider.class);
    RestTemplate restTemplate = new RestTemplateInitializer().getRestTemplate();
    final String baseUrl = "https://e97131ec-e35b-48d3-9604-adb51e0a93b3.mock.pstmn.io/grains";

    /**
     * This method is used to get list of grains from grain provider
     * @return
     * @throws URISyntaxException
     */
    public List<FoodDTO> getGrainListFromProvider() throws URISyntaxException {
    	List<FoodDTO> grainList = new ArrayList<>();

    	try {
        ResponseEntity<FoodDTO[]> result = restTemplate.getForEntity(new URI(baseUrl), FoodDTO[].class);
        
        
        
        if (result.getStatusCode().equals(HttpStatus.OK)) {
            grainList = Arrays.asList(result.getBody());
        }
    	}
    	catch(Exception e) {
    		log.error("Error occured while calling Grain Provider API : {}", e);
    		throw e;
    	}
        return grainList;
    }
}
