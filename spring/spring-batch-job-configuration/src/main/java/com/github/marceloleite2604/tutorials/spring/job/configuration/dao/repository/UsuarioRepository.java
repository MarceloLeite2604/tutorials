package com.github.marceloleite2604.tutorials.spring.job.configuration.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.po.UsuarioPO;

public interface UsuarioRepository extends CrudRepository<UsuarioPO, String>{

}
