# hsweb 演示
演示如何在新项目中使用hsweb
基于[hsweb-framework](https://github.com/hs-web/hsweb-framework)和[hsweb-platform](https://github.com/hs-web/hsweb-platform) 

## 环境
运行唯一需要的环境: **java8**

## 运行
**直接运行**
```shell
    $ ./mvnw spring-boot:run    #linux
    $ mvnw.cmd spring-boot:run  #windows
```
**IDE**
导入ide后等待maven依赖下载完成后执行`org.hsweb.demo.Run`

## 目录说明
```shell
----------src/main/java
---------------org.hsweb.demo
--------------------bean,controller,dao,service #不做过多说明，都懂
--------------------Run.java                    #项目启动类,直接运行启动项目
----------resources
---------------config                           #配置目录(logback和mybatis配置文件)
---------------mappers                          #mybatis mapper配置文件
---------------system.install.sql               #系统首次启动执行的sql
---------------templates                        #模板文件(freemarker)
---------------static                           #静态文件目录
---------------application.yml                  #应用主配置,数据源啥的都在这里
---------------transactions.properties          #动态数据源jta所需配置(自行搜索atomikos)
```

## 增删改查
演示中已建了一个测试功能(test),暂无页面
启动成功登录后,用浏览器打开 `http://localhost:8080/test` 试试

[上报问题](https://github.com/hs-web/hsweb-demo/issues/new)