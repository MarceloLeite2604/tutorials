package com.journaldev.design.bridge;

/**
 * This class is the second end of the bridge on the Bridge design pattern. It
 * is an abstraction of the objects which must be connected with the first end
 * of the bridge ({@link Shape}).
 * 
 */
public interface Color {

	public void applyColor();
}