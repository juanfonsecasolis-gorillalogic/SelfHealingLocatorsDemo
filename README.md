# Self-healing locators

Juan M. Fonseca Solís

Gorilla Logic, Sabana Business Center 10th Floor, Bv. Ernesto Rohrmoser, San José.

<img width="250" height="100" src='img/GL_Logo_Primary_Blk.png'/>

## Abstract

**Problem.** Evaluate some popular self-healing driver options.

**Importance.** Self-healing drivers can reduce automation maintenance costs by automatically detecting changes in the web page layout and updating locators to ensure the test cases are functional [1].

**Theory.**  For DotNet we found that the library Selenium.WebDriver.SelfHealing is the only available NuGet package in the official repositories and that it offers self-healing capabilities with a privative license (it allows a monthly quota for free). For Java, TestRigor and Healenium were available solutions, the first one with a privative license (with a fifteen days trial) whereas Healenium is free [2, 3]. Only Healenium offered an explanation of its internal functioning, and apparently, only Healenium and TestRigor use Artificial Intelligence.

**Method.** We evaluated all libraries using the same test procedure, which is entering a value in an input field and confirming that value was displayed in a label after the user pressed a button. The test cases of table 1 consisted in a combination of locators that could be "valid" or "broken" depending on the site implementation (table 2). The expectation was that the self-healing libraries could heal the locators after presenting the valid examples. 

For DotNet we implemented a project using the Selenium/nUnit combo, and for Java, we implemented a project using Maven/TestNg/Selenium. The test cases were executed in Healenium's docker hub container [2, 3].

Table 1. Test cases.

| TC | Proposed by | Site | Locator 1 | Locator 2 | Locator Configuration |
| --- | --- | --- | --- | --- | --- |
| TC1 | TestRigor | Site #1 | //input[@id='messageNew'] | //button[@id='changer'] | valid |
| TC2 | TestRigor | Site #2 | //input[@id='messageNew'] | //a[@id='pusher'] | valid |
| TC3 | TestRigor | Site #1 | //input[@id='messageNew'] | //a[@id='pusher'] | broken |
| TC4 | TestRigor | Site #2 | //input[@id='messageNew'] | //button[@id='changer'] | broken |
| TC5 | Healenium | Site #1 | //input[@placeholder='Message'] | //button[@id='changer'] | valid |
| TC6 | Healenium | Site #3 | //input[@placeholder='Enter some text'] | //button[@id='changer'] | valid |
| TC7 | Healenium | Site #1 | //input[@placeholder='Enter some text'] | //button[@id='changer'] | broken | 
| TC8 | Healenium | Site #3 | //input[@placeholder='Message'] | //button[@id='changer'] | broken |

Table 2. Site configuration.

| Page | Input box | Button |
| --- | --- | --- |
| [Site #1](demo-site/form-button-label.html)  | `\\input[@placeholder="Message"]`           | `\\button[@id='changer']` |
| [Site #2](demo-site/form-button-label2.html) | `\\input[@placeholder="Message"]`           | `\\a[@id='pusher']` |
| [Site #3](demo-site/form-button-label3.html) | `\\input[@placeholder="Enter some text"]`   | `\\button[@id='changer']` | 

**Results.** As shown by table 3, Healenium was the only solution that worked solving the self-proposed scenarios. 

Table 3. Results. 

| TC | Passed | Failed |
| --- | --- | --- |
| TC1 | NoSelfHealing, Healenium | TestRigor |
| TC2 | NoSelfHealing, Healenium | TestRigor |
| TC3 | - | NoSelfHealing, Healenium, TestRigor |
| TC4 | - | NoSelfHealing, Healenium, TestRigor |
| TC5 | NoSelfHealing, Healenium | TestRigor |
| TC6 | NoSelfHealing, Healenium | TestRigor |
| TC7 | Healenium | NoSelfHealing, TestRigor |
| TC8 | Healenium | NoSelfHealing, TestRigor |

**Future work.** 
1. Find more scenarios of broken locators to evaluate the implemented libraries.
2. Incorporate more libraries.
3. For the Healenium case, evaluate the proposed test cases at the database level and explain the internal functioning.

## Reproducibe results

Please follow the steps below to reproduce the results obtained:
1. If running for the first time, run `cd healenium; docker-compose up -d;`, otherwise start the Healenium's containers using Docker Desktop.
2. Execute the non-self-healing suite: `mvn clean test -DDriverType=Chrome`.
3. Copy test report at target/surefire-reports/index.html to a folder named Chrome.
4. Repeat steps 2-3 with the other suites:
```
mvn clean test -DDriverType=ChromeHealenium
mvn clean test -DDriverType=ChromeTestRigor
```

**Note:** This solution also comes with a YAML file that runs in Github Actions.

### Image versions for Healenium:
* healenium/hlm-backend:3.4.5          
* selenium/node-chrome:latest (Google Chrome 128.0.6613.119)      
* healenium/hlm-proxy:2.0.0            
* healenium/hlm-selector-imitator:1.4  
* selenium/hub:latest                  
* postgres:15.5-alpine   

## References
1. Herschmann, J., Murphy, T., Scheibmeir, J., O'Connor, F., & Wan, D. D. K. (2024, February 13). Market guide for AI-augmented software-testing tools (ID G00783848). Gartner.
2. Anton Angelov. [Healenium: Self-Healing Library for Selenium-based Automated Tests](https://www.automatetheplanet.com/healenium-self-healing-tests/). Automate the Planet (last consulted on 09/12/2024).
3. TestRigor. [Selenium self-healing](https://testrigor.com/selenium-self-healing) (last consulted on 09/30/24).
4. Hungarian Testing Board - Magyar Szoftvertesztelői Tanács Egyesület. [Stabilization of E2E tests with Healenium](https://www.youtube.com/live/ttuZkpCOt3g?si=UYtog10_U-fsAae_&t=1566). YouTube (last consulted on 10/01/24).