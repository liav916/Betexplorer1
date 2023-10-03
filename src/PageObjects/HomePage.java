package PageObjects;


import org.openqa.selenium.*;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {super(driver);}

    By IAcceptButton = By.cssSelector("[id='onetrust-accept-btn-handler']");
    By ShowAllCountries = By.cssSelector("[class='button js-toggle-countrymenu closed']");
    By showAllResults = By.cssSelector(".wrap-section__header__link");

    public void clickOniAcceptButtonAndShowAllCountries (){
        scrollTobYElementAndClick(IAcceptButton);
        scrollTobYElementAndClick(ShowAllCountries);
    }
    public void ClickOnAllCountries() throws InterruptedException {
        List<WebElement> countries;
        countries = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));
        System.out.println("Country:size " + countries.size());

        for (int i = 0; i < countries.size(); i++) {
           // String country = countries.get(i).getText().toLowerCase();
            WebElement countryElement =countries.get(i);
            scrollToElementAndClick(countryElement);
            clickOnFirstLeagueName();
            scrollTobYElementAndClick(showAllResults);

        }
    }
    public void clickOnFirstLeagueName ( ) throws InterruptedException {
    List<WebElement> leauge= driver.findElements(By.cssSelector(".box-overflow tbody"));
        WebElement table = leauge.get(0);
        System.out.println(table.getText());
if (leauge.size()==71) {
    table =  leauge.get(0);
    List<WebElement> leagueList =table.findElements(By.cssSelector("tr td a"));
    WebElement leagueName = leagueList.get(0);
    System.out.println(leagueList.size());
    leagueName.click();

}
else if (leauge.size()==72){
   table =  leauge.get(1);
   List<WebElement> leagueList =table.findElements(By.cssSelector("tr td a"));
   WebElement leagueName = leagueList.get(0);
   System.out.println(leagueList.size());
   leagueName.click();
}
    }
    public void getAllText() {
        List<WebElement> Teams;
        Teams = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));


    }












    }