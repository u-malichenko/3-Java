package Lesson_7.DZ_My;

public class TestClass {
    @Before
    public static void start() {
        System.out.println("start");
    }
    @Test
    public static void method1() {
        System.out.println("met1");
    }

    @Test(priority = 11)
    public static void method2() {
        System.out.println("met2");
    }

    @Test(priority = 12)
    public static void method3() {
        System.out.println("met3");
    }

    @Test(priority = 13)
    public static void method4() {
        System.out.println("met4");
    }

    @After
    public static void shutdown() {
        System.out.println("End");
    }
}