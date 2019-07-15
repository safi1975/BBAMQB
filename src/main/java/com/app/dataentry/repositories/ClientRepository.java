package com.app.dataentry.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.app.dataentry.model.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
	
	List<Client> findAllByProduct(String product);
}
