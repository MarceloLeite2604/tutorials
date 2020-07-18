db.produtos.insertOne({
    "marca": "Apple",
    "modelo": "iPhone 11",
    "descricao": "O celular mais novo da Apple com duas cameras",
    "sistema": "iOS 13",
    "memoria": "128GB",
    "chips": 1,
    /* NumberDecimal allow us to to float operations with more precise results. */
    /* Other More data types can be found on Mongo BSON Types page. */
    "preco": NumberDecimal('4999.99')
});

db.clientes.insertOne({
    nome: "Giovanni",
    endereco: "Rua Vergueiro, 3185",
    email: "giovanni@example.com",
    senha: "giovanni123"
});

db.clientes.insertOne({
    nome: "Ricardo",
    endereco: "Rua do Ouvidor, 50",
    email: "ricardo@example.com",
    senha: "ricardo123"
});

db.produtos.update(
    {
        "modelo" : "iPhone 11"
    },
    {
        "$set" : {
            "quantidade": 7
        }
    }
);

db.clientes.updateMany(
    {},
    {
        "$set" : {
            carrinho: {
                produtos: []
            }
        }
    }
);

var produto = db.produtos.findOneAndUpdate(
    {
        "modelo": "iPhone 11",
        "quantidade": {
            "$gt": 0
        }
    },
    {
        /* Increments "quantidade" by -1 (decrements, in this case) */
        "$inc": {
            quantidade: -1
        }
    },
    {
        /* "projection" indicates which properties should be present in the document returned. */ 
        "projection": {
            "marca": 1,
            "modelo": 1,
            "preco": 1
        }
    }
);

db.clientes.update(
    {
        "nome": "Giovanni"
    }, 
    {
        "$push": {
            "carrinho.produtos" : produto
        }
    }
);

db.clientes.aggregate([
    {
        "$match" : {
            "nome" : "Ricardo"
        }
    },
    {
        "$project" : {
            "_id": 0,
            "id_cliente" : "$_id",
            "endereco" : "$endereco",
            "data" : ISODate(),
            "produtos" : "$carrinho.produtos",
            "valotTotal" : {
                "$sum" : "$carrinho.produtos.preco"
            }
        }
    },
    {
        /* Merges the output documents into "pedidos" collection. */
        "$merge" : "pedidos"
    }
]);

db.clientes.update(
    {
        "nome" : "Ricardo"
    },
    {
        "$set" : {
            "carrinho.produtos" : []
        }
    }
);

db.lojas.insertOne({
    "nome": "Apple Store",
    "endereco": "Av Roque Petroni Junior, 1080"
});

var magazineLuiza = db.lojas.findOne(
    {
        "nome" : "Magazine Luiza"
    }
);

var fastShop = db.lojas.findOne(
    {
        "nome" : "Fast Shop"
    }
);

db.produtos.update(
    {
        "modelo": "Moto G7"
    },
    {
        "$set": {
            "vendido_em": [ magazineLuiza._id, fastShop._id ]
        }
    }
);

var iphone11 = db.produtos.findOne({
    "modelo" : "iPhone 11"
});

var iphone8 = db.produtos.findOne({
    "modelo" : "iPhone 8"
});

db.lojas.update(
    {
        "nome": "Apple Store"
    },
    {
        "$set": {
            "produtos": [ iphone11._id, iphone8._id ]
        }
    }
);

db.lojas.aggregate([
    {
        "$match": {
            "nome": "Apple Store"
        }
    },
    {
        "$lookup" : {
            /* "produtos" collection */
            "from" : "produtos",
            /* "produtos" property defined on documents inside "lojas" will be used to match content. */
            "localField" : "produtos",
            /* "_id" property defined on documents inside the other collection will be checked to match. */
            "foreignField": "_id",
            /* Name given to the property result. */
            "as": "listaProdutos"
        }
    }
]).pretty();

db.produtos.aggregate([
    {
        "$match": {
            "modelo": "Moto G7"
        }
    },
    {
        "$lookup" : {
            "from" : "lojas",
            "localField" : "vendido_em",
            "foreignField": "_id",
            "as": "listaLojas"
        }
    }
]).pretty();

db.lojas.updateMany(
    {},
    {
        "$unset" : {
            "produtos" : 1
        }
    }
);

var appleStore = db.lojas.findOne(
    {
        "nome" : "Apple Store"
    }
);

db.produtos.find({
    "vendido_em": appleStore._id
});