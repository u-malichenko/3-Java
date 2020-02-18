import Lesson_6.DZ_My.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestNewArray {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7,}, new int[]{1, 7,}, 4},
                {new int[]{1, 4, 3, 0}, new int[]{3, 0}, 4},
                {new int[]{4, 2, 3, 1}, new int[]{2, 3, 1}, 4},
                {new int[]{4, 2, 4, 1}, new int[]{4, 1}, 4} //разная длинна
        });
    }

    private TestArray testArray;
    private int[] startArray;
    private int[] result;
    private int num;

    public TestNewArray(int[] startArray, int[] result, int num) {
        this.startArray = startArray;
        this.result = result;
        this.num = num;
    }

    @Before
    public void init() {
        testArray = new TestArray();
    }

    @Test
    public void arrayTest() {
        Assert.assertArrayEquals(result, testArray.createArray(startArray,num));
    }
}
