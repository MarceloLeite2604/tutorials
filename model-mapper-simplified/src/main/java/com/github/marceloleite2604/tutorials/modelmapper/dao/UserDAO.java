package com.github.marceloleite2604.tutorials.modelmapper.dao;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.dao.repository.UserRepository;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserDAO extends AbstractDAO<UserPO, UUID> {

	@Inject
	private UserRepository userRepository;

	@Override
	protected CrudRepository<UserPO, UUID> getRepository() {
		return userRepository;
	}

	@Override
	protected Class<UserPO> getPersistentObjectClass() {
		return UserPO.class;
	}
}
