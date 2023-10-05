package PageObjects;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

String StringexcelFilePath ="C:\\\\Users\\\\liav\\\\Desktop\\\\excel BetExplorer\\\\file.csv";


    By IAcceptButton = By.cssSelector("[id='onetrust-accept-btn-handler']");
    By ShowAllCountries = By.cssSelector("[class='button js-toggle-countrymenu closed']");
    By showAllResults = By.cssSelector(".wrap-section__header__link");
    By clickOverUnder = By.cssSelector("[title=\"Over/Under\"]");
    By clickBothTeamsScore = By.cssSelector("[title=\"Both Teams To Score\"]");
By BystatsPage = By.cssSelector("section[class=\"wrap-section\"]");
    By BygroupNames = By.cssSelector("[class=\"wrap-section__header\"]");
    By ByDate = By.cssSelector("#match-date");
    By ByScore = By.cssSelector("#js-score");
    By ByHalfTime = By.cssSelector("#js-partial");
    By ByOdds = By.cssSelector("[id=\"match-add-to-selection\"]");
    By clickOnTabLeagueName = By.cssSelector("[href=\"/football/england/premier-league/\"]");
    By secandTableMain = By.cssSelector("#sortable-9");
    By ByoverAndUnder = By.cssSelector(".table-main__detail-odds");
    By ByBTS = By.cssSelector("tfoot");
    By BymainTeamAScorer = By.cssSelector(".list-details.list-details--shooters");


    By ByTeamAScorer = By.cssSelector(".list-details__item");





    public void clickOniAcceptButtonAndShowAllCountries() {
        scrollTobYElementAndClick(IAcceptButton);
        scrollTobYElementAndClick(ShowAllCountries);
    }

    public void ClickOnAllCountries() throws InterruptedException {
        List<WebElement> countries;
        countries = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));
        for (int i = 0; i < countries.size(); i++) {

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
            getAllInfoInsideGame(i);

        }
    }
public String GetText(By by){
    String TeamsNames;
    try{    waitForElement(by);
    WebElement statsPage = driver.findElement(BystatsPage);
    WebElement Text = statsPage.findElement(by);
    String GenralInfo = Text.getText();
     TeamsNames = GenralInfo.replace("Stats", "").trim();
    }catch(TimeoutException e){TeamsNames = "Not Found"; }
    return  TeamsNames;
}

    public String GetText2(By Main, By by) {
try {
    waitForElement(Main);
}
 catch (NoSuchElementException e){waitForElement(Main);}
        WebElement temp = driver.findElement(Main);
        List<WebElement> temp2 = temp.findElements(by);
        int a = temp2.size()-1;
        int b = temp2.size()-2;
        String over =  temp2.get(a).getText();
        String under =  temp2.get(b).getText();
        String overAndUnder = over+" "+ under;
        return overAndUnder;
    }

    public List<String> GetText3(By Main, By by) {
        try {
            waitForElement(Main);
        } catch (NoSuchElementException e) {
            waitForElement(Main);
        }
        WebElement temp = driver.findElement(Main);
        List<WebElement> temp2 = temp.findElements(by);
        int a = temp2.size() - 1;
        int b = temp2.size() - 2;
        String over = temp2.get(a).getText();
        String under = temp2.get(b).getText();

        List<String> result = new ArrayList<>();
        result.add(over);
        result.add(under);

        return result;
    }


    public void getAllInfoInsideGame(int i) throws InterruptedException {
        List<WebElement> scorers = driver.findElements(By.cssSelector("li [class=\"table-main\"]"));
        String TeamsNames = GetText(BygroupNames);
         String Date = GetText(ByDate);
        String[] NewDate = Date.split(" - ");
        String date= NewDate[0];
        String time= NewDate[1];
        String Score = GetText(ByScore);
        String Halftime = GetText(ByHalfTime);
        String[] NewHalfTime = Halftime.split(",");
        String tempHT1= NewHalfTime[0];
       String HT1 = tempHT1.replace("(", "");
        String tempHT2= NewHalfTime[1];
        String HT2 = tempHT2.replace(")", "");

        List<String> texts = GetText3(BymainTeamAScorer,ByTeamAScorer);
        String TeamAScorer = texts.get(0);
        String TeamBScorer = texts.get(1);
        String averageOdds1 = GetText(ByOdds);
        String averageOdds = averageOdds1.replace("Average odds", "");

        String[] split = TeamsNames.split(" vs ");
        String teamA= split[0];
        String teamB= split[1];
        Thread.sleep(1000);
        System.out.println("Name of groups: "+TeamsNames);
        System.out.println("Date: "+Date);
        System.out.println("Score: "+Score);
        System.out.println("HT1 "+ HT1);
        System.out.println("HT2 "+ HT2);

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
        String overAndUnder = GetText2(secandTableMain,ByoverAndUnder);
        System.out.println("over And Under: " + overAndUnder);
        Thread.sleep(2000);
        waitForElement(clickBothTeamsScore);
        scrollTobYElementAndClick(clickBothTeamsScore);

        Thread.sleep(2000);
       String BTSOdds = GetText(ByBTS);
        System.out.println("Avarage Odds Both teams to score: " + BTSOdds);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        WriteToExcel(StringexcelFilePath,TeamsNames,teamA,teamB,date,time,Score,HT1,HT2,TeamAScorer,TeamBScorer,averageOdds,overAndUnder, BTSOdds,i);
        click(clickOnTabLeagueName);
        scrollTobYElementAndClick(showAllResults);

}

    public void WriteToExcel(String excelFilePath, String TeamsNames,String teama,String teamb, String Date,String time, String Score, String HT1,String HT2, String TeamAScorer, String TeamBScorer,String averageOdds,String overAndUnder,String BTSOdds ,int rowNum) {
        int num = rowNum +1;
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming you want to write to the first sheet

            Row row = sheet.createRow(num);

            // Create cells and set values for each column
            row.createCell(0).setCellValue(TeamsNames);
            row.createCell(1).setCellValue(teama);
            row.createCell(2).setCellValue(teamb);
            row.createCell(3).setCellValue(Date);
            row.createCell(4).setCellValue(time);
            row.createCell(5).setCellValue(Score);
            row.createCell(6).setCellValue(HT1);
            row.createCell(7).setCellValue(HT2);
            row.createCell(8).setCellValue(TeamAScorer);
            row.createCell(9).setCellValue(TeamBScorer);
            row.createCell(10).setCellValue(averageOdds);
            row.createCell(11).setCellValue(overAndUnder);
            row.createCell(12).setCellValue(BTSOdds);

            // Save the changes
            FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
            workbook.write(fileOutputStream);

            // Close streams
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void WriteToExcel(String TeamsNames , String Date, String Score,String HalfTime, String TeamA,String TeamAScorer, String TeamB, String TeamBScorer ,int num){
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("file.csv");
//        Row row = sheet.createRow(num); // Create a new row (0-based index)
//        Cell cell = row.createCell(0); // Create a cell in the first column (0-based index)
//        cell.setCellValue("Hello, World!");
//
//        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\liav\\Desktop\\excel BetExplorer\\file.csv")) {
//            workbook.write(outputStream);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//}
}

