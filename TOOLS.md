面向对象特性/常用工具/算法与数据结构/虚拟机





# compile-processor

自定义一个 processor，在编译 Java Class 时可以用来检测代码语法

`javac -processor`

```java
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.Set;

/*
自定义一个 processor，在编译 Java Class 时可以用来检测代码语法

测试步骤:
1.编译检测编译类：
javac -encoding UTF-8 NameCheckProcessor.java
2.使用代码检测插件编译目标类：
javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    private NameCheckScanner nameChecker;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameCheckScanner(processingEnv.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.scan(element);
            }
        }
        return false;
    }
}
```



```java
import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.util.ElementScanner6;

import static javax.lang.model.element.ElementKind.METHOD;
import static javax.tools.Diagnostic.Kind.WARNING;

public class NameCheckScanner extends ElementScanner6<Void, Void> {
    private final Messager messager;

    public NameCheckScanner(Messager messager) {
        this.messager = messager;
    }

    // 检查方法命名
    @Override
    public Void visitExecutable(ExecutableElement e, Void p) {
        if (e.getKind() == METHOD) {
            Name name = e.getSimpleName();
            if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                messager.printMessage(WARNING, "一个普通方法 “" + name + "”不应当与类名重复", e);
            }
        }
        super.visitExecutable(e, p);
        return null;
    }

}

```



```java
// 被检测的类
public class BADLY_NAMED_CODE {
    // 方法名与类名相同，会被 processor 检测
    protected void BADLY_NAMED_CODE() {
        return;
    }
}
```



测试结果

```
BADLY_NAMED_CODE.java:4: 警告: 一个普通方法 “BADLY_NAMED_CODE”不应当与类名重复
    protected void BADLY_NAMED_CODE() {
                   ^
1 个警告

```



# aspectj

maven denpencies

```xml
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
        <version>1.8.9</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.8.9</version>
    </dependency>
</dependencies>
```

maven plugin

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>aspectj-maven-plugin</artifactId>
    <version>1.7</version>
    <configuration>
        <complianceLevel>1.8</complianceLevel>
        <source>1.8</source>
        <target>1.8</target>
        <showWeaveInfo>true</showWeaveInfo>
        <verbose>true</verbose>
        <Xlint>ignore</Xlint>
        <encoding>UTF-8 </encoding>
    </configuration>
    <executions>
        <execution>
            <goals>
                <!-- use this goal to weave all your main classes -->
                <goal>compile</goal>
                <!-- use this goal to weave all your test classes -->
                <goal>test-compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

target object

```java
public class Account {

    public void withdraw() {
        System.out.println("withdraw");
    }
}

```

aspect

```java
public aspect AccountAspect {

    // pointcut name: custom defined
    // call(): match target method
    pointcut callWithDraw123(Account account): call(void withdraw()) && target(account);

    // before() ??
    // advice works if match pointcut
    before(Account account): callWithDraw123(account) {
        System.out.println("before");
    }
}
```

test aspect

```java
public class AccountTest {
    @Test
    public void test1() {
        new Account().withdraw();
    }
}
```



# cglib

maven dependency

```
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.2.5</version>
</dependency>
```

proxy target object

```java
public class TargetObject{
    public void request() {
        System.out.println("do request");
    }
}
```

cglib interceptor

```java
public class MyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before");
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("after");
        return object;
    }
}
```



# encrypt

对称加密/非对称加密



```java
package com.example;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

// 非对称加密 RSA，私钥加密，公钥解密
public class AsymmetricEncryptionTest {

    @Test
    public void rsaTest() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String input = "2121212sdfssa";

        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);
        PublicKey publicKey = keyFactory.generatePublic(publicSpec);

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptBytes = cipher.doFinal(input.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(input, decryptStr);
    }

}

```



