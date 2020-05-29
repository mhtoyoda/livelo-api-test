package com.toyoda.livelo.repository;

import com.toyoda.livelo.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, String> {

    Optional<City> findByNameAndState(String name, String state);

    Optional<List<City>> findByName(String name);

    Optional<List<City>> findByState(String state);

}
