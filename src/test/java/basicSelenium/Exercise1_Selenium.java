package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;

public class Exercise1_Selenium {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://todo.ly/");
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    @Test
    public void verifyCRUDProject() throws InterruptedException {

        // login
        driver.findElement(By.xpath("//img[contains(@src,'pagelogin')]")).click();
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("nel@gmail.com");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).isDisplayed()
                ,"ERROR login was incorrect");

        // create
        String nameProject="Nelson-project"+new Date().getTime();
        driver.findElement(By.xpath("//td[text()='Add New Project']")).click();
        driver.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        driver.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(1000);
        int actualResult=driver.findElements(By.xpath(" //td[text()='"+nameProject+"'] ")).size();
        Assertions.assertTrue(actualResult >= 1
                ,"ERROR The project was not created");

        nameProject="Update"+new Date().getTime();

        // create task
        String nameUpdated = "tarea1";
        driver.findElement(By.id("NewItemContentInput")).click();
        driver.findElement(By.id("NewItemContentInput")).sendKeys(nameUpdated);
        driver.findElement(By.id("NewItemAddButton")).click();

        Thread.sleep(1000);

        // Update task
        //driver.findElement(By.xpath("//td/div[text()='tarea1']")).click(); // it works

        //driver.findElement(By.xpath("//td/div/img[contains(@style,'inline')]")).click();

        //driver.findElement(By.xpath("//td/div[@class='UnderEditingItem'}")).clear();
        //driver.findElement(By.xpath("//td/div[text()='tarea1']")).sendKeys("tarea editada",Keys.ENTER);
        //driver.findElement(By.id("ItemId_11079457")).sendKeys("Nelson's task updated", Keys.ENTER);


        Thread.sleep(5000);

    }
}
//aria/Options[role="img"]