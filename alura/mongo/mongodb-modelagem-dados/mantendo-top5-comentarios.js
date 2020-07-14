db.artigos.find({
    comentarios: {
        /* Checks is specified property exists. */
        "$exists": true
    }
}).forEach((artigo) => {
    artigo.comentarios = db.comentarios.find(
        {
            id_artigo: artigo._id
        },
        {
            _id: 0,
            nome: 1,
            email: 1,
            texto: 1,
            data: 1
        })
            .sort({ data: 1 })
            .limit(5)
            .toArray();

    db.artigos.save(artigo);
}
);