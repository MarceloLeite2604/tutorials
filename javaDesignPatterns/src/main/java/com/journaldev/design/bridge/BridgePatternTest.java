package com.journaldev.design.bridge;

/**
 * <p>
 * The Bridge design pattern decouples the abstraction from its implementation
 * on both sides of a code, so that the two can vary independently.
 * </p>
 * <p>
 * As an example, the {@link Shape#applyColor()} method works as a two-step
 * method: The first step is implemented by the first end of the bridge (a class
 * which extends the {@link Shape} class, such as {@link Triangle}), and the
 * second step is implemented by the second end of the bridge (a class which
 * implements the {@link Color} interface, such as {@link Red}).
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1491/bridge-design-pattern-java">JournalDev
 *      Java Bridge Design Pattern</a>
 *
 */
public class BridgePatternTest {

	public static void main(String[] args) {
		Color red = new RedColor();
		Shape triangle = new Triangle(red);
		// Here, the first end "triangle" will apply the second end "red" of the bridge
		// interface.
		triangle.applyColor();

		Color green = new GreenColor();
		// Likewise, the first end "pentagon" will apply the second end "green" of the
		// bridge interface.
		Shape pentagon = new Pentagon(green);
		pentagon.applyColor();
	}

}