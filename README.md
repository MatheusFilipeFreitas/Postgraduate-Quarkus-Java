# unipds-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Topics covered in this project

This repository is a postgraduate Quarkus lab project. It introduces the following concepts and extensions:

### REST APIs (Jakarta REST)

Basic REST endpoints with different media types and HTTP verbs.

| Resource | Path | Description |
| --- | --- | --- |
| `GreetingResource` | `/hello` | Simple text response |
| `UnipdsResource` | `/unipds` | Stateful counter with GET, POST, PUT, DELETE |
| `PersonResource` | `/person` | CRUD and query by birth year |
| `StarWarsResource` | `/starwars/starships` | Proxy to an external REST API |
| `SecureResource` | `/secure/claim` | JWT-protected endpoint |

### OpenAPI / Swagger UI

API documentation is generated automatically. In dev mode, access Swagger UI at <http://localhost:8080/q/swagger-ui>.

### REST Client

`StarWarService` uses the MicroProfile REST Client to call the [SWAPI](https://swapi.info/api/) starships endpoint.

### Fault tolerance

Applied on the REST client call in `StarWarService`:

- **Timeout** — fails the request after 3 seconds
- **Circuit breaker** — opens when failures exceed the configured ratio and volume
- **Fallback** — returns a default message when the external API is unavailable

Endpoint: `GET /starwars/starships`

### Health checks

Custom liveness and readiness probes using SmallRye Health:

- **Liveness** (`LivenessCheck`) — reports the application is running
- **Readiness** (`ReadinessCheck`) — checks whether the Star Wars API is reachable (down when fallback is used)

Endpoints: `/q/health/live` and `/q/health/ready`

### Persistence (Hibernate ORM + Panache)

`Person` is a Panache entity backed by PostgreSQL, with seed data in `import.sql`. `PersonResource` exposes full CRUD and a custom query by birth year.

### Security (JWT + RBAC)

JWT validation is configured in `application.properties`. `SecureResource` reads the `preferred_username` claim and restricts access with `@RolesAllowed("Subscriber")`.

### Observability

The project covers metrics, tracing, and JDBC instrumentation:

- **Micrometer + Prometheus** — custom metrics with `@Counted` on `GET /person` (exposed at `/q/metrics`)
- **OpenTelemetry** — distributed tracing integrated with Jaeger
- **JDBC telemetry** — database calls traced via `quarkus.datasource.jdbc.telemetry=true`

Run Jaeger locally (see [Jaeger section](#jaeger-distributed-tracing)) and inspect traces at <http://localhost:16686>.

### Testing

Unit and integration tests with JUnit and REST Assured (`GreetingResourceTest`, `GreetingResourceIT`).

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

- REST ([guide](https://quarkus.io/guides/rest))
- REST Client ([guide](https://quarkus.io/guides/rest-client))
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui))
- SmallRye Fault Tolerance ([guide](https://quarkus.io/guides/smallrye-fault-tolerance))
- SmallRye Health ([guide](https://quarkus.io/guides/smallrye-health))
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache))
- Security with JWT ([guide](https://quarkus.io/guides/security-jwt))
- Micrometer metrics ([guide](https://quarkus.io/guides/micrometer))
- OpenTelemetry tracing ([guide](https://quarkus.io/guides/opentelemetry))
