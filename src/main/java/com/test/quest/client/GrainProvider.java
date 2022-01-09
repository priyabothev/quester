package com.test.quest.client;

import com.test.quest.dto.FoodDTO;
import com.test.quest.model.Fruit;
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
    RestTemplate restTemplate = new RestTemplateInitializer().getRestTemplate();
    final String baseUrl = "https://f8776af4-e760-4c93-97b8-70015f0e00b3.mock.pstmn.io/grains";

    /**
     * This method is used to get list of grains from grain provider
     * @return
     * @throws URISyntaxException
     */
    public List<FoodDTO> getGrainListFromProvider() throws URISyntaxException {
        ResponseEntity<FoodDTO[]> result = restTemplate.getForEntity(new URI(baseUrl), FoodDTO[].class);
        List<FoodDTO> grainList = new ArrayList<>();
        if (result.getStatusCode().equals(HttpStatus.OK)) {
            grainList = Arrays.asList(result.getBody());
        }
        return grainList;
    }
}
