## Java Spring Boot 后台项目结构示例  


​    
​    
### 简介  

如何创建一个 SpringBoot 项目？参考 Spring 文档就可以很容易创建。如何在公司中设计一个后台项目的架构？确实一个值得让人思考的问题。在项目的起初，一个 Spring Boot 项目就是公司业务的全部，但是随着公司的业务发展，一个 Spring Boot 已经不够用了，或者项目太过于庞大了，公司会产生很多个业务，这时就需要根据业务对项目进行拆分。业务的拆分就需要有一个合理的架构来保证各个子项目代码风格的一致性、使用技术的一致性以及避免子项目中重复造轮子等问题。那么，这个项目就是作者设计的一个可用于多业务的、多项目的后台架构。  

### 项目架构  

`demo-base` 项目: 该项目为一个基础项目，提供一些适用于所有项目的、统一的API规范。

`demo-general-boot` 项目: 该项目为一个通用的子项目示例，包括 `Contrpller` -`Service` -`DAO` 层的代码编写示例。 

项目不提供统一的父类，根据不同的项目创建时间使用最新 SpringBoot 作为父类即可   

### 项目维护  

`demo-base` 项目由专门的架构组维护，每一次更新之后通知所有子业务项目组进行更新同步  

`demo-general-boot` 项目属于子业务项目，和其他众多的业务项目一样，由每个业务项目组进行维护      

### 项目技术与规范  

#### 1 REST 风格前后端交互  

| 请求接口 | URL            | 请求方式 | 请求参数载体 | 请求参数格式        |
| -------- | -------------- | -------- | ------------ | ------------------- |
| 新增     | /xxx/add       | POST     | body         | json                |
| 批量新增 | /xxx/add/batch | POST     | body         | json                |
| 查询单条 | /xxx/info      | GET      | header       | key-value拼接       |
| 查询列表 | /xxx/list      | GET      | header       | key-value拼接       |
| 修改     | /xxx/update    | PUT      | body         | json                |
| 删除     | /xxx/delete    | DELETE   | body         | json                |
| 批量删除 | /xxx/delete/   | DELETE   | body         | json                |
|          |                |          |              |                     |
| 上传     | /xxx/upload    | POST     | body         | multipart/form-data |
| 导入     | /xxx/import    | POST     | body         | multipart/form-data |
| 导出     | /xxx/export    | POST     | body         | json                |
| 下载     | /xxx/download  | GET      | header       | key-value拼接       |

Controller 层示例:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/controller/UserController.java
```



#### 2 Api 返回结果封装与拓展  

该 ApiResult 为 Service 层的统一返回结果，也是 Controller 层的返回结果泛型  

```
./demo-base/src/main/java/com/ljq/demo/base/common/api/ApiResult.java
```

子项目中可对该 ApiResult 进行拓展  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/common/api/SubApiResult.java
```

在子项目中可以自行定义错误异常枚举类  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/common/api/SubApiMsgEnum.java
```



#### 3 异常处理  

常见异常类整理:  

```
./demo-base/src/main/java/com/ljq/demo/base/common/constant/ExceptionConst.java
```

子项目中可对其进行拓展:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/common/constant/SubExceptionConst.java
```

自定义异常:  

```
./demo-base/src/main/java/com/ljq/demo/base/common/exception/CommonException.java
```

子项目对自定义异常进行拓展:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/common/exception/BizException.java
```

全局异常处理类:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/interceptor/GlobalExceptionHandler.java
```



#### 4 国际化

多语言枚举类，用于列出项目支持的语言类型  

```
./demo-base/src/main/java/com/ljq/demo/base/common/i18n/LanguageEnum.java
```

语言工具类，用于获取上下文语言环境  

```
./demo-base/src/main/java/com/ljq/demo/base/common/i18n/LanguageUtil.java
```

国际化多语言返回消息工具类，用于读取多语言配置文件中的信息:  

```
./demo-base/src/main/java/com/ljq/demo/base/common/i18n/I18nMsgUtil.java
```

多语言配置文件(在子项目中配置):  

```
./demo-general-boot/src/main/resources/i18n/zh_cn.properties
./demo-general-boot/src/main/resources/i18n/en_us.properties
```

#### 5 Mybatis-Plus 集成  

[SpringBoot 2.3 集成 Mybatis Plus 3.4](https://blog.csdn.net/mrqiang9001/article/details/108343743)  

#### 6 优先使用第三方类库提供的工具类  

第三方库可不添加至 `demo-base` 项目中，而是由子项目中根据需要自行引入  

推荐使用的第三方工具类库:  

##### 6.1 Huool

hutool 是一个由国内开发团队开源的Java工具类集合，日常使用到的转换工具都可以在这里找到  

```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.4.3</version>
</dependency>
```

##### 6.2 Spring 框架自带的工具类库  

Spring 框架自带的类库中也有很多实用的工具类，如 Http 请求相关的，集合操作相关的等等。引入Spring 核心依赖即可  

##### 6.3 Apache common-lang3  

Apache 提供的一些常用的工具类，很多优秀的算法都可以实用该类库提供的  

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.11</version>
</dependency>
```



#### 7 日志记录与配置  

日志框架使用 Logback  

日志配置(子项目中):  

```
./demo-general-boot/src/main/resources/logback.xml
```

Controller 出入参日志记录:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/interceptor/AopLog.java
./demo-general-boot/src/main/java/com/ljq/demo/general/common/config/AopLogConfig.javas
```



#### 8 拦截器与 Token 校验  

拦截器:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/interceptor/WebInterceptor.java
```

拦截器配置:  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/interceptor/MebConfig.java
```



#### 9 配置规范与说明  

自定义配置(子项目):  

```
./demo-general-boot/src/main/java/com/ljq/demo/general/common/config/SftpUploadConfig.java
```

yml 文件:  

```yaml
## sftp 附件上传配置
uploadSftp:
  host: 172.16.140.10
  port: 22
  username: root
  password: root
  path: /home/ljq/upload
```






