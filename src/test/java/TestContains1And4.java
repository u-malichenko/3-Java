import Lesson_6.DZ_My.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestContains1And4 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 4, 4, 1, 1, 4, 1, 1,}},
                {new int[]{1, 4, 3, 0}},
                {new int[]{4, 4, 1, 1}},
                {new int[]{4, 4, 4, 4}},
                {new int[]{1, 1, 1, 1}}
        });
    }

    private TestArray testArray;
    private int[] startArray;
    private boolean result;

    public TestContains1And4(int[] startArray) {
        this.startArray = startArray;
    }

    @Before
    public void init() {
        testArray = new TestArray();
    }

    @Test
    public void arrayTest() {
        Assert.assertTrue(testArray.contains1And4(startArray));
    }
}
