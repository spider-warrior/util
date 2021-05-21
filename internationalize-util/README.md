* 语言代码列表: https://baike.baidu.com/item/ISO%20639-1/8292914?fr=aladdin
* 国家/地区代码列表: https://baike.baidu.com/item/ISO%203166-1/5269555?fr=aladdin


为了国际化，我们需要为不同的本地化类型分别提供对应的资源文件，并以规范的方式进行命名 。命名格式为：
> <资源名>_<语言代码>_<国家/地区代码>.properties

1. 语言代码和国家/地区代码都是可选的。
2. < 资源名 >.properties 命名的文件是默认的资源文件，即某个本地化类型在系统中找不到对应的资源文件，就采用这个默认的资源文件。
3. < 资源名 >_< 语言代码 >.properties 命名的国际化资源文件是某一语言默认的资源文件，即某个本地化类型在系统中找不到精确匹配的资源文件时，将采用相应语言默认的资源文件 。

假设我们需要国际化主页信息，资源文件名为 “content”，那么中国的本地化资源文件名就为 content_zh_CN.properties；美国的本地化资源文件名为 content_en_US.properties。

content_zh_CN.properties 内容：
    index.greeting=\u6B22\u8FCE\u60A8
中文的本地化资源文件内容采用了特殊的编码来表示中文字符，这是因为资源文件只能使用 ASCII 字符 。 所以必须将非 ASCII 字符的内容转换为 Unicode 代码的表示方式 。

--------------------------------------------------------------------------------------------------------------------------
Java提供了用于加载本地化资源文件的类 java.util.ResourceBundle。
1. 尝试加载指定的本地化资源文件（xxx_en_US.properties）。
2. 尝试加载本地系统默认的资源文件（xxx_zh_CN.properties）。
3. 尝试加载默认的资源文件（xxx.properties） 。
4. 以上资源文件都不存在，则抛出 java.util.MissingResourceException。
--------------------------------------------------------------------------------------------------------------------------
Control 方法分析
1. public List<String> getFormats(String baseName);

    默认java实现了java.class和java.properties加载bundle的方式
    java.class方式为根据bundleName加载的资源为ResourceBundle的子类，使用类加载器加载该类并创建该类的无参对象
    java.properties方式为为bundleName追加.properties后缀加载文件的方式实现









