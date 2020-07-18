package br.com.alura;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        MongoClient cliente = new MongoClient();

        MongoDatabase bancoDeDados = cliente.getDatabase("test");

        MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

        Document aluno = alunos.find()
                .first();

        System.out.println(aluno);

        // inserirAluno(alunos);

        //atualizarAluno(alunos);

        excluirAluno(alunos);

        cliente.close();
    }

    private static void excluirAluno(MongoCollection<Document> alunos) {
        alunos.deleteOne(Filters.eq("nome", "João Silva"));
    }

    private static void atualizarAluno(MongoCollection<Document> alunos) {
        Bson filtroNomeJoao = Filters.eq("nome", "João");
        Document setNovoNome = new Document("$set", new Document("nome", "João Silva"));
        alunos.updateOne(filtroNomeJoao, setNovoNome);
    }

    private static void inserirAluno(MongoCollection<Document> alunos) {
        Document inglesBasico = new Document()
                .append("nome", "Inglês")
                .append("nível", "Básico");

        Document espanholBasico = new Document()
                .append("nome", "Espanhol")
                .append("nível", "Básico");

        List<Document> habilidades = Arrays.asList(inglesBasico,
                espanholBasico);

        Date dataNascimento = new Date(2003, 10, 10);

        Document nomeCurso = new Document("nome", "História");
        List<Integer> notas = Arrays.asList(10, 9, 8);

        Document novoAluno = new Document("nome", "João")
            .append("data_nascimento", dataNascimento)
            .append("curso", nomeCurso)
            .append("notas", notas)
            .append("habilidades", habilidades);

        alunos.insertOne(novoAluno);
    }
}
