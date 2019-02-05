package org.marceloleite.tutorials.spring.job.configuration.util;

import javax.inject.Inject;

import org.marceloleite.encrypt.EncryptorDecryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Criptografia {

	@Value("${criptografia.chave}")
	private String chave;
	
	@Inject
	private EncryptorDecryptor encryptorDecryptor;
	
	public String criptografar(String texto) {
		return encryptorDecryptor.encrypt(texto, chave);
	}
	
	public String decriptografar(String texto) {
		return encryptorDecryptor.decrypt(texto, chave);
	}
}
