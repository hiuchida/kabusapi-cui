# kabusapi-cui
Simple console application using kabusapi-client-ex.

## Requirements

Building the console application requires:
1. Java 1.7+
2. Maven
3. Dependent Maven Projects (Not registered in public repositories)
    1. https://github.com/hiuchida/kabusapi-client-ex
        1. https://github.com/hiuchida/kabusapi-client
        2. https://github.com/hiuchida/kabusapi-enums

## Installation

To install the console application to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

## Getting Started

To try the console application, run the following command.

```shell
mvn exec:java -q
```

For development purposes, add compile.

```shell
mvn compile exec:java -q
```

### token

If you have not yet logged in, log in first to obtain a token.
Specify the API password in xxxxxx.

```shell
mvn exec:java -q -Dexec.args="token xxxxxx"
```

If successful, a string representation of the model class is displayed.

```
class TokenSuccess {
    resultCode: 0
    token: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
```

If the -token option is specified, only tokens are displayed.

```
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

If the -json option is specified, JSON is displayed.

```json
{"ResultCode":0,"Token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}
```

If the -curl option is specified, curl command is displayed.
RequestToken file is created.

```
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" "http://localhost:18080/kabusapi/token" -d @RequestToken
```

### apisoftlimit

Call the apisoftlimit API.
Specify the token in xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.

```shell
mvn exec:java -q -Dexec.args="apisoftlimit xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
```

If successful, a string representation of the model class is displayed.

```
class ApiSoftLimitResponse {
    stock: 200.0
    margin: 200.0
    future: 10.0
    futureMini: 100.0
    futureMicro: 1000.0
    option: 20.0
    optionMini: 200.0
    kabuSVersion: 5.13.1.0
}
```

-json or -curl options can be specified.

