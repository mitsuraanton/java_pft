package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created by Anton.Mitsura on 03.06.2016.
 */
public class ChangePassword extends TestBase {
    WebDriver wd;

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void changePassword() throws IOException, MessagingException {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/mantisbt-1.2.19");
        wd.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[5]/td[2]/input")).click();
        wd.findElement(By.name("username")).sendKeys("administrator");
        wd.findElement(By.name("password")).sendKeys("root");
        wd.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[6]/td/input")).click();
        wd.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[7]")).click();
        wd.findElement(By.xpath("/html/body/div[2]/p/span[1]/a")).click();
        wd.findElement(By.xpath("/html/body/table[3]/tbody/tr[11]/td[1]/a")).click();
        String user = wd.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[2]/td[2]/input")).getAttribute("value");
        String email = wd.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[4]/td[2]/input")).getAttribute("value");
        String password = "New Password";
        wd.findElement(By.xpath("/html/body/div[4]/form[1]/input[3]")).click();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }





}
