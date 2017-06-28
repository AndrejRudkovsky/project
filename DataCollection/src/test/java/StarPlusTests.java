import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class StarPlusTests extends WebDriverTestBase {
    public SelenideElement loadProgressBar = $("#load_BaseMainContent_MainContent_jqgTrade");
    String EIS_number_b2b = "eisNumber";

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
        String fz223rts_rule_xpath = "//label[@for='BaseMainContent_MainContent_chkPurchaseType_0']";
        String fz615_rile_xpath = "//label[@for='BaseMainContent_MainContent_chkPurchaseType_3']";
        String ZMO_rile_xpath = "//label[@for='BaseMainContent_MainContent_chkPurchaseType_2']";
        String kolvostranic223_id = "sp_1_BaseMainContent_MainContent_jqgTrade_toppager";
        String kolvostranic223_idDown = "sp_1_BaseMainContent_MainContent_jqgTrade_pager";


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
        //////////////////////////////44//////////44//////////////44////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 44 ФЗ
        $(By.xpath(fz44_rule_xpath)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible, 10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible, 10000).setValue(dateStamp);

        //В фильтрах площадок выбираем площадку РТС-тендер
        marketPlaceHolder.waitUntil(visible, 10000).click();
        $(By.xpath(ploshadka)).waitUntil(visible, 10000).click();

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible, 10000).click();
        int b2b_count = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible, 10000).getText());
        String[] b2bResultArray44 = getTextResultsCollection223(EIS_number_b2b);

        driver.get("https://www.rts-tender.ru/auctionsearch");

        //Клик на радио баттоне Фз44
        $(By.id(rts44FZ_id)).waitUntil(visible, 10000).click();

        //Открываем подменю с выбором дат
        $(By.xpath(publication_date_checkbox_xpath)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id)).waitUntil(visible, 10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id)).waitUntil(visible, 10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id)).waitUntil(visible, 10000).click();
        Thread.sleep(5000);

        //Получаем кол-во закупок

        int rts44_count = Integer.parseInt($(By.id(notificationCounter_id)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));

        //Вместо этого можно создать файл и отправить полученные результаты письмом
        System.out.print("кол-во закупок по 44фз на площадке S2: " + rts44_count + " кол-во закупок по 44фз на b2b S1: " + b2b_count + " S2-S1: " + (rts44_count - b2b_count) + "\r\n");
        FileWriter writer = new FileWriter("src/notes3.txt", false);
        {
            // запись всей строки
            String text_ ="\r\n_______________________________________________________________________________________________\r\n";
            String text3 = "Кол-во закупок по 44фз на площадке S2: " + rts44_count + " \nКол-во закупок по 44фз на b2b S1: " + b2b_count + "\nS2-S1: " + (rts44_count - b2b_count) + "\r\n";
            writer.write(text3);
            // запись по символам
            writer.append('\n');

            writer.flush();
        }
        String[] in44ResultArray = getTextResultsCollection44();

        //System.out.print("Count: " + tradesCount + "\r\n");
        // выводим
        //System.out.print(getResultsCollection());
        List<String> from1to2Diif44 = getDiffFrom2Arrays(b2bResultArray44, in44ResultArray);
        List<String> from2to1Diif44 = getDiffFrom2Arrays(in44ResultArray, b2bResultArray44);
        String text44="\nЗакупки, которые присутсвуют только в одном источнике по 44 фз: ";
        writer.append(text44);
        {writer.write("");
            for (String l : from1to2Diif44) {
                writer.append(l);
                writer.append(", ");
            }
            writer.append('\n');
            writer.flush();}
        {writer.write("");
            for (String l : from2to1Diif44) {
                writer.append(l);
                writer.append(", ");
            }
            writer.append('\n');
            writer.flush();}

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////615////////////////////////615/////////////615/////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://b2bpoint.ru/?FilterData.PageSize=100\n");
        //В фильтрах выбрать правило провдения закупки 223 ФЗ
        $(By.xpath(pp615_rule_xpath)).waitUntil(visible, 10000).click();

        marketPlaceHolder.waitUntil(visible, 10000).click();
        //marketPlaceHolder.click();

        //В фильтрах площадок выбираем площадку РТС-тендер
        $(By.xpath(ploshadka)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible, 10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible, 10000).setValue(dateStamp);

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible, 10000).click();
        int b2b_count3 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible, 10000).getText());

        String[] b2bResultArray615 = getTextResultsCollection223(EIS_number_b2b);


        driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

        // выбираем правило проведения закупки 615
        $(By.xpath(fz615_rile_xpath)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id223)).waitUntil(visible, 10000).click();
        Thread.sleep(5000);

        //выбираем в выпадающем списке отображение по 100 штук
        $(By.className("ui-pg-selbox")).waitUntil(visible, 10000).selectOptionByValue("100");
        Thread.sleep(5000);


        //Получаем кол-во закупок
        int rts615_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
        int kolvostranic615_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
        int currentPage615;

        String tmp615 = "";
        int tradesCount615 = 0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPage615 = 1; currentPage615 <= kolvostranic615_id_count; currentPage615++) {

            for (SelenideElement i : getResultsCollection()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                tradesCount615++;

            }
            $(By.id(nextPage)).waitUntil(visible, 10000).click();
        }

        //System.out.print("Count: " + tradesCount + "\r\n");
        String[] in615ResultArray = getTextResultsCollection("//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']");
        List<String> from1to2Diif615 = getDiffFrom2Arrays(b2bResultArray615, in615ResultArray);
        System.out.print(from1to2Diif615);
        // Arrays.toString(from1to2Diif615.toString()).replaceAll("\\[|\\]", "").replaceAll(", ","\t")
        List<String> from2to1Diif615 = getDiffFrom2Arrays(in615ResultArray, b2bResultArray615);
        System.out.print(from2to1Diif615);
        // выводим
        System.out.print("кол-во закупок по 615фз на площадке S2: " + tradesCount615 + " кол-во закупок по 615 на b2b S1: " + b2b_count3 + " S2-S1: " + (tradesCount615 - b2b_count3) + "\r\n");
        {
            // запись всей строки
            String text4 = "Кол-во закупок по 615фз на площадке S2: " + tradesCount615 + "\r\nКол-во закупок по 615 на b2b S1: " + b2b_count3 + "\nS2-S1: " + (tradesCount615 - b2b_count3) + "\r\n";
            writer.write(text4);
            // String spisok615 = join(" ",from1to2Diif615);

            writer.append('\n');
            String text615="\r\nЗакупки, которые присутсвуют только в одном источнике по 615 фз: ";
            writer.append(text615);
            {writer.write("");
                for (String l : from1to2Diif615) {
                    writer.append(l);
                    writer.append(", ");
                }
                writer.append('\n');
                writer.flush();}
            {writer.write("");
                for (String l : from2to1Diif615) {
                    writer.append(l);
                    writer.append(", ");
                }
                writer.append('\n');
                writer.flush();}

  
            ////////////32/////////////////////////////////////////////////////////////////////////////////////////////
            ////ЗМО////////////ЗМО///////////////ЗМО//////////////ЗМО//////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////
        /*driver.get("http://b2bpoint.ru/");

        //В фильтрах выбрать правило провдения закупки 223 ФЗ
        $(By.xpath(ZMO_rule_xpath)).waitUntil(visible, 10000).click();

        marketPlaceHolder.waitUntil(visible, 10000).click();
        //marketPlaceHolder.click();

        //В фильтрах площадок выбираем площадку РТС-тендер
        $(By.xpath(ploshadka)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(stardDate_id)).waitUntil(visible, 10000).setValue(dateStamp);
        $(By.id(endDate_id)).waitUntil(visible, 10000).setValue(dateStamp);

        //Нажимаем поиск
        $(By.xpath(btnSearch_xpath)).waitUntil(visible, 10000).click();
        int b2b_count4 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible, 10000).getText());

        /////поиск на площадке
        driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

        // выбираем правило проведения закупки 615
        $(By.xpath(ZMO_rile_xpath)).waitUntil(visible, 10000).click();

        //В фильтре даты выбрать текущую дату.
        $(By.id(publicationDateFrom_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);
        $(By.id(publicationDateTo_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);

        //Наживаем на "Поиск"
        $(By.id(searchButton_id223)).waitUntil(visible, 10000).click();
        Thread.sleep(5000);

        //выбираем в выпадающем списке отображение по 100 штук
        $(By.className("ui-pg-selbox")).waitUntil(visible, 10000).selectOptionByValue("100");
        Thread.sleep(5000);


        //Получаем кол-во закупок
        int ZMO_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
        int kolvostranicZMO_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
        int currentPageZMO;

        String tmpZMO = "";
        int tradesCountZMO = 0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPageZMO = 1; currentPageZMO <= kolvostranicZMO_id_count; currentPageZMO++) {

            for (SelenideElement i : getResultsCollection()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                tradesCountZMO++;

            }
            $(By.id(nextPage)).waitUntil(visible, 10000).click();
        }

        //System.out.print("Count: " + tradesCount + "\r\n");
        // выводим
        System.out.print("кол-во закупок по ЗМО на площадке S2: " + tradesCountZMO + " кол-во закупок по ZMO на b2b S1: " + b2b_count4 + " S2-S1: " + (tradesCountZMO - b2b_count4) + "\r\n");
        {
            // запись всей строки
            String text = "Кол-во закупок по ЗМО на площадке S2: " + tradesCountZMO + "\n Кол-во закупок по ZMO на b2b S1: " + b2b_count4 + "\nS2-S1: " + (tradesCountZMO - b2b_count4) + "\r\n";
            writer.write(text);
            // запись по символам
            writer.append('\n');
        }*/
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223
            ////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223////////////////// ПОИСК ПО 223
            driver.get("http://b2bpoint.ru/?FilterData.PageSize=100\n");

            //В фильтрах выбрать правило провдения закупки 223 ФЗ
            $(By.xpath(fz223_rule_xpath)).waitUntil(visible, 10000).click();

            marketPlaceHolder.waitUntil(visible, 10000).click();
            //marketPlaceHolder.click();

            //В фильтрах площадок выбираем площадку РТС-тендер
            $(By.xpath(ploshadka)).waitUntil(visible, 10000).click();

            //В фильтре даты выбрать текущую дату.
            $(By.id(stardDate_id)).waitUntil(visible, 10000).setValue(dateStamp);
            $(By.id(endDate_id)).waitUntil(visible, 10000).setValue(dateStamp);


            //Нажимаем поиск
            $(By.xpath(btnSearch_xpath)).waitUntil(visible, 10000).click();
            int b2b_count2 = Integer.parseInt($(By.id(b2b_count_id)).waitUntil(visible, 10000).getText());


            String[] b2bResultArray = getTextResultsCollection223(EIS_number_b2b);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            //парсим номера закупок по 223//////////////////////////////////////


       /* int kolvostranic223_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
        int currentPage;

        String tmp = "";
        int tradesCount = 0;

        //for (int i = 0; i < kolvostranic223_id ; i++) {

        for (currentPage = 1; currentPage <= kolvostranic223_id_count; currentPage++) {

            for (SelenideElement i : getResultsCollection223()) {
                //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                if (i.getText().equals("")) {
                    continue;
                } else {
                    tradesCount++;
                }
            }
            $(By.id(nextPage)).waitUntil(visible, 10000).click();
        }
*/

            ////////////////// ПОИСК ПО 223
            driver.get("https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx");

            // выбираем правило проведения закупки 223
            $(By.xpath(fz223rts_rule_xpath)).waitUntil(visible, 10000).click();


            //В фильтре даты выбрать текущую дату.
            $(By.id(publicationDateFrom_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);
            $(By.id(publicationDateTo_id223)).waitUntil(visible, 10000).sendKeys(dateStamp);

            //Наживаем на "Поиск"
            $(By.id(searchButton_id223)).waitUntil(visible, 10000).click();
            Thread.sleep(5000);

            //выбираем в выпадающем списке отображение по 100 штук
            $(By.className("ui-pg-selbox")).waitUntil(visible, 10000).selectOptionByValue("100");
            Thread.sleep(5000);

            //Получаем кол-во закупок
            int rts223_count = Integer.parseInt($(By.id(notificationCounter_id2)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
            int kolvostranic223_id_count = Integer.parseInt($(By.id(kolvostranic223_idDown)).waitUntil(visible, 10000).getText().replaceAll("\\s+", ""));
            int currentPage;

            String tmp = "";
            int tradesCount = 0;

            //for (int i = 0; i < kolvostranic223_id ; i++) {

            for (currentPage = 1; currentPage <= kolvostranic223_id_count; currentPage++) {

                for (SelenideElement i : getResultsCollection()) {
                    //System.out.print("Page: " + currentPage + ", Count: " + tradesCount + "\r\n");
                    if (i.getText().equals("")) {
                        continue;
                    } else {
                        tradesCount++;
                    }
                }
                $(By.id(nextPage)).waitUntil(visible, 10000).click();
            }
            String[] in223ResultArray = getTextResultsCollection("//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']");

            //System.out.print("Count: " + tradesCount + "\r\n");
            // выводим
            //System.out.print(getResultsCollection());
            List<String> from1to2Diif = getDiffFrom2Arrays(b2bResultArray, in223ResultArray);
            System.out.print(from1to2Diif);
            List<String> from2to1Diif = getDiffFrom2Arrays(in223ResultArray, b2bResultArray);
            System.out.print(from2to1Diif);
            System.out.print("\r\nкол-во закупок по 223 Фз на площадке S2 : " + tradesCount + "\r\nкол-во закупок по 223 фз на b2b S1: " + b2b_count2 + "\r\nS2-S1: " + (tradesCount - b2b_count2) + "\r\nКол-во страниц: " + kolvostranic223_id_count + "\r\n");
            String text2 = "\nКол-во закупок по 223 Фз на площадке S2 : " + tradesCount + "\nКол-во закупок по 223 фз на b2b S1: " + b2b_count2 + "\nS2-S1: " + (tradesCount - b2b_count2);
            writer.write(text2);
            // запись по символам
            writer.append('\n');
            writer.flush();
            String text223="\nЗакупки, которые присутсвуют только в одном источнике по 223 фз:";
            writer.append(text223);
            {writer.write("");
                for (String l : from1to2Diif) {
                    writer.append(l);
                    writer.append(", ");
                }
                writer.append('\n');
                writer.flush();}
            {writer.write("");
                for (String l : from2to1Diif) {
                    writer.append(l);
                    writer.append(", ");
                }
                writer.append('\n');
                writer.flush();}


        }
    }

    public ElementsCollection getResultsCollection() {
        loadProgressBar.shouldBe(Condition.attribute("style", "display: none;"));
        ElementsCollection resultsCollection = $$(By.xpath("//td[@aria-describedby='BaseMainContent_MainContent_jqgTrade_OosNumber']"));
        return resultsCollection;

    }

    public String[] getTextResultsCollection(String path) {
        loadProgressBar.shouldBe(Condition.attribute("style", "display: none;"));
        ElementsCollection resultsCollection = $$(By.xpath(path));
        return resultsCollection.getTexts();

    }

    public ElementsCollection getResultsCollection223() {
        ElementsCollection resultsCollection = $$(By.id(EIS_number_b2b));
        return resultsCollection;

    }
    public String[] getTextResultsCollection44() {
        ElementsCollection resultsCollection = $$("dnn_ctr691_View_procResultList > tbody > tr:nth-child(2) > td:nth-child(3)");
        return resultsCollection.getTexts();

    }

    public String[] getTextResultsCollection223(String elemId) {
        ElementsCollection resultsCollection = $$(By.id(elemId));
        return resultsCollection.getTexts();

    }

    List<String> getDiffFrom2Arrays(String[] Array1, String[] Array2) {
        boolean contains = false;
        List<String> results = new ArrayList<String>();


        for (int i = 0; i < Array1.length; i++) {
            for (int j = 0; j < Array2.length; j++) {
                if (Array1[i].equalsIgnoreCase(Array2[j])) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                results.add(Array1[i]);
            } else {
                contains = false;
            }
        }
        return results;
    }
}



