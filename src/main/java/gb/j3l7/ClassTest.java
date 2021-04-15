package gb.j3l7;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ClassTest {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This method testing first");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This method testing last");
    }

    @Test(priority = 1)
    public void Test1() {
        System.out.println("Test 1");
    }

    @Test(priority = 10)
    public void Test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 4)
    public void Test3() {
        System.out.println("Test 3");
    }

    @Test(priority = 4)
    public void Test4() {
        System.out.println("Test 4");
    }
}
