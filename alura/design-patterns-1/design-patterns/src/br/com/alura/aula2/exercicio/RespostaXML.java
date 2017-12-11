package br.com.alura.aula2.exercicio;

public class RespostaXML implements Resposta {

	private Resposta resposta;

	public RespostaXML(Resposta resposta) {
		super();
		this.resposta = resposta;
	}

	@Override
	public void responde(Requisicao requisicao, Conta conta) {
		if (requisicao.getFormato() == Formato.XML) {
			System.out.println(
					"<conta><nome>" + conta.getNome() + "</nome><saldo>" + conta.getSaldo() + "</saldo></conta>");
		} else {
			resposta.responde(requisicao, conta);
		}

	}
}
