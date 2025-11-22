ReferalSystem
===============

Small Spring Boot service that fetches product/search data (via Flipkart) and exposes a simple referral API.

Quick overview
--------------
- Java Spring Boot application (Java 17, see `pom.xml`).
- Provides endpoints to search Flipkart results and fetch raw Flipkart responses.
- A simple in-memory cache is used and refreshed on a scheduled cron (configurable).

Prerequisites
-------------
- Java 17 (or compatible JDK)
- Maven (or use the included `mvnw` wrapper)

Build
-----
From project root:

```
./mvnw -v        # optional: show Maven and Java versions
./mvnw clean package
```

Run
---
Run with the wrapper or Maven:

```
./mvnw spring-boot:run
# or
java -jar target/ReferalSystem-0.0.1-SNAPSHOT.jar
```

The app runs on port 8080 by default.

Configuration
-------------
Application properties live under `src/main/resources` and will be picked up by Spring Boot. Useful keys:

- `cache.expiry` - cron expression used to schedule cache refresh (default in `application.properties`)
- `log4j.config.path` - location of log4j2 config

Note about Flipkart credentials
------------------------------
Currently the `FlipkartClient` class contains hardcoded affiliate id and token values. For production or shared repositories you should remove those hardcoded values and wire them via environment variables or `application.properties` (for example, add properties such as `flipkart.affiliate.id` and `flipkart.affiliate.token` and use `@Value` to inject them).

Endpoints
---------
Base path: `/referral/system`

- GET /referral/system/search
  - Query params:
    - `searchString` (string) - search term
    - `count` (int) - number of results requested (note: the implementation currently parses and returns available results)
  - Returns: list of `SearchResponse` JSON objects.

  Example:
  ```
  curl "http://localhost:8080/referral/system/search?searchString=mobile&count=20"
  ```

- GET /referral/system/fetch
  - Query params:
    - `searchString` (string)
  - Returns: raw Flipkart fetch JSON as string.

  Example:
  ```
  curl "http://localhost:8080/referral/system/fetch?searchString=mobile"
  ```

Development / Tests
-------------------
Run unit tests with:

```
./mvnw test
```

Troubleshooting
---------------
- If the application cannot reach Flipkart APIs, check network connectivity and be aware some endpoints may require valid affiliate credentials.
- If you want to change the cache refresh frequency, edit `cache.expiry` in `src/main/resources/application.properties`.

Contributing
------------
- Replace hardcoded credentials with configuration (environment variables or properties).
- Add more robust error handling and unit/integration tests for the HTTP client and transformer logic.

License
-------
No license specified. Add a LICENSE file if you want to open-source this repo.

