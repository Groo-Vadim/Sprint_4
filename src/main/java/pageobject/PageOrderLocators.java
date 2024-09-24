package pageobject;

import org.openqa.selenium.By;


public class PageOrderLocators {

    //Локаторы для первого экрана заказа

    //Кнопка Заказать сверху
    public static By orderButtonTop = By.xpath("//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");
    //Кнопка Заказать снизу
    public static By orderButtonBottom = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Поле Имя
    public static By nameInput = By.xpath("//input[@placeholder = '* Имя']");
    //Поле Фамилия
    public static By surnameInput = By.xpath("//input[@placeholder = '* Фамилия']");
    //Поле Адрес
    public static By addressInput =  By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Поле Станция метро
    public static By metroInput = By.xpath("//input[@placeholder = '* Станция метро']");
    //Поле телефон
    public static By phoneInput = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    public static By nextButton = By.xpath("//button[text()='Далее']");


    //Локаторы для второго экрана заказа

    //Поле Когда привезти самокат
    public static By datePicker  = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    //Поле Срок Аренды
    public static By rentalPeriod = By.xpath("//div[@class = 'Dropdown-root']");//div[@class = 'Dropdown-placeholder' and text() = '* Срок аренды']");
    //Поле Цвет самоката Черный жемчуг
    public static By CheckboxLabelBlack = By.xpath("//input[@id = 'black']");
    //Поле Цвет самоката Серая безысходность
    public static By CheckboxLabelGray = By.xpath("//input[@id = 'gray']");
    //Поле Комментарий для курьера
    public static By commentInput = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    //Кнопка Заказать в форме заказа
    public static By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Окно подтверждения заказа с кнопкой ДА
    public static By confirmOrderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()= 'Да']");
    //Окно Заказ оформлен
    public static By successOrderWindow = By.xpath("//div[@class = 'Order_ModalHeader__3FDaJ' and text() = 'Заказ оформлен']");
}