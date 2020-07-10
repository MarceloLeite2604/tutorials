db.createCollection("alunos");

db.alunos.insert(
    {
        "nome": "felipe",
        "data_nascimento": new Date(1994,02,26),
        "curso": {
            "nome": "Sistemas de informação"
        },
        "notas": [10.0, 9.0, 4.5],
        "habilidades": [
            {
                "nome": "inglês",
                "nível": "avançado"
            },
            {
                "nome": "taekwondo",
                "nível": "básico"
            }
        ]
    }
)

db.alunos.remove(
    {
        "_id": ObjectId("5f083cfe85815e3f3cd5872a")
    }
)

db.alunos.insert(
    {
        "nome": "Julio",
        "data_nascimento": new Date(1972,08,30),
        "curso": {
            "nome": "Medicina"
        },
        "habilidades": [
            {
                "nome": "inglês",
                "nível": "avançado"
            }
        ]
    }
)

db.alunos.insert(
    {
        nome: "Alberto",
        data_nascimento: new Date(1972,09,30),
        curso: {
            nome: "Engenharia Química"
        },
        habilidades: [
            {
                nome: "inglês",
                nível: "intermediário"
            }
        ]
    }
)

db.alunos.insert(
    {
        nome: "Daniela",
        curso: {
            nome: "Moda"
        },
        habilidades: [
            {
                nome: "alemão",
                nível: "básico"
            }
        ]
    }
)

db.alunos.find().pretty()