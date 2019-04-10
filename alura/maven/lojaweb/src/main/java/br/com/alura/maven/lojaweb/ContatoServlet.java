package br.com.alura.maven.lojaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.stella.tinytype.CPF;

@WebServlet(urlPatterns={"/contato"})
public class ContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Produto produto = new Produto("bala", 0.15); 
		new CPF("01120711045").isValido();
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html><h2>Entre em contato conosco.</h2></html>");
		writer.close();
	}
	
}
