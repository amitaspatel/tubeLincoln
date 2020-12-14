package application_page_base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reporting.TestLogger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class NavScraper {

    public static void findBrokenLinks(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        String scrapeURL = "";
        String baseOfURL = "https://wwwuat.fordtube.dealerconnection.com/";
        Iterator<WebElement> it = links.iterator();
        HttpURLConnection huc = null;
        int respCode = 200;

        while (it.hasNext()) {

            scrapeURL = it.next().getAttribute("href");
//            TestLogger.log(scrapeURL);

            if (scrapeURL == null || scrapeURL.isEmpty()) {
                TestLogger.log(scrapeURL + " : " + "URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!scrapeURL.startsWith(baseOfURL)) {
                TestLogger.log(scrapeURL + " : " + "URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(scrapeURL).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    TestLogger.log(scrapeURL + " is a broken link");
                } else {
                    TestLogger.log(scrapeURL + " is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void findBrokenImages(WebDriver driver) throws IOException {
        List<WebElement> links = driver.findElements(By.tagName("img"));
        int brokenImagesCount = 0;
        for (WebElement link : links) {
            String linkURL = link.getAttribute("src");
            TestLogger.log(linkURL);
            URL url = new URL(linkURL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            http.setConnectTimeout(10000);
            http.setReadTimeout(20000);
            int statusCode = http.getResponseCode();
            if (statusCode == 404 || statusCode == 500) {
                brokenImagesCount = brokenImagesCount + 1;
                TestLogger.log(linkURL + "and its Status codes is:" + statusCode);
            }
        }
        TestLogger.log("total number of broken images are: " + brokenImagesCount);
    }
}

