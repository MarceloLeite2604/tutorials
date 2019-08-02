package com.github.marceloleite2604.tutorials.modelmapper.dao.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

public interface LibrariesRepository extends CrudRepository<LibraryPO, UUID> {

}