```java
package com.example;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

// 对称加密，加密和解密都是同一个密钥
public class SymmetricalEncryptionTest {

    // DES 对称加密
    @Test
    public void desTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "njkasdgh";
        String algorithm = "DES";
        String transformation = "DES";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    // AES 对称加密
    @Test
    public void aesTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "NKvVyDwnIKSDRABpR7NO9w==";
        // 算法
        String algorithm = "AES";
        String transformation = "AES";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    // AES-128-ECB 加密
    @Test
    public void aes128ecbTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "jkl;POIU1234++==";
        // 算法
        String algorithm = "AES";
        // 算法/模式/补码方式
        String transformation = "AES/ECB/PKCS5Padding";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    /**
     * AES-128-CBC 加密
     * CBC模式，需要一个向量iv，可增加加密算法的强度
     */
    @Test
    public void aes128cbcTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 算法
        String algorithm = "AES";
        // 算法/模式/补码方式
        String transformation = "AES/CBC/PKCS5Padding";
        // 密钥
        String key = "xl9YTgGf6jUk1EZE9Wohcg==";
        // 向量
        String iv = "IZ0Cl5x7MZba3DdG";
        // 要加密的数据
        String data = "{data:[{'name':'hi','age':20},{'name':'zd','age':18}]}";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(data, decryptStr);
    }

    // 生成AES DES 密钥
    @Test
    public void generateAesKeyTest() throws Exception {
        int length = 128;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 设置密钥长度
        keyGenerator.init(length);
        SecretKey key = keyGenerator.generateKey();
        byte[] bytes = key.getEncoded();
        String genKey = Base64.getEncoder().encodeToString(bytes);
        Assert.assertNotNull(genKey);
    }

}

```





# jvmlab



测试方法栈溢出

```java
private int stackLength = 1;

// 方法递归不断压栈
public void stackLeak() {
  stackLength++;
  stackLeak();
}
```



不断创建线程导致内存溢出异常

```java
public void stackLeafByThread() {
  while (true) {
    new Thread(() -> dontStop()).start();
  }
}
```



不断创建新实例导致堆空间不足

```java
static class OOMObject{}
public static void main(String[] args) {
  List<OOMObject> list = new ArrayList<>();
  while (true) {
    list.add(new OOMObject());
  }
}
```



不断申请直接内存导致溢出

```java
Field unsafeField = Unsafe.class.getDeclaredFields()[0];
unsafeField.setAccessible(true);

Unsafe unsafe = (Unsafe) unsafeField.get(null);
while (true) {
  unsafe.allocateMemory(1024 * 1024);
}
```



# maven assembly



## 将文件打包到 jar

maven pom 引入插件

```xml
<project>
    ...
    <build>
        <plugins>
            <!-- 命令执行 mvn clean assembly:single -->
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <descriptors>
                        <!-- assembly 描述文件位置 -->
                        <descriptor>distribution.xml</descriptor>
                    </descriptors>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

assembly 描述文件指定哪些文件需要打包到 jar

```xml
<assembly xmlns="http://maven.apache.org/ASSEMBLY/2.1.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.1.0 http://maven.apache.org/xsd/assembly-2.1.0.xsd">
    <id>distribution</id>
    <formats>
        <format>jar</format>
    </formats>

    <!-- 文件打包到jar -->
    <files>
        <file>
            <source>README.txt</source>
            <outputDirectory>/libs</outputDirectory>
            <filtered>true</filtered>
        </file>
        <file>
            <source>LICENSE.txt</source>
        </file>
        <file>
            <source>NOTICE.txt</source>
            <filtered>false</filtered>
        </file>
    </files>

</assembly>
```

测试文件

README.txt

LICENSE.txt

NOTICE.txt



assembly 描述文件模糊匹配的文件打包到 jar

```xml
<assembly xmlns="http://maven.apache.org/ASSEMBLY/2.1.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.1.0 http://maven.apache.org/xsd/assembly-2.1.0.xsd">
    <id>distribution</id>
    <formats>
        <format>jar</format>
    </formats>
    
    <!-- *.txt 扩展名的文件打包到 jar，但是不包括 README.txt和NOTICE.txt -->
    <fileSets>
        <fileSet>
            <directory>${basedir}</directory>
            <includes>
                <include>*.txt</include>
            </includes>
            <excludes>
                <exclude>README.txt</exclude>
                <exclude>NOTICE.txt</exclude>
            </excludes>
        </fileSet>
    </fileSets>
