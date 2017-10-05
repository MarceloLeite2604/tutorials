package com.journaldev.design.behavior.chainofresponsibility;

import java.util.Scanner;

public class ChainOfResponsibilityPatternTest {

	private DispenseChain dispenseChain;

	public ChainOfResponsibilityPatternTest() {

		// Initialize the chain
		this.dispenseChain = new Dollar50Dispenser();
		DispenseChain dispenseChain2 = new Dollar20Dispenser();
		DispenseChain dispenseChain3 = new Dollar10Dispenser();

		// Set the chain of responsibility
		dispenseChain.setNextChain(dispenseChain2);
		dispenseChain2.setNextChain(dispenseChain3);
	}

	public static void main(String[] args) {
		ChainOfResponsibilityPatternTest atmDispenseChain = new ChainOfResponsibilityPatternTest();
		Scanner input;
		int amount = 0;
		System.out.println("Enter amount to dispense: ");
		input = new Scanner(System.in);
		amount = input.nextInt();
		if (amount % 10 != 0) {
			System.out.println("Amount should be in multiple of 10s.");
		} else {
			// process the request
			atmDispenseChain.dispenseChain.dispense(new Currency(amount));
		}

		input.close();
	}
}