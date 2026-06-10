# unipds-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Quarkus CLI and useful commands

If you have the [Quarkus CLI](https://quarkus.io/guides/cli-tooling) installed, these are the most common commands for day-to-day work:

| Command | Description |
| --- | --- |
| `quarkus dev` | Run the application in dev mode with live reload |
| `quarkus build` | Build the application (JAR or native, depending on configuration) |
| `quarkus create` | Create a new Quarkus project |
| `quarkus ext add <name>` | Add an extension to the project (e.g. `quarkus ext add rest-client`) |
| `quarkus ext list` | List available extensions |
| `quarkus info` | Show project and platform information |

This project also supports the same workflows through Maven:

```shell script
./mvnw quarkus:dev
./mvnw package
./mvnw quarkus:add-extension -Dextensions="rest-client"
```

### Jaeger (distributed tracing)

To run Jaeger locally for tracing and observability:

```shell script
docker run --name=jaeger -d -p 16686:16686 -p 4317:4317 -e COLLECTOR_OTLP_ENABLED=true jaegertracing/all-in-one:latest
```

After the container starts, open the Jaeger UI at <http://localhost:16686>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/unipds-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): Build RESTful web services and APIs using Jakarta REST (formerly JAX-RS)
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Generate OpenAPI schemas and serve Swagger UI for REST API documentation

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
