# WebDriver + Java + TestNG framework

Site under test: https://www.saucedemo.com (swap pages/data for your own Module 8 application if required).

## Run
    mvn clean test                                                   # chrome, qa, smoke
    mvn clean test -Dbrowser=firefox -Denv=staging -Dsuite=regression
    mvn clean test -Dheadless=true -Dlog.console.level=INFO

| Parameter | Values | Default |
|---|---|---|
| -Dbrowser | chrome, firefox, edge | chrome |
| -Denv | qa, staging (src/test/resources/config/<env>.properties) | qa |
| -Dsuite | smoke, regression (src/test/resources/suites/testng-<suite>.xml) | smoke |
| -Dheadless | true/false | false |
| -Dhighlight.enabled | true/false | true |
| -Dlog.console.level | DEBUG/INFO/... | DEBUG |

Any key of the properties file can be overridden with -Dkey=value.

## Logs
Console + `logs/test.log` (rolled daily to `logs/test-yyyy-MM-dd.log`). Levels used: DEBUG, ACTION (custom), INFO, WARN, ERROR.

## Screenshots on failure
`target/screenshots/<Class>_<test>_<timestamp>.png`; the path is written to the log (`Screenshot saved: ...`).
To see it: run with `-Dproduct.price='$0.00' -Dsuite=regression` (price assertion fails).

## Jenkins
Use the Jenkinsfile (parameterized pipeline). The `junit` step shows the test-result trend graph; `archiveArtifacts` stores screenshots and logs.

## Patterns and SOLID
See `SOLID_AND_PATTERNS.md` (Singleton, Factory Method, Decorator, Builder + table of SOLID corrections).
