> 这里记录些项目的思考

- 项目架构分层
  - app : 项目启动所需要的一些配置类内容
    - config
    - exception
  - trigger : 触发层，接收 HTTP 请求的地方
    - controller
  - domain
  - types