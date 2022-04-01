# Example Java Cryptographic License Keys

This is an example of how to verify [cryptographic license keys][docs] in Java 18 using Bouncy Castle.

This example verifies the `ED25519_SIGN` scheme.

## Running the example

First, install dependencies with [`mvn`][maven]:

```
mvn compile
```

Then run the program:

```
mvn exec:java -Dexec.mainClass=sh.keygen.example.Main
```

You should see log output indicating the current license key is valid:

```
License key is valid!
> Dataset: {"account":{"id":"1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"},"product":{"id":"1f086ec9-a943-46ea-9da4-e62c2180c2f4"},"policy":{"id":"29d9d8d3-bf1d-44d1-a1b9-820440d422ff","duration":null},"user":null,"license":{"id":"f3d657de-2b50-4adf-867b-ef03dc86a3fe","created":"2021-10-14T15:20:32.670Z","expiry":"2021-10-15T00:00:00.000Z"}}
```

## Questions?

Reach out at [support@keygen.sh][support] if you have any
questions or concerns!

[docs]: https://keygen.sh/docs/api/cryptography/#cryptographic-keys
[maven]: https://mvnrepository.com/
[support]: mailto:support@keygen.sh
