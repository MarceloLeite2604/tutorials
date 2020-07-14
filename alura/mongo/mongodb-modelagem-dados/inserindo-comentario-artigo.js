db.artigos.update({
    "slug": "visualizando-dados-de-frequencia"
}, {
    /* Removes last item from array. */
    "$pop": {
        "comentarios": 1
    }
});

/* Finds a document and updates its content. */
var documento = db.artigos.findOneAndUpdate(
    {
    "slug": "visualizando-dados-de-frequencia"
    }, 
    {
        /* Adds an item in the array. */
        "$push": {
            "comentarios": {
                "$each": [
                    {
                        "nome": "Giovanni",
                        "email": "giovanni@alura.com.br",
                        "texto": "Gostei do artigo!",
                        "data": ISODate('2019-09-24')
                    }
                ],
                /* By default, "$push" adds an item in the end of the array. "$position" changes it by defining the push position. */ 
                "$position": 0
            }
        }
    }, 
    {
        /* Requests the new document to be returned by the method. */
        returnNewDocument: true
    }
);

db.comentarios.insertOne({
    "id_artigo": documento._id,
    "nome": "Giovanni",
    "email": "giovanni@alura.com.br",
    "texto": "Gostei do artigo!",
    "data": ISODate('2019-09-24') 
})