package com.github.marceloleite2604;

import java.util.function.Consumer;

public class ImprimeNaLinha implements Consumer<String>{

	@Override
	public void accept(String string) {
		System.out.println(string);
	}

}
