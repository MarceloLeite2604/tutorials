package br.com.alura.escolalura.escolalura.repositories;

import br.com.alura.escolalura.escolalura.codecs.AlunoCodec;
import br.com.alura.escolalura.escolalura.models.Aluno;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    public void salvar(Aluno aluno) {

        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> alunos = getAlunos(cliente);

        if (aluno.getId() == null) {
            alunos.insertOne(aluno);
        } else {
            alunos.updateOne(Filters.eq("_id", aluno.getId()), new Document("$set", aluno));
        }

        fecharConexao(cliente);
    }

    public List<Aluno> obterTodosAlunos() {

        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> alunos = getAlunos(cliente);

        MongoCursor<Aluno> resultado = alunos.find()
                .iterator();

        List<Aluno> alunosEncontrados = popularAlunos(resultado);

        fecharConexao(cliente);

        return alunosEncontrados;
    }

    private MongoCollection<Aluno> getAlunos(MongoClient cliente) {
        MongoDatabase bancoDeDados = cliente.getDatabase("test");

        return bancoDeDados.getCollection("alunos", Aluno.class);
    }

    private MongoClient criarCliente() {
        Codec<Document> documentCodec = MongoClient.getDefaultCodecRegistry()
                .get(Document.class);

        AlunoCodec alunoCodec = new AlunoCodec(documentCodec);

        CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(alunoCodec));


        MongoClientOptions opcoes = MongoClientOptions.builder()
                .codecRegistry(registro)
                .build();

        return new MongoClient("localhost:27017", opcoes);
    }

    public Aluno obterAlunoPor(String id) {
        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> alunos = getAlunos(cliente);

        Aluno aluno = alunos.find(Filters.eq("_id", new ObjectId(id)))
                .first();

        fecharConexao(cliente);

        return aluno;
    }

    public List<Aluno> pesquisarPor(String nome) {
        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> collection = getAlunos(cliente);

        MongoCursor<Aluno> alunoMongoCursor = collection.find(Filters.eq("nome", nome), Aluno.class)
                .iterator();

        List<Aluno> alunos = popularAlunos(alunoMongoCursor);

        fecharConexao(cliente);

        return alunos;

    }

    public List<Aluno> pesquisarPor(String classificacao, double notaCorte) {
        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> collection = getAlunos(cliente);

        Bson filter;
        if ("reprovados".equals(classificacao)) {
            filter = Filters.lt("notas", notaCorte);
        } else {
            filter = Filters.gte("notas", notaCorte);
        }

        MongoCursor<Aluno> alunoMongoCursor = collection.find(filter)
                .iterator();

        List<Aluno> alunos = popularAlunos(alunoMongoCursor);

        fecharConexao(cliente);

        return alunos;
    }

    public List<Aluno> pesquisaPorGeolocalizacao(Aluno aluno) {
        MongoClient cliente = criarCliente();

        MongoCollection<Aluno> collection = getAlunos(cliente);

        collection.createIndex(Indexes.geo2dsphere("contato"));

        List<Double> coordinates = aluno.getContato()
                .getCoordinates();
        Point pontoReferencia = new Point(new Position(coordinates.get(0), coordinates.get(1)));

        MongoCursor<Aluno> resultados = collection.find(Filters.nearSphere("contato", pontoReferencia, 2000.0, 0.0))
                .limit(3)
                .skip(1)
                .iterator();

        List<Aluno> alunos = popularAlunos(resultados);

        fecharConexao(cliente);

        return alunos;
    }

    private List<Aluno> popularAlunos(MongoCursor<Aluno> alunoMongoCursor) {
        List<Aluno> alunos = new ArrayList<>();

        while (alunoMongoCursor.hasNext()) {
            alunos.add(alunoMongoCursor.next());
        }
        return alunos;
    }

    private void fecharConexao(MongoClient cliente) {
        cliente.close();
    }
}
