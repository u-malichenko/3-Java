import Lesson_6.DZ_My.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRuntimeException {
    private TestArray testArray;

    @Before
    public void init() {
        testArray = new TestArray();
    }

    @Test(expected = RuntimeException.class)
    public void arrayTest() {
        Assert.assertArrayEquals(new int[]{3, 1}, testArray.createArray(new int[]{2, 2, 3, 1}, 4));
    }

    @Test(expected = RuntimeException.class) //ошибка отутствия ошибки
    public void arrayTestNotEx() {
        Assert.assertArrayEquals(new int[]{3, 1}, testArray.createArray(new int[]{2, 4, 3, 1}, 4));
    }
}