# Web_easyEn
A web project by my friends and I for our class~
目前该项目缺少定期维护，全凭偶尔缝缝补补。欢迎有志之士完善。- [Web\_easyEn](#web_easyen)
- [Web\_easyEn](#web_easyen)
  - [项目功能说明](#项目功能说明)
  - [项目情况说明](#项目情况说明)
      - [功能Todo](#功能todo)
      - [如何食用本项目](#如何食用本项目)


## 项目功能说明

旨在实现一个提供英语学习服务的网站。可以实现登录、注册、密码找回；拉取外刊阅读，单词阅读；发学习交流贴，评论；GPT批改作文；查看批改记录等。部分页面如下：

<img src="intro_img\mainPage.png" width = "2200" height = "500" alt="图片名称" align=center>

<img src="intro_img\comment.png" width = "2200" height = "500" alt="图片名称" align=center>

<img src="intro_img\progress.png" width = "2200" height = "500" alt="图片名称" align=center>

## 项目情况说明

#### 功能Todo

口语练习

外刊阅读，单词阅读（目前是拉取某不喜欢写java的组员用C++实现的外部服务实现的）

帖子和评论部分的一些细节完善（小bug等等

学习进度可视化



#### 如何食用本项目

想要使用较为全面的功能，需要在database_config文件夹中获取data文件夹（也就是整个数据库文件），安装数据库或者导出sql文件，安装表结构即可；

如果希望使用docker部署数据库，相应的docker compose文件也在database_config文件夹中给出了。

部署完成后，修改backend\src\main\resources里面application.yaml的相应信息；

如果需要使用GPT相关的功能，也需要在backend\src\main\resources里面application.yaml里添加相应信息；

<img src="intro_img\config.png" width = "2200" height = "900" alt="图片名称" align=center>





