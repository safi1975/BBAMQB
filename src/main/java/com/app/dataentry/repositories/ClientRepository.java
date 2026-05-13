package com.app.dataentry.repositories;

import com.app.dataentry.model.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository
    extends CrudRepository<Client, Long>,
            PagingAndSortingRepository<Client, Long> {

  List<Client> findAll();

  List<Client> findAllByProduct(String product);

  Long countByCreatedBy(String username);

  Long countByCreatedByAndProduct(String username, String product);

  List<Client> findAllByCreatedBy(String username);
}
