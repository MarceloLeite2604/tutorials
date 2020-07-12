db.alunos.find({
    "nome" : "Felipe"
}).pretty();

/* In Mongo, it is possible to save a coordinate on a property object. To do it, the object must have two mandatory properties, an array named "coordinates" and a string named "type". The former have the coordinates latitude and longitude while the latter will inform which type of coordinate is stored. The object may also contain other properties. */
db.alunos.update(
    {
        "nome" : "Felipe"
    },
    {
        "$set": {
            "localizacao": {
                "endereco" : "Rua Vergueiro, 3185",
                "cidade" : "São Paulo",
                "coordinates" : [-23.588213, -46.632356],
                "type": "Point"
            }           
        }
    }
);

/* Before execute the following commands, it is necessary to execute "lesson-5-import.sh" script. */

/* Creates an index for "localizacao" property informing it is a 2D sphere (i. e. it stores only latitude and longitude coordinates). */
db.alunos.createIndex({
    "localizacao": "2dsphere"
});

/* "Aggregate" function will aggregate the documents based on a criteria. "$geoNear" informs that my criteria is by geographical proximity. "near" informs the initial point to check proximity. "distanceField" contains the property which the distance will be informed on results. "spherical" informs that the proximity will be checked using a spherical distance. "num" informs the number of results to be returned by the aggregation. "$skip" requests the aggregation the amount or object to be skipped on the result. */
db.alunos.aggregate([
    {
        $geoNear : {
            near: {
                coordinates: [-23.5640265, -46.6527128],
                type: "Point"
            },
            "distanceField" : "distancia.calculada",
            "spherical": true,
            "num": 4
        }
    }, 
    {
        "$skip" : 1
    }
]);