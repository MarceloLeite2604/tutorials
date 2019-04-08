package com.github.marceloleite2604.tutorials.spring.data.jpa.util;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.encryptor.EncryptorDecryptor;
import com.github.marceloleite2604.properties.PropertyUtil;
import com.github.marceloleite2604.tutorials.spring.data.jpa.configuration.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.data.jpa.propriedade.CriptografiaProperty;

@Component
public class CriptografiaUtil {

	@Inject
	@Named(NomesBeans.PROPERTIES_CRIPTOGRAFIA)
	private Properties propertiesCriptografia;

	@Inject
	private EncryptorDecryptor encryptorDecryptor;

	public String criptografar(String texto) {

		return encryptorDecryptor.encrypt(texto, obterChave());
	}

	public String decriptografar(String texto) {
		return encryptorDecryptor.decrypt(texto, obterChave());
	}

	private String obterChave() {
		return PropertyUtil.retrieve(CriptografiaProperty.CHAVE, propertiesCriptografia);
	}
}
