package com.revolut.server;

import com.revolut.annotations.RevolutApiPath;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Api object container.
 */
public class ApiObjectContainer {

    private static Map<String, String> methodNameVsClassName =
            new HashMap<>();
    private static Map<String, Method> methodNameVsMethod = new HashMap<>();
    private static Map<String, Object> classNameVsObject = new HashMap<>();

    /**
     * Gets method.
     *
     * @param methodName the method name
     * @return the method
     */
    public static Method getMethod(String methodName) {
        return methodNameVsMethod.get(methodName);
    }

    /**
     * Gets object.
     *
     * @param methodName the method name
     * @return the object
     */
    public static Object getObject(String methodName) {
        String className = methodNameVsClassName.get(methodName);
        return classNameVsObject.get(className);
    }

    /**
     * Instantiate api objects.
     */
    public static void instantiateAPIObjects() {
        Reflections reflections = new Reflections(
                "com.revolut.api", new MethodAnnotationsScanner()
        );
        Set<Method> collectedMethods =
                reflections.getMethodsAnnotatedWith(RevolutApiPath.class);
        for (Method method : collectedMethods) {
            Class<?> declaringClass = method.getDeclaringClass();
            String methodName = method.getName();
            String className = declaringClass.getName();
            if (!methodNameVsClassName.containsKey(methodName)) {
                methodNameVsClassName.put(methodName, className);
            }
            if (!classNameVsObject.containsKey(className)) {
                try {
                    classNameVsObject.put(className,
                            declaringClass.newInstance());
                } catch (InstantiationException e) {
                    throw new RuntimeException(e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            if (!methodNameVsMethod.containsKey(methodName)) {
                methodNameVsMethod.put(methodName, method);
            }
        }
    }
}