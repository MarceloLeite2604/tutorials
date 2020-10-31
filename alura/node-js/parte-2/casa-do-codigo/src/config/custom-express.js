require('marko/node-require').install();
require('marko/express');

const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const methodOverride = require('method-override');
const rotas = require('../app/rotas/rotas');
const templates = require('../app/views/templates');
const sessaoAutenticacao = require('./sessao-autenticacao');

app.use('/estatico', express.static('src/app/public'));

app.use(bodyParser.urlencoded({
  extended: true
}));
app.use(methodOverride(function(req, res) {
  if (req.body && typeof req.body === 'object' && '_method' in req.body) {
    const method = req.body._method;
    delete req.body._method;
    return method;
  }
}));

sessaoAutenticacao(app);

rotas(app);

app.use(function(_req, resp, _next) {
  return resp.status(404).marko(
    templates.base.erro404
  );
});

app.use(function(_erro, _req, resp, _next) {
  return resp.status(500).marko(
    templates.base.erro500
  );
});

module.exports = app;
