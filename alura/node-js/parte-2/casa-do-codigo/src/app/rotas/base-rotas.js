const BaseControlador = require('../controlatores/base-controlador');
const baseControlador = new BaseControlador();

module.exports = (app) => {
  const rotasBase = BaseControlador.rotas();

  app.get(rotasBase.home, baseControlador.home());

  app.route(rotasBase.login)
    .get(baseControlador.login())
    .post(baseControlador.efetuarLogin());
};
