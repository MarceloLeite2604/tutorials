/* "Greater than" operator. */
db.alunos.find({
    "notas" : {
        $gt: 5
    }
})

db.alunos.insert(
    {
        nome: "André",
        data_nascimento: new Date(1991, 01, 25),
        curso: {
            nome: "Matemática"
        },
        notas: [ 7, 5, 9, 4.5 ]
    }
)

db.alunos.insert(
    {
        nome: "Lúcia",
        data_nascimento: new Date(1984, 07, 17),
        curso: {
            nome: "Matemática"
        },
        notas: [ 8, 9.5, 10 ]
    }
)

/* Returns only one result */
db.alunos.findOne({
    "notas" : {
        $gt: 5
    }
})


/* Sort results by name ascending. */
db.alunos.find().sort({
    "nome": 1
});

/* Sort results by name descending. */
db.alunos.find().sort({
    "nome": -1
});

/* Sort results by name ascending and limit results on first three elements. */
db.alunos.find().sort({
    "nome": 1
}).limit(3);