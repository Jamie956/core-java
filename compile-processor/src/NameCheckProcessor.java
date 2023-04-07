import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import java.util.Set;

import static javax.lang.model.element.ElementKind.*;
import static javax.tools.Diagnostic.Kind.WARNING;

// java 编译插件

// 测试步骤
//1.编译检测编译类：javac -encoding UTF-8 NameCheckProcessor.java
//2.使用代码检测插件编译目标类：javac -processor NameCheckProcessor -encoding UTF-8 BADLY_NAMED_CODE.java
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    private NameChecker nameChecker;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkNames(element);
            }
        }
        return false;
    }

    static class NameChecker {
        private final Messager messager;
        NameCheckScanner nameCheckScanner = new NameCheckScanner();

        NameChecker(ProcessingEnvironment processingEnv) {
            this.messager = processingEnv.getMessager();
        }

        public void checkNames(Element element) {
            nameCheckScanner.scan(element);
        }

        private class NameCheckScanner extends ElementScanner6<Void, Void> {
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
    }
}