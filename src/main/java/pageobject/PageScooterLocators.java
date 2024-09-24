package pageobject;

import org.openqa.selenium.By;

public class PageScooterLocators {
    //Локатор для стрелочки (вопроса) по тексту
    public By dropdownToggle(String questionText) {
        return By.xpath("//div[text()='" + questionText + "']");
    }

    //Локатор для текста ответа по тексту вопроса
    public By answerText(String expectedText) {
        return By.xpath("//p[text()='" + expectedText + "']");
    }
}