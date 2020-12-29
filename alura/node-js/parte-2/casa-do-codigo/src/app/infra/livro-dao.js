class LivroDao {
  constructor(db) {
    this._db = db;
  }

  adiciona(livro) {
    return new Promise((resolve, reject) => {
      this._db.run(
        `
          INSERT INTO livros (
              titulo, 
              preco,
              descricao
          ) values (?,?,?)
        `,
        [
          livro.titulo,
          livro.preco,
          livro.descricao
        ],
        function(err) {
          if (err) {
            console.log(err);
            return reject(new Error('Não foi possível adicionar o livro.'));
          }

          resolve();
        }
      );
    });
  }

  lista() {
    return new Promise((resolve, reject) => {
      this._db.all(
        'SELECT * FROM livros',
        (erro, resultados) => {
          if (erro) return reject(new Error('Não foi possível listar os livros.'));

          return resolve(resultados);
        }
      );
    });
  }

  buscaPorId(id) {
    return new Promise((resolve, reject) => {
      this._db.get(
        `
          SELECT *
          FROM livros
          WHERE id = ?
        `,
        [id],
        (erro, livro) => {
          if (erro) {
            return reject(new Error('Não foi possível encontrar o livro.'));
          }
          return resolve(livro);
        }
      );
    });
  }

  atualiza(livro) {
    return new Promise((resolve, reject) => {
      this._db.run(
        `
          UPDATE livros SET
          titulo = ?,
          preco = ?,
          descricao = ?
          WHERE id = ?
        `,
        [
          livro.titulo,
          livro.preco,
          livro.descricao,
          livro.id
        ],
        erro => {
          if (erro) {
            return reject(new Error('Não foi possível atualizar o livro.'));
          }

          resolve();
        });
    });
  }

  remove(id) {
    return new Promise((resolve, reject) => {
      this._db.get(
        `
          DELETE 
          FROM livros
          WHERE id = ?
        `,
        [id],
        (erro) => {
          if (erro) {
            return reject(new Error('Não foi possível remover o livro.'));
          }
          return resolve();
        }
      );
    });
  }
}

module.exports = LivroDao;
