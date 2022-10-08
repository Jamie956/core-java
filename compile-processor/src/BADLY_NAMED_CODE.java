/**
 * 测试步骤
 * 编译检测编译类的核心代码：javac -encoding UTF-8 NameChecker.java
 * 编译检测编译类的入口代码：javac -encoding UTF-8 NameCheckProcessor.java
 * 使用代码检测插件检测测试代码：javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java
 */
public class BADLY_NAMED_CODE {
    enum colors {
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;
    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }
}