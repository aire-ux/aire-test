# aire-test

Aire Testing Framework is a powerful and convenient framework for testing Vaadin components
and interactions. Aire Test builds off of the fantastic
[Karibu in-memory testing framework](https://github.com/mvysny/karibu-testing)
and extends it with:

1. Complete CSS selectors for navigating the in-memory DOM
2. Annotation-driven route-discovery
3. Pluggable DOM-rewriting functionality
4. Transparent mocking

You may locate the latest
version [here](https://mvnrepository.com/search?q=%22io.sunshower.aire-test%22)

## Examples

### Step 1: Install Aire-Test

Aire-Test publishes a bill-of-materials containing all
of its subprojects. If you're using Maven, simply add:

```xml

<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.sunshower.aire-test</groupId>
      <artifactId>bom-exported</artifactId>
      <version>${aire-test.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

```

to your POM file. If you're using the Gradle with the Spring dependencies plugin (recommended),
you can add

```groovy
    dependencyManagement {
    imports {
        mavenBom "io.sunshower.aire-test:bom-imported:$version"
    }
}
```

to your build-file and have access to all of the relevant project
versions

### Gradle

Add

```groovy
    dependencyManagement {
    imports {
        mavenBom "io.sunshower.aire-test:bom-imported:$version"
    }
}
```

to your `build.gradle` file (if using the Spring dependency management plugin)

Or add the desired projects:

1. io.sunshower.aire-test:aire-test-common:$aireVersion // common testing infrastructure
1. io.sunshower.aire-test:aire-test-vaadin:$aireVersion // if not using spring
1. io.sunshower.aire-test:aire-test-spring:$aireVersion // if using spring

## Scenario

For both the Spring and the Vaadin scenarios we have the follow use-case:

```java

@Route("main")
public class MainLayout extends Section {

  public MainLayout() {
    getElement().getClassList().add("main-layout"); //note--for selectors
    add(new Span("hello"), new Span("World"), new Checkbox("Click me, bub!"));
  }
}
@Route("secondary")
public class SecondaryLayout extends Div {

  public SecondaryLayout() {
    val button = new Button("waddup");
    button.setClassName("aire-button");
    add(button);
  }
}
```

Both routes reside in the same package here, but this is not a requirement

### Plain Vaadin

```java
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.aire.ux.test.AireTest;
import com.aire.ux.test.Navigate;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.Select;
import com.aire.ux.test.ViewTest;
import com.aire.ux.test.vaadin.scenarios.routes.MainLayout;

/**
 * Run this test with the Vaadin Test Runner.  Equivalent to:
 *
 * @Order(50)
 * @Target(ElementType.TYPE)
 * @Retention(RetentionPolicy.RUNTIME)
 * @ExtendWith(VaadinExtension.class) public @interface AireTest {}
 */
@AireTest

/**
 * @RouteLocation is repeatable and can be supplied either a base package class
 * or a package-name
 */
@RouteLocation(scanPackage = "com.aire.ux.test.vaadin.scenarios.routes")
public class VaadinTestCaseTest {

  /**
   * `@ViewTest` runs this test separately from 
   * standard tests (e.g. annotated with 
   * `@Test`, `@RepeatedTest`, 
   * `@ParameterizedTest`, etc.)
   */
  @ViewTest

  /**
   * `@Navigate` ensures that this test is being run on the desired page, in this case, "main".
   * You can navigate to other pages within the same test
   */
  @Navigate("main")
  void ensureVaadinRootViewCanBeInjected(@Select MainLayout layout, @Context TestContext $) {
    assertNotNull(layout);
    
    // you can retrieve MainLayout via CSS selector or class-name:
    
    $.selectFirst("section.main-layout").get(); // returns an option
    
  }


  /**
   * you can inject components by CSS selector as well.
   * Omitting the @Navigate annotation we can still reach the route:
   */
  @ViewTest
  void ensureVaadinRootViewCanBeInjected(@Select("section.main-layout") MainLayout layout, @Context TestContext $) {
    
    $.navigate(MainLayout.class); //navigate to this route (the actual path works as well)
    assertNotNull(layout);
    $.downTo(MainLayout.class).selectFirst("span[text='hello']").get(); // will select the first span
  }
}

```

### Spring 
Spring support works identically to the vanilla Vaadin support above, but Spring injection annotations
can be used with parameters as well.  Spring support can be enabled by adding the `@EnableSpring` annotation

```java
@AireTest
@EnableSpring
@RouteLocation(scanClassPackage = com.aire.ux.spring.test.scenario2.TestService.class)
@ContextConfiguration(classes = Scenario2Configuration.class)
public class AdjacentSpringTest {

  @Inject private TestService service;

  @Test
  void ensureServiceIsInjected() {
    assertNotNull(service);
  }

  @ViewTest
  @Navigate("main")
  void ensureSpringValueIsInjected(@Select MainView view) {
    assertNotNull(service);
    assertNotNull(view.getService());
  }

  @ViewTest
  @Navigate("main")
  void ensureInjectingCssSelectedValueWorks(
      @Autowired TestService service, @Select(".main") Element mainView) {
    assertNotNull(service);
    assertNotNull(mainView);
    assertEquals(mainView.getComponent().get().getClass(), MainView.class);
  }
}


```

If your Vaadin components and routes have been discovered by Spring, there is no need to add the `@RouteLocation` 
annotation




