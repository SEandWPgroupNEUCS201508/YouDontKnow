# YOU DONT KNOW DOCS



## issue
- todo





## frontend interfaces introduction
- todo







## backend url for frontend




### for user service
#### 登录 done
- url: http://server_addr:port/login
- get: return a basic jsp
- post:  参数　username, password, 如果登录成功，返回成功提示信息，否则返回失败原因（字符串）


#### 注册 done
- url: http://server_addr:port/register
- get: return a basic jsp
- post: 参数　username, password, email, 如果注册成功，返回成功信息，否则返回失败原因


#### 用户信息 done
- url: http://server_addr:port/user_profile
- get: 默认返回已登录用户自己的信息
- post: 如果找到了，会返回一个json(user_profile.jsp)，如果找到该用户返回该用户的所有信息
```json
{
	"user_id": 1,
    "username": "eg",
    "email":"email"
}
```
否则返回一个获取失败的字符串，(这个接口还要继续完善)


#### 更新用户信息 done
- url: http:/server_addr:port/update_user
- get: 返回一个基础html(缺失，暂时404)
- post: 参数　user_id, origin_password(检查用户密码的正确性，如果错误，后端会拒绝修改用户信息), new_email, new_password,如果更新成功，返回一个提示成功信息，否则返回一个失败提示信息



### for the article service

#### 获取板块动态 done
- url: http://server_addr:port/forum
- get:返回模块为test的10个最新的文章的一个测试jsp
- post:参数　forum, last_id，limit_num如果成功，返回一个json(forum.jsp)，否则返回一个提示失败的字符串
```json
{
    "forum":"test",
    "pre_last_id":100000,
    "limit_num":10,
    "article_node_list":[{
        "user_id":1,
        "article_id":1,
        "title":"eg",
        "content":"eg",
        "published_date":"date",
        "published_time":"time"
    }, {
        "user_id":1,
        "article_id":2,
        "title":"eg",
        "content":"eg",
        "published_date":"date",
        "published_time":"time"
    }]
}
```

#### 发表文章 done
- url: http://server_addr:port/publish_article
- get: 返回一个基础的测试html
- post: 参数title, content, user_id, forum,如果发布文章成功返回成功提示信息

#### 更新文章 done
- url:http://server_addr:port/update_article
- get:404
- post: 参数title,content,article_id,forum 如果修改文章成功返回成功提示信息

#### 查询文章 
- url:http://server_addr:port/search
- get: 参数word也就是搜索关键词
- post: 参数word也就是搜索关键词
- 说明: 所谓的word也就是关键字了，本来想写根据user_id或者article_id来查询的，但是换位思考一下，如果你是用户，后端要求你用id来查询，你会不会觉得这个站长特别shaby？反正我打算只让前端传文本了，我目前会根据标题，作者或者板块 来较为精确匹配这个关键字。等以后有时间了，再做匹配内容的模糊搜索。**word精确匹配作者名字、文章标题、板块！**虽然板块在前端是中文的，在后端数据库中是以英文存储的，**但是后端不负责把中文翻译成英文！**查询到的结果会以以下格式的json返回
```json
{
    "search_word":"word",
    "search_result":[{
        "user_id":1,
        "article_id":0,
        "title":"search res",
        "content":"text",
        "published_date":"date",
        "published_time":"time"
    }]
}
```

### for comment service
#### 获取某文章下评论列表 done without testing
- url:http://server_addr:port/article_comment
- get: 一个测试结果html [未完成]
- post: 参数article_id返回一个json(article_comment.jsp)
```json
{
    "article_id":1,
    "comment_list": [{
        "user_id":1,
        "comment_id":1,
        "response_to_comment":0,
        "comment":"我是评论本体",
        "published_date":"date",
        "published_time":"time"
    }, {
        "user_id":1,
        "comment_id":2,
        "response_to_comment":1,
        "comment":"我是评论本体",
        "published_date":"date",
        "published_time":"time"
    }, ....
    ]
}
```

修正一下，response_to_comment,现在代表的是该评论回复的评论的ID，如果它是一个非正数，那么代表这条评论不回复任何评论，comment_id是该评论本身的id

