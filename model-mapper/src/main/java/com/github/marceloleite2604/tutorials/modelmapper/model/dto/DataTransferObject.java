package com.github.marceloleite2604.tutorials.modelmapper.model.dto;

import java.io.Serializable;

public interface DataTransferObject<I> extends Serializable {

	public I getId();
}
