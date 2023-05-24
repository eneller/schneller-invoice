package org.neller.schneller_invoice.endpoints;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import java.nio.file.Paths;

public class UniFIT {
    private static final String SHIFTS_URL="https://sport.uni-ulm.de/de/unifit/schicht";
    public static void main(String[] args){
        try(Playwright playwright = Playwright.create()){
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            Page.NavigateOptions idle = new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE);
            page.navigate(SHIFTS_URL, idle);
            // TODO wait for page load
            // if not on correct page, attempt to log in
            if(!page.url().equals(SHIFTS_URL)){
                System.out.println(page.url());
                page.navigate("https://sport.uni-ulm.de/login/uulm", idle);
                page.getByLabel("Mailadresse/Benutzername").fill(args[0]);
                page.getByLabel("Mailadresse/Benutzername").press("Tab");
                page.getByLabel("Passwort").fill(args[1]);
                page.getByLabel("Passwort").press("Enter");
            }
            page.navigate(SHIFTS_URL, idle);
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshot.png"))
                    .setFullPage(true));
            // if still not on correct page, abort TODO
            /*
            if(!page.url().equals(SHIFTS_URL)){
                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("error_"+page.url()+".png"))
                        .setFullPage(true));
                System.exit(1);

            }
             */




            //close stuff
            /*
            page.close();
            browser.close();
             */
        }

    }
}
