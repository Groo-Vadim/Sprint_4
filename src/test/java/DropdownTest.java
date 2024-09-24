import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import org.openqa.selenium.By;
import pageobject.PageScooterLocators;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DropdownTest {
    private WebDriver driver;
    private PageScooterLocators locators;

    private String questionText;
    private String expectedText;

    public DropdownTest(String questionText, String expectedText) {
        this.questionText = questionText;
        this.expectedText = expectedText;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        locators = new PageScooterLocators();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                { "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                { "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
        });
    }

    @Test
    public void testDropdown() {
        //Открытие страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //Явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Принимаем куки
        driver.findElement(By.xpath("//button[text() = 'да все привыкли']")).click();

        //Находим элемент FAQ
        WebElement faqSection = driver.findElement(By.xpath("//div[text()='Вопросы о важном']"));

        //Прокрутка до элемента FAQ
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);

        //Нажимаем на стрелочку, чтобы открыть вопрос
        WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(locators.dropdownToggle(questionText)));
        dropdownToggle.click();

        //Отображается ли соответствующий текст
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locators.answerText(expectedText)));

        //Проверяем текст
        String actualText = answerElement.getText();

        assertTrue("Текст неверен. Ожидалось: " + expectedText + ", но отображается: " + actualText,
                actualText.contains(expectedText));
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}