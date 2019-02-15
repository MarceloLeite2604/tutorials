package org.marceloleite.tutorials.spring.job.configuration.util;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.encrypt.EncryptorDecryptor;
import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.CriptografiaPropriedade;
import org.springframework.stereotype.Component;

@Component
public class CriptografiaUtil {

	@Inject
	@Named(NomesBeans.ENCRYPTOR_DECRYPTOR_PROPERTIES)
	private Properties encryptorDecryptorProperties;

	@Inject
	private EncryptorDecryptor encryptorDecryptor;

	public String criptografar(String texto) {
		return encryptorDecryptor.encrypt(texto, obterChave());
	}

	public String decriptografar(String texto) {
		return encryptorDecryptor.decrypt(texto, obterChave());
	}

	private String obterChave() {
		return PropriedadeUtil.obter(CriptografiaPropriedade.CHAVE, encryptorDecryptorProperties);
	}
}
