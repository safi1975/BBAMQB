package com.app.dataentry.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.dataentry.model.Client;
import com.app.dataentry.model.User;

@Repository
public interface UserRepository extends CrudRepository<Client, Long> {
	User findByName(String name);
}
