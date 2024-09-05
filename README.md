# Self-healing locators investigation
Juan Fonseca, Gorilla Logic 2024.

PoC for implementing Self Healing Locators in a Selenium DotNet project.

# Creation
```
dotnet new nunit
dotnet add package Selenium.WebDriver --version 4.24.0
dotnet add package Selenium.WebDriver.SelfHealing --version 0.99.21-beta.1
```

# Execution
```
dotnet test SelfHealingLocatorsDemo.csproj
```

# Tools
## Selenium.WebDriver.SelfHealing
Library that implements self-healing, auto-wait, and selector-optimizations. 
* **Self-healing:** web elements can still be found when they change position (avoid stale element error).
* **Auto-wait:** "waits for elements to be actionable prior to performing actions" (avoid "element cannot be found" error).
* **Selector-optimization.**

This library doesn't solve the problem of finding a locator with a renamed attribute (for instance, a mispelled ID), and it is constrained by an usage quota [3]:

> Subject to the terms of this Agreement, Licensee is granted a right to use the Software for small projects and evaluation purposes without charge provided that use of the Software is within the “Free Usage Quota” published on Licensor’s website

> Upon exceeding the Free Usage Quota limits, Licensee must obtain License Key for continued use of the Software or cease using the Software.

> The Software contains features that removes technical restrictions and automatically disables the Software upon exceeding the free usage limits.

The [quota mentioned page](deliveryassured.com.au/selenium.webdriver.selfhealing) is broken.

# References
1. Courtney Zhan. Set up and Run Selenium C# Tests in Visual Studio Code on macOS. Medium, Apr 2, 2023. URL: https://courtneyzhan.medium.com/set-up-and-run-selenium-c-tests-in-visual-studio-code-on-macos-179ee9f4a46e (last consulted on 09/05/2024)
2. Marcus Hammarberg. Writing a Selenium test using .NET core and Visual Studio Code. MarcuSoft blog, Oct 7th 2021. URL: https://www.marcusoft.net/2021/10/setting-up-selenium-web-automation-with-net-core.html (last consulted on 09/05/2024)
3. Craig Richards. Selenium.WebDriver.SelfHealing. NuGet gallery, Microsoft. URL: https://www.nuget.org/packages/Selenium.WebDriver.SelfHealing/0.99.21-beta.1 (last consulted on 09/05/2024)
