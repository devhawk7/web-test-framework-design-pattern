# Design patterns and SOLID corrections

## Patterns (all of them are executed on every test run)
| Pattern | Classes | Where it is invoked |
|---|---|---|
| Singleton | `ConfigReader` (holder idiom, lazy, thread-safe) | every page, factory, test-data creator and test reads config through `ConfigReader.getInstance()` |
| Factory Method | `WebDriverFactory` (creator, factory method `createDriver`) + `ChromeDriverFactory`, `FirefoxDriverFactory`, `EdgeDriverFactory`; `BrowserType` | `DriverManager.getDriver()` -> `BrowserType.factory().create()` in `BaseTest.setUp()` |
| Decorator | `WebElementDecorator` + `LoggingDecorator`, `WaitingDecorator`, `HighlightingDecorator`; plugged into PageFactory by `DecoratingFieldDecorator` | every `@FindBy` field of every page; visible in log as `ACTION ... Click on 'login button'` and in the browser as highlighted elements |
| Builder (bonus) | `User.Builder`, `Product.Builder` | `TestDataCreator` - used by all tests |

## SOLID corrections
| Class | Problem | Solution |
|---|---|---|
| DriverManager | **SRP** - managed driver lifecycle AND contained creation/options code for every browser | Only lifecycle (`getDriver/peek/quit`) is left. Creation moved to `ChromeDriverFactory`, `FirefoxDriverFactory`, `EdgeDriverFactory` |
| DriverManager | **OCP** - `switch` on browser type; every new browser required editing the class | `BrowserType` maps to a `WebDriverFactory`; a new browser = new constant + new factory subclass, `DriverManager` untouched |
| DriverManager | **DIP** - high-level class depended on concrete `ChromeDriver/FirefoxDriver/EdgeDriver` | Depends on the `WebDriverFactory` abstraction |
| AbstractPage | **SRP** - mixed page initialisation with `click/type/textOf` helpers that also waited, highlighted and logged (plus a static `HighlightUtil`) | Helpers and `HighlightUtil` removed. Waiting, highlighting and logging are separate decorators; pages call `element.click()` directly |
| AbstractPage / LoginPage / InventoryPage | **LSP** - `isOpened()` promised a boolean but some pages threw `NoSuchElementException` when the page was not open | `isOpened()` is final in the base class and never throws; subclasses implement `checkOpened()` |
| ConfigReader | Static mutable-style utility (hidden global state) | Converted to a Singleton instance with immutable state |
| WebElement wrappers | **ISP** - reviewed, no violation: `WebDriverFactory` has a single method, decorators implement only the `WebElement` contract | - |
