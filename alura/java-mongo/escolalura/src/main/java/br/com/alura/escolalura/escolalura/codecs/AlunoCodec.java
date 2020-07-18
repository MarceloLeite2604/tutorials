package br.com.alura.escolalura.escolalura.codecs;

import br.com.alura.escolalura.escolalura.models.*;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlunoCodec implements CollectibleCodec<Aluno> {

    private Codec<Document> codec;

    public AlunoCodec(Codec<Document> codec) {
        this.codec = codec;
    }

    @Override
    public Aluno generateIdIfAbsentFromDocument(Aluno aluno) {
        return documentHasId(aluno) ? aluno.criarId() : aluno;
    }

    @Override
    public boolean documentHasId(Aluno aluno) {
        return aluno.getId() == null;
    }

    @Override
    public BsonValue getDocumentId(Aluno aluno) {
        if (documentHasId(aluno)) {
            throw new IllegalStateException("Esse document não tem Id.");
        }

        return new BsonString(aluno.getId()
                .toHexString());
    }

    @Override
    public Aluno decode(BsonReader bsonReader, DecoderContext decoderContext) {
        Document document = codec.decode(bsonReader, decoderContext);

        Aluno aluno = new Aluno();

        aluno.setId(document.getObjectId("_id"));
        aluno.setNome(document.getString("nome"));
        aluno.setDataNascimento(document.getDate("dataNascimento"));
        Document curso = (Document) document.get("curso");

        if (curso != null) {
            String nomeCurso = curso.getString("nome");
            aluno.setCurso(new Curso(nomeCurso));
        }

        List<Document> habilidadesDocument = (List<Document>) document.get("habilidades");

        if (habilidadesDocument != null) {
            List<Habilidade> habilidadesDoAluno = new ArrayList<>();
            for (Document habilidadeDocument : habilidadesDocument) {
                Habilidade habilidade = new Habilidade(habilidadeDocument.getString("nome"), habilidadeDocument.getString("nivel"));
                habilidadesDoAluno.add(habilidade);
            }
            aluno.setHabilidades(habilidadesDoAluno);
        }

        List<Double> notas = (List<Double>)document.get("notas");

        if (notas != null) {
            List<Nota> notasDoAluno = new ArrayList<>();
            for (Double nota : notas) {
                notasDoAluno.add(new Nota(nota));
            }

            aluno.setNotas(notasDoAluno);
        }

        Document contatoDocument = (Document) document.get("contato");

        if (contatoDocument != null) {
            String endereco = contatoDocument.getString("endereco");
            List<Double> coordinates = contatoDocument.getList("coordinates", Double.class);
            Contato contato = new Contato(endereco, coordinates);
            aluno.setContato(contato);
        }

        return aluno;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Aluno aluno, EncoderContext encoderContext) {
        ObjectId id = aluno.getId();
        String nome = aluno.getNome();
        Date dataNascimento = aluno.getDataNascimento();
        Curso curso = aluno.getCurso();
        List<Habilidade> habilidades = aluno.getHabilidades();
        List<Nota> notas = aluno.getNotas();

        Document document = new Document();

        document.put("_id", id);
        document.put("nome", nome);
        document.put("dataNascimento", dataNascimento);
        document.put("curso", new Document("nome", curso.getNome()));

        if (habilidades != null) {
            List<Document> habilidadesDocument = new ArrayList<>();

            for (Habilidade habilidade : habilidades) {
                Document habilidadeDocument = new Document("nome", habilidade.getNome()).append("nivel", habilidade.getNivel());
                habilidadesDocument.add(habilidadeDocument);
            }

            document.put("habilidades", habilidadesDocument);
        }

        if (notas != null) {
            List<Double> notasParaSalvar = new ArrayList<>();

            for (Nota nota : notas) {
                notasParaSalvar.add(nota.getValor());
            }

            document.put("notas", notasParaSalvar);
        }

        Contato contato = aluno.getContato();

        if (contato != null) {
            List<Double> coordinates = new ArrayList<>(contato.getCoordinates());

            Document contatoDocument = new Document()
                    .append("endereco", contato.getEndereco())
                    .append("coordinates", coordinates)
                    .append("type", contato.getType());

            document.put("contato", contatoDocument);
        }

        codec.encode(bsonWriter, document, encoderContext);
    }

    @Override
    public Class<Aluno> getEncoderClass() {
        return Aluno.class;
    }
}
