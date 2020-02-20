package Lesson_7.DZ_My;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.*;
import java.net.*;

public class TestDz {

    public static void main(String[] args) throws Exception {
        TestDz checkDZ = new TestDz();
        checkDZ.testDZ();
    }

    public void testDZ() throws Exception {
        Class ch = URLClassLoader.newInstance(new URL[]{
                new File("C:\\12345").toURL()}).loadClass("Main");

        Constructor constructor = ch.getConstructor();
        Object dz = constructor.newInstance();

        Method method1 = ch.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
        method1.setAccessible(true);
        int res1 = (int) method1.invoke(dz, 2, 2, 2, 2);
        System.out.println("calculate " + res1 + (res1 == 6 ? " test7 ok": " test7 bad"));

        Method method2 = ch.getDeclaredMethod("calculate", float.class, float.class, float.class, float.class);
        method2.setAccessible(true);
        float res2 = (float) method2.invoke(dz, 2, 2, 2, 2);
        System.out.println("calculate " + res2 + (res2 == 6.0 ? " test7 ok": " test7 bad"));

        Method method3 = ch.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
        method3.setAccessible(true);
        boolean res3 = (boolean)method3.invoke(dz, 12, 2);
        System.out.println("checkTwoNumbers " + res3+ (res3 ? " test7 ok": " test7 bad"));

        Method method4 = ch.getDeclaredMethod("printIsPositive", int.class);
        method4.setAccessible(true);
        String res4 = (String)method4.invoke(dz, 12);
        System.out.println("printIsPositive " + res4 + (res4 == null ? " test7 ok": " test7 bad"));

        Method method5 = ch.getDeclaredMethod("isNegative", int.class);
        method5.setAccessible(true);
        boolean res5 = (boolean)method5.invoke(dz, 12);
        System.out.println("isNegative " + res5 + (!res5 ? " test7 ok": " test7 bad"));

        Method method6 = ch.getDeclaredMethod("printWelocome", String.class);
        method6.setAccessible(true);
        String res6 = (String)method6.invoke(dz, "Artem");
        System.out.println("printWelocome " + res6 + (res6 == null ? " test7 ok": " test7 bad"));

        Method method7 = ch.getDeclaredMethod("isLeapYear", int.class);
        method7.setAccessible(true);
        boolean res7 =(boolean)method7.invoke(dz, 1222);
        System.out.println("isLeapYear 1222 " + res7 + (!res7 ? " test7 ok": " test7 bad"));

    }
}