</assembly>
```



## 将依赖打包到指定的文件夹

maven 测试依赖，打包插件 assembly

```xml
<dependencies>
    <!-- 打包测试用 -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.0</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- 命令执行 mvn clean assembly:single -->
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
                <descriptors>
                    <descriptor>distribution.xml</descriptor>
                </descriptors>
            </configuration>
        </plugin>
    </plugins>
</build>
```

assembly 描述文件，将依赖打包到指定的文件夹

```xml
<assembly xmlns="http://maven.apache.org/ASSEMBLY/2.1.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.1.0 http://maven.apache.org/xsd/assembly-2.1.0.xsd">
    <id>distribution</id>
    <formats>
        <!-- 输出到文件夹 -->
        <format>dir</format>
    </formats>

    <!-- 将依赖打包到指定的文件夹 -->
    <dependencySets>
        <dependencySet>
            <useProjectArtifact>false</useProjectArtifact>
            <outputDirectory>/libs</outputDirectory>
            <scope>runtime</scope>
        </dependencySet>
    </dependencySets>
</assembly>
```

测试类生成 jar

```java
package org.cat;

public class App {
    public static void main(String[] args) {
        System.out.println(1);
    }
}
```



## 将依赖打包到 jar

maven 依赖测试用，assembly 把依赖打包到 jar，定义可执行 jar 入口

```xml
<dependencies>
    <!-- 打包测试用 -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.0</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- 打包依赖到jar-->
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
                <!-- 打包可执行应用指定入口 -->
                <!-- java -jar target/assembly-jar-with-dep-1.0-SNAPSHOT-jar-with-dependencies.jar -->
                <archive>
                    <manifest>
                        <mainClass>com.cat.Application</mainClass>
                    </manifest>
                </archive>
            </configuration>
            <executions>
                <execution>
                    <!-- 名字任意 -->
                    <id>make-assembly</id>
                    <!-- 绑定到package生命周期阶段上 -->
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

测试类

```java
package com.cat;

import com.google.gson.Gson;

public class Application {
    public static void main(String[] args) {
        System.out.println("参数："+args);
        new Gson();
    }
}
```

打包后执行可执行 jar

`java -jar target/assembly-jar-with-dep-1.0-SNAPSHOT-jar-with-dependencies.jar`



# Apache poi - Excel

maven dependencies

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>4.1.2</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>4.1.2</version>
</dependency>
```

 poi Excel Api usage

```java
public class ExcelAPITest {
    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("D:/test.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        //创建sheet
        Sheet sheet = workbook.createSheet("sheetName");
        //创建行
        Row row = sheet.createRow(0);
        //创建列
        Cell cell = row.createCell(1);
        //设置cell样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cell.setCellStyle(cellStyle);
        //设置cell内容
        cell.setCellValue("aa123");

        //设置字体
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Verdana");
//        font.setColor(HSSFFont.COLOR_RED);
        //自定义颜色
        java.awt.Color rgb = translate("#ff9900");
        XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());
        font.setColor(xssfColor);
        cellStyle.setFont(font);

        //设置背景颜色
//        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        cellStyle.setFillForegroundColor(xssfColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //边框样式
        cellStyle.setBorderBottom(BorderStyle.THIN);

        //设置列宽
        sheet.setColumnWidth(1, 10000);

        workbook.write(out);
        workbook.close();
        out.close();
    }

    public static java.awt.Color translate(String rgbStr) {
        if (rgbStr.startsWith("rgb")){
            Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher m = c.matcher(rgbStr);

            if (m.matches()) {
                return new java.awt.Color(Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)));
            }
        } else if(rgbStr.startsWith("#")) {
            return Color.decode(rgbStr);
        }
        return null;
    }
}
```



# fastjson

maven 依赖

```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.70</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

