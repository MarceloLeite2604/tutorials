package org.marceloleite.tutorials.spring.mongo.embedded.dao.repository;

import java.util.List;

import org.marceloleite.tutorials.spring.mongo.embedded.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}