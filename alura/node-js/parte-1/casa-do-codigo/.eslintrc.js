module.exports = {
  env: {
    browser: true,
    commonjs: true,
    es2021: true
  },
  extends: [
    'standard'
  ],
  parserOptions: {
    ecmaVersion: 12
  },
  ignorePatterns: ['**/*marko.js'],
  rules: {
    semi: ['error', 'always'],
    'space-before-function-paren': ['error', 'never']
  }
};
