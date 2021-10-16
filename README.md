# Mybatis Generator-demo

## 1 专业名词解释

### 1.1 Dao

Dao层叫数据访问层、持久层，全称为Data access object，属于一种比较底层，比较基础的操作，具体到对于某个表的增删改查，也就是说某个Dao一定是和数据库的某一张表一一对应的，其中封装了增删改查基本操作，完全根据Domain的要求来查询数据，建议Dao只做原子操作，增删改查。

### 1.2 Domain

Domain层考虑业务逻辑，例如过滤条件，放行或者返回，以及数据的处理，为调用Dao层做好准备，一个Domain可以调用一个或者一组相关的Dao层。

### 1.3 Service

Service层叫服务层，被称为服务。Service层调用一个或者一组Domain层，主要是展现需要开放出去的接口，其中Domain层不是所有的接口都要再Dervice层体现的，可能仅在Service层开放几个接口出去。此外，主要接口需要对接受的参数要尽量的扩大化，也就是说可以容纳各种类型的参数的接入(Object)，然后需要在Service层做好转换，以备Domain层使用。

### 1.4 Controler

Controler负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面。

### 1.5 业务逻辑

用户发出请求 -> Controller处理传过来的参数 -> Domain接收控制数据转向(可以返回也可以进入Service -> Service验证数据正确性或者是否符合业务要求 -> Dao存入



## 2 MyBatis Generator简介

MyBatis Generator（MBG）是MyBatis MyBatis 和iBATIS的代码生成器。它将为所有版本的MyBatis以及版本2.2.0之后的iBATIS版本生成代码。它将内省数据库表（或许多表），并将生成可用于访问表的工件。这减少了设置对象和配置文件以与数据库表交互的初始麻烦。MBG寻求对简单CRUD（创建，检索，更新，删除）的大部分数据库操作产生重大影响。您仍然需要为连接查询或存储过程手动编写SQL和对象代码。

### 2.1 MyBatis Generator将生成

#### 2.1. 1 与表结构匹配的Java POJO

这可能包括：

- 一个匹配表的主键的类（如果有主键）
- 一个匹配表的非主键字段的类（BLOB字段除外）
- 包含表的BLOB字段的类（如果表具有BLOB字段）
- 用于启用动态选择，更新和删除的类

这些类之间存在适当的继承关系。请注意，生成器可以配置为生成不同类型的POJO层次结构 - 例如，如果您愿意，可以选择为每个表生成单个域对象。

#### 2.1.2 MyBatis / iBATIS兼容的SQL Map XML文件

MBG在配置中的每个表上为简单的CRUD函数生成SQL。生成的SQL语句包括：

- 插入
- 按主键更新
- 通过示例更新（使用动态where子句）
- 按主键删除
- 按示例删除（使用动态where子句）
- 按主键选择
- 按示例选择（使用动态where子句）
- 以身作则

根据表的结构，这些语句有不同的变体（例如，如果表没有主键，则MBG不会通过主键功能生成更新）。

#### 2.1.3 适当使用上述对象的Java客户端类

Java客户端类的生成是可选的。MBG将为MyBatis 3.x生成以下类型的Java客户端：

- 适用于MyBatis 3.x映射器基础结构的映射器接口

MBG将为iBATIS 2.x生成以下类型的Java客户端：

- 符合Spring框架的DAO
- 仅使用iBATIS SQL映射API的DAO。这些DAO可以生成两种：通过构造函数或setter注入提供SqlMapClient。
- 符合iBATIS DAO框架的DAO（iBATIS的可选部分，现在不推荐使用此框架，我们建议您使用Spring框架）

MyBatis生成器设计为在迭代开发环境中运行良好，并且可以作为Ant任务或Maven插件包含在连续构建环境中。迭代运行MBG时需要注意的重要事项包括：

- 如果存在与新生成的XML文件同名的现有文件，MBG将自动合并XML文件。MBG不会覆盖您对其生成的XML文件所做的任何自定义更改。您可以反复运行它，而不必担心会丢失对XML的自定义更改。MBG将替换先前运行中生成的任何XML元素。
- MBG 不会合并Java文件，它可以覆盖现有文件或使用不同的唯一名称保存新生成的文件。如果对生成的Java文件进行更改并以迭代方式运行MBG，则必须手动合并更改。当作为Eclipse 插件运行时 ，MBG可以自动合并Java文件。



## 3 MyBatis Generator下载

### 3.1 源码地址

https://github.com/mybatis/generator/releases

### 3.2 官方文档

http://www.mybatis.org/generator/index.html



## 4 Mybatis Generator配置和使用

### 4.1 数据库及数据表创建

#### 4.1.1 新建数据库：mybatis_generator_demo-db

#### 4.1.2 新建数据表ad

```sql
--
-- Table structure for table `ad`
--

DROP TABLE IF EXISTS `ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ad`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(63)  NOT NULL DEFAULT '' COMMENT '广告标题',
    `link`        varchar(255) NOT NULL DEFAULT '' COMMENT '所广告的商品页面或者活动页面链接地址',
    `url`         varchar(255) NOT NULL COMMENT '广告宣传图片',
    `position`    tinyint(3) DEFAULT '1' COMMENT '广告位置：1则是首页',
    `content`     varchar(255)          DEFAULT '' COMMENT '活动内容',
    `start_time`  datetime              DEFAULT NULL COMMENT '广告开始时间',
    `end_time`    datetime              DEFAULT NULL COMMENT '广告结束时间',
    `enabled`     tinyint(1) DEFAULT '0' COMMENT '是否启动',
    `add_time`    datetime              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime              DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY           `enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='广告表';
```

#### 4.1.3 往数据表中插入数据

```sql
--
-- Dumping data for table `ad`
--

LOCK
TABLES `ad` WRITE;
/*!40000 ALTER TABLE `ad` DISABLE KEYS */;
INSERT INTO `ad`
VALUES (1, '合作 谁是你的菜', '', 'http://yanxuan.nosdn.127.net/65091eebc48899298171c2eb6696fe27.jpg', 1, '合作 谁是你的菜', NULL,
        NULL, 1, '2018-02-01 00:00:00', '2018-02-01 00:00:00', 0),
       (2, '活动 美食节', '', 'http://yanxuan.nosdn.127.net/bff2e49136fcef1fd829f5036e07f116.jpg', 1, '活动 美食节', NULL, NULL,
        1, '2018-02-01 00:00:00', '2018-02-01 00:00:00', 0),
       (3, '活动 母亲节', '', 'http://yanxuan.nosdn.127.net/8e50c65fda145e6dd1bf4fb7ee0fcecc.jpg', 1, '活动 母亲节5', NULL, NULL,
        1, '2018-02-01 00:00:00', '2018-02-01 00:00:00', 0);
/*!40000 ALTER TABLE `ad` ENABLE KEYS */;
UNLOCK
TABLES;
```

### 4.2 新建项目Mybatis Generator-demo

#### 4.2.1 在pom.xml中导入插件

```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.7</version>
    <configuration>
        <configurationFile>
            mybatis-generator/generatorConfig.xml
        </configurationFile>
        <overwrite>true</overwrite>
        <verbose>true</verbose>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
        </dependency>
        <dependency>
            <groupId>com.itfsw</groupId>
            <artifactId>mybatis-generator-plugin</artifactId>
            <version>1.2.12</version>
        </dependency>

        <dependency>
            <groupId>dom4j</groupId>
            <artifactId>dom4j</artifactId>
            <version>1.6.1</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.28</version>
        </dependency>
    </dependencies>
</plugin>
```

#### 4.2.2 编写配置文件

在项目根目录下新建文件夹mybatis-generator，并创建generatorConfig.xml文件， 在文件中写入：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

    <context id="mysqlgenerator" targetRuntime="MyBatis3">
        <property name="autoDelimitKeywords" value="true"/>
        <!--可以使用``包括字段名，避免字段名与sql保留字冲突报错-->
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <!-- 自动生成toString方法 -->
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>
        <!-- 自动生成equals方法和hashcode方法 -->
        <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"/>

        <!-- 非官方插件 https://github.com/itfsw/mybatis-generator-plugin -->
        <!-- 查询单条数据插件 -->
        <plugin type="com.itfsw.mybatis.generator.plugins.SelectOneByExamplePlugin"/>
        <!-- 查询结果选择性返回插件 -->
        <plugin type="com.itfsw.mybatis.generator.plugins.SelectSelectivePlugin"/>
        <!-- Example Criteria 增强插件 -->
        <plugin type="com.itfsw.mybatis.generator.plugins.ExampleEnhancedPlugin"/>
        <!-- 数据Model属性对应Column获取插件 -->
        <plugin type="com.itfsw.mybatis.generator.plugins.ModelColumnPlugin"/>
        <!-- 逻辑删除插件 -->
        <plugin type="com.itfsw.mybatis.generator.plugins.LogicalDeletePlugin">
            <!-- 这里配置的是全局逻辑删除列和逻辑删除值，当然在table中配置的值会覆盖该全局配置 -->
            <!-- 逻辑删除列类型只能为数字、字符串或者布尔类型 -->
            <property name="logicalDeleteColumn" value="deleted"/>
            <!-- 逻辑删除-已删除值 -->
            <property name="logicalDeleteValue" value="1"/>
            <!-- 逻辑删除-未删除值 -->
            <property name="logicalUnDeleteValue" value="0"/>
        </plugin>

        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <!--<property name="suppressAllComments" value="true"/>-->
        </commentGenerator>

        <!--数据库连接信息-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1:3306/mybatis_generator_demo-db?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC&amp;verifyServerCertificate=false&amp;useSSL=false"
                        userId="root"
                        password="root"/>

        <javaTypeResolver>
            <property name="useJSR310Types" value="true"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="com.hyd.mybatisdemo.domain" targetProject="src/main/java"/>
        <sqlMapGenerator targetPackage="com.hyd.mybatisdemo.dao" targetProject="src/main/resources"/>
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.hyd.mybatisdemo.dao" targetProject="src/main/java"/>
        <!--表名-->
        <table tableName="ad">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>

    </context>
</generatorConfiguration>
```

#### 4.2.3 运行插件

打开窗口右侧的Maven，依次展开Mybatis Generator-demo->Plugin->mybatis-generator，双击mybatis-generator:generate，可以看到控制台的相关信息以及生成的 dao、domain、com.hyd.mybatisdemo.dao

![1](https://img-blog.csdnimg.cn/ae9b0c396c0443a5947bd72d609b3989.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)

### 4.3 开始使用

#### 4.3.1 新建AdService.java

```java
@Service
public class AdService {
    @Resource
    private AdMapper adMapper;

    public List<Ad> queryIndex() {
        AdExample example = new AdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }

    public List<Ad> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        AdExample example = new AdExample();
        AdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adMapper.selectByExample(example);
    }

    public int updateById(Ad ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        adMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(Ad ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adMapper.insertSelective(ad);
    }

    public Ad findById(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }
}
```

#### 4.3.2 新建AdController.java

```java
@RestController
@RequestMapping
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping("/getad")
    public Object getad() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, Object> data = new HashMap<>();
        Callable<List> bannerListCallable = () -> adService.queryIndex();
        FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
        executorService.submit(bannerTask);
        try {
            data.put("banner", bannerTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return ResponseUtil.ok(data);
    }

}
```

#### 4.3.3 测试接口

```xml
http://localhost:8080/getad
```

![2](https://img-blog.csdnimg.cn/7b607731218e4ba789ccda842a24fa4b.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)







