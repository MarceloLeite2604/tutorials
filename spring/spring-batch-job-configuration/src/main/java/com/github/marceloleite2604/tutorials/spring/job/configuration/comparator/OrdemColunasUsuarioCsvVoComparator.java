package com.github.marceloleite2604.tutorials.spring.job.configuration.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Optional;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class OrdemColunasUsuarioCsvVoComparator implements Comparator<String> {

	@Override
	public int compare(String nomeColuna1, String nomeColuna2) {
		return Integer.compare(obterOrdemCampo(nomeColuna1), obterOrdemCampo(nomeColuna2));
	}

	private int obterOrdemCampo(String nomeColuna) {

		for (Field campo : FieldUtils.getFieldsListWithAnnotation(UsuarioCsvVO.class,
				CsvBindByName.class)) {
			CsvBindByName csvBindByName = campo.getAnnotation(CsvBindByName.class);
			CsvBindByPosition csvBindByPosition = campo.getAnnotation(CsvBindByPosition.class);
			if (csvBindByName.column()
					.equals(nomeColuna)) {
				return Optional.ofNullable(csvBindByPosition)
						.map(CsvBindByPosition::position)
						.orElse(Integer.MAX_VALUE);
			}
		}

		return Integer.MAX_VALUE;
	}

}