fastjson API test

```java
public class FastJsonApiTest {
    @Data
    static class AlternateNamesObject {
        // 字段别名，json key abstract/summary1 可以映射到本字段
        @JSONField(alternateNames = {"abstract", "summary1"})
        private String summary;
    }

    @Test
    public void alternateNamesMappingTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abstract", "aaa");
        String jsonString = jsonObject.toJSONString();
        Assert.assertEquals("{\"abstract\":\"aaa\"}", jsonString);

        // json key 映射对象别名并设置到对象字段
        AlternateNamesObject result = JSON.parseObject(jsonString, AlternateNamesObject.class);
        Assert.assertEquals("aaa", result.getSummary());
    }

    @Test
    public void jsonFileTest() {
        @Data
        @AllArgsConstructor
        class JsonUser {
            // java object 转 json时，key 使用 name123 而不是 name
            @JSONField(name = "name123")
            private String name;
            // java object 转 json时，不包括这个字段
            @JSONField(serialize = false)
            private String tag;
        }

        JsonUser user = new JsonUser("Jamie", "C");
        String jsonString = JSON.toJSONString(user);
        Assert.assertEquals("{\"name123\":\"Jamie\"}", jsonString);
    }

    @Data
    static class User {
        private String name;
        private String age;

        @JSONField(format = "yyyy-MM-dd")
        private Date pubTime;

        public User() {
        }

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void jsonStaticMethodTest() {
        String jsonString = "{\"age\":\"100\",\"name\":\"tom\"}";
        String jsonArrayString = "[{\"name\":\"lily\",\"age\":12},{\"name\":\"lucy\",\"age\":15}]";

        //java object -> json string
        String userJsonStr = JSON.toJSONString(new User("tom", "100"));
        Assert.assertEquals(jsonString, userJsonStr);

        //json string -> json
        JSONObject userJson = JSON.parseObject(jsonString);
        Assert.assertEquals("100", userJson.getString("age"));
        Assert.assertEquals("tom", userJson.getString("name"));

        //json string -> java object
        User userObj = JSON.parseObject(jsonString, User.class);
        Assert.assertEquals("100", userObj.getAge());
        Assert.assertEquals("tom", userObj.getName());

        //string -> json array
        JSONArray jsonArray = JSON.parseArray(jsonArrayString);
        Assert.assertEquals(12, ((JSONObject) jsonArray.get(0)).get("age"));

        //json array -> string
        String jsonArrayStringResult = JSON.toJSONString(JSON.parseArray(jsonArrayString));
        Assert.assertEquals(jsonArrayString, jsonArrayStringResult);

        //json object -> java object
        User user = JSON.toJavaObject(JSON.parseObject(jsonString), User.class);
        Assert.assertEquals("tom", user.getName());
        Assert.assertEquals("100", user.getAge());

        //string to object(json array)
        Object arrayStr = JSON.parse("[\"a\",\"b\",\"c\"]");
        Assert.assertTrue(arrayStr instanceof JSONArray);

        Object jsonStr = JSON.parse("{\"k\" : \"v\"}");
        Assert.assertTrue(jsonStr instanceof JSONObject);
    }

    @Test
    public void jsonNonStaticMethodTest() {
        //map -> json object
        Map<String, Object> map = new HashMap<>();
        map.put("age", "24");
        map.put("name", "tim");
        JSONObject json = new JSONObject(map);
        Assert.assertEquals("24", json.getString("age"));
        Assert.assertEquals("tim", json.getString("name"));

        //json object -> string
        String jsonString = new JSONObject().toJSONString();
        Assert.assertEquals("{}", jsonString);

        //json object -> java object
        User user1 = json.toJavaObject(User.class);
        Assert.assertEquals("tim", user1.getName());
        Assert.assertEquals("24", user1.getAge());
    }

    @Test
    public void jsonFieldDateTest() throws ParseException {
        String jsonStr = "{\"pub_time\": \"2020-11-05\" }";
        // json 字符串日期 转 java object Date
        User user = JSON.parseObject(jsonStr, User.class);
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-05"), user.getPubTime());
    }

}
```



