const passport = require('passport');
const templates = require('../views/templates');
const LivroControlador = require('./livro-controlador');

class BaseControlador {
  static rotas() {
    return {
      home: '/',
      login: '/login'
    };
  };

  home() {
    return function(_, resp) {
      resp.marko(
        templates.base.home
      );
    };
  }

  login() {
    return function(_, resp) {
      resp.marko(templates.base.login);
    };
  }

  efetuarLogin() {
    return function(req, resp, next) {
      const authMethod = passport.authenticate('local', (erro, usuario, info) => {
        if (info) {
          return resp.marko(templates.base.login);
        }

        if (erro) {
          return next(erro);
        }

        req.login(usuario, () => {
          if (erro) {
            return next(erro);
          }

          return resp.redirect(LivroControlador.rotas().lista);
        });
      });

      authMethod(req, resp, next);
    };
  }
};

module.exports = BaseControlador;
