db.infoAutores.insertOne({
    "nome": "Yan Orestes",
    "email": "yan@caelum.com.br",
    "senha": "yan1234",
    "descricao": "Instrutor e desenvolvedor na Alura." 
});

var autorInfo = db.infoAutores.findOne({
    "email": "yan@caelum.com.br"
})

db.artigos.insertOne({
    "autor": {
        "nome": autorInfo.nome,
        "descricao": autorInfo.descricao,
        id_info: autorInfo._id
    },
    "slug": "dicas-politicas-de-senha",
    "titulo": "Algumas dicas importantes sobre políticas de senha",
    "texto": "O fato é que, por algum motivo, meus usuários estavam usando senhas fracas para suas próprias contas. E por que isso? Ademais, por que eu deveria me importar com isso? Afinal, aparentemente o problema de segurança não está do lado da aplicação, mas sim dos usuários… certo? Na verdade, não!",
    "dataCriado": ISODate('2018-07-18'),
    "categorias": [
        "Arquitetura",
        "Programação"
    ],
    "comentarios": []
})

var documento = db.artigos.findOneAndUpdate(
    {
        "slug": "dicas-politicas-de-senha"
    },
    {
        "$push": {
            "comentarios": {
                "$each": [
                    {
                        "nome": "Giovanni",
                        "email": "giovanni@alura.com.br",
                        "texto": "Achei muito legal este artigo!",
                        "data": ISODate('2019-09-24')
                    }
                ],
                "$position": 0
            }
        }
    },
    {
        returnNewDocument: true
    }
);

db.comentarios.insertOne({
    "id_artigo": documento._id,
    "nome": "Giovanni",
    "email": "giovanni@alura.com.br",
    "texto": "Achei muito legal este artigo!",
    "data": ISODate('2019-09-24')
});