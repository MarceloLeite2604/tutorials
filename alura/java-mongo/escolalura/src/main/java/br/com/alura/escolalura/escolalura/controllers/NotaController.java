package br.com.alura.escolalura.escolalura.controllers;

import br.com.alura.escolalura.escolalura.models.Aluno;
import br.com.alura.escolalura.escolalura.models.Nota;
import br.com.alura.escolalura.escolalura.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotaController {

    private AlunoRepository repository;

    @Autowired
    public NotaController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/nota/cadastrar/{id}")
    public String cadastrar(@PathVariable String id, Model model) {

        Aluno aluno = repository.obterAlunoPor(id);

        model.addAttribute("aluno", aluno);
        model.addAttribute("nota", new Nota());

        return "nota/cadastrar";
    }

    @PostMapping("/nota/salvar/{id}")
    public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
        Aluno aluno = repository.obterAlunoPor(id);
        aluno.adicionarNota(nota);
        repository.salvar(aluno);
        return "redirect:/aluno/listar";
    }

    @GetMapping("/nota/iniciar-pesquisa")
    public String iniciarPesquisa() {
        return "nota/pesquisar";
    }

    @GetMapping("/nota/pesquisar")
    public String pesquisarPor(@RequestParam("classificacao") String classificacao, @RequestParam("notacorte") String notaCorte, Model model) {
        List<Aluno> alunos = repository.pesquisarPor(classificacao, Double.parseDouble(notaCorte));

        model.addAttribute("alunos", alunos);

        return "nota/pesquisar";
    }
}
