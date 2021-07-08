# Re-producer for FaultToleranceDefinitionException in Quarkus 2.0

After migrating to Quarkus 2.0, @Retry throws the following excpetion:
```
Caused by: javax.enterprise.inject.spi.DeploymentException: org.eclipse.microprofile.faulttolerance.exceptions.
FaultToleranceDefinitionException: Invalid @Blocking/@NonBlocking on public java.lang.String org.acme.getting.
started.ReactiveGreetingResource.greeting(java.lang.String): must return java.util.concurrent.CompletionStage or io.smallrye.mutiny.Uni
```

## How to trigger the problem
Can be triggered by running the unit test.

## How to mitigate the problem
Remove retry annotation or roll back to pre Quarkus 2 version.

## Why resteasy reactive and blocking resource at the same time?
We have a common library with resteasy reactive, but not all the microservices moved to full reactive yet. Hence, there are blocking 
endpoints.

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

### Configuring GraalVM and JDK 11+

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 11+ `java` command is on the path.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image-guide)
for help setting up your environment.

## Building the application

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

This command will leave Quarkus running in the foreground listening on port 8080.

1. Visit the default endpoint: [http://127.0.0.1:8080](http://127.0.0.1:8080).
    - Make a simple change to [src/main/resources/META-INF/resources/index.html](src/main/resources/META-INF/resources/index.html) file.
    - Refresh the browser to see the updated page.
2. Visit the `/hello` endpoint: [http://127.0.0.1:8080/hello](http://127.0.0.1:8080/hello)
    - Update the response in [src/main/java/org/acme/quickstart/GreetingResource.java](src/main/java/org/acme/quickstart/GreetingResource.java). Replace `hello` with `hello there` in the `hello()` method.
    - Refresh the browser. You should now see `hello there`.
    - Undo the change, so the method returns `hello` again.
    - Refresh the browser. You should now see `hello`.

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

> ./mvnw package

Then run it:

> java -jar ./target/quarkus-app/quarkus-run.jar

Have a look at how fast it boots, or measure the total native memory consumption.

### Run Quarkus as a native executable

You can also create a native executable from this application without making any
source code changes. A native executable removes the dependency on the JVM:
everything needed to run the application on the target platform is included in
the executable, allowing the application to run with minimal resource overhead.

Compiling a native executable takes a bit longer, as GraalVM performs additional
steps to remove unnecessary codepaths. Use the  `native` profile to compile a
native executable:

> ./mvnw package -Dnative

After getting a cup of coffee, you'll be able to run this executable directly:

> ./target/getting-started-1.0.0-SNAPSHOT-runner
