package com.github.marceloleite2604.tutorials.spring.job.configuration.util;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.encrypt.EncryptorDecryptor;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.CriptografiaPropriedade;

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
