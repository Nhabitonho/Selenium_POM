import org.testng.annotations.Test;

public class SeleniumProjectTest {
    @Test
    public void Test01() {
        System.out.println("This is a Test01");
        System.out.println(System.getProperty("abc"));
    }

    @Test
    public void Test02() {
        System.out.println("This is a Test02");
    }
}
