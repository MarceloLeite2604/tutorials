package com.github.marceloleite2604;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

	private static List<String> palavras = new ArrayList<String>();

	static {
		palavras.add("alura online");
		palavras.add("editora casa do codigo");
		palavras.add("caelum");
	}

	public static void main(String[] args) {

		/* Collections.sort(palavras); */
		Comparator<String> comparador = new ComparadorPorTamanho();
		palavras.sort(comparador);

		System.out.println(palavras);

		for (String palavra : palavras) {
			System.out.println(palavra);
		}

		/* Consumer<String> consumidor = new ImprimeNaLinha(); */
		Consumer<String> consumidor = new Consumer<String>() {
			@Override
			public void accept(String string) {
				System.out.println(string);

			}
		};

		palavras.sort(new Comparator<String>() {

			@Override
			public int compare(String string1, String string2) {
				if (string1.length() < string2.length()) {
					return -1;
				} else {
					if (string1.length() > string2.length()) {
						return 1;
					}
				}
				return 0;
			}
		});

		/*
		 * Interfaces que possuem somente um método e denominado "Interface functional".
		 */
		palavras.sort(new Comparator<String>() {

			@Override
			public int compare(String string1, String string2) {
				if (string1.length() < string2.length()) {
					return -1;
				} else {
					if (string1.length() > string2.length()) {
						return 1;
					}
				}
				return 0;
			}
		});

		palavras.sort((String string1, String string2) -> {
			if (string1.length() < string2.length()) {
				return -1;
			} else {
				if (string1.length() > string2.length()) {
					return 1;
				}
			}
			return 0;
		});

		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s1.length()));

		/* palavras.forEach(consumidor); */

		palavras.forEach(new Consumer<String>() {
			@Override
			public void accept(String string) {
				System.out.println(string);
			}
		});

		palavras.forEach((String string) -> {
			System.out.println(string);
		});

		palavras.forEach((string) -> System.out.println(string));

		/*
		 * Funções lambdas também podem ser utilizadas para instanciar objetos de classe
		 * anônima que implementam interfaces funtionais.
		 */
		Consumer<String> impressor = s -> System.out.println(s);
		palavras.forEach(impressor);

		new Thread(() -> System.out.println("Executando um runnable."));
	}

	private void methodReferences() {
		/*
		 * Interface "Function", onde é definido um parâmetro de entrada e um parâmetro
		 * de saída (os templates) onde nela é descrito a função a ser executada.
		 */
		Function<String, Integer> funcao = new Function<String, Integer>() {

			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};

		/* Inicializando-a com uma função lambda. */
		funcao = s -> s.length();

		/* Criando um comparator com a função escrita. */
		Comparator<String> comparador = Comparator.comparing(funcao);

		/* Ou criando um comparator através da função lambda. */
		comparador = Comparator.comparing(s -> s.length());

		/*
		 * Ainda, é possível instanciar uma função através da sua definição (desde que o
		 * método possua a mesma assinatura do objeto Function definido).
		 */
		funcao = String::length;
		/* Equivalente a: */
		funcao = s -> s.length();

		/* E então atribuí-la na interface funcional. */
		comparador = Comparator.comparing(funcao);

		/*
		 * Para reduzir mais ainda o código, é possível passar a própria definição do
		 * método na interface.
		 */
		comparador = Comparator.comparing(String::length);

		palavras.sort(comparador);
		
		palavras.sort(Comparator.comparing(String::length));

		/*
		 * Não necessariamente precisa ser utilizado um método da própria classe
		 * utilizada. Pode ser utilizado qualquer método que receba o objeto manipulado.
		 */
		palavras.forEach(System.out::println);
	}

}
