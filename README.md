# Self-healing locators

[![Java CI with Maven](https://github.com/juanfonsecaGL/SelfHealingLocatorsDemo/actions/workflows/maven.yml/badge.svg)](https://github.com/juanfonsecaGL/SelfHealingLocatorsDemo/actions/workflows/maven.yml)

Juan M. Fonseca Solís

Gorilla Logic, Sabana Business Center 10th Floor, Bv. Ernesto Rohrmoser, San José.

<img width="250" height="100" src='img/GL_Logo_Primary_Blk.png'/>

## Abstract

**Problem.** Evaluate some popular self-healing driver options.

**Importance.** Self-healing drivers can reduce automation maintenance costs by automatically detecting changes in the web page layout and updating locators to ensure the test cases are functional [1].

**Theory.** Selenium.WebDriver.SelfHealing is the only available library for DotNet in the NuGet repository, and Healenium and TestRigor are only Java libraries. Selenium.WebDriver.SelfHealing offers a free monthly quota, Healenium is free without limit, and TestRigor is private with a fifteen-day trial [2, 3]. Only Healenium and TestRigor mentioned to be using of Artificial Intelligence. 

**Method.** We evaluated all libraries using two test procedures. In the first one, the user opens a web form, enters a value in an input field, submit the form by pressing a button, and confirms that the value is displayed in a label (test cases 1-8 in Table 1). In the second one, the user enters a form and presses a button to reveal a hidden link (test cases 9-12 in Table 1). The test procedures were implemented in a DotNet project using Selenium/nUnit and in a Java project using Maven/TestNg/Selenium. By design, the Healenium project runs in Selenium Hub on a Docker container. The configurations of the web sites were proposed by TestRigor and Chernyshova and were illustrated in Figures 1 and 2 [3, 4, 5]. 

Table 1. Test cases.

| TC | Proposed by | Site | Input box | Button | Locator Configuration |
| --- | --- | --- | --- | --- | --- |
| TC1 | TestRigor | #1 | `//input[@id='messageNew']` | `//button[@id='changer']` | valid |
| TC2 | TestRigor | #2 | `//input[@id='messageNew']` | `//a[@id='pusher']` | valid |
| TC3 | TestRigor | #1 | `//input[@id='messageNew']` | `//a[@id='pusher']` | broken |
| TC4 | TestRigor | #2 | `//input[@id='messageNew']` | `//button[@id='changer']` | broken |
| TC5 | Chernyshova | #1 | `//input[@placeholder='Message']` | `//button[@id='changer']` | valid |
| TC6 | Chernyshova | #3 | `//input[@placeholder='Enter some text']` | `//button[@id='changer']` | valid |
| TC7 | Chernyshova | #1 | `//input[@placeholder='Enter some text']` | `//button[@id='changer']` | broken | 
| TC8 | Chernyshova | #3 | `//input[@placeholder='Message']` | `//button[@id='changer']` | broken |
| TC9 | Chernyshova | #4 | - | `//div[@class='container']/div/div/button` | valid |
| TC10 | Chernyshova | #4 | - | `//div[@class='container']/button` | broken |
| TC11 | Chernyshova | #5 | - | `//div[@class='container']/button` | valid |
| TC12 | Chernyshova | #5 | - | `//div[@class='container']/div/div/button` | broken |

Table 2. Website valid locators.

| Page | Input box | Button |
| --- | --- | --- |
| [Site #1](demo-site/demo-site1/form-button-label.html)  | `//input[@placeholder="Message"]`           | `//button[@id='changer']` |
| [Site #2](demo-site/demo-site1/form-button-label2.html) | `//input[@placeholder="Message"]`           | `//a[@id='pusher']` |
| [Site #3](demo-site/demo-site1/form-button-label3.html) | `//input[@placeholder="Enter some text"]`   | `//button[@id='changer']` | 
| [Site #4](demo-site/demo-site2/reveal-link1.html) | -   | `//div[@class='container']/div/div/button` |
| [Site #5](demo-site/demo-site2/reveal-link2.html) | -   | `//div[@class='container']/button` | 

Figure 1. Configuration for websites 1 to 3.

<img src='img/DOM_diagram_site1.png'/>

Figure 2. Configuration for websites 4 and 5.

<img src='img/DOM_diagram_site2.png'/>

**Results.** As shown by Table 3, Healenium is the solution with the highest pass-rate and the only one that passed its own proposed scenario. 

Table 3. Results. 

| TC | NoSelfHealing | TestRigor<sup>1</sup> | Healenium | DotNet SelfHealing | 
| --- | --- | --- | --- | --- |
| TC1 | ✓ | x | ✓ | ✓ |
| TC2 | ✓ | x | ✓ | ✓ |
| TC3 | x | x | x | x |
| TC4 | x | x | x | x |
| TC5 | ✓ | x | ✓ | ✓ |
| TC6 | ✓ | x | ✓ | ✓ |
| TC7 | x | x | ✓ | x |
| TC8 | x | x | ✓ | x |
| TC9 | ✓ | x | ✓ | ✓ |
| TC10 | x | x | x | x |
| TC11 | ✓ | x | ✓ | ✓ |
| TC12 | x | x | ✓ | x |
| Pass rate | .5 | .0 | .75 | 0.5 |

<sup>1</sup> For TestRigor we got a "Java.lang.NoClassDefFoundError: com/testrigor/selfhealingselenium/infrastructure/adapters/TestrigorAdapter" error.

**Future work.** 
1. Investigate a solution for the "TestrigorAdapter" issue.
2. Evaluate the chosen libraries against more test cases and websites.
3. Incorporate more self-healing libraries (like LambdaTest or BrowserStack).

## Reproducible results

Please follow the steps below to reproduce the results obtained:
1. If running for the first time, run `cd healenium; docker-compose up -d;`, otherwise start the Healenium's containers using Docker Desktop.
2. Execute the non-self-healing suite: `cd ..; mvn clean test -DDriverType=Chrome`.
3. Copy the test report at target/surefire-reports/index.html to a folder named Chrome.
4. Repeat steps 2-3 with the other suites:
```
mvn clean test -DDriverType=ChromeHealenium
mvn clean test -DDriverType=ChromeTestRigor
```
5. For the case of Healenium, if you run the project locally (not in Github Actions) you can see the report by accessing http://localhost:7878/healenium/report/ (hint provided by Rajasekar Kulasekaran).

**Note:** This solution also comes with a YAML file that runs in GitHub Actions.

### Image versions for Healenium:
* healenium/hlm-backend:3.4.5          
* selenium/node-chrome:latest (Google Chrome 128.0.6613.119)      
* healenium/hlm-proxy:2.0.0            
* healenium/hlm-selector-imitator:1.4  
* selenium/hub:latest                  
* postgres:15.5-alpine   

## Note about Healenium
Healenium uses an algorithm called "Longest Common Subsequence" (LCS). The algorithm works as follows. The webpage layout is represented in a tree data structure, where each node of the tree contains a list of numerical weights assigned to the node's atributes (for instance, tag, Id, class, and value). For each node in the tree, the LCS calculates the sequence or "path" of previous nodes needed to arrive to that node (presumably from the html's root) and stores it in a database. Then, when a layout change invalidates a path, the algorithm searches a replacement using the closest subsequence [5]:

> "It solves the problem of finding the longest subsequence common to all sequences in a set of sequences with extra weight for tag, Id, class, value, other attributes. So if an element changes its place in DOM or has a new id, Healenium will find it and generate new locator." - Chernyshova, A.

## References
1. Herschmann, J., Murphy, T., Scheibmeir, J., O'Connor, F., & Wan, D. D. K. (2024, February 13). Market guide for AI-augmented software-testing tools (ID G00783848). Gartner.
2. Angelov, A. [Healenium: Self-Healing Library for Selenium-based Automated Tests](https://www.automatetheplanet.com/healenium-self-healing-tests/). Automate the Planet (last consulted on 09/12/2024).
3. TestRigor. [Selenium self-healing](https://testrigor.com/selenium-self-healing) (last consulted on 09/30/24).
4. Chernyshova, A. Hungarian Testing Board - Magyar Szoftvertesztelői Tanács Egyesület. [Stabilization of E2E tests with Healenium](https://www.youtube.com/live/ttuZkpCOt3g?si=UYtog10_U-fsAae_&t=1566). YouTube (last consulted on 10/01/24).
5. Chernyshova, A. Healenium: Self-Healing Library for Selenium Test Automation. Medium, Geek Culture. URL: https://medium.com/geekculture/healenium-self-healing-library-for-selenium-test-automation-26c2358629c5 (last consulted on 10/16/24).