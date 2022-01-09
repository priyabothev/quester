package com.test.quest.repository;

import com.test.quest.model.Fruit;
import com.test.quest.model.Grain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrainsRepository extends CrudRepository<Grain, Integer> {
    public List<Grain> findByQuantityLessThanEqual(int qty);
}
