package com.juhezi.bookshelf.dataModule;


import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BookInfo {


    /**
     * max : 10
     * numRaters : 372
     * average : 9.0
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"numRaters":372,"average":"9.0","min":0}
     * subtitle :
     * author : ["David Flanagan"]
     * pubdate : 2012-4-1
     * tags : [{"count":626,"name":"JavaScript","title":"JavaScript"},{"count":205,"name":"犀牛书","title":"犀牛书"},{"count":197,"name":"Web前端开发","title":"Web前端开发"},{"count":125,"name":"前端","title":"前端"},{"count":94,"name":"Web开发","title":"Web开发"},{"count":87,"name":"编程","title":"编程"},{"count":82,"name":"计算机","title":"计算机"},{"count":76,"name":"前端开发","title":"前端开发"}]
     * origin_title : JavaScript: The Definitive Guide, Sixth Edition
     * image : https://img3.doubanio.com/mpic/s8958854.jpg
     * binding : 平装
     * translator : ["淘宝前端团队"]
     * catalog : 前言  1
     第1章 JavaScript概述  5
     1.1 JavaScript语言核心  8
     1.2 客户端JavaScript  12
     第一部分 JavaScript 语言核心
     第2章 词法结构  25
     2.1 字符集  25
     2.2 注释  27
     2.3 直接量  27
     2.4 标识符和保留字  28
     2.5 可选的分号  30
     第3章 类型、值和变量  32
     3.1 数字  34
     3.2 文本  38
     3.3 布尔值  43
     3.4 null和undefined  44
     3.5 全局对象  45
     3.6 包装对象  46
     3.7 不可变的原始值和可变的对象引用  47
     3.8 类型转换  48
     3.9 变量声明  55
     3.10 变量作用域  56
     第4章 表达式和运算符  60
     4.1 原始表达式  60
     4.2 对象和数组的初始化表达式  61
     4.3 函数定义表达式  62
     4.4 属性访问表达式  63
     4.5 调用表达式  64
     4.6 对象创建表达式  64
     4.7 运算符概述  65
     4.8 算术表达式  69
     4.9 关系表达式  74
     4.10 逻辑表达式  79
     4.11 赋值表达式  81
     4.12 表达式计算  83
     4.13 其他运算符  86
     第5章 语句  91
     5.1 表达式语句  92
     5.2 复合语句和空语句  92
     5.3 声明语句  94
     5.4 条件语句  96
     5.5 循环  101
     5.6 跳转  106
     5.7 其他语句类型  113
     5.8 JavaScript语句小结  116
     第6章 对象  118
     6.1 创建对象  120
     6.2 属性的查询和设置  123
     6.3 删除属性  127
     6.4 检测属性  128
     6.5 枚举属性  130
     6.6 属性getter和setter  132
     6.7 属性的特性  134
     6.8 对象的三个属性  138
     6.9 序列化对象  141
     6.10 对象方法  142
     第7章 数组  144
     7.1 创建数组  144
     7.2 数组元素的读和写  145
     7.3 稀疏数组  147
     7.4 数组长度  148
     7.5 数组元素的添加和删除  149
     7.6 数组遍历  149
     7.7 多维数组  151
     7.8 数组方法  152
     7.9 ECMAScript 5中的数组方法  156
     7.10 数组类型  160
     7.11 类数组对象  161
     7.12 作为数组的字符串  163
     第8章 函数  165
     8.1 函数定义  166
     8.2 函数调用  168
     8.3 函数的实参和形参  173
     8.4 作为值的函数  178
     8.5 作为命名空间的函数  181
     8.6 闭包  182
     8.7 函数属性、方法和构造函数  188
     8.8 函数式编程  194
     第9章 类和模块  201
     9.1 类和原型  202
     9.2 类和构造函数  203
     9.3 JavaScript中Java式的类继承  207
     9.4 类的扩充  210
     9.5 类和类型  212
     9.6 JavaScript中的面向对象技术  217
     9.7 子类  230
     9.8 ECMAScript 5 中的类  239
     9.9 模块  248
     第10章 正则表达式的模式匹配  253
     10.1 正则表达式的定义  253
     10.2 用于模式匹配的String方法  261
     10.3 RegExp对象  263
     第11章 JavaScript的子集和扩展  267
     11.1 JavaScript的子集  268
     11.2 常量和局部变量  271
     11.3 解构赋值  274
     11.4 迭代  276
     11.5 函数简写  285
     11.6 多Catch 从句  285
     11.7 E4X: ECMAScript for XML  286
     第12章 服务器端JavaScript  290
     12.1 用Rhino脚本化Java  291
     12.2 用Node实现异步I/O  297
     第二部分 客户端JavaScript
     第13章 Web浏览器中的JavaScript  309
     13.1 客户端JavaScript  309
     13.2 在HTML里嵌入JavaScript  313
     13.3 JavaScript程序的执行  319
     13.4 兼容性和互用性  326
     13.5 可访问性  333
     13.6 安全性  334
     13.7 客户端框架  339
     第14章 Window对象  341
     14.1 计时器  342
     14.2 浏览器定位和导航  343
     14.3 浏览历史  345
     14.4 浏览器和屏幕信息  346
     14.5 对话框  348
     14.6 错误处理  351
     14.7 作为Window对象属性的文档元素  351
     14.8 多窗口和窗体  353
     第15章 脚本化文档  361
     15.1 DOM概览  362
     15.2 选取文档元素  364
     15.3 文档结构和遍历  371
     15.4 属性  375
     15.5 元素的内容  378
     15.6 创建、插入和删除节点  382
     15.7 例子：生成目录表  387
     15.8 文档和元素的几何形状和滚动  389
     15.9 HTML表单  396
     15.10 其他文档特性  404
     第16章 脚本化CSS  410
     16.1 CSS概览  411
     16.2 重要的CSS属性  416
     16.3 脚本化内联样式  427
     16.4 查询计算出的样式  431
     16.5 脚本化CSS类  433
     16.6 脚本化样式表  435
     第17章 事件处理  440
     17.1 事件类型  442
     17.2 注册事件处理程序  451
     17.3 事件处理程序的调用  454
     17.4 文档加载事件  459
     17.5 鼠标事件  461
     17.6 鼠标滚轮事件  465
     17.7 拖放事件  468
     17.8 文本事件  475
     17.9 键盘事件  478
     第18章 脚本化HTTP  484
     18.1 使用XMLHttpRequest  487
     18.2 借助<script>发送HTTP请求：JSONP  505
     18.3 基于服务器端推送事件的Comet技术  508
     第19章 jQuery类库  514
     19.1 jQuery基础  515
     19.2 jQuery的getter和setter  522
     19.3 修改文档结构  528
     19.4 用jQuery处理事件  531
     19.5 动画效果  542
     19.6 jQuery中的Ajax  550
     19.7 工具函数  563
     19.8 jQuery选择器和选取方法  566
     19.9 jQuery的插件扩展  574
     19.10 jQuery UI类库  577
     第20章 客户端存储  579
     20.1 localStorage和sessionStorage  581
     20.2 cookie  586
     20.3 利用IE userData来持久化数据  592
     20.4 应用程序存储和离线Web应用  594
     第21章 多媒体和图形编程  606
     21.1 脚本化图片  606
     21.2 脚本化音频和视频  608
     21.3 SVG：可伸缩的矢量图形  615
     21.4 <canvas>中的图形  623
     第22章 HTML5 API  658
     22.1 地理位置  659
     22.2 历史记录管理  662
     22.3 跨域消息传递  668
     22.4 Web Workers  671
     22.5 类型化数组和ArrayBuffer  678
     22.6 Blob  682
     22.7 文件系统API  691
     22.8 客户端数据库  696
     22.9 Web套接字  704
     第三部分 JavaScript核心参考
     JavaScript核心参考  711
     第四部分 客户端JavaScript参考
     客户端JavaScript参考  847
     * ebook_url : https://read.douban.com/ebook/15113928/
     * pages : 1004
     * images : {"small":"https://img3.doubanio.com/spic/s8958854.jpg","large":"https://img3.doubanio.com/lpic/s8958854.jpg","medium":"https://img3.doubanio.com/mpic/s8958854.jpg"}
     * alt : https://book.douban.com/subject/10549733/
     * id : 10549733
     * publisher : 机械工业出版社华章公司
     * isbn10 : 7111376617
     * isbn13 : 9787111376613
     * title : JavaScript权威指南(第6版)
     * url : https://api.douban.com/v2/book/10549733
     * alt_title : JavaScript: The Definitive Guide, Sixth Edition
     * author_intro : David Flanagan是一名程序员，也是一名作家，它的个人网站是http://davidflanagan.com。他在O'Reilly出版的其他畅销书还包括《JavaScript Pocket Reference》、《The Ruby Programming Language》，以及《Java in a Nutshell》。David毕业于麻生理工学院，获得计算机科学与工程学位。他和妻子和孩子一起生活在西雅图和温哥华之间的美国太平洋西北海岸。
     * summary : 本书是程序员学习核心JavaScript语言和由Web浏览器定义的JavaScript API的指南和综合参考手册。
     第6版涵盖HTML 5和ECMAScript 5。很多章节完全重写，以便与时俱进，紧跟当今的最佳Web开发实践。本书新增章节描述了jQuery和服务器端JavaScript。
     本书适合那些希望学习Web编程语言的初、中级程序员和希望精通JavaScript的JavaScript程序员阅读。
     * ebook_price : 30.00
     * series : {"id":"697","title":"博文视点O'reilly系列"}
     * price : 139.00元
     */

    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String ebook_url;
    private String pages;
    /**
     * small : https://img3.doubanio.com/spic/s8958854.jpg
     * large : https://img3.doubanio.com/lpic/s8958854.jpg
     * medium : https://img3.doubanio.com/mpic/s8958854.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String ebook_price;
    /**
     * id : 697
     * title : 博文视点O'reilly系列
     */

    private SeriesBean series;
    private String price;
    private List<String> author;
    /**
     * count : 626
     * name : JavaScript
     * title : JavaScript
     */

    private List<TagsBean> tags;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEbook_url() {
        return ebook_url;
    }

    public void setEbook_url(String ebook_url) {
        this.ebook_url = ebook_url;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEbook_price() {
        return ebook_price;
    }

    public void setEbook_price(String ebook_price) {
        this.ebook_price = ebook_price;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class SeriesBean {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TagsBean {
        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public BookSimInfo toBookSimInfo() {
        BookSimInfo bookSimInfo = new BookSimInfo();
        bookSimInfo.setiState(0);
        bookSimInfo.setTitle(title);
        bookSimInfo.setAuthor(getAuthorX());
        bookSimInfo.setIsbn(isbn13);
        bookSimInfo.setDesc(summary);
        bookSimInfo.setImageUrl(images.getLarge());
        return bookSimInfo;
    }

    String getAuthorX() {
        StringBuilder result = new StringBuilder();
        for(int i = 0;i < author.size();i++) {
            result.append(author.get(i) + " ");
        }
        return result.toString();
    }
}
