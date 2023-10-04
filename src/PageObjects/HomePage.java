package PageObjects;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }
    By IAcceptButton = By.cssSelector("[id='onetrust-accept-btn-handler']");
    By ShowAllCountries = By.cssSelector("[class='button js-toggle-countrymenu closed']");
    By showAllResults = By.cssSelector(".wrap-section_header_link");
    By clickOverUnder = By.cssSelector("[title=\"Over/Under\"]");
    By clickBothTeamsScore = By.cssSelector("[title=\"Both Teams To Score\"]");
By BystatsPage = By.cssSelector("section[class=\"wrap-section\"]");

    By BygroupNames = By.cssSelector("[class=\"wrap-section__header\"]");
    By ByDate = By.cssSelector("[class=\"list-details_item_date\"]");
    By ByScore = By.cssSelector("[class=\"list-details_item_score\"]");
    By ByLastScore = By.cssSelector("[class=\"list-details_item_partial\"]");

    By ByOdds = By.cssSelector("[id=\"match-add-to-selection\"]");





    By clickOnTabLeagueName = By.cssSelector("[href=\"/football/england/premier-league/\"]");
    //  By statsPage = By.cssSelector("section[class=\"wrap-section\"]");
    // By getListOfGamesBanner = By.cssSelector("tbody tr [colspan=\"6\"]");
    public void clickOniAcceptButtonAndShowAllCountries() {
        scrollTobYElementAndClick(IAcceptButton);
        scrollTobYElementAndClick(ShowAllCountries);
    }

    public void ClickOnAllCountries() throws InterruptedException {
        List<WebElement> countries;
        countries = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));
        for (int i = 0; i < countries.size(); i++) {
            // String country = countries.get(i).getText().toLowerCase();
            WebElement countryElement = countries.get(i);
            scrollToElementAndClick(countryElement);
            clickOnFirstLeagueName();
            scrollTobYElementAndClick(showAllResults);
            getAllGames();


        }
    }

    public void clickOnFirstLeagueName() throws InterruptedException {
        List<WebElement> leauge = driver.findElements(By.cssSelector(".box-overflow tbody"));
        WebElement table;
        if (leauge.size() == 71) {
            table = leauge.get(0);
            List<WebElement> leagueList = table.findElements(By.cssSelector("tr td a"));
            WebElement leagueName = leagueList.get(0);
            leagueName.click();

        } else if (leauge.size() == 72) {
            table = leauge.get(1);
            List<WebElement> leagueList = table.findElements(By.cssSelector("tr td a"));
            WebElement leagueName = leagueList.get(0);
            System.out.println(leagueList.size());
            leagueName.click();
        }
    }


    public void getAllGames() throws InterruptedException {
        List<WebElement> listOfGames = driver.findElements(By.cssSelector("td[class=\"h-text-left\"] a"));
        System.out.println("Amount of games: " + listOfGames.size());
        for (int i = 0; i < listOfGames.size(); i++){
            listOfGames = driver.findElements(By.cssSelector("td[class=\"h-text-left\"] a"));
            Thread.sleep(3000);
            System.out.println(listOfGames.get(i).getText());
            listOfGames.get(i).click();
            getAllInfoInsideGame();

        }
    }
public String GetText(By by){
        waitForElement(by);
    WebElement statsPage = driver.findElement(BystatsPage);
    WebElement Text = statsPage.findElement(by);
    String GenralInfo = Text.getText();
    String TeamsNames = GenralInfo.replace("Stats", "").trim();
    return  TeamsNames;
}
    public void getAllInfoInsideGame() throws InterruptedException {
        List<WebElement> scorers = driver.findElements(By.cssSelector("li [class=\"table-main\"]"));
        String TeamsNames = GetText(BygroupNames);
         String Date = GetText(ByDate);
        String Score = GetText(ByScore);
        String HalfTime = GetText(ByLastScore);
        String averageOdds = GetText(ByOdds);
        String[] split = TeamsNames.split(" vs ");
        String teamA= split[0];
        String teamB= split[1];
        Thread.sleep(1000);
        System.out.println("Name of groups: "+TeamsNames);
        System.out.println("Date: "+Date);
        System.out.println("Score: "+Score);
        System.out.println("Half time scores: "+ HalfTime);
        String ScorersName1 = scorers.get(0).getText();
        System.out.println(teamA +" Scorer names:  "+ ScorersName1);
        String ScorersName2 = scorers.get(1).getText();
        String ScorersName3 = teamB.replace("Stats", "").trim();
        System.out.println(ScorersName3 +" Scorer names:  "+ ScorersName2);
        System.out.println("Avarage Odds 1X2: " + averageOdds);
        Thread.sleep(2000);
        waitForElement(clickOverUnder);
        WebElement clickOverUnder1 = driver.findElement(clickOverUnder);
        scrollToElementAndClick(clickOverUnder1);
        Thread.sleep(2000);
        WebElement statsContentFrame = driver.findElement(By.cssSelector("[id=\"odds-content\"]"));
        WebElement averageOddsElement = statsContentFrame.findElement(By.cssSelector("[id=\"odds-content\"] [id=\"sortable-1\"] tfoot"));
        String AverageOdds = averageOddsElement.getText();
        System.out.println("Avarage Odds Over/Under (2.5): " +AverageOdds);
        waitForElement(clickBothTeamsScore);
        WebElement clickBothTeamsScore1 = driver.findElement(clickBothTeamsScore);
        scrollToElementAndClick(clickBothTeamsScore1);
        Thread.sleep(2000);
        WebElement averageOddsElementBT = driver.findElement(By.cssSelector("tfoot"));
        String averageOddsElementBT1 = averageOddsElementBT.getText();
        System.out.println("Avarage Odds Both teams to score: " + averageOddsElementBT1);
        click(clickOnTabLeagueName);
        scrollTobYElementAndClick(showAllResults);
}


    public void WriteToExcel(String TeamsNames , String Date, String Score,String HalfTime, String TeamA,String TeamAScorer, String TeamB, String TeamBScorer ){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("file.csv");
        Row row = sheet.createRow(0); // Create a new row (0-based index)
        Cell cell = row.createCell(0); // Create a cell in the first column (0-based index)
        cell.setCellValue("Hello, World!");

        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\liav\\Desktop\\excel BetExplorer\\file.csv")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
}
}

}