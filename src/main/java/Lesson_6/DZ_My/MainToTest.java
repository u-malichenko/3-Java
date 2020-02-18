package Lesson_6.DZ_My;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class MainToTest {
    private static final Logger logger = Logger.getLogger(Lesson_6.DZ_My.MainToTest.class.getName());

    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.ALL);

        Handler handler = new FileHandler("mylog.log", true);
        handler.setLevel(Level.ALL);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + "\t" + record.getMessage() + "\t" + record.getMillis() + "\n";
            }
        });

        logger.addHandler(handler);

        TestArray testArray = new TestArray();
        int[] startArray = {1, 4, 1, 1};
        //int[] startArray = {1, 2, 3, 4};
        int[] startArray1 = {1, 2, 4, 4, 2, 3, 4, 1, 7,};
        int[] resArray = testArray.createArray(startArray1,4);
        logger.log(Level.SEVERE, Arrays.toString(resArray));
        System.out.println(Arrays.toString(resArray));

        logger.log(Level.SEVERE, " " + testArray.contains1And4(startArray));
        System.out.println(testArray.contains1And4(startArray));
    }


}

