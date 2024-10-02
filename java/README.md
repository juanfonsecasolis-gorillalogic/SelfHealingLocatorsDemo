# Self-healing locators investigation
Juan Fonseca, Gorilla Logic 2024.

PoC for implementing Self Healing Locators in a Selenium Java project.

# Creation
See [1].

# Execution
```
docker-compose up -d
mvn clean test
```

# Tools
## com.epam.healenium

Pros:
* Free.
* Uses a ML algorithm.

Cons:
* Only available for Java.

# References
1. Anton Angelov. Healenium: Self-Healing Library for Selenium-based Automated Tests. Automate the Planet. URL: https://www.automatetheplanet.com/healenium-self-healing-tests/ (last consulted on 09/12/2024)