### 获取单条评论 done without testing
- 因为在上面的情况中，被回复的评论仅以ID形式给前端，但是如果前端要获取这条回复的话，需要这条接口
- url: http://serer_addr:port/comment
- get: 404
- post: 参数comment_id，如果查找失败，返回错误信息，如果成功，返回json如下
```json
{
    "user_id":1,	
    "comment_id":1,
    "response_to_comment":0,
    "comment":"我是评论本体",
    "published_date":"date",
    "published_time":"time"
}
```

### 发表评论 done without testing
- url:http://server_addr:port/publish_comment
- get: 404
- post: 参数user_id, article_id, comment_id, comment, 其中, user_id是发表该评论的用户id; comment_id代表是被回复的评论的id, 如果没有置0, article_id代表被评论的文章. 如果评论发表成功, 后端发出成功提示信息，否则发送失败信息



### for chat and discuss service
- url:ws://server_addr:port/chat
Message在java中的JavaBean的字段格式如下
```java
private int id;//数据库自增的字段
private int source;
private int destination;
private String message;
private String time;
```

前后端交换数据的格式为
```json
{
	source: ,
	destination: ,
	message : "you don't know",
	time : "Mon May 14 14:41:43 CST 2018"
}
```

**source 的获取用**
**前端发给后端的数据不需要有time这个属性，后端返回的数据是有time的**
**time字符串的格式`dow mon dd hh:mm:ss zzz yyyy`**

# log web.xml日志
```xml
<!--new interface-->
    <!--############################## user service #########################-->

    <!--#### 用户信息 -->
    <servlet>
        <servlet-name>user_profile</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.user.UserProfile</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>user_profile</servlet-name>
        <url-pattern>/user_profile</url-pattern>
    </servlet-mapping>


    <!--#### 更新用户信息 -->
    <servlet>
        <servlet-name>update_user</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.user.UpdateUser</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>update_user</servlet-name>
        <url-pattern>/update_user</url-pattern>
    </servlet-mapping>

    <!--################################ article service ###########################-->
    <!--### 获取板块动态-->
    <servlet>
        <servlet-name>forum</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.forum.ForumPage</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>forum</servlet-name>
        <url-pattern>/forum</url-pattern>
    </servlet-mapping>

    <!--### 发表文章-->
    <servlet>
        <servlet-name>publish_article</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.article.ArticlePublication</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>publish_article</servlet-name>
        <url-pattern>/publish_article</url-pattern>
    </servlet-mapping>

    <!--### 更新文章-->
    <servlet>
        <servlet-name>update_article</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.article.UpdateArticle</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>update_article</servlet-name>
        <url-pattern>/update_article</url-pattern>
    </servlet-mapping>

    <!--################################ comment service ###########################-->

    <!--### 获取某文章下评论列表-->
    <servlet>
        <servlet-name>article_comment</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.article.ArticleComment</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>article_comment</servlet-name>
        <url-pattern>/article_comment</url-pattern>
    </servlet-mapping>

    <!--### 获取单条评论-->
    <servlet>
        <servlet-name>comment</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.comment.CommentPage</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>comment</servlet-name>
        <url-pattern>/comment</url-pattern>
    </servlet-mapping>

    <!--### 发表评论-->
    <servlet>
        <servlet-name>publish_comment</servlet-name>
        <servlet-class>com.neu.youdontknow.servlet.pages.comment.CommentPublication</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>publish_comment</servlet-name>
        <url-pattern>/publish_comment</url-pattern>
    </servlet-mapping>
```
- 测试 forum 为test, 长度为10的article list，结果如下

```json
{ "forum":test, "pre_last_id":2147483647, "limit_num":10, "article_node_list": [{"user_id":1,"article_id":18,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:50"},{"user_id":1,"article_id":17,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:49"},{"user_id":1,"article_id":16,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:49"},{"user_id":1,"article_id":15,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:48"},{"user_id":1,"article_id":14,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:47"},{"user_id":1,"article_id":13,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:46"},{"user_id":1,"article_id":12,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:45"},{"user_id":1,"article_id":11,"title":"curl","content":"curl_test","published_date":"2018-05-13","published_time":"22:04:34"},{"user_id":1,"article_id":10,"title":"1","content":"1","published_date":"2018-05-13","published_time":"22:02:05"},{"user_id":2,"article_id":9,"title":"1231","content":"1234","published_date":"2018-05-13","published_time":"22:01:10"}] }
```