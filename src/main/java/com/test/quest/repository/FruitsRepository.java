package com.test.quest.repository;

import com.test.quest.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitsRepository extends JpaRepository<Fruit, Integer> {
    public List<Fruit> findByQuantityLessThanEqual(int qty);
}
