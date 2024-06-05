const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use('/api',
    createProxyMiddleware({
      target: 'http://218.38.44.59:5000',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '',
      },
    })
  );
};