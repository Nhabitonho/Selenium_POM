import org.testng.annotations.Test;
public class DependsOnTest
{
    @Test(dependsOnMethods = {"SignIn"}, priority = 1)
    public void LogOut() {
        System.out.println("Logging Out");
    }

    @Test(dependsOnMethods = {"SignIn"}, priority = 0)
    public void Mess() {
        System.out.println("Hello");
    }

    @Test(dependsOnMethods = {"OpenBrowser"})
    public void SignIn() {
        System.out.println("Signing In");
    }
    @Test
    public void OpenBrowser() {
        System.out.println("Opening The Browser");
    }

}