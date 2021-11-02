打包
mvn clean package

测试打包可执行应用指定入口
java -jar target/mvn-plugins-0.0.1-SNAPSHOT-jar-with-dependencies.jar

不指定入口时报错
target/mvn-plugins-0.0.1-SNAPSHOT.jar中没有主清单属性


