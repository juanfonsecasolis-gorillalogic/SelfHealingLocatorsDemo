# Self-healing locators

Juan M. Fonseca Solís</br>
Gorilla Logic, Sabana Business Center 10th Floor, Bv. Ernesto Rohrmoser, San José.

<img width="275" height="100" src='img/GL_Logo_Primary_Blk.png'/>

## Abstract

**Problem.** Evaluate some popular self-healing driver options.

**Importance.** Self-healing drivers can reduce automation maintenance costs by automatically detecting changes in the web page layout and updating locators to ensure the test cases are functional [1].

**Method.** For DotNet we implemented a sample project using Selenium.WebDriver.SelfHealing (the only available package in the NuGet repositories), and for Java we implemented a project that uses TestRigor and Healenium [2, 3]. We evaluated all implementations against the tests proposed by each one:

    * Test #1: 
    * Test #2: 

**Results.** To be defined...

**Future work.** Find more examples or scenarios of broken locators to evaluate the implemented libraries and incorporate more libraries.

## Recreate the results
1. Start the Docker containers using Docker Desktop (or if running the first time, `cd healenium; docker-compose up -d;`).
2. Execute the desired suites: 
```
mvn clean test -DDriverType=Chrome
mvn clean test -DDriverType=ChromeHealenium
mvn clean test -DDriverType=ChromeTestRigor
```
3. The test report can be found in target/surefire-reports/index.html.

## References
1. Herschmann, J., Murphy, T., Scheibmeir, J., O'Connor, F., & Wan, D. D. K. (2024, February 13). Market guide for AI-augmented software-testing tools (ID G00783848). Gartner.
2. Anton Angelov. Healenium: Self-Healing Library for Selenium-based Automated Tests. Automate the Planet. URL: https://www.automatetheplanet.com/healenium-self-healing-tests/ (last consulted on 09/12/2024)
3. TestRigor. Selenium self-healing. URL: https://testrigor.com/selenium-self-healing/ (09/30/24)