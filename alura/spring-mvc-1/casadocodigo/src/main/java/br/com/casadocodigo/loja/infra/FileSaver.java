package br.com.casadocodigo.loja.infra;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/*
 * Necessário informar para o spring que os objetos desta classe serão um
 * componente do Spring. É similar a annotation "Bean", porém o a annotation
 * "Component" refere-se a classe, enquanto a "Bean" refere-se ao objeto
 * declarado ou retornado por um método.
 */
@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {

		try {
			String realPath = request.getServletContext().getRealPath(File.separatorChar + baseFolder);
			String path = realPath + File.separatorChar + file.getOriginalFilename();
			file.transferTo(new File(path));

			return baseFolder + File.separatorChar + file.getOriginalFilename();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}