# freemarker

maven 依赖

```xml
<dependency>
    <groupId>freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.8</version>
</dependency>
```



## Function

```java
public class FunctionTest {
    @Test
    public void test5() throws IOException, TemplateException {
        String dir = "src/main/java";
        String file = "com/example/template/function.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate(file);

        StringWriter writer = new StringWriter();
        template.process(new HashMap<>(1), writer);
        System.out.println(writer.toString());
    }
}
```

function.ftl

```ftl
<#assign cname=r"特殊字符完成输出(http:\www.baidu.com)">
${cname}

<#assign cname2='<img class="k">hi.asd//http:\\www.baidu.com<img>'>
${cname2}


算数运算：
<#assign number1 = 10>
<#assign number2 = 5>
"+" : ${number1 + number2}
"－" : ${number1 - number2}
"*" : ${number1 * number2}
"/" : ${number1 / number2}
"%" : ${number1 % number2}

比较运算符：
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
    "*" : ${number1 * number2}
<#else>
    "/" : ${number1 / number2}
</#if>

内建函数：
<#assign data = "abcd1234">
<#assign data2 = "HiWorld">
第一个字母大写：${data?cap_first}
第一个字母小写：${data2?uncap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}
<#assign floatData = 12.34>
数值取整数：${floatData?int}


Map集合：
<#assign mapData={"name":"程序员", "salary":15000}>
name：${mapData["name"]}

通过Key遍历Map：
<#list mapData?keys as key>
    Key: ${key} - Value: ${mapData[key]}
</#list>

通过Value遍历Map：
<#list mapData?values as value>
    Value: ${value}
</#list>

List集合：
<#assign listData=["ITDragon", "blog", "is", "cool"]>
<#list listData as value>
    ${value}
</#list>

include指令：
引入其他文件：<#include "otherFreeMarker.ftl" />


命名空间：
<#import "otherFreeMarker.ftl" as otherFtl>
${otherFtl.otherName}
<@otherFtl.addMethod a=10 b=20 />
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值"/>
${otherFtl.otherName}
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值" in otherFtl />
${otherFtl.otherName}


<#assign x = ["red", 16, "blue", "cyan"]>
数组是否包含blue，包含返回yes，否则no
"blue": ${x?seq_contains("blue")?string("yes", "no")}

<#if x?seq_contains("blue")>
    has blueblueblue
</#if>


<#assign cat="mmm">
宏，类似函数
<#macro mo>
    ${cat}  111
</#macro>
使用宏
<@mo />

<#macro moArgs a b c>
    ${a+b+c}
</#macro>
<@moArgs a=1 b=2 c=3 />
```



## List

```java
public class ListTest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User implements Serializable {
        private static final long serialVersionUID = -3307269962764425802L;
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    @Test
    public void tt() throws IOException, TemplateException {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "tom"));
        users.add(new User(2, "tim"));
        users.add(new User(3, "to"));

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("users", users);

        String dir = "src/main/java";
        String file = "com/example/template/list.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate(file);

        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        Assert.assertEquals("3\n" +
                "        ListTest.User(id=1, name=tom)\n" +
                "        ListTest.User(id=2, name=tim)\n" +
                "        ListTest.User(id=3, name=to)\n", writer.toString());
    }
}
```

list.ftl

```ftl
<#--集合长度-->
${users?size}
<#--空判断和对象集合-->
<#if users??>
    <#list users as user >
        ${user}
    </#list>
<#else>
    ${user!"变量为空则给一个默认值"}
</#if>
```



## Map

