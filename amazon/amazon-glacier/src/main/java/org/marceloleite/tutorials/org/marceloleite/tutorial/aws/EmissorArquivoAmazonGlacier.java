package org.marceloleite.tutorials.org.marceloleite.tutorial.aws;

import java.io.File;
import java.io.FileNotFoundException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClientBuilder;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManagerBuilder;
import com.amazonaws.util.StringUtils;

public class EmissorArquivoAmazonGlacier {

	private ArchiveTransferManager archiveTransferManager;

	public EmissorArquivoAmazonGlacier(Regions region, AWSCredentialsProvider provedorCredenciais) {
		AmazonGlacier amazonGlacier = AmazonGlacierClientBuilder.standard()
				.withRegion(region)
				.withCredentials(provedorCredenciais)
				.build();

		this.archiveTransferManager = new ArchiveTransferManagerBuilder()
				.withGlacierClient(amazonGlacier)
				.build();
	}

	public void upload(String nomeVault, File arquivo, String descricaoArquivo) {
		
		if (StringUtils.isNullOrEmpty(nomeVault)) {
			throw new RuntimeException("O nome do vault não pode ser nulo ou vazio.");
		}
		
		if (null == arquivo ) {
			throw new RuntimeException("O arquivo não pode sern nulo.");
		}
		
		try {
			archiveTransferManager.upload(nomeVault, descricaoArquivo, arquivo);
		} catch (AmazonClientException | FileNotFoundException excecao) {
			throw new RuntimeException("Erro ao fazer o upload do arquivo \""
					+ arquivo.getAbsolutePath() + "\" para o vault \"" + nomeVault + "\".");
		}
	}
}
