using OpenQA.Selenium;
using Selenium.WebDriver.SelfHealing;

namespace SelfHealingLocatorsDemo;

public class Tests : TestBase
{
    ISelfHealingWebDriver _driver;
    string _testCaseId;

    [SetUp]
    public void Setup()
    {
        _driver = DriverManager.GetDriver();
        _testPassed = false;
    }

    [TearDown]
    public void TearDown()
    {
        _driver.Close();
        _driver.Quit();
        string outcome = _testPassed==true? "PASSED" : "FAILED";
        File.AppendAllText(_reportFilePath, $"{_testCaseId} - { outcome }\n");
    }

    [TestCaseSource(nameof(TestDataSite123))]
    public void TestSite123(string testCaseId, string url, By inputTextLocator, By updateButtonLocator, string classification)
    {
        // arrange
        _testCaseId = testCaseId;
        string expectedMessage = "Testing site 123";
        FormPageSite123 formPage = new(url, inputTextLocator, updateButtonLocator, classification, _driver);
        
        // act
        formPage.UpdateLabel(expectedMessage);

        // assert
        Assert.That(formPage.GetLabel().Contains(expectedMessage));
        _testPassed = true;
    }

    [TestCaseSource(nameof(TestDataSite45))]
    public void TestSite45(string testCaseId, string url, By buttonLocator, string classification)
    {
        // arrange
        _testCaseId = testCaseId;
        string expectedMessage = "Go to Gorilla Logic site";
        FormPageSite45 formPage = new(url, buttonLocator, classification, _driver);
        
        // act
        formPage.RevealHiddenLink();

        // assert
        Assert.That(formPage.GetPageLink().Equals(expectedMessage));
        _testPassed = true;
    }
}