```java
public class MapsTest {
    // map 遍历
    @Test
    public void tt() throws IOException, TemplateException {
        Map<String, List<String>> kindsMap = new HashMap<>();
        kindsMap.put("a", Arrays.asList("a1", "a2"));
        kindsMap.put("b", Arrays.asList("b1", "b2"));

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("kindsMap", kindsMap);
        dataMap.put("kindsMap3", new HashMap<>(1));

        String dir = "src/main/java";
        String file = "com/example/template/maps.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));

        Template template = conf.getTemplate(file);
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        Assert.assertEquals("map size 不大于0\n" +
                "\n" +
                "    a\n" +
                "        a1\n" +
                "        a2\n" +
                "    b\n" +
                "        b1\n" +
                "        b2\n", writer.toString());
    }
}
```

maps.ftl

```ftl
<#if kindsMap3?size gt 0>
map size 大于0
<#else>
map size 不大于0
</#if>

<#list kindsMap?keys as mKey>
    ${mKey}
    <#assign item = kindsMap[mKey]>
    <#list item as itemValue>
        ${itemValue}
    </#list>
</#list>
```



## String template

```java
public class StringTemplateTest {
    public static void main(String[] args) throws IOException, TemplateException {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", "tim");

        // load template
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate", "halo：${name}");
        Configuration conf = new Configuration();
        conf.setTemplateLoader(stringLoader);
        // get template and write to stream with data
        Template template = conf.getTemplate("myTemplate", "utf-8");
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        Assert.assertEquals("halo：tim", writer.toString());
    }
}
```



## String

```java
public class StringTest {
    public static void main(String[] args) throws ParseException {
        String dir = "src/main/java";
        String file = "com/example/template/string.ftl";
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("name", "jamie");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dataMap.put("dateTime", sdf.parse("2022-02-02"));

        try {
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File(dir));

            Template template = conf.getTemplate(file);
            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            String result = writer.toString();
            Assert.assertEquals("Hello jamie !\n" +
                    "Hello jamie !\n" +
                    "m\n" +
                    "jam\n" +
                    "2022-02-02", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

string.ftl

```ftl
<#--字符串输出-->
${"Hello ${name} !"}
${"Hello " + name + " !"}
<#--索引访问-->
${name[2]}
<#--截取字符串-->
${name[0..2]}
<#--时间格式化-->
${dateTime?string("yyyy-MM-dd")}
```



# jackson

maven 依赖

```xml
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.8.10</version>
</dependency>
```



jackson 转换

```java
public class ConvertTest {
	public static class Object01 {
		public String a;
	}
	// java 对象转 json string
	@Test
	public void javaObject2jsonStrTest() throws IOException {
		Object01 o = new Object01();
		o.a = "123";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"a\":\"123\"}", result);
	}
	// json string 转 java object
	@Test
	public void jsonStr2javaObjectTest() throws IOException {
		Object01 o = new ObjectMapper().readerFor(Object01.class).readValue("{\"a\":\"123\"}");
		Assert.assertEquals("123", o.a);
	}

	public static class Object02 {
		// object date 按格式转成 json string
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		public Date date;
	}
	@Test
	public void jsonDateFormatTest() throws JsonProcessingException, ParseException {
		Object02 o = new Object02();
		o.date = new Date(1419013800000L);
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"date\":\"19-12-2014\"}", result);
	}

	// 使用json key过滤器
	@JsonFilter("myFilter")
	public static class Object03 {
		public int id;
		public String name;
	}
	@Test
	public void filterJsonField() throws JsonProcessingException {
		Object03 o = new Object03();
		o.id = 1;
		o.name = "a";
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));

		String result = new ObjectMapper().writer(filters).writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}


	public static class Object31 {
		public int id;
		public String name;
		// mapping json key 的别名
		@JsonCreator
		public Object31(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
			this.id = id;
			this.name = name;
		}
	}
	@Test
	public void jsonStr2JavaObjectWithNameTest() throws IOException {
		Object31 o = new ObjectMapper().readerFor(Object31.class).readValue("{\"id\":1,\"theName\":\"a\"}");
		Assert.assertEquals(1, o.id);
		Assert.assertEquals("a", o.name);
	}

	public static class Object32 {
		// 注入字段到 json
		@JacksonInject
		public int id;
		public String name;
	}
	@Test
	public void jsonStr2JavaObjectWithInjectTest() throws IOException {
		InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
		Object32 o = new ObjectMapper().reader(inject).forType(Object32.class).readValue("{\"name\":\"a\"}");
		Assert.assertEquals(1, o.id);
		Assert.assertEquals("a", o.name);
	}

	public static class Object011 {
		private Map<String, String> properties;
		// 展开map k-v，不使用字段名
		@JsonAnyGetter
		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}
	}
	@Test
	public void flatObjectMapTest() throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		Object011 o = new Object011();
		o.setProperties(map);

		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"k1\":\"v1\",\"k2\":\"v2\"}", result);
	}

	// key 排序
	@JsonPropertyOrder({"name", "id"})
	public static class Object022 {
		public int id;
		public String name;
	}
	@Test
	public void fieldsOrderTest() throws JsonProcessingException {
		Object022 o = new Object022();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\",\"id\":1}", result);
	}

	public static class Object033 {
		// 字符串字段识别为 json
		@JsonRawValue
		public String json;
	}
	@Test
	public void rawJsonFieldTest() throws JsonProcessingException {
		Object033 o = new Object033();
		o.json = "{\"attr\":false}";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"json\":{\"attr\":false}}", result);
	}

	@JsonRootName(value = "rootName")
	public static class Object04 {
		public int id;
	}
	@Test
	public void jsonWithRootNameTest() throws JsonProcessingException {
		Object04 o = new Object04();
		o.id = 1;
		String result = new ObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE)
				.writeValueAsString(o);
		Assert.assertEquals("{\"rootName\":{\"id\":1}}", result);
	}

	// 声明 json string 不转换的 key
	@JsonIgnoreProperties({ "id" })
	public static class Object21 {
		public int id;
		public String name;
	}
	@Test
	public void ignoreObjectFieldsTest() throws JsonProcessingException {
		Object21 o = new Object21();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}

	public static class Object22 {
		// 声明的字段不会转换到 json string
		@JsonIgnore
		public int id;
		public String name;
	}
	@Test
	public void ignoreObjectField() throws JsonProcessingException {
		Object22 o = new Object22();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}

	// 只转换非空字段
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Object23 {
		public int id;
		public String name;
	}
	@Test
	public void hiddenNullField() throws JsonProcessingException {
		Object23 o = new Object23();
		o.id = 1;
		o.name = null;
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"id\":1}", result);
	}
}

