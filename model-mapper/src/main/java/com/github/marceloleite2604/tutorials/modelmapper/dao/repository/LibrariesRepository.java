package com.github.marceloleite2604.tutorials.modelmapper.dao.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

public interface LibrariesRepository extends CrudRepository<LibraryPO, UUID> {

	List<LibraryPO> findAllByUserId(UUID userId);

}
