测试步骤

- 编译检测编译类的入口代码：javac -encoding UTF-8 NameCheckProcessor.java
- 使用代码检测插件检测测试代码：javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java