import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import java.util.EnumSet;
import java.util.Set;

import static javax.lang.model.element.ElementKind.*;
import static javax.lang.model.element.ElementKind.FIELD;
import static javax.lang.model.element.Modifier.*;
import static javax.tools.Diagnostic.Kind.WARNING;

/**
 * 编译 Class 时作为插件检查 Java 代码
 */
// 可以用"*"表示支持所有Annotations
@SupportedAnnotationTypes("*")
// 只支持JDK 6的Java代码
@SupportedSourceVersion(SourceVersion.RELEASE_6)
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

    /**
     * 检查代码规范
     */
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
            /**
             * 检查Java类
             */
            @Override
            public Void visitType(TypeElement e, Void p) {
                scan(e.getTypeParameters(), p);
                checkCamelCase(e, true);
                super.visitType(e, p);
                return null;
            }

            /**
             * 检查方法命名
             */
            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                if (e.getKind() == METHOD) {
                    Name name = e.getSimpleName();
                    if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                        messager.printMessage(WARNING, "一个普通方法 “" + name + "”不应当与类名重复", e);
                    }
                    checkCamelCase(e, false);
                }
                super.visitExecutable(e, p);
                return null;
            }

            /**
             * 检查变量命名
             */
            @Override
            public Void visitVariable(VariableElement e, Void p) {
                if (e.getKind() == ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                    checkAllCaps(e);
                } else {
                    checkCamelCase(e, false);
                }
                return null;
            }

            /**
             * 检查第一个字母必须是大写的英文字母
             */
            private void checkAllCaps(Element e) {
                String name = e.getSimpleName().toString();
                boolean conventional = true;
                int firstCodePoint = name.codePointAt(0);
                if (!Character.isUpperCase(firstCodePoint)){
                    conventional = false;
                } else {
                    boolean previousUnderscore = false;
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (cp == (int) '_') {
                            if (previousUnderscore) {
                                conventional = false;
                                break;
                            }
                            previousUnderscore = true;
                        } else {
                            previousUnderscore = false;
                            if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                                conventional = false;
                                break;
                            }
                        }
                    }
                }
                if (!conventional) {
                    messager.printMessage(WARNING, "常量“" + name + "”应当全部以大写字母或下划线命名， 并且以字母开头", e);
                }
            }

            /**
             * 判断一个变量是否是常量
             */
            private boolean heuristicallyConstant(VariableElement e) {
                if (e.getEnclosingElement().getKind() == INTERFACE) {
                    return true;
                }
                else if (e.getKind() == FIELD && e.getModifiers().containsAll(EnumSet.of(PUBLIC, STATIC, FINAL))) {
                    return true;
                }
                else {
                    return false;
                }
            }

            /**
             * 检查传入的Element是否符合驼式命名法
             */
            private void checkCamelCase(Element e, boolean initialCaps) {
                String name = e.getSimpleName().toString();
                boolean previousUpper = false;
                boolean conventional = true;
                int firstCodePoint = name.codePointAt(0);
                if (Character.isUpperCase(firstCodePoint)) {
                    previousUpper = true;
                    if (!initialCaps) {
                        messager.printMessage(WARNING, "名称“" + name + "”应当以小写字母开头", e);
                        return;
                    }
                } else if (Character.isLowerCase(firstCodePoint)) {
                    if (initialCaps) {
                        messager.printMessage(WARNING, "名称“" + name + "”应当以大写字母开头", e);
                        return;
                    }
                } else {
                    conventional = false;
                }
                if (conventional) {
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (Character.isUpperCase(cp)) {
                            if (previousUpper) {
                                conventional = false;
                                break;
                            }
                            previousUpper = true;
                        } else {
                            previousUpper = false;
                        }
                    }
                }
                if (!conventional) {
                    messager.printMessage(WARNING, "名称“" + name + "”应当符合驼式命名法（Camel Case Names） ", e);
                }
            }
        }
    }
}