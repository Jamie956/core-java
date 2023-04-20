import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.util.ElementScanner6;

import static javax.lang.model.element.ElementKind.METHOD;
import static javax.tools.Diagnostic.Kind.WARNING;

public class NameCheckScanner extends ElementScanner6<Void, Void> {
    private Messager messager;

    public void setMessager(Messager messager) {
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
