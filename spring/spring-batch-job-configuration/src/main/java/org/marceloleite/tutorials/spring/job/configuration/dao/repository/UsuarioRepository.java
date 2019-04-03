package org.marceloleite.tutorials.spring.job.configuration.dao.repository;

import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioPO, String>{

}
