import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import pageobject.PageOrderLocators;

public class ScooterOrderTest {

    private WebDriver driver;

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOrderScooterDataSet1() {
        orderScooter("Иван", "Иванов", "ул. Пушкина, д. 10", 3, "+79001234567",
                "01.10.2024", "сутки", "black", "Тут что-то не так");
    }

    @Test
    public void testOrderScooterDataSet2() {
        orderScooter("Анна", "Смирнова", "пр. Ленина, д. 5",  2,"89112345678",
                "30.09.2024", "трое суток", "grey", "Тут всё ок");
    }

    private void orderScooter(String name, String surname, String address, int metro,
                              String phone, String deliveryDate, String rental,
                              String color, String comment) {

        //Нажимаем кнопку «Заказать» (сверху)
        WebElement orderButtonTop = driver.findElement(PageOrderLocators.orderButtonTop);
        orderButtonTop.click();

        //Заполняем форму заказа
        driver.findElement(PageOrderLocators.nameInput).sendKeys(name);
        driver.findElement(PageOrderLocators.surnameInput).sendKeys(surname);
        driver.findElement(PageOrderLocators.addressInput).sendKeys(address);
        driver.findElement(PageOrderLocators.phoneInput).sendKeys(phone);

        //Выбираем станцию метро
        //Кликаем по полю выбора станции
        WebElement metroStationField = driver.findElement(PageOrderLocators.metroInput);
        metroStationField.click();
        //Выбираем нужную станцию
        WebElement metroStationOption = driver.findElement(By.xpath("//button[@value = " + metro + "]"));
        metroStationOption.click();


        //Нажимаем кнопку "Далее"
        WebElement nextButton = driver.findElement(PageOrderLocators.nextButton);
        nextButton.click();


        //Заполняем вторую форму заказа
        driver.findElement(PageOrderLocators.datePicker).sendKeys(deliveryDate);
        //Нажимаем Enter чтобы закрыть окно календаря
        driver.findElement(PageOrderLocators.datePicker).sendKeys(Keys.ENTER);

        //Выбираем срок аренды
        //Кликаем по полю
        WebElement rentalField = driver.findElement(PageOrderLocators.rentalPeriod);
        rentalField.click();

        //Выбираем сколько дней
        WebElement rentalFieldOption = driver.findElement(By.xpath(String.format("//div[@class = 'Dropdown-menu']/div[text() = '%s']", rental)));
        rentalFieldOption.click();

        //Выбор цвета самоката
        WebElement colorScooterChoise = driver.findElement(By.xpath(String.format("//input[@id = '%s']", color)));
        //WebElement colorScooterChoise = driver.findElement(By.xpath("//input[@id = "+ color +"]"));
        colorScooterChoise.click();

        //Напишем коммент
        driver.findElement(PageOrderLocators.commentInput).sendKeys(comment);

        //Нажимаем на кнопку Заказать
        WebElement orderButtonClick = driver.findElement(PageOrderLocators.orderButton);
        orderButtonClick.click();

        // Ожидание, пока не появится окно с текстом "Хотите оформить заказ?"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement orderDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Хотите оформить заказ?']")));
        } finally {

        }

        //Нажимаем кнопку «Подтвердить заказ»
        WebElement confirmOrderButtonYes = driver.findElement(PageOrderLocators.confirmOrderButton);
        confirmOrderButtonYes.click();

        // Проверяем, что появилось окно Заказ оформлен
        WebElement successPopup = driver.findElement(PageOrderLocators.successOrderWindow);
        String expectedMessage = "Заказ оформлен";
        Assert.assertEquals(expectedMessage, successPopup.getText());
    }
}
