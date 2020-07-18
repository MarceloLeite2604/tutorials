db.alunos.insert({
    "_id" : ObjectId("58fb5f0f9823dc9c0ffccd95"),
    "nome" : "Felipe",
    "data_nascimento" : ISODate("1994-03-26T03:00:00Z"),
    "curso" : {
      "nome" : "Sistemas de informação"
    },
    "notas" : [
      10,
      9,
      4
    ],
    "habilidades" : [
      {
        "nome" : "inglês",
        "nivel" : "avançado"
      },
      {
        "nome" : "taekwondo",
        "nivel" : "básico"
      }
    ],
    "contato" : {
      "endereco" : "R. Dona Avelina, 10 - Vila Mariana, São Paulo - SP, 04111-010",
      "coordinates" : [
        -23.586917,
        -46.633484
      ],
      "type" : "Point"
    }
  }
);
db.alunos.insert({
    "_id" : ObjectId("58fb5f0f9823dc9c0ffccd96"),
    "nome" : "Celina",
    "data_nascimento" : ISODate("2011-03-09T03:00:00Z"),
    "notas" : [
      10,
      9,
      8
    ],
    "curso" : {
      "nome" : "Química"
    },
    "habilidades" : [
      {
        "nome" : "Inglês",
        "nivel" : "Basico"
      },
      {
        "nome" : "Alemão",
        "nivel" : "Basico"
      }
    ],
    "contato" : {
      "endereco" : "Av. dos Eucaliptos, 300 - Indianópolis, São Paulo - SP, 04517-050",
      "coordinates" : [
        -23.606913,
        -46.673175
      ],
      "type" : "Point"
    }
  }
);
db.alunos.insert({
    "_id" : ObjectId("58fb5f109823dc9c0ffccd97"),
    "nome" : "Lazaro",
    "data_nascimento" : ISODate("1987-01-30T02:00:00Z"),
    "notas" : [
      10,
      9,
      10
    ],
    "curso" : {
      "nome" : "Análise de Sistemas"
    },
    "habilidades" : [
      {
        "nome" : "Inglês",
        "nivel" : "Basico"
      },
      {
        "nome" : "Alemão",
        "nivel" : "Basico"
      }
    ],
    "contato" : {
      "endereco" : "Av. Ibirapuera, 2120 - Indianópolis, São Paulo - SP, 04028-001",
      "coordinates" : [
        -23.602668,
        -46.661897
      ],
      "type" : "Point"
    }
  }
);