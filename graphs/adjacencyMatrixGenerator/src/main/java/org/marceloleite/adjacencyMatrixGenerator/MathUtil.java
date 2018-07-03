package org.marceloleite.adjacencyMatrixGenerator;

public class MathUtil {

	public static int summation(int value) {
		int result = 0;
		for(int i = 1; i<=value; i++) {
			result += i;
		}
		return result;
	}
}
