package com.github.marceloleite2604.tutorials.spring.data.jpa.dao.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.data.jpa.model.Item;

@Component
@RepositoryRestResource(path = "itens")
public interface ItemRepository extends CrudRepository<Item, UUID> {
}
