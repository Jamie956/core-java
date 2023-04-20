import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.Set;

// java 编译插件

// 测试步骤
//1.编译检测编译类：javac -encoding UTF-8 NameCheckProcessor.java
//2.使用代码检测插件编译目标类：javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    private NameCheckScanner nameChecker;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameCheckScanner();
        nameChecker.setMessager(processingEnv.getMessager());
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