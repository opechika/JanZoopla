package co.uk.zoopla.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class SearchResultPage extends BasePage
{
    public SearchResultPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(tagName = "h1")
    private WebElement pageTitle;
    @FindBy(css = ".listing-results-price.text-price")
    private List<WebElement> results;

    public void isSearchResultPageDisplayed()
    {
        Assert.assertTrue(pageTitle.isDisplayed());
    }

    public void isCorrectUrlDisplayedForSearch(String location)
    {
        driver.getCurrentUrl().contains(location.toLowerCase());
    }

    public void isPropertyTypeDisplayed(String property)
    {
        String title = pageTitle.getText();
        Assert.assertTrue(title.contains(property));
    }

    public void isLocationDisplayed(String location)
    {
        String title = pageTitle.getText();
        Assert.assertTrue(title.contains(location));
    }

    public ProductDetailsPage clickOnTheFirstResultLink()
    {
        results.get(0).click();
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage clickOnAnyResultsLinkAtRandom()
    {
        Random ran = new Random();
        int randomNumber = ran.nextInt(results.size() - 1 );

        results.get(randomNumber).click();
        return new ProductDetailsPage(driver);
    }
}
