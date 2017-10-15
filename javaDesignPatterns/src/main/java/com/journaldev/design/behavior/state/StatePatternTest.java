package com.journaldev.design.behavior.state;

/**
 * <p>
 * The State design pattern is used when an object change its behavior based on
 * its internal state. To implement it, it is necessary to declare the states
 * ({@link State}) that the object must have and a context ({@link TVContext})
 * to store the object current state.
 * </p>
 * <p>
 * Different from the {@link TVRemoteBasic} example, the client code
 * {@link StatePatternTest#main(String[])} doesn't need to know the possible
 * values and select the command to be executed. Also if the number of states
 * increase, it does not need to be revised. Thus, the {@link StatePatternTest}
 * is loose coupled.
 * </p>
 * <p>
 * This type of pattern is similar with the Strategy, so here are some of its
 * differences.
 * <ul>
 * <li>The Strategy pattern deals with <b>what</b> an object currently is (its
 * state or type). It must encapsulate its state-dependent behavior.</li>
 * <li>The State pattern deals with <b>how</b> an object must accomplish a task.
 * It must encapsulate the problem-solving algorithm.
 * <li>The State pattern allows the object to change its state (i. e. to replace
 * the state reference to one to another), thus allowing it behavior to change.
 * <li>The Strategy pattern does not allow its replacement. Once defined, it is
 * the chosen method for the object.</li>
 * <li>In the Strategy pattern, the strategy is passed to the context as
 * parameter.</li>
 * <li>In the State pattern, the state is created and controlled by the object
 * itself.</li>
 * </ul>
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1751/state-design-pattern-java">JournalDev
 *      State Design Pattern Java Tutorial</a>
 * @see <a href=
 *      "https://stackoverflow.com/questions/1658192/what-is-the-difference-between-strategy-design-pattern-and-state-design-pattern">Stack
 *      overflow: What is the difference between Strategy design pattern and
 *      State design pattern?</a>
 *
 */
public class StatePatternTest {

	public static void main(String[] args) {
		TVContext tvContext = new TVContext();
		State tvStartState = new TVStartState();
		State tvStopState = new TVStopState();

		tvContext.setState(tvStartState);
		tvContext.doAction();

		tvContext.setState(tvStopState);
		tvContext.doAction();
	}
}