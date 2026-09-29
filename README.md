# Selenium Java E-Commerce Automation Framework

UI tests for [SauceDemo](https://www.saucedemo.com/) covering login, products, cart, checkout, logout, and purchase flows. Tests run locally or through Selenium Grid; the included Docker Compose file runs the Grid infrastructure only.

## Stack

Java 17, Maven, Selenium 4, TestNG, Jackson, Allure, and Log4j2.

## Framework features

- Page Objects with shared interaction and explicit wait utilities.
- JSON test data mapped to Java models and supplied through TestNG data providers.
- Local drivers and remote sessions, with a thread-local WebDriver instance and setup/teardown for each test invocation.
- Smoke and regression suites configured for parallel methods with eight threads.
- Test lifecycle logging and failure screenshots attached to Allure when a driver is available.

## Project structure

```text
src/main/java/io/github/arniejumaquio/framework/
  base/          Shared page behavior
  config/        Configuration loading
  driver/        Driver creation and thread-local storage
  listeners/     TestNG listeners
  pages/         Page Objects
  utils/         Waits, interactions, JSON loading, and reporting
src/test/java/io/github/arniejumaquio/tests/
  base/          Test setup and teardown
  models/        Test-data models and data providers
  login/, product/, cart/, checkout/, logout/, end_to_end/
src/test/resources/
  testdata/      JSON test cases
  qa.properties, staging.properties
  allure.properties, log4j2.xml
smoke.xml
regression.xml
docker-compose.yml
Jenkinsfile
pom.xml
```

## Prerequisites

- JDK 17 or newer and Maven installed.
- The selected browser installed for local runs. DriverFactory includes Chrome, Firefox, Edge, and Safari; Safari does not use the headless option.
- Docker with Compose for the included Grid setup.
- Allure CLI to view Allure reports.

Run the commands below from the project root.

## Configuration and credentials

`ConfigReader` loads `src/test/resources/qa.properties` by default. Use `-Denv=<name>` to select another `<name>.properties` file. A staging file exists, but its application and Grid addresses need to match your environment before use.

Configuration precedence is Java system properties, then environment variables, then the selected properties file. Environment variable names use uppercase with dots replaced by underscores, such as `GRID_URL` for `grid.url`. The environment file itself is selected through `-Denv`.

| Setting | QA default |
| --- | --- |
| `base.url` | `https://www.saucedemo.com/` |
| `browser` | `chrome` |
| `headless` | `true` |
| `execution` | `local` |
| `grid.url` | `http://localhost:4445/` |
| `page.load.timeout` | `15` seconds |
| `explicit.wait.timeout` | `15` seconds |

Before running tests, set valid SauceDemo credentials in the environment that launches Maven or in your IDE run configuration. Replace these example values:

```bash
export SAUCE_USERNAME='your-valid-saucedemo-username'
export SAUCE_PASSWORD='your-valid-saucedemo-password'
```

JSON files contain `${SAUCE_USERNAME}` and `${SAUCE_PASSWORD}` placeholders. `JSONUtils` resolves them through `ConfigReader` before creating test-data objects. Missing required configuration causes data loading to fail. Literal negative-test values are left unchanged. Credentials belong in the test process environment, not in the Grid containers.

## Run tests locally

```bash
# QA defaults: local Chrome, headless
mvn clean test -PSmoke
mvn clean test -PRegression

# Local Firefox with a visible browser
mvn clean test -PRegression -Dexecution=local -Dbrowser=firefox -Dheadless=false -Denv=qa

# One test method
mvn test -PRegression '-Dtest=LoginTest#validateValidLogin'
```

Profile names are case-sensitive: `Smoke` selects `smoke.xml`, and `Regression` selects `regression.xml`. Smoke includes methods in the `smoke` group; regression includes all tests in the six test classes. The POM does not wire a `-Dsuite` parameter to suite selection.

## Run with Dockerized Selenium Grid

Compose defines a Hub and one Node each for Chrome and Firefox. Each Node is configured for up to eight sessions; actual concurrency depends on available resources and matching tests. Maven and the Java tests run outside Docker.

```bash
docker compose up -d
docker compose ps
```

Open `http://localhost:4445` to check browser registration. Compose maps host port `4445` to Hub port `4444`; Nodes communicate with the Hub internally.

```bash
mvn clean test -PRegression \
  -Dexecution=remote \
  -Dgrid.url=http://localhost:4445 \
  -Dbrowser=chrome \
  -Dheadless=false
```

Use `-Dbrowser=firefox` for the Firefox Node or `-Dheadless=true` for headless execution. The included Compose setup has no Edge or Safari Nodes.

Stop and remove the Grid containers when finished:

```bash
docker compose down
```

## Reports and logs

| Output | Location |
| --- | --- |
| Allure results | `target/allure-results/` |
| Maven test reports | `target/surefire-reports/` |
| Failure screenshots | `target/screenshots/` |
| Execution log | `target/logs/test-execution.log` and console |

```bash
allure serve target/allure-results
```

Use `mvn clean test ...` for fresh results; `clean` removes previous outputs under `target`. `allure serve` displays existing results and does not run tests. The execution log is overwritten on each run.

## Jenkins CI

Two Jenkins jobs use the same root [Jenkinsfile](Jenkinsfile), loaded through **Pipeline script from SCM**. This project demonstrates continuous integration: running automated tests and publishing results. It does not deploy the application.

### Jobs and triggers

| Job | Trigger | Maven profile | Test suite |
| --- | --- | --- | --- |
| `SauceDemo-Smoke` | GitHub push | `Smoke` | Tests in the `smoke` group |
| `SauceDemo-Regression` | Nightly Jenkins schedule | `Regression` | All tests in the configured classes, including Smoke |

The triggers are configured in Jenkins and determine when each job starts. The shared Jenkinsfile controls what happens after it starts, using `env.JOB_BASE_NAME` to select the Maven profile. Other job names cause the pipeline to fail.

```text
GitHub push ------> SauceDemo-Smoke --------\
                                              \
                                               > Shared Jenkinsfile
                                              /
Nightly schedule -> SauceDemo-Regression ----/
                         |
                  JOB_BASE_NAME
                         |
             Smoke or Regression profile
                         |
                Checkout repository
                         |
                Start Docker Grid
                         |
               Inject credentials
                         |
                 Run tests
                         |
              Publish Allure report
                         |
               Stop Docker Grid
```

### Pipeline steps

1. `checkout scm` checks out the automation repository using the job's SCM configuration.
2. `docker compose up -d --wait` starts the Selenium Hub and browser nodes.
3. Jenkins binds the `saucedemo-login` credential to `SAUCE_USERNAME` and `SAUCE_PASSWORD` for the Maven step.
4. Maven runs the selected suite using remote, headless Chrome:

   ```bash
   # SauceDemo-Smoke
   mvn clean test -PSmoke -Dexecution=remote -Dbrowser=chrome -Dheadless=true

   # SauceDemo-Regression
   mvn clean test -PRegression -Dexecution=remote -Dbrowser=chrome -Dheadless=true
   ```

5. Tests write raw Allure results to `target/allure-results`. Jenkins publishes the report in `post { always { ... } }`, including after test failures.
6. `post { cleanup { ... } }` runs `docker compose down`, including if report publication fails.

Maven and the Java tests run on the Jenkins agent. Browser sessions run in Docker through Selenium Grid.

### Jenkins configuration

- Both jobs use **Pipeline script from SCM**, with the script path set to `Jenkinsfile`.
- Jenkins Tools provides Maven and the JDK through installations named `Maven-3.9.9` and `JDK-21`. The project targets Java 17.
- Docker with Compose must be available on the Jenkins agent, with access to the Docker daemon. The current Grid address is `http://localhost:4445/`.
- SauceDemo secrets are stored in Jenkins Credentials Store and are not committed to Git. The Jenkinsfile contains only the credential ID and environment variable names.
- The Jenkins Allure plugin and its command-line tool must be configured to publish reports.

Both jobs use host port `4445`, so they must not overlap on the same Docker host. `disableConcurrentBuilds()` prevents overlapping builds of one job; it does not prevent Smoke and Regression from running together.
