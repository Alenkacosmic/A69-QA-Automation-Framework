import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "invalidLoginData")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"elena.ioksha@testpro.io","werty456"},
                {"elena.ioksha@testpro.io",""},
                {"vasyaPupkin@testpro.do","e1XeRcG9"},
                {"","e1XeRcG9"},
                {"",""}
        };
    }
}
