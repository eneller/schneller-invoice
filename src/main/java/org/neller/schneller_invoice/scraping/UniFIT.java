package org.neller.schneller_invoice.scraping;
import com.microsoft.playwright.*;

public class UniFIT {
    private static final String SHIFTS_URL="https://sport.uni-ulm.de/de/unifit/schicht";
    public static void main(String[] args){
        try(Playwright playwright = Playwright.create()){
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate(SHIFTS_URL);
            System.out.println(page.url());
            page.close();
            browser.close();
        }
    }
}
