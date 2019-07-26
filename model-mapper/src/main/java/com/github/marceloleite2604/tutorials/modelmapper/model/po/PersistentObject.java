package com.github.marceloleite2604.tutorials.modelmapper.model.po;

import java.io.Serializable;

public interface PersistentObject<T> extends Serializable {

	T getId();
}
