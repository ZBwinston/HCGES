module.exports = {
  devServer: {
    proxy: {
      // 接口地址为 /web 开头的请求都会被代理到 http://localhost:10086/web
      '/web': {
        target: 'http://localhost:10086',  // 代理目标地址
        changeOrigin: true,  // 开启跨域
        pathRewrite: {
          '^/web': '/web'   // 请求路径去掉 /web 前缀
        }
      }
    }
  }
}


