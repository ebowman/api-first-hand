## Api-First-Hand: [xxxx]

[![Build Status](https://travis-ci.org/zalando/api-first-hand.svg)](https://travis-ci.org/zalando/api-first-hand) [![codecov](https://codecov.io/gh/zalando/api-first-hand/branch/master/graph/badge.svg)](https://codecov.io/gh/zalando/api-first-hand) [![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zalando/api-first-hand?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Table of Contents
------------------------------------------------------------

- [Intro to API-First-Hand](#intro-to-api-first-hand)
  - [Plugin Features](#plugin-features)
  - [Build Status and Requirements](#build-status-and-requirements)
    - [More About the Activator Template](#more-about-the-activator-template)
- [Getting Started with API-First-Hand](#getting-started-with-api-first-hand)
  - [Creating Your Play Application with the Plugin](#creating-your-play-application-with-the-plugin)
  - [Running Your Application with the Plugin](#running-your-application-with-the-plugin)
  - [Play Routes Integration](#play-routes-integration)
  - [Model Definitions](#model-definitions)
    - [Primitive Types](#primitive-types)
    - [Complex Types](#complex-types)
  - [Specification Cross-References](#specification-cross-references)
  - [Swagger Validations](#swagger-validations)
- [About API-First-Hand: Architecture and Structure](#about-api-first-hand-architecture-and-structure)
  - [Plugin Architecture](#plugin-architecture)
  - [Plugin Project Structure](#plugin-project-structure)
  - [Plugin Developing](#plugin-developing)
  - [Plugin Testing](#plugin-testing)
  - [Custom Templates For Code Generation](#custom-templates-for-code-generation)
  - [Code Quality](#code-quality)
      
## Intro to API-First-Hand
**API-First-Hand** is an API-First bootstrapping tool for building RESTful web services from a [Swagger/OpenAPI](http://swagger.io/) specification. Constructed as a plugin, it takes your Swagger/OpenAPI definition as the single source of truth and regenerates these code snippets for you, simply and consistently. Instead of writing lots of boilerplate code, you can focus instead on implementing service business logic. Subsequent regenerations keep the code that you have added—either by commenting out the parts that are no longer valid, or by adding parts that are needed because you've changed the API.

We built API-First-Hand for use with [Play Framework](http://www.playframework.com/), but we would like to extend it for use with **Akka HTTP**. [Get in touch](https://github.com/zalando/api-first-hand/blob/master/CONTRIBUTING.md) if you'd like to help make that possible.

### Plugin Features 

Api-First-Hand supports round-trip regeneration and compilation of:
- Play route definitions (managed)
- Swagger domain model definitions and parameters onto Scala case classes (managed)
- Swagger domain model constraints onto Play validations (managed)
- Generators for random test data and parameter values (managed)
- Unit tests for invalid and valid parameter sets // validating your service at the API boundary (managed)
- Swagger path definitions onto skeletons for Play controller implementations (unmanaged)
- Skeletons for domain-driven controller implementation and customized deserializers
- Wrappers for Play route files to convert semantics from HTTP-related to domain-related (controller_base)
- Model classes and validation rules
- Security extractors (manual generation and compilation)
- Unmarshallers for custom content types (manual generation and compilation)

Managed means "managed by sbt"—and this means that you don't have to control or change the code as you make your REST service. The security extractors and unmarshallers are available through manual generation and compilation, and supported if A) No security extractor or unmarshaller with the same name already exists; B) The developer issues the `playScalaSecurity` or `playScalaMarshallers` sbt command.

### Build Status and Requirements

API-First-Hand is under active development and **should not be considered production-ready**.

To use the plugin, you need:
- Play Framework 2.5.4+
- Swagger (OpenAPI) 2.0
- the Activator template, hosted by [Lightbend](https://www.lightbend.com/activator/template/api-first-hand)

#### More About the Activator Template
The Activator template provides a sandbox for your application to run with API-First-Hand. It contains the following:
- HTML tutorial (found in the `tutorial` folder)
- the Swagger UI frontend included as static files, run from within Play (located in the `public/swagger` folder)
- a pre-configured `plugins.sbt` file with a definition of all required resolvers and plugins (found in the `project` folder)
- a `conf` folder with the following customized contents:
    * `routes` file with route configuration for Swagger UI, example specification, and commented-out links to other examples
    * A template Swagger API definition called `example.yaml`, with a dummy implementation in the `app` folder 
    * `examples` folder containing additional Swagger specification examples, each representing some aspect of Api-First-Hand in greater detail. For the plugin to pick up the specification, move it into the `conf` folder. You can simultaneously have multiple Swagger specifications in the `conf` folder. 
- `app` directory with following template implementations:
    * `controllers/Swagger.scala`: a backend side of the Swagger UI
    * `generated_controllers/example.yaml.scala`: a dummy implementation of the example controller that's (re)generated if deleted
    * `security/example.yaml.scala`: a marshaller for OAuth2 tokens. Will not be regenerated until either deleted or renamed; and then explicitly requested by issuing a `playScalaSecurity` command.
 
## Getting Started with API-First-Hand

### Creating Your Play Application with the Plugin

- Use the Activator template, for example: ```activator new hello-world api-first-hand```
- Look at the `project/plugins.sbt` of your generated project and add the required plugins and resolvers there
- Do the same for `build.sbt`
- Put a Swagger specification with a ```.yaml``` or ```.json``` extension into the ```conf``` directory
- Add a specification link (`->`) to the Play's routes file

### Running Your Application with the Plugin

Now let's run your application with the plugin:

- Open a shell and `cd` into your service project directory.
- Start `sbt` and `run` the service. This creates a folder on your local machine, using the Activator template.
- View the running application at [http://localhost:9000](http://localhost:9000).

A single specification defines a single API; in our case, these are three API endpoints:

- The `GET /token` API plays a role of an authentication server and is used by the Swagger UI for OAuth token requests. 
- The `POST /token` API represents an authorization server and is used by the security part of the generated code to validate OAuth tokens.
- The `GET /todos/{user_id}` takes a path parameter `user_id` and returns a TODO list for the given user. Use `security/example.yaml.scala`, the marshaller for OAuth2 tokens, to request an OAuth token with the scope `admin:org`. This will grant permission for the client to access this endpoint and enable you to test the API.  

Click the default button to expand the API definition in the Swagger UI. Now you can change the specification or write some backend code and use the Swagger UI to see the results.

### Play Routes Integration

Play application developers are used to defining endpoints in the `conf/routes` file. With Api-First-Hand, however, Swagger API specifications already define endpoints as `path` definitions—saving you from doing the work twice. Just link your API definition in the routes file once. This makes all Swagger API-defined endpoints available as children of a single path context location, and generates Play route definitions from them (as shown below):

```
->      /example        example.yaml.Routes
```

The `conf/routes` file provided by the Activator template also contains additional `GET` mappings required for the Swagger UI sandbox, and some commented-out links to other examples. If you activate some specification by moving it from the `examples` folder into the `conf` folder, you'll have to uncomment an appropriate line in the `routes` file so that Play can find it.  

### Model Definitions

API-First-Hand generates Scala domain model definitions for all data types defined as Swagger parameters in an API specification. Swagger parameters can be of path, query, header, form or body types, and consist of either primitive data types or more complex types composed from objects and arrays with primitives as leaves. Both primitive types and complex types are mapped to Scala.

For more information and an example, [go here](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md).

#### Primitive Types

Swagger version 2.0 allows for primitive data types based on the types defined by 
[JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8). When generated as Scala, the mapping indicated in [this chart](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md#primitive-types) applies.

#### Complex Types: Objects and Arrays

Complex types are made up of either primitive objects or nested objects. [Go here](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md#complex-types) for details and examples related to **Objects** (nested objections, optionality, object extensions, polymorphism, and additional properties) and **Arrays** (including nested arrays).

### Specification Cross-References

You can use a filename to reference part of another specification with the `$ref` element. You can split a single specification into multiple files (as shown in [`cross_spec_references.yaml`](https://github.com/zalando/api-first-hand-activator/blob/master/conf/examples/cross_spec_references.yaml)), and also reference a definition in one specification across other specifications. (For example, you can create `domain_model.yaml` and then reference it from any other API specification.)

An independent copy of the class definition is created for each referencing specification. The definition is then placed into the appropriate package for each specification. 

Therefore, even if multiple classes with the same name and structure are generated, they all will coexist in their 
own separate namespaces and won't be interchangeable.

### Swagger Validations

Swagger API definitions allow you to impose constraints on parameter types. You can use the `required` constraint to mark a parameter or specific field within a domain definition, as required upon input. You can also add to your API definition more constraints, as defined by the [Parameter Object](https://github.com/swagger-api/swagger-spec/blob/master/versions/2.0.md#parameterObject). API-First-Hand will generate validations for these parameter constraints and make sure that your controller methods are only called if the input of your service complies to those constraints.

[Go here](https://github.com/zalando/api-first-hand/blob/master/docs/swagger-validations.md) for more information and examples.

[Go here](https://github.com/zalando/api-first-hand/blob/master/docs/TEST_GENERATORS.md) for more detailed information about test generators.

## About API-First-Hand: Architecture and Structure

to build a plugin from sources, do the following:

- Clone the repository to your local filesystem
- Publish the plugin into your local ivy repository by running ```sbt publishLocal``` in the API-First-Hand directory 

## Plugin Architecture

Api-First-Hand has a three-tier architecture:

* **Specification**, responsible for finding and parsing a specification and converting it into raw AST format
* **Normalization**, which performs optimiztions on the AST—including type deduplication, flattening and parameter dereferencing
* **generation**, a final step including transformation of the AST into source code-related data and generation of source code from it

By separating the specification and generation tiers, we can plug in different specification standards and generate source code for different frameworks.

## Plugin Project Structure

There are a couple of sub-projects:

* `swagger-model`: A standalone Scala Swagger model with Jackson parser. Can be used by another projects.
* `api`: Automatically added to the runtime classpath of any projects using API-First-Hand.
* `swagger-parser`: A converter of the Swagger model to the internal AST of the plugin
* `api-first-core`: This is a core of the plugin with minimal functionality. It includes defining an AST structure and some transformations on AST.  
* `play-scala-generator`: The standalone generator for transforming an AST into the skeleton of Play-Scala application. 
* `plugin`: sbt plugins, one for each tier:
    - `ApiFirstSwaggerParser`: wraps the Swagger-parsing part 
    - `ApiFirstCore`: wrapper for AST-related functionality
    - `ApiFirstPlayScalaCodeGenerator`: a wrapper for the Play-Scala generator

You must enable each module separately in sbt's `build.sbt` and configure which parser(s) the plugin will use, like this: 

```scala
lazy val root = (project in file(".")).enablePlugins(PlayScala, ApiFirstCore, ApiFirstPlayScalaCodeGenerator, ApiFirstSwaggerParser)

apiFirstParsers := Seq(ApiFirstSwaggerParser.swaggerSpec2Ast.value).flatten
```

Check out the Activator template's configuration for a complete example.

## Custom Templates for Code Generation

The PlayScala generator supports custom templates. In order to override default template for some of the components,
please provide your custom template named in accordance to the following list:

  * `play_scala_test.mustache`: for unit tests
  * `play_validation.mustache`: for validators 
  * `generators.mustache`: for test data generators
  * `model.mustache`: for model classes and query and path bindables
  * `play_scala_controller_base.mustache`: for Play controller bases 
  * `play_scala_controller_security.mustache`: for security adapters used by controller bases
  * `play_scala_form_parser.mustache`: for form parsers used by the controller bases
  * `play_scala_controller.mustache`: for Play controller skeletons; you can augment them
  * `play_scala_response_writers.mustache`: for custom serializers; you can augment them
  * `play_scala_security_extractors.mustache`: for custom security extractors; you can augment them 

Generated artifacts must preserve some specific shape to be compiled together without errors.

You must configure the location for custom templates by overriding the plugin setting `playScalaCustomTemplateLocation`. For example, this configuration will set the project's `conf/templates` folder as the location:

```scala
playScalaCustomTemplateLocation := Some(((resourceDirectory in Compile) / "templates").value)
```

## Plugin Developing

sbt doesn't allow sub-projects to depend on each other as sbt plugins. To test an sbt plugin, you need a separate 
project. This project is `swagger-tester`.  To test your changes as you're developing the plugin, cd into this 
directory, and run sbt. This project uses an sbt `ProjectRef` to the sbt plugin, which means you don't need to 
`publishLocal` the plugin after each change. Just run `reload` in the sbt console, and it will pick up your changes.

API-First-Hand provides a few commands useful for development: 

* `apiFirstPrintDenotations`: outputs common names of different parts of the AST as they are intended for use in generated Scala code
* `apiFirstPrintRawAstTypes`: outputs all type definitions as they read from the specification before type optimizations
* `apiFirstPrintRawAstParameters`: outputs all parameters definitions before type optimizations
* `apiFirstPrintFlatAstTypes`: outputs type definitions after type optimizations
* `apiFirstPrintFlatAstParameters`: outputs parameter definitions after type optimizations

## Plugin Testing

We're using the sbt scripted framework for testing. You can find the tests in `plugin/src/sbt-test` and run them 
by running `scripted` in the sbt console.

## Code Quality

There are some quality checks embedded into the build script:
* the source code is (re)formatted using [scalariform](https://github.com/scala-ide/scalariform) each time it is compiled (currently deactivated).
* [`scalastyle`](http://www.scalastyle.org) sbt command performs code style checks before putting changes into the repository
* [`lint:compile`](https://github.com/HairyFotr/linter) sbt command performs static code analysis before putting changes into the repository
* Execute code coverage for API and compiler modules with the `sbt clean coverage test` command. Generate coverage statistics with the `coverageReport` sbt command.
