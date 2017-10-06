package com.journaldev.design.behavior.chainofresponsibility;

/**
 * A class which implements the {@link DispenseChain} interface. It must
 * implement the methods requested on it to define its responsibility with the
 * object analyzed and to chain the next element on the chain to be declared
 * responsible for the object.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java">JournalDev
 *      Chain Of Responsibility Design Pattern Example</a>
 *
 */
public class Dollar10Dispenser implements DispenseChain {

	private DispenseChain chain;

	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void dispense(Currency currency) {
		if (currency.getAmount() >= 10) {
			int fraction = currency.getAmount() / 10;
			int remainder = currency.getAmount() % 10;
			System.out.println("Dispensing " + fraction + " 10$ note");
			if (remainder != 0)
				this.chain.dispense(new Currency(remainder));
		} else {
			this.chain.dispense(currency);
		}
	}

}