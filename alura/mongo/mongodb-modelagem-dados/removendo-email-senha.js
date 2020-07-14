db.artigos.find({}).forEach(function(artigo){
    var infoAutor = db.infoAutores.findOne({
        email: artigo.autor.email
    });

    artigo.autor.id_info = infoAutor._id;

    delete artigo.autor.email;
    delete artigo.autor.senha;

    db.artigos.save(artigo);
})