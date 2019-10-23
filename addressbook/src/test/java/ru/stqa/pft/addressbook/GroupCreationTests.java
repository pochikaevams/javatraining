package ru.stqa.pft.addressbook;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class GroupCreationTests {

    ChromeDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        login("admin");
    }

    private void login(String username) {
        wd.findElement(By.name("user")).click();
    }

}
