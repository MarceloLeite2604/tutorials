db.alunos.insert(
    {
        "nome": "Fernando",
        "data_nascimento": new Date(1994,03,26),
        "notas": [10.0, 4.5, 7],
        "curso": {
            "nome": "Sistema de informação"
        }
    }
)

db.alunos.find({
    "curso.nome": "Sistemas de informação"
})

/* By default, an update in Mongo updates the whole document. Also, since it is a full modification, by default, the command only updates the first document found. */
db.alunos.update(
    {
        "curso.nome": "Sistema de informação"
    }, 
    {
        "nome": "Sistemas de informação"
    }
)

/* To avoid the whole documentation change (i. e. modify only the property informed), we use the "$set" property. But this will keep modifying only the first occurence found. */
db.alunos.update(
    {
        "curso.nome": "Sistema de informação"
    }, 
    {
        $set: {
            "curso.nome": "Sistemas de informação"
        }
    }
)

/* To modify selected fields on all documents that matches the criteria, we use "$set" along with "multi: true" */
db.alunos.update(
    {
        "curso.nome": "Sistemas de Informação"
    }, 
    {
        $set: {
            "curso.nome": "Sistemas De Informação"
        }
    },
    {
        multi: true
    }
)

db.alunos.find({
    "_id" : ObjectId("5f083dd185815e3f3cd5872b")    
})

/* Add a new element on an array. */
db.alunos.update(
    {
        "_id" : ObjectId("5f083dd185815e3f3cd5872b")    
    },
    {
        "$push" : {
            notas : 8.5
        }
    }
)

db.alunos.update(
    {
        "_id" : ObjectId("5f083dd185815e3f3cd5872b")    
    },
    {
        "$push" : {
            notas : {
                $each : [ 
                    8.5, 
                    3
                ]
            }
        }
    }
)