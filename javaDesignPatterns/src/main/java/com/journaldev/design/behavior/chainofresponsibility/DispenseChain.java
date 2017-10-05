package com.journaldev.design.behavior.chainofresponsibility;

public interface DispenseChain {

	void setNextChain(DispenseChain nextChain);

	void dispense(Currency currency);
}