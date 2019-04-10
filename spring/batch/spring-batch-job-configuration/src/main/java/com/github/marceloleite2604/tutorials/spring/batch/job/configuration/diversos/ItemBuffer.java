package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos;

import java.io.IOException;
import java.util.Iterator;

public abstract class ItemBuffer<T> {
	
	private Iterator<T> iterator;
	
	public T read() throws IOException {
		
		if (iterator == null || !iterator.hasNext()) {
			iterator = get();
		}
		
		if (!iterator.hasNext()) {
			throw new IOException("Retrieved iterator is empty after \"get\" method.");
		}
		
		return iterator.next();
	}

	protected abstract Iterator<T> get();

}
