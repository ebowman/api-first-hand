## Api-First-Hand: [xxxx]

[![Build Status](https://travis-ci.org/zalando/api-first-hand.svg)](https://travis-ci.org/zalando/api-first-hand) [![codecov](https://codecov.io/gh/zalando/api-first-hand/branch/master/graph/badge.svg)](https://codecov.io/gh/zalando/api-first-hand) [![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zalando/api-first-hand?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Table of Contents
------------------------------------------------------------

- [Features and Add-ons](#features-and-add-ons)
- [Compatibility](#compatibility)
- [Build Status: Beta](#build-status-beta)
- [More About the Activator Template](#tutorial)
- [Running an Application](#running-an-application)
- [Play Routes Integration](#play-routes-integration)
  - [Swagger Domain Definitions](#play-routes-integration)
  - [Specification Cross-References](#specification-cross-references)
  - [Primitive Types](#primitive-types)
  - [Complex Types](#complex-types)
    - [Objects](#objects)
    - [Nested Objects](#nested-objects)
    - [Optionality](#optionality)
    - [Parameter Optionality](#parameter-optionality)
    - [Extension](#extension)
    - [Polymorphism](#polymorphism)
    - [Additional Properties](#additional-properties)
  - [Arrays](#arrays)
    - [Nested Arrays](#nested-arrays)
  - [Swagger Validations](#swagger-validations)
  - [Test Generators](#test-generators)
- [Building an Api-First-Hand Plugin](#building-an-api-first-hand-plugin)
  - [Plugin Architecture](#plugin-architecture)
  - [Plugin Project Structure](#plugin-project-structure)
  - [Plugin Developing](#plugin-developing)
  - [Plugin Testing](#plugin-testing)
  - [Custom Templates For Code Generation](#custom-templates-for-code-generation)
  - [Code Quality](#code-quality)
      
API-First-Hand is an API-First bootstrapping tool for building RESTful web services from a [Swagger/OpenAPI](http://swagger.io/) specification. It's a plugin that takes your Swagger/OpenAPI definition as the single source of truth and regenerates these code snippets for you, simply and consistently. Instead of writing lots of boilerplate code, you can focus instead on implementing service business logic. Subsequent regenerations keep the code that you have added—either by commenting out the parts that are no longer valid, or by adding parts that are needed because you've changed the API.

The plugin was built for use with [Play Framework](http://www.playframework.com/), but we'd like to extend it for use with **Akka HTTP**. [Get in touch](https://github.com/zalando/api-first-hand/blob/master/CONTRIBUTING.md) if you'd like to help make that possible.

### Features and Add-ons
Api-First-Hand supports round-trip regeneration and compilation of these (managed means "managed by sbt"):
- Play route definitions (managed)
- Swagger domain model definitions and parameters onto Scala case classes (managed)
- Swagger domain model constraints onto Play validations (managed)
- Generators for random test data and parameter values (managed)
- Unit tests for for invalid and valid parameter sets // validating your service at the API boundary (managed)
- Swagger path definitions onto skeletons for Play controller implementations (unmanaged)
- Skeletons for domain-driven controller implementation and customized deserializers
- Wrappers for Play route files to convert semantics from HTTP-related to domain-related (controller_base)
- Model classes and validation rules
- Security extractors (manual generation and compilation)
- Unmarshallers for custom content types (manual generation and compilation)

"Managed by sbt" means that you don't have to control or change the code as you make your REST service. The security extractors and unmarshallers are available through manual generation and compilation, and supported if A) No security extractor or unmarshaller with the same name already exists; B) The developer issues the `playScalaSecurity` or `playScalaMarshallers` sbt command.

### Build Status: Beta

[Lightbend](https://www.lightbend.com/activator/template/api-first-hand) hosts an Activator template for API-First-Hand.  Enable API-First-Hand with this template; the version in this repository is under active development.

#### More About the Activator Template
The template contains the following:
- `tutorial` folder with HTML tutorial
- `public/swagger` folder containing static files needed for the Swagger UI
- `project` folder containing a pre-configured `plugins.sbt` file with a definition of all required resolvers and plugins
- `conf` folder with the following customized contents:
    * `routes` file with route configuration for Swagger UI, example specification, and commented out links to other examples
    * `example.yaml`, a demo Swagger specification. The specification has a dummy implementation in the `app` folder. 
    * `examples` folder containing additional Swagger specification examples that each represents some aspect of the Api-First-Hand plugin in more detail. For the plugin to pick up the specification, move it into the `conf` folder. You can have multiple Swagger specifications in the `conf` folder at the same time. 
- `app` directory with following template implementations:
    * `controllers/Swagger.scala` - a backend side of the Swagger UI
    * `generated_controllers/example.yaml.scala` - a dummy implementation of the example controller. Will be (re)generated if deleted
    * `security/example.yaml.scala` - a marshaller for OAuth2 tokens. Will not be regenerated until either deleted or renamed; and then explicitly requested by issuing a `playScalaSecurity` command.

## Compatibility

- Play 2.5.4+
- Swagger (OpenAPI) 2.0
  
## Running Your Application

[**waiting for clarification....**] 

### Play Routes Integration

Play application developers are used to defining endpoints in the `conf/routes` file. With Api-First-Hand, however, Swagger API specifications already define endpoints as `path` definitions—saving you from doing the work twice. Just link your API definition in the routes file once. This makes all Swagger API-defined endpoints available as children of a single path context location, and generates Play route definitions from them (as shown below):

```
->      /example        example.yaml.Routes
```

Note that the `conf/routes` file provided by the Activator template also contains additional `GET` mappings required for the the Swagger UI sandbox, and some commented-out links to other examples. If you activate some specification by moving it from the `examples` folder into the `conf` folder, you'll have to uncomment an appropriate line in the `routes` file so that Play can find it.  


### Swagger Domain Definitions

API-First-Hand generates Scala domain model definitions for all data types defined as Swagger parameters in an API specification. Swagger parameters can be of path, query, header, form or body types, and consist of either primitive data types or more complex types composed from objects and arrays with primitives as leaves. Both primitive types and complex types are mapped to Scala.

For more information and an example, [go here](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md).


### Specification Cross-References

A `$ref` element of the specification is allowed to contain a name of file as it's part. Because of this, it is possible to split 
a single specification into multiple files as shown in [`cross_spec_references.yaml`](https://github.com/zalando/api-first-hand-activator/blob/master/conf/examples/cross_spec_references.yaml) 
example. It is also possible to reference a definition in one specification from another specification. 
In this case for each reference an independent copy of the class definition will be created for each referencing specification. 
The definition is then placed into the appropriate package for each specification. 

Thus, even if multiple classes with  the same name and structure might be generated, they all will coexist in their 
own separate namespaces and won't be interchangeable.


### Primitive Types

Swagger version 2.0 allows for primitive data types based on the types defined by 
[JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8). When generated as Scala, the mapping indicated in [this chart](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md#primitive-types) applies. Additionally, if a validation of type "enum" is defined for some primitive type, a trait and a set of case objects forming an ADT will be generated for this enum.

## Complex Types

Complex types are made up of either primitive objects or nested objects.

### Objects

Complex object types are defined in Swagger model definitions as either objects or arrays. [Go here](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md#complex-types) for more documentation about nested objects, Optionality, parameter optionality, object extension, Polymorphism


### Additional Properties

Swagger's model language allows objects' additional properties to be loosely defined employing the `additionalProperties` annotation 
in order to model dictionaries. These dictionaries are mapped to Scala's `Map` type, for which a type alias is 
generated following the same (by now) well-known pattern as for optional properties, with the map's key parameter type being a Scala `String`.

A Swagger additional property definition takes as its type property the element type of the dictionary, 
which can be of primitive or complex type and which is mapped on Scala as the map's value parameter type. 
Swagger allows for one `additionalProperties` annotation per object definition, so we can generate this Scala parameter 
with the static name `additionalProperties`.

In the following example we define a Swagger model object definition `KeyedArray` that uses the `additionalProperties` 
annotation to provide the object with a set of key value mappings from string to array. E.g.

```yaml
definitions:
  KeyedArrays:
    type: object
    additionalProperties:
      type: array
      items:
        type: integer
```

Which is generated as:

```scala
package api

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper
    import scala.math.BigInt
    import scala.collection.immutable.Map

    type KeyedArraysAdditionalPropertiesCatchAll = ArrayWrapper[BigInt]
    type KeyedArraysAdditionalProperties = Map[String, KeyedArraysAdditionalPropertiesCatchAll]
    case class KeyedArrays(additionalProperties: KeyedArraysAdditionalProperties) 
}
```

## Arrays

Swagger's `array` is used to define properties that hold sets or lists of model values—possibly of a primitive type, 
but complex element types are also allowed. Depending on the place where the array definition appears, Swagger array can be mapped to one of two Scala types, parametrised for the element type that it contains:
- if an array only defined inline as a part of the response definition, it is translated to a `Seq` type
- otherwise (array appears in the parameter definition or in the `definitions` part of the specification) it is 
defined as a `de.zalando.play.controllers.ArrayWrapper`

For example, in the snippet below, an `Activity` object definition is referred to as an item element in the 
`messages` property of `type: array` of the containing object definition `Example`. 
A Scala type alias will be generated for the array type (just as we've seen before with optional properties), 
after which the array-containing property can be generated within the case class as being of this alias type. 
E.g. in the Swagger definition and code

```yaml
definitions:
  Activity:
    type: object
    required:
    - actions
    properties:
      actions:
        type: string
  Example:
    type: object
    required:
    - messages
    properties:
      messages:
        type: array
        items:
          $ref: '#/definitions/Activity'
```

Which is generated as:

```scala
package api

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper

    type ExampleMessages = ArrayWrapper[Activity]

    case class Activity(actions: String) 
    case class Example(messages: ExampleMessages) 
}

```

If the description of the same array is inlined as a part of the response definition like that:

```yaml
paths:
  /api:
    get:
      responses:
        200:
          schema:
            type: object
            required:
            - messages
            properties:
              messages:
                type: array
                items:
                  $ref: '#/definitions/Activity'
          description: array payload
definitions:
  Activity:
    type: object
    required:
    - actions
    properties:
      actions:
        type: string
```

than the `Seq` scala type will be used:
```scala
package api
package object yaml {
    type ApiGetResponses200Messages = Seq[Activity]
    case class Activity(actions: String) 
    case class ApiGetResponses200(messages: ApiGetResponses200Messages) 
}
```


### Nested Arrays

Array definition types can be nested and are possibly optional. 
The following (contrived) snippet depicts the generated Scala code when both definition types are 
employed in a somewhat non-useful manner. The intent of this example is to show that the case 
class definitions are rather concisely generated, even though a stack of type aliases is needed 
to make sure that we still refer in Scala code to an aptly named Swagger definition—especially 
in conjunction with the object properties being optional. Next to its benefits, 
type safety against `null` pointers does have an associated cost as well.

```yaml
definitions:
  Activity:
    type: object
    properties:
      actions:
        type: string
  Example:
    type: object
    properties:
      messages:
        type: array
        items:
          type: array
          items:
            $ref: '#/definitions/Activity'
      nested:
        type: array
        items:
          type: array
          items:
            type: array
            items:
              type: array
              items:
                type: string
```

Which is generated as:

```scala
package api

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper

    type ExampleMessagesOpt = ArrayWrapper[ExampleMessagesOptArr]
    type ExampleMessages = Option[ExampleMessagesOpt]
    type ExampleNested = Option[ExampleNestedOpt]
    type ExampleMessagesOptArr = ArrayWrapper[Activity]
    type ExampleNestedOptArrArrArr = ArrayWrapper[String]
    type ExampleNestedOptArrArr = ArrayWrapper[ExampleNestedOptArrArrArr]
    type ActivityActions = Option[String]
    type ExampleNestedOptArr = ArrayWrapper[ExampleNestedOptArrArr]
    type ExampleNestedOpt = ArrayWrapper[ExampleNestedOptArr]

    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nested: ExampleNested) 
}

```

## Swagger Validations

Swagger API definitions allow for constraints to be put on parameter types. 
We have already seen the `required` constraint, used to mark a parameter or specific field within 
a domain definition to be required upon input. Additional constraints, as defined by the 
[Parameter Object](https://github.com/swagger-api/swagger-spec/blob/master/versions/2.0.md#parameterObject), 
can be added to your API definition. The Api-First-Hand plugin will generate validations for these parameter 
constraints and make sure that your controller methods are only called if the input of your service 
complies to those constraints. 

In the example below, the API definition of the `token` parameter of 
type `Base64String`, as the form parameter, contains validation rules for the length of the parameter as well as a regexp pattern the value of the parameter must confirm to.
The parameter is also required.

```yaml
...
parameters:
      - name: token
        in: formData
        description: oauth2 token
        type: string
        format: byte
        pattern: "[A-Za-z0-9]*"
        minLength: 5
        maxLength: 100
        required: true
...
```

Let's take another example:

```yaml
...
    get:
      parameters:
      - name: state
        in: query
        description: Any application state to be forwarded back to the frontend
        type: string
        minLength: 1
        maxLength: 110
        required: false
...
```

The `state` parameter is of type string, is not required and has no default value. 
It is also only allowed to have a state of length between 1 and 110, otherwise it won't pass validation. 
For the demo purposes, let's change it's type to `integer` and make it required. 

As the parameter is required now, the `default` value cannot be present. The `maxLength` and `maxLength` validations 
are not allowed for integer parameters, therefore let's replace them with `minimum` and `maximum` values:
    
```yaml
...
    get:
      parameters:
      - name: state
        in: query
        description: Any application state to be forwarded back to the frontend
        type: integer
        format: int32
        required: true
        minimum: 2000
        maximum: 2100      
...
```    

As we just changed the parameter type, refreshing Swagger UI will, in addition to generating validations 
for that parameter type, also force a regeneration of the model consistent with the validation. 
That's nice, but note that it will break the current implementation of the controller class, as the 
implementation of the `postAction` expects `state` to be of type `String`.

![Validation screenshot](/docs/validations-01.png)

Let's change the implementation. The second parameter `state` is no longer 
of type `Option[String]` but of type `Int`. We change the implementation to take this fact into the account:

```scala
...
val tokenGet = tokenGetAction { input: (String, String, String, Int) =>
    val (redirect_uri, scope, response_type, state) = input
    // ----- Start of unmanaged code area for action  TokenService.tokenGet
    val statePart = s"""state=$state"""
...
}
```

Refreshing Swagger UI and trying out a couple of integer values for `state` shows that the service 
now excepts value within the range `[2000..2100]`, but returns a descriptive error when outside. I.e.

```json
[
  {
    "messages": [
      "error.max"
    ],
    "args": [
      2100
    ]
  }
]
```
    
# Building an Api-First-Hand Plugin

To build a plugin, do the following:

- Clone the repository to your local filesystem
- Run ```sbt publishLocal``` in the Api-First-Hand directory. This will publish the plugin into your local ivy repository

To use the plugin in a plain Play project:

- Create a new Api-First-Hand project using activator template, for example: ```activator new hello-world api-first-hand```
- Take a look at the `project/plugins.sbt` of the generated project and add required plugins and resolvers to the `project/plugins.sbt` of your Play project
- Do the same for `build.sbt`
- Put a Swagger specification with a ```.yaml``` or ```.json``` extension into the ```conf``` directory
- Add a specification link (`->`) to the Play's routes file


## Plugin Architecture

Ths Api-First-Hand plugin has a three-tier architecture:

* specification - this tier is responsible for finding and parsing a specification and converting it into the raw AST format
* normalisation - this tier performs a couple of optimisations on the AST including type deduplication, flattening and parameter dereferencing
* generation - a final step including transformation of the AST into the source-code related data and generation of source code from it   

The separation of the specification and generation tiers allows for plugging in different specification standards 
and generating source code for different frameworks.


## Plugin Project Structure

There are a couple of sub-projects:

* `swagger-model` - A standalone Scala Swagger model and a Jackson parser for it. Can be used by another projects
* `api` - This is the project that's automatically added to the runtime classpath of any projects that use this plugin.
* `swagger-parser` - A converter of the Swagger model to the internal AST of the plugin
* `api-first-core` - This is a core of the plugin with minimal functionality. It includes defining an AST structure and some transformations on AST.  
* `play-scala-generator` - The standalone generator for transforming an AST into the skeleton of Play-Scala application. 
* `plugin` - A coupble of sbt plugins, one for each tier:
    - `ApiFirstSwaggerParser` - a plugin wrapping Swagger parsing part 
    - `ApiFirstCore` - a wrapper for AST-related functionality
    - `ApiFirstPlayScalaCodeGenerator` - a wrapper for the Play-Scala generator

Because of the modular plugin architecture, all modules must be enabled separatly in sbt's `build.sbt`. 
It is also necessary to configure which parser(s) must be used by the plugin, like that: 

```scala
lazy val root = (project in file(".")).enablePlugins(PlayScala, ApiFirstCore, ApiFirstPlayScalaCodeGenerator, ApiFirstSwaggerParser)

apiFirstParsers := Seq(ApiFirstSwaggerParser.swaggerSpec2Ast.value).flatten
```

Please take a look at activator template's configuration for complete example.


## Custom Templates For Code Generation

The PlayScala generator supports custom templates. In order to override default template for some of the components,
please provide your custom template named in accordance to the following list:

  * `play_scala_test.mustache` - for unit tests
  * `play_validation.mustache` - for validators 
  * `generators.mustache` - for test data generators
  * `model.mustache` - for model classes and query and path bindables
  * `play_scala_controller_base.mustache` - for Play controller bases 
  * `play_scala_controller_security.mustache` - for security adapters used by controller bases
  * `play_scala_form_parser.mustache` - for form parsers used by the controller bases
  * `play_scala_controller.mustache` - for Play controller skeletons supposed to be augmented by the programmer
  * `play_scala_response_writers.mustache` - for custom serializers to be augmented by the programmer
  * `play_scala_security_extractors.mustache` - for custom security extractors to be augmented by the programmer 


Please be aware that generated artifacts need to preserve some specific shape in order to be compiled together without errors.

The location where custom templates reside needs to be configured by overriding the plugin setting `playScalaCustomTemplateLocation`.

For example following configuration will set this place to be `conf/templates` folder of the project:
```scala
playScalaCustomTemplateLocation := Some(((resourceDirectory in Compile) / "templates").value)
```


## Plugin Developing

sbt doesn't allow sub-projects to depend on each other as sbt plugins. To test an sbt plugin, you need a separate 
project.  This project is `swagger-tester`.  To test your changes as you're developing the plugin, cd into this 
directory, and run sbt. This project uses an sbt `ProjectRef` to the sbt plugin, which means you don't need to 
`publishLocal` the plugin after each change. Just run `reload` in the sbt console, and it will pick up your changes.

The Api-First-Hand plugin provides a couple of commands useful for development: 

* `apiFirstPrintDenotations` - outputs a common names of different parts of the AST as they are intended to be used in generated Scala code
* `apiFirstPrintRawAstTypes` - outputs all type definitions as they read from the specification before type optimisations
* `apiFirstPrintRawAstParameters` - outputs all parameters definitions before type optimisations
* `apiFirstPrintFlatAstTypes` - outputs type definitions after type optimisations
* `apiFirstPrintFlatAstParameters` - outputs parameter definitions after type optimisations


## Plugin Testing

We're using the sbt scripted framework for testing. You can find the tests in `plugin/src/sbt-test`, and run them 
by running `scripted` in the sbt console.


## Code quality

There are some quality checks embedded into the build script:
* the source code is (re)formatted using [scalariform](https://github.com/scala-ide/scalariform) each time it is compiled (currently deactivated).
* [`scalastyle`](http://www.scalastyle.org) sbt command shall be used to perform code style checks before putting changes into the repository.
* [`lint:compile`](https://github.com/HairyFotr/linter) sbt command shall be used to perform static code analysis before putting changes into the repository.
* code coverage for api and compiler modules can be executed by issuing `sbt clean coverage test` command for these 
projects. Coverage statistics can be generated using `coverageReport` sbt command. 
