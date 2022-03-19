/**
 * 测试命令
 * javac -encoding UTF-8 NameChecker.java
 * javac -encoding UTF-8 NameCheckProcessor.java
 * javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java
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