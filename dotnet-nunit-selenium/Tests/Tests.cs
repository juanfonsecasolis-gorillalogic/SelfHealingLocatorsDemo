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

    [TestCaseSource(nameof(TestData))]
    public void FormTest(string testCaseId, string url, By inputTextLocator, By updateButtonLocator, string classification)
    {
        // arrange
        _testCaseId = testCaseId;
        string expectedMessage = "Testing 123";
        FormPage formPage = new(url, inputTextLocator, updateButtonLocator, classification, _driver);
        
        // act
        formPage.UpdateLabel(expectedMessage);

        // assert
        Assert.That(formPage.GetLabel().Contains(expectedMessage));
        _testPassed = true;
    }
}