work-presentation-connector
=============

A Java library providing a client to the work-presentation service

### usage

Add the dependency to your Maven pom.xml

```xml
<dependency>
  <groupId>dk.dbc</groupId>
  <artifactId>work-presentation-connector</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
 In your Java code

```java
import dk.dbc.opensearch.WorkPresentationConnector;
import javax.inject.Inject;
...

// Assumes environment variable WORK_PRESENTATION_SERVICE_URL
// is set to the base URL of the work-presentation-service provider service.
@Inject
WorkPresentationConnector connector;

// Todo: Add usage example


```

### development

**Requirements**

To build this project JDK 1.8 or higher and Apache Maven is required.

### License

Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3.
See license text in LICENSE.txt
