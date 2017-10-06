package com.journaldev.design.behavior.chainofresponsibility;

import java.util.Scanner;

/**
 * Chain of responsibility pattern is used to achieve loose coupling in software
 * design where a request from client is passed to a chain of objects to process
 * them. Then the object in the chain will decide themselves who will be
 * processing the request and whether the request is required to be sent to the
 * next object in the chain or not.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java">JournalDev
 *      Chain Of Responsibility Design Pattern Example</a>
 *
 */
public class ChainOfResponsibilityPatternTest {

	private DispenseChain firstElementOnChain;

	public ChainOfResponsibilityPatternTest() {

		// Initialize the chain
		this.firstElementOnChain = new Dollar50Dispenser();
		DispenseChain dispenseChain20 = new Dollar20Dispenser();
		DispenseChain dispenseChain10 = new Dollar10Dispenser();

		// Set the chain of responsibility
		firstElementOnChain.setNextChain(dispenseChain20);
		dispenseChain20.setNextChain(dispenseChain10);
	}

	public static void main(String[] args) {
		ChainOfResponsibilityPatternTest chainOfResponsibilityPatternTest = new ChainOfResponsibilityPatternTest();
		Scanner input;
		int amount = 0;
		System.out.println("Enter amount to dispense: ");
		input = new Scanner(System.in);
		amount = input.nextInt();
		if (amount % 10 != 0) {
			System.out.println("Amount should be in multiple of 10s.");
		} else {
			// Process the request
			chainOfResponsibilityPatternTest.firstElementOnChain.dispense(new Currency(amount));
		}

		input.close();
	}
}