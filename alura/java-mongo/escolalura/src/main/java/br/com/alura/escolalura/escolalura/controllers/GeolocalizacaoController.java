package br.com.alura.escolalura.escolalura.controllers;

import br.com.alura.escolalura.escolalura.models.Aluno;
import br.com.alura.escolalura.escolalura.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GeolocalizacaoController {

    private AlunoRepository repository;

    @Autowired
    public GeolocalizacaoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/geolocalizacao/iniciar-pesquisa")
    public String inicializarPesquisa(Model model) {
        List<Aluno> alunos = repository.obterTodosAlunos();
        model.addAttribute("alunos", alunos);
        return "geolocalizacao/pesquisar";
    }

    @GetMapping("/geolocalizacao/pesquisar")
    public String pesquisar(@RequestParam("alunoId") String alunoId, Model model) {

        Aluno aluno = repository.obterAlunoPor(alunoId);
        List<Aluno> alunosProximos = repository.pesquisaPorGeolocalizacao(aluno);
        model.addAttribute("alunosProximos", alunosProximos);

        List<Aluno> alunos = repository.obterTodosAlunos();
        model.addAttribute("alunos", alunos);

        return "geolocalizacao/pesquisar";
    }

}
