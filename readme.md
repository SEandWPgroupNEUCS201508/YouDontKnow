# YOU DONT KNOW DOCS



## issue
- todo





## frontend interfaces introduction
- todo







## backend url for frontend




### for user service
#### 登录
- url: http://server_addr:port/login
- get: return a basic jsp
- post:  参数　username, password, 如果登录成功，返回成功提示信息，否则返回失败原因（字符串）


#### 注册
- url: http://server_addr:port/register
- get: return a basic jsp
- post: 参数　username, password, email, 如果注册成功，返回成功信息，否则返回失败原因


#### 用户信息
- url: http://server_addr:port/user_profile
- get: return a basic jsp
- post: 参数　user_id, 如果找到了，会返回一个json(user_profile.jsp)，如果找到该用户返回该用户的所有信息
```json
{
	"user_id": id,
    "username": "eg",
    "email":"email"
}
```
否则返回一个获取失败的字符串


#### 更新用户信息
- url: http:/server_addr:port/update_user
- get: 返回一个基础html
- post: 参数　user_id, origin_password(检查用户密码的正确性，如果错误，后端会拒绝修改用户信息), new_email, new_password,如果更新成功，返回一个提示成功信息，否则返回一个失败提示信息



### for the article service

#### 获取板块动态
- url: http://server_addr:port/forum
- get:参数  forum, last_id（要求最后一个article的id)，limit_num(限制文章数) 返回一个基础的测试html
- post:参数　forum, last_id，limit_num如果成功，返回一个json(forum.jsp)，否则返回一个提示失败的字符串
```json
{
    "forum":"test",
    "pre_last_id":num,
    "limit_num":num,
    "article_node_list":[{
        "user_id": id,
        "username": "eg",
        "email":"email",
        "article_id":num,
        "title":"eg",
        "content":"eg",
        "published_date":"date",
        "published_time":"time"
    }, {
        "user_id": id,
        "username": "eg",
        "email":"email",
        "article_id":num,
        "title":"eg",
        "content":"eg",
        "published_date":"date",
        "published_time":"time"
    } ...(忽略后续)
    ]
}
```

#### 发表文章
- url: http://server_addr:port/publish_article
- get: 返回一个基础的测试html
- post: 参数title, content, user_id, forum,如果发布文章成功返回成功提示信息

#### 更新文章
- url:http://server_addr:port/update_article
- get:404
- post: 参数title,content,user_id,forum 如果修改文章成功返回成功提示信息

### for comment service
#### 获取某文章下评论列表
- url:http://server_addr:port/article_comment
- get: 404
- post: 参数article_id返回一个json(comment_list.jsp)
```json
{
    "article_id":1,
    "comment_list": [{
        "user_id":1,
        "comment_id":1,
        "response_to_comment": {
            "user_id":1,
            "comment_id":0,
            "comment":"我是被回复的评论",
            "published_date":"date",
            "published_time":"time"
        },
        "comment":"我是评论本体",
        "published_date":"date",
        "published_time":"time"
    }, {
        "user_id":1,
        "comment_id":1,
        "response_to_comment": {
            
        },
        "comment":"我是评论本体",
        "published_date":"date",
        "published_time":"time"
    }, ....(忽略)
    ]
}
```
需要注意的是，当评论的comment_id是一个非正数，代表它不回复任何评论，response_to_comment为空


#### 发表评论
- url:http://server_addr:port/publish_comment
- get: 404
- post: 参数user_id, article_id, comment_id, comment, 其中, user_id是发表该评论的用户id; comment_id代表是被回复的评论的id, 如果没有置0, article_id代表被评论的文章. 如果评论发表成功, 后端发出成功提示信息，否则发送失败信息



### for chat and discuss service
- todo

在java中的JavaBean的字段格式如下
```java
    private int id;
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
message : ,
time : 
```
**source 的获取用**
**前端发给后端的数据不需要有time这个属性，后端返回的数据是有time的**
**time字符串的格式`dow mon dd hh:mm:ss zzz yyyy`**

* Example:Mon May 14 14:41:43 CST 2018