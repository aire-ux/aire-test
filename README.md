# aire-test
Aire Testing Framework is a powerful and convenient framework for testing Vaadin components
and interactions.  Aire Test builds off of the fantastic 
[Karibu in-memory testing framework](https://github.com/mvysny/karibu-testing)
and extends it with:

1. Complete CSS selectors for navigating the in-memory DOM
2. Annotation-driven route-discovery
3. Pluggable DOM-rewriting functionality
4. Transparent mocking



## Examples

### Plain Vaadin

#### Step 1: Install Aire-Test

Aire-Test publishes a bill-of-materials containing all
of its subprojects.  If you're using Maven, simply add:

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
to your POM file.  If you're using the Gradle with the Spring dependencies plugin (recommended),
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

##### Gradle

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



##### Maven 

