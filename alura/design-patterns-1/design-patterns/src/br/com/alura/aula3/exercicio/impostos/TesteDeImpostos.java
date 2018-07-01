package br.com.alura.aula3.exercicio.impostos;

import java.util.Arrays;
import java.util.List;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula2.Item;
import br.com.alura.aula3.exemplo.ImpostoCondicionalIHIT;

public class TesteDeImpostos {

	public static void main(String[] args) {
		// exercicio03();
		exercicio05();
	}
	
	private static void exercicio03() {
		Orcamento primeiroOrcamento = montarPrimeiroOrcamento();
		Orcamento segundoOrcamento = montarSegundoOrcamento();

		apresentarImpostoIKCV(primeiroOrcamento);
		apresentarImpostoIKCV(segundoOrcamento);
		apresentarImpostoICPP(primeiroOrcamento);
		apresentarImpostoICPP(segundoOrcamento);
	}
	
	private static void exercicio05() {
		apresentarImpostoIHIT(montarPrimeiroOrcamento());
		apresentarImpostoIHIT(montarSegundoOrcamento());
		apresentarImpostoIHIT(montarTerceiroOrcamento());
	}

	

	private static void apresentarImpostoIHIT(Orcamento orcamento) {
		System.out.println("IHIT para o orçamento de " + orcamento.getValor() + ": "
				+ new ImpostoCondicionalIHIT().calcula(orcamento));
	}

	private static void apresentarImpostoICPP(Orcamento orcamento) {
		System.out.println("ICPP para o orçamento de " + orcamento.getValor() + ": "
				+ new ImpostoCondicionalICPP().calcula(orcamento));
	}

	private static void apresentarImpostoIKCV(Orcamento orcamento) {
		System.out.println("IKCV para o orçamento de " + orcamento.getValor() + ": "
				+ new ImpostoCondicionalICKV().calcula(orcamento));
	}
	
	public static Orcamento montarPrimeiroOrcamento() {
		Item camisaSocial = new Item("Camisa social", 50);
		Item calcaJeans = new Item("Calça Jeans", 70);
		Item gravataDeSeda = new Item("Gravata De Seda", 80);
		Item cintoDeCouro = new Item("Cinto De Couro", 40);
		
		List<Item> itens = Arrays.asList(camisaSocial, calcaJeans, gravataDeSeda, cintoDeCouro);
		Orcamento orcamento = new Orcamento(somarValores(itens));
		return adicionarItens(orcamento, itens);
	}

	public static Orcamento montarSegundoOrcamento() {
		Item touca = new Item("Touca", 20);
		Item proteroresDeOrelha = new Item("Protetores de orelha", 10);
		Item casacoImpermeavel = new Item("Casaco Impermeável", 430);
		Item calcaImpermeavel = new Item("Calça Impermeável", 210);

		List<Item> itens = Arrays.asList(touca, proteroresDeOrelha, casacoImpermeavel, calcaImpermeavel);
		Orcamento orcamento = new Orcamento(somarValores(itens));
		return adicionarItens(orcamento, itens);
	}
	
	public static Orcamento montarTerceiroOrcamento() {
		Item mesa = new Item("Mesa", 310);
		Item cadeira = new Item("Cadeira", 80);

		List<Item> itens = Arrays.asList(mesa, cadeira, cadeira, cadeira);
		Orcamento orcamento = new Orcamento(somarValores(itens));
		return adicionarItens(orcamento, itens);
	}

	private static Orcamento adicionarItens(Orcamento orcamento, List<Item> itens) {
		itens.forEach(orcamento::adicionaItem);
		return orcamento;
	}

	private static double somarValores(List<Item> itens) {
		return itens.stream()
				.mapToDouble(Item::getValor)
				.sum();
	}
	
}
