package agent;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Author: sazal
 * Date: 10/1/18
 */
public class MainEntryTransformer implements ClassFileTransformer {

    private ClassPool classPool;

    MainEntryTransformer() {
        this.classPool = ClassPool.getDefault();
    }

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        try {
            if (className.equals("entry/Entry")) {
                classPool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
                CtClass cclass = classPool.getCtClass(className.replace("/", "."));
                if (!cclass.isFrozen()) {
                    for (CtMethod method : cclass.getDeclaredMethods()) {
                        if (method.getName().equalsIgnoreCase("main")) {
                            method.insertBefore("System.out.println(\"Instrumented by " + MainEntryTransformer.class.getSimpleName() + "\");");
                        }
                    }
                }
                return cclass.toBytecode();
            }
        } catch (Exception e) {
            //ignore
        }
        return classfileBuffer;
    }
}
