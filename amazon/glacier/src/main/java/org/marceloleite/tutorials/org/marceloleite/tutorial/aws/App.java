package org.marceloleite.tutorials.org.marceloleite.tutorial.aws;

import java.io.File;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;

public class App {
	public static void main(String[] args) {
		EmissorArquivoAmazonGlacier emissorArquivoAmazonGlacier = new EmissorArquivoAmazonGlacier(
				Regions.US_EAST_1, new ProfileCredentialsProvider("default"));
		String nomeVault = "richueloVault";
		File arquivo = new File("teste.txt");
		String descricaoArquivo = "Arquivo de teste para envio de arquivos ao Amazon Glacier.";
		emissorArquivoAmazonGlacier.upload(nomeVault, arquivo, descricaoArquivo);
	}
}
