import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Alex Kiselev on 06.04.2017.
 */
public class StarPlusTests extends WebDriverTestBase{
    public SelenideElement loadProgressBar = $("#load_BaseMainContent_MainContent_jqgTrade");
    @Test
    public void verData() throws Exception {

        //Elements b2b
        String rtsLink = "https://www.rts-tender.ru/auctionsearch";
        String b2bLink = "http://b2bpoint.ru";

        String fz44_rule_xpath = "//label[@for='TenderRule_1']";
        String fz223_rule_xpath = "//label[@for='TenderRule_2']";
        String pp615_rule_xpath = "//label[@for='TenderRule_5']";
        String ZMO_rule_xpath = "//label[@for='TenderRule_3']";

        String ploshadka = "//label[@for='marketPlace_5']";

        String stardDate_id = "DatePublishedFrom";
        String endDate_id = "DatePublishedTo";
        String b2b_count_id = "AllCount";
        String b2b_count_id2 = "AllCount";
        String b2b_count_id3 = "AllCount";
        SelenideElement marketPlaceHolder = $("#SearchForm > div:nth-child(2) > div > div > div > div > button");
        String nextPage = "next_t_BaseMainContent_MainContent_jqgTrade_toppager";
        String btnSearch_xpath = "//span[@class='btn btn-type1 mt2 Search']";

        //Elements rts-tender
        String rts44FZ_id = "dnn_ctr691_View_RadioButton3";
        String rts223FZ_id = "dnn_ctr691_View_RadioButton9";

        String publication_date_checkbox_xpath = "//label[@for='dnn_ctr691_View_aSdateFiilert1']";
        String publicationDateFrom_id = "dnn_ctr691_View_dateFilter1From";
        String publicationDateTo_id = "dnn_ctr691_View_dateFilter1To";
        String searchButton_id = "dnn_ctr691_View_aSbuttonSearch";
        String searchButton_id223 = "BaseMainContent_MainContent_btnSearch";
        String notificationCounter_id = "dnn_ctr691_View_aStotalCount";
        String notificationCounter_id2 = "sp_1_BaseMainContent_MainContent_jqgTrade_toppager";
        String fz223rts_rule_xpath ="//label[@for='BaseMainContent_MainContent_chkPurchaseType_0']";
        String fz615_rile_xpath ="//label[@for='BaseMainContent_MainContent_chkPurchaseType_3']";
        String ZMO_rile_xpath ="//label[@for='BaseMainContent_MainContent_chkPurchaseType_2']";
        String kolvostranic223_id ="sp_1_BaseMainContent_MainContent_jqgTrade_toppager";
        String kolvostranic223_idDown ="sp_1_BaseMainContent_MainContent_jqgTrade_pager";




        String publicationDateFrom_id223 = "BaseMainContent_MainContent_txtPublicationDate_txtDateFrom";
        String publicationDateTo_id223 = "BaseMainContent_MainContent_txtPublicationDate_txtDateTo";
        WebElement combobox = $("ui-pg-selbox");
        WebElement combobox2 = $(By.className("ui-pg-selbox"));
                //(driver.findElement(By.xpath("//select[@id='periodId']));
                //driver.getClass().())


        //Текущая дата
        String timeStamp = new SimpleDateFormat("HH-mm").format(Calendar.getInstance().getTime());
        String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 44 ФЗ
        $(By.xpath(fz44_rule_xpath)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible,10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible,10000).setValue(dateStamp);

        //В фильтрах площадок выбираем площадку РТС-тендер
        marketPlaceHolder.waitUntil(visible,10000).click();
        $(By.xpath(ploshadka)).waitUntil(visible,10000).click();

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible,10000).click();
        int b2b_count = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible,10000).getText());

        driver.get("https://www.rts-tender.ru/auctionsearch");

        //Клик на радио баттоне Фз44
        $(By.id(rts44FZ_id)).waitUntil(visible,10000).click();

        //Открываем подменю с выбором дат
        $(By.xpath(publication_date_checkbox_xpath)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id)).waitUntil(visible,10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id)).waitUntil(visible,10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id)).waitUntil(visible,10000).click();
        Thread.sleep(5000);

        //Получа5ем кол-во закупок

        int rts44_count = Integer.parseInt($(By.id(notificationCounter_id)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));

        //Вместо этого можно создать файл и отправить полученные результаты письмом
        System.out.print("кол-во закупок по 44фз на площадке S2: " + rts44_count + " кол-во закупок по 44фз на b2b S1: " + b2b_count + " S2-S1: "+ (rts44_count-b2b_count) + "\r\n");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 223 ФЗ
        $(By.xpath(pp615_rule_xpath)).waitUntil(visible,10000).click();

        marketPlaceHolder.waitUntil(visible,10000).click();
        //marketPlaceHolder.click();

        //В фильтрах площадок выбираем площадку РТС-тендер
        $(By.xpath(ploshadka)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible,10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible,10000).setValue(dateStamp);

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible,10000).click();
        int b2b_count3 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible,10000).getText());


        driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

        // выбираем правило проведения закупки 615
        $(By.xpath(fz615_rile_xpath)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id223)).waitUntil(visible,10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id223)).waitUntil(visible,10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id223)).waitUntil(visible,10000).click();
        Thread.sleep(5000);

        //выбираем в выпадающем списке отображение по 100 штук
        $(By.className("ui-pg-selbox")).waitUntil(visible,10000).selectOptionByValue("100");
        Thread.sleep(5000);



        //Получаем кол-во закупок
        int rts615_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int  kolvostranic615_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int currentPage615;

        String tmp615="";
        int tradesCount615=0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPage615 = 1; currentPage615 <= kolvostranic615_id_count; currentPage615++) {

            for (SelenideElement i : getResultsCollection()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                    tradesCount615++;

            }
            $(By.id(nextPage)).waitUntil(visible,10000).click();
        }

        //System.out.print("Count: " + tradesCount + "\r\n");
        // выводим
        System.out.print("кол-во закупок по 615фз на площадке S2: " + tradesCount615 + " кол-во закупок по 615 на b2b S1: " + b2b_count3 + " S2-S1: "+ (tradesCount615-b2b_count3) + "\r\n");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////ЗМО////////////ЗМО///////////////ЗМО//////////////ЗМО//////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 223 ФЗ
        $(By.xpath(ZMO_rule_xpath)).waitUntil(visible,10000).click();

        marketPlaceHolder.waitUntil(visible,10000).click();
        //marketPlaceHolder.click();

        //В фильтрах площадок выбираем площадку РТС-тендер
        $(By.xpath(ploshadka)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible,10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible,10000).setValue(dateStamp);

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible,10000).click();
        int b2b_count4 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible,10000).getText());

        /////поиск на площадке
        driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

        // выбираем правило проведения закупки 615
        $(By.xpath(ZMO_rile_xpath)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id223)).waitUntil(visible,10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id223)).waitUntil(visible,10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id223)).waitUntil(visible,10000).click();
        Thread.sleep(5000);

        //выбираем в выпадающем списке отображение по 100 штук
        $(By.className("ui-pg-selbox")).waitUntil(visible,10000).selectOptionByValue("100");
        Thread.sleep(5000);



        //Получаем кол-во закупок
        int ZMO_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int  kolvostranicZMO_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int currentPageZMO;

        String tmpZMO="";
        int tradesCountZMO=0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPageZMO = 1; currentPageZMO <= kolvostranicZMO_id_count; currentPageZMO++) {

            for (SelenideElement i : getResultsCollection()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                tradesCountZMO++;

            }
            $(By.id(nextPage)).waitUntil(visible,10000).click();
        }

        //System.out.print("Count: " + tradesCount + "\r\n");
        // выводим
        System.out.print("кол-во закупок по ЗМО на площадке S2: " + tradesCountZMO + " кол-во закупок по ZMO на b2b S1: " + b2b_count4 + " S2-S1: "+ (tradesCountZMO-b2b_count4) + "\r\n");



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223
        ////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223
        driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 223 ФЗ
        $(By.xpath(fz223_rule_xpath)).waitUntil(visible,10000).click();

        marketPlaceHolder.waitUntil(visible,10000).click();
        //marketPlaceHolder.click();

        //В фильтрах площадок выбираем площадку РТС-тендер
        $(By.xpath(ploshadka)).waitUntil(visible,10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible,10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible,10000).setValue(dateStamp);

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible,10000).click();
        int b2b_count2 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible,10000).getText());

        ////////////////// ПОИСК ПО 223
        driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

        // выбираем правило проведения закупки 223
        $(By.xpath(fz223rts_rule_xpath)).waitUntil(visible,10000).click();


        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id223)).waitUntil(visible,10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id223)).waitUntil(visible,10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id223)).waitUntil(visible,10000).click();
        Thread.sleep(5000);

        //выбираем в выпадающем списке отображение по 100 штук
        $(By.className("ui-pg-selbox")).waitUntil(visible,10000).selectOptionByValue("100");
        Thread.sleep(5000);

        //Получаем кол-во закупок
        int rts223_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int  kolvostranic223_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible,10000).getText().replaceAll("\\s+",""));
        int currentPage;

        String tmp="";
        int tradesCount=0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPage = 1; currentPage <= kolvostranic223_id_count; currentPage++) {

            for (SelenideElement i : getResultsCollection()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                if (i.getText().equals("")) {
                    continue;
                }
                else {
                    tradesCount++;
                }
            }
            $(By.id(nextPage)).waitUntil(visible,10000).click();
        }

        //System.out.print("Count: " + tradesCount + "\r\n");
        // выводим
        System.out.print("кол-во закупок по 223 Фз на площадке S2 : " + tradesCount + " кол-во закупок по 223 фз на b2b S1: " + b2b_count2 + " S2-S1: "+ (tradesCount-b2b_count2) + "\r\nКол-во страниц: " + kolvostranic223_id_count + "\r\n");

    }


        public ElementsCollection getResultsCollection() {
        loadProgressBar.shouldBe(Condition.attribute("style", "display: none;"));
        ElementsCollection resultsCollection = $$(By.xpath("//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']"));
        return resultsCollection;
    }
  //ПОИСК ПО 615




}



