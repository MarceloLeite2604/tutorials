package com.github.marceloleite2604.tutorials.modelmapper.dao.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

public interface UserRepository extends CrudRepository<UserPO, UUID> {

}