```



自定义序列化和反序列化

```java
public class CustomDateDeserializer extends StdDeserializer<Date> {
	private static final long serialVersionUID = 1L;

	public CustomDateDeserializer() {
		this(null);
	}

	public CustomDateDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		String date = jsonparser.getText();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
```



```java
public class CustomDateSerializer extends StdSerializer<Date> {
	private static final long serialVersionUID = 1L;

	public CustomDateSerializer() {
		this(null);
	}

	public CustomDateSerializer(Class<Date> t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		gen.writeString(sdf.format(value));
	}
}
```



```java
public class SerializeTest {
    public static class Object33 {
        // 自定义的json反序列类，to object date 时转成 object date
        @JsonDeserialize(using = CustomDateDeserializer.class)
        public Date eventDate;
    }
    @Test
    public void deserializeTest() throws IOException {
        Object33 o = new ObjectMapper().readerFor(Object33.class)
                .readValue("{\"eventDate\":\"20-12-2014\"}");
        Assert.assertEquals(1419004800000L, o.eventDate.getTime());
    }

    public static class Object05 {
        // 自定义的序列化类，to json 时转成 format date
        @JsonSerialize(using = CustomDateSerializer.class)
        public Date eventDate;
    }
    @Test
    public void serializeTest() throws JsonProcessingException {
        Object05 o = new Object05();
        o.eventDate = new Date(1681394691967L);
        String result = new ObjectMapper().writeValueAsString(o);
        Assert.assertEquals("{\"eventDate\":\"13-04-2023\"}", result);
    }
}
```



