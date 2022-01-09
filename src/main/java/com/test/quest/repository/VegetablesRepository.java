package com.test.quest.repository;

import com.test.quest.model.Fruit;
import com.test.quest.model.Vegetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetablesRepository extends CrudRepository<Vegetable, Integer> {
    public List<Vegetable> findByQuantityLessThanEqual(int qty);
}
