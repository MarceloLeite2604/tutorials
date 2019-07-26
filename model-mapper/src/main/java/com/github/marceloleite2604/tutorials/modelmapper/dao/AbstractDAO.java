package com.github.marceloleite2604.tutorials.modelmapper.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.modelmapper.exception.ModelMapperRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.PersistentObject;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.ErrorMessage;

public abstract class AbstractDAO<T extends PersistentObject<I>, I> {

	protected abstract CrudRepository<T, I> getRepository();

	public Optional<T> findById(I id) {
		return getRepository().findById(id);
	}

	public T findMandatoryById(I id) {
		return getRepository().findById(id)
				.orElseThrow(() -> new ModelMapperRuntimeException(
						ErrorMessage.PERSISTENT_OBJECT_NOT_FOUND, getPersistentObjectClass(), id));
	}

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public void delete(T entity) {
		getRepository().delete(entity);
	}

	public void deleteById(I id) {
		getRepository().deleteById(id);
	}

	protected abstract Class<T> getPersistentObjectClass();
}
