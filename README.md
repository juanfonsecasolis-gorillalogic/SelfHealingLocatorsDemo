# Self-healing locators

[![Java CI with Maven](https://github.com/juanfonsecaGL/SelfHealingLocatorsDemo/actions/workflows/maven.yml/badge.svg)](https://github.com/juanfonsecaGL/SelfHealingLocatorsDemo/actions/workflows/maven.yml)

Juan M. Fonseca Solís

Gorilla Logic, Sabana Business Center 10th Floor, Bv. Ernesto Rohrmoser, San José.

<img width="250" height="100" src='img/GL_Logo_Primary_Blk.png'/>

## Abstract

**Problem.** Evaluate some popular self-healing driver options.

**Importance.** Self-healing drivers can reduce automation maintenance costs by automatically updating locators after detecting changes in the web page layout [1].

**Previous work.** Doing a literacy review of self-healing libraries we found Healenium, a popular open-source solution that works with Selenium, a solid test framework used by test teams since 2004. Healenium is not free-software, meaning that using it doesn't force development teams to release their code, but is free to use and users would need to pay only if they want to get support. For the moment, Healenium only works with Java, and so do another solution called TestRigor, which is private with a fifteen-day trial [2, 3]. For C#, a different language used in automation, we found Selenium.WebDriver.SelfHealing; the only available NuGet official package for self-healing that comes with a free monthly quota [6]. Only Healenium and TestRigor mentioned to be using of Artificial Intelligence. 

**Method.** We evaluated all libraries using two different combination of web sites and test procedures. 

The first configuration is illustrated in Figure 1 and consists in a mix of scenarios proposed by TestRigor and Chernyshova; on it the user opens a web form, enters a value in an input field, submit the form by pressing a button, and confirms that the value is displayed in a label. This configuration corresponds to test cases 1-8 in Table 1 and sites 1-3 in Table 2 [3, 4]. 

The second configuration is depicted in Figure 2 and was proposed by Chernyshova; on it the user presses a button to reveal a hidden link in a form. This configuration corresponds to test cases 9-12 in Table 1 and sites 4-5 on Table 2 [5]. 

All test procedures were implemented in both, a DotNet/Selenium/nUnit project and a Java/Maven/TestNg/Selenium project. For Java, we chose Selenium Hub on a Docker container for using Healenium. 

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

**Results.** As shown by Table 3, Healenium is the solution with the highest pass rate and the only one that passed almost all of its own proposed scenario. 

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
| Pass rate | .5 | .0 | .75 | .5 |

<sup>1</sup> For TestRigor we got a _Java.lang.NoClassDefFoundError: com/testrigor/selfhealingselenium/infrastructure/adapters/TestrigorAdapter_ error.

**Future work.** 
1. Find more libraries (like LambdaTest or BrowserStack) for other languages better suited for automation, like Python or Ruby.
2. Find a solution for the TestRigor's "TestrigorAdapter" issue.
2. Evaluate the chosen libraries against more test cases and websites.

## Reproducible results

Please follow the steps below to reproduce the results obtained:
1. Clone [https://github.com/juanfonsecasolis-gorillalogic/SelfHealingLocatorsDemo](https://github.com/juanfonsecasolis-gorillalogic/SelfHealingLocatorsDemo)
2. If executing for the first time, run `cd healenium; docker-compose up -d;`, otherwise start the Healenium's containers using Docker Desktop.
3. Execute the non-self-healing suite: `cd ..; mvn clean test -DDriverType=Chrome`.
4. Copy the test report at target/surefire-reports/index.html to a folder named Chrome.
5. Repeat steps 2-3 with the other suites:
```
mvn clean test -DDriverType=ChromeHealenium
mvn clean test -DDriverType=ChromeTestRigor
```
6. For the case of Healenium, if you run the project locally (not in Github Actions) you can see the report by accessing [http://localhost:7878/healenium/report/](http://localhost:7878/healenium/report/) (hint provided by Rajasekar Kulasekaran).

**Note:** This solution also comes with a YAML file that runs in GitHub Actions.

### Image versions for Healenium:
* healenium/hlm-backend:3.4.5          
* selenium/node-chrome:latest (Google Chrome 128.0.6613.119)      
* healenium/hlm-proxy:2.0.0            
* healenium/hlm-selector-imitator:1.4  
* selenium/hub:latest                  
* postgres:15.5-alpine   

## Note about Healenium
Healenium uses an algorithm called "Longest Common Subsequence" (LCS). The algorithm works as follows. The webpage layout is represented in a tree data structure. Each node of the tree represents a web element and contains a list of numerical weights associated to the atributes (for instance, tag, Id, class, and value). For each node in the tree, the LCS calculates the path, or sequence of nodes, from the root of the tree (the HTML element). This path is like a web relative locator. Then, when a change in the layout invalidates a path, the algorithm uses the previously calculated weights and paths (from other nodes) to find the closest subsequence and performs a recovery [5]. In Chernyshova, A. words:

> "It solves the problem of finding the longest subsequence common to all sequences in a set of sequences with extra weight for tag, Id, class, value, other attributes. So if an element changes its place in DOM or has a new id, Healenium will find it and generate new locator."

## References
1. Herschmann, J., Murphy, T., Scheibmeir, J., O'Connor, F., & Wan, D. D. K. (2024, February 13). Market guide for AI-augmented software-testing tools (ID G00783848). Gartner.
2. Angelov, A. [Healenium: Self-Healing Library for Selenium-based Automated Tests](https://www.automatetheplanet.com/healenium-self-healing-tests/). Automate the Planet (last consulted on 09/12/2024).
3. TestRigor. [Selenium self-healing](https://testrigor.com/selenium-self-healing) (last consulted on 09/30/24).
4. Chernyshova, A. Hungarian Testing Board - Magyar Szoftvertesztelői Tanács Egyesület. [Stabilization of E2E tests with Healenium](https://www.youtube.com/live/ttuZkpCOt3g?si=UYtog10_U-fsAae_&t=1566). YouTube (last consulted on 10/01/24).
5. Chernyshova, A. Healenium: Self-Healing Library for Selenium Test Automation. Medium, Geek Culture. URL: https://medium.com/geekculture/healenium-self-healing-library-for-selenium-test-automation-26c2358629c5 (last consulted on 10/16/24).
6. Richards, C. Selenium.SelfHealing.v3. Nuget, Microsoft. URL: https://www.nuget.org/packages/Selenium.SelfHealing.v3/0.99.24-beta.1 (last consulted on 21/16/24).
