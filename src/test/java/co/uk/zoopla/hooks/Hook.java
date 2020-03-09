package co.uk.zoopla.hooks;

import co.uk.zoopla.commons.Browsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook extends Browsers
{
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario)
    {
        this.scenario = scenario;
        launchBrowser("Chrome");
    }

    @After
    public void tearDown()
    {

        if(scenario.isFailed())
        {
            try
            {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "PNG", "failurePage.png");
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        closeBrowser();
    }
}
