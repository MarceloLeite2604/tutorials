package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.List;
import java.util.Optional;

import com.github.marceloleite2604.tutorials.modelmapper.dao.AbstractDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.DataTransferObject;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.PoToDtoMapper;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.PersistentObject;

public abstract class AbstractBO<I, P extends PersistentObject<I>, D extends DataTransferObject> {

	public P mapAsPo(D dto) {
		return getPoToDtoMapper().mapAsPo(dto);
	}

	public List<P> mapAsPo(List<D> dtos) {
		return getPoToDtoMapper().mapAsPo(dtos);
	}

	public D mapAsDto(P po) {
		return getPoToDtoMapper().mapAsDto(po);
	}

	public List<D> mapAsDto(List<P> pos) {
		return getPoToDtoMapper().mapAsDto(pos);
	}
	
	protected P save(P po) {
		return getDAO().save(po);
	}
	
	protected D save(D dto) {
		P po = mapAsPo(dto);
		po = getDAO().save(po);
		return mapAsDto(po);
	}

	public List<P> findAll() {
		return getDAO().findAll();
	}

	public Optional<P> findById(I id) {
		return getDAO().findById(id);
	}

	public P findMandatoryById(I id) {
		return getDAO().findMandatoryById(id);
	}

	protected void delete(P entity) {
		getDAO().delete(entity);
	}

	protected void deleteById(I id) {
		getDAO().deleteById(id);
	}

	protected abstract PoToDtoMapper<P, D> getPoToDtoMapper();

	protected abstract AbstractDAO<P, I> getDAO();
}
