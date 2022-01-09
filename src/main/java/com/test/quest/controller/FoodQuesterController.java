package com.test.quest.controller;

import com.test.quest.dto.FoodDTO;
import com.test.quest.service.FruitsService;
import com.test.quest.service.GrainService;
import com.test.quest.service.VegetableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  This is Food quester controller for searching food
 */

@RestController
@RequestMapping(path = "/")
public class FoodQuesterController {
    private static final Logger log = LogManager.getLogger(FoodQuesterController.class);
    @Autowired
    private FruitsService fruitsService;
    @Autowired
    private VegetableService vegetableService;
    @Autowired
    private GrainService grainService;

    /**
     * This method is used to search food which is having quantity less than or equal to given qty
     * @param qty Given qty to fetch list of food
     * @return List of food having quantity less than or equal to given qty
     */
    @GetMapping(value = "quest/{qty}")
    public ResponseEntity<List<FoodDTO>> questForFood(@PathVariable int qty) {
        List<FoodDTO> finalList = new ArrayList<>();
        finalList.addAll(fruitsService.searchForFruits(qty));
        finalList.addAll(vegetableService.searchForVegetables(qty));
        finalList.addAll(grainService.searchForGrains(qty));
        finalList = finalList.stream().filter(f -> f.getQty() <= qty).sorted(Comparator.comparing(FoodDTO::getName)).collect(Collectors.toList());
        if(finalList.isEmpty()){
            log.error("there are no items found for given quantity");
            return new ResponseEntity("No items found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(finalList, HttpStatus.OK);
    }
}
