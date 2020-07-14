db.alunos.find({
    nome: "felipe"
}).pretty()

db.alunos.insert(
    {
        "nome": "felipe",
        "curso": {
            "nome": "Matemática"
        }
    }
)

db.alunos.remove({
    "_id": ObjectId("5f08438e4ea7c72a3f46d18c")
});

db.alunos.find({
    "habilidades.nome": "inglês"
}).pretty()

db.alunos.find({
    "nome": "felipe",
    "habilidades.nome": "inglês"
}).pretty()

db.alunos.find({
    "curso.nome": "Sistemas de Informação"
}).pretty()

db.alunos.find({
    "curso.nome": "Engenharia Química"
}).pretty()

db.alunos.find({
    $or: [
        {"curso.nome": "Sistemas de informação"},
        {"curso.nome": "Engenharia Química"}
    ]
}).pretty()

db.alunos.find({
    $or: [
        {"curso.nome": "Sistemas de informação"},
        {"curso.nome": "Engenharia Química"},
        {"curso.nome": "Moda"}
    ],
    nome: "Daniela"
}).pretty()

db.alunos.find({
    "curso.nome": { 
        $in: [
            "Sistemas de informação",
            "Engenharia Química"
        ] 
    }
}).pretty()