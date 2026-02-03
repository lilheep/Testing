import org.testng.annotations.Test;

import static org.example.Main.sum;

public class MainTest {
    @Test
    public void test1() {
        int a = 2;
        int b = 2;
        int exceptedResult = sum(2, 2);
        assert a + b == exceptedResult;
    }
}
