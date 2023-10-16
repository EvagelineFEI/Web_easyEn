# Web_easyEn
A web project by my friends and I for our class~

## 新版项目结构

hh鉴定原结构耦合度高，所以我换了个结构，变成前后端完全分离在两个文件夹下面的结构了。今天发现之前的web配置（Jsp+Servlet）也是耦合的，赶紧把pom文件也改了。

但是发现有些项目还是使用的old结构，所以就留着旧版的供参考了。

## 项目结构old

参考的是网上通用的Java开源项目结构介绍

`/src/main/resources`目录，里面主要存放静态配置文件和页面静态资源等东西：

```text
|_mapper：存放mybatis的XML映射文件（如果是mybatis项目）
|_static：存放网页静态资源，比如下面的js/css/img
   |__
   |__
   |__
   |__
|_template：存放网页模板，比如thymeleaf/freemarker模板等
   |__
   |__
   |__
   |__
```

`/src/main/java`目录，就是主要的后端代码部分

```
|_com.easyen：下面是代码的主要部分
   |__
   |__
   |__
   |__
```



