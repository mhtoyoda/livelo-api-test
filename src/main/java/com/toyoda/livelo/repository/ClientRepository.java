package com.toyoda.livelo.repository;

import com.toyoda.livelo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    Optional<Client> findByName(String name);
}
