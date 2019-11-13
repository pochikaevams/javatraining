package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
    }

    public void gotoNewContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//tr[.//*[@id='" + id + "']]//*[@title='Edit']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmAlert() {
        wd.switchTo().alert().accept();
    }

    public void editContact() {
        click(By.cssSelector("img[title=\"Edit\"]"));
    }

    public void confirmUpdateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        gotoNewContactPage();
        fillContactForm(contact);
        submitContactCreation();
        click(By.linkText("home"));
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        confirmUpdateContact();
        goToMainPage();
    }

    public void delete(ContactData contact) {
        goToMainPage();
        selectContactById(contact.getId());
        deleteSelectedContact();
        confirmAlert();
    }

    private void goToMainPage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }


    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("img[title=\"Edit\"]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }
}
