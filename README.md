# Play-Swagger

[![Build Status](https://travis-ci.org/zalando/play-swagger.svg)](https://travis-ci.org/zalando/play-swagger)
[![codecov](https://codecov.io/gh/zalando/play-swagger/branch/master/graph/badge.svg)](https://codecov.io/gh/zalando/play-swagger)
[![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zalando/play-swagger?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Compatibility

- Play 2.4
- Swagger (OpenAPI) 2.0

## Status

This plugin should be enabled using the [play-swagger-service](http://www.typesafe.com/activator/template/play-swagger-service) activator template 
as the version in this repository is under active development. The status of this software is beta, 
an end-to-end functional release intended to demonstrate the possibility to generate following from a Swagger specification:

- Play route files
- Generators of random test data
- Wrappers for Play route files to convert semantics from http-related to domain-related (controller_base)
- Skeletons for the domain-driven controller implementation
- Model classes and validation rules
- Unit tests for invalid and valid parameter sets
- Security extractors (if needed)
- Skeletons for custom deserializers (if needed)

We benefit from community feedback. All comments are welcome!

# Play-Swagger Tutorial

This tutorial is based on the [play-swagger-service](http://www.typesafe.com/activator/template/play-swagger-service) activator template.

```bash
$ activator new playground play-swagger-service
```

The template project contains following:

- `tutorial` folder with HTML tutorial
- `public/swagger` folder containing static files needed for swagger UI
- `project` folder containing pre-configured `plugins.sbt` file with a definition of all required resolvers and plugins
- `conf` folder with following customized contents:
    * `routes` file with route configuration for Swagger UI, example specification and commented out links to other examples
    * `example.yaml`, a demo Swagger specification. The specification has a dummy implementation in `app` folder. 
    * `examples` folder containing other different Swagger specification examples. Each specification in this folder represents some aspect of the Play-Swagger plugin in more details.
        For the specification to be picked up by the plugin it must be moved into the `conf` folder. It is allowed to have multiple Swagger specifications in the `conf` folder at the same time. 
- `app` directory with following template implementations:
    * `controllers/Swagger.scala` - a backend side of the Swagger UI
    * `generated_controllers/example.yaml.scala` - a dummy implementation of the example controller. Will be (re)generated if deleted
    * `security/example.yaml.scala` - a marshaller for OAuth2 tokens. Will not be regenerated until 
        a) deleted or renamed
        b) explicitly requested by issuing a `apiFirstSecurity` command 


## Welcome to Play-Swagger

Congratulations, you just created a new Play-Swagger application!

The [Play Framework](http://www.playframework.com/) with the [Play-Swagger](https://github.com/zalando/play-Swagger/) 
plugin make it easy to build RESTful web services from a Swagger API specification as the single source of truth. 
Play is based on a lightweight, stateless, web-friendly architecture. Built on [Akka](http://akka.io), 
Play provides predictable and minimal resource consumption for highly-scalable applications. 
The Play-Swagger plugin takes Swagger API definitions and treats them as the single source of truth of your REST services.

Play-Swagger supports round-trip regeneration and compilation of:

- Play routes definitions (managed).
- Swagger domain model definitions and parameters onto Scala case classes (managed).
- Swagger domain model constraints onto Play validations (managed).
- Generators for random test data generation of parameter values (managed).
- Unit tests for validating your service at the API boundary (managed).
- Swagger path definitions onto skeletons for Play controller implementations (unmanaged).

In the list above, "(managed)" means that the code is managed by sbt. The code is not controlled 
and altered by you, the programmer of the REST service. The plugin takes your Swagger API definition as the single 
source of truth and regenerates these code parts in a consistent manner.
You'll instead be focusing on implementing the service business logic in an (unmanaged) Play controller class 
that is generated once. Subsequent regenerations keep the code that you have added, either by commenting out the 
parts that are no longer valid, or by adding parts that are needed because you have made a change to the API.

Manual generation and compilation of:

- Security extractors
- Unmarshallers for custom content types 

is supported in the case if

a) No security extractor or unmarshaller with the same name already exists
b) The developer issues `apiFirstSecurity` or `apiFirstMarshallers` sbt command 

## Run Your Application

Before we go any further, let's run the application.

- Open a shell and `cd` into your service project directory.
- Start `sbt` and `run` the service.
- View the running application at [http://localhost:9000](http://localhost:9000).

The service template comes with the Swagger UI frontend included, 
run statically from the within Play, which provides a sandbox for your service. 
The template is configured with a template Swagger API definition called `example.yaml` 
and located in the `conf` directory of the Play application. 

The `example.yaml` definition provides an example [API description](https://github.com/zalando/play-swagger-service/blob/master/conf/example.yaml)

This definition contains three end points: 
- the `/token` path, which accept the `GET` and `POST` methods
- the `/todos/{user_id}`, which accepts the `GET` method. 

The `GET /token` API plays a role of an authentication server and is used by the Swagger UI for OAuth token requests.
The `POST /token` API represents an authorization server and is used by the security part of the 
generated code to validate OAuth tokens.
 
The `GET /todos/{user_id}` takes a path parameter `user_id` and returns a TODO list for given user. 
For the client to be allowed to access this endpoint, it must provide an OAuth token with the scope `admin:org`. 
The token can be requested using the Swagger UI.

Try it out for yourself: 

Click the [default](http://localhost:9000/) button to expand the API definition in the Swagger UI.


# Play Routes Integration

As a Play application developer, you are used to defining your endpoints in the `conf/routes` file. 
Not so with the Play-Swagger plugin! Swagger API specifications already define endpoints as `path` definitions, 
as seen in the example above. So why do the work twice, right? Instead, the Play-Swagger plugin requires you to 
link your API definition in the routes file ones—making all Swagger API-defined endpoints available as children 
of one single path context location, and generating Play route definitions from them (as shown below):

```
->      /example        example.yaml.Routes
```

Note that the `conf/routes` file provided by this activator template also contains a couple of additional `GET` 
mappings required for the the Swagger UI sandbox.

There are a couple of commented out links to other examples. If you activate some specification by moving it from 
the `examples` folder into the `conf` folder, you'll need to uncomment an appropriate line in the `routes` file in
order for play to be able to find it.  


## Swagger Domain Definitions

Scala domain model definitions are generated for all data types defined as Swagger parameters in an API specification. 
Swagger parameters can be of path, query, header, form or body types, and consist of either primitive data types or 
more complex types composed from objects and arrays with primitives as leaves. 

Both primitive types and complex types are mapped to scala.

As an example, let's look at the Swagger API specification file [`simple.petstore.api.yaml`](https://github.com/zalando/play-swagger-service/blob/master/conf/examples/simple.petstore.api.yaml), 
which defines the API of a simple pet store. It contains a model definition for a pet.

```yaml
definitions:
  pet:
    required:
      - id
      - name
    properties:
      id:
        type: integer
        format: int64
      name:
        type: string
      tag:
        type: string
```

This definition consists of an object `pet` containing the required properties `id` and `name` 
and the optional property `tag`. The Swagger primitive types of these properties are a 64-bit `integer` 
and (twice) a `string`, successively.  The Play-Swagger plugin will map this definition on to a generated Scala model.

```scala
package simple.petstore.api

package object yaml {

    type PetTag = Option[String]

    case class Pet(id: Long, name: String, tag: PetTag)
}
```

This generated model contains a type definition `PetTag`, which declares a type alias for the optional `tag` property, 
and a `Pet` case class with the properties as named in the Swagger API definition and mapped on the subsequent 
Scala primitive or declared types. The case class and type alias are generated in an package object `yaml`, 
this package  object itself is contained in the package `simple.petstore.api` so that full object name corresponds 
to the API filename.

Note that models are generated within a Play application as _managed_ code in the target folder. 
Generated model code is not intended to be altered.  We should instead look upon the Swagger definition as the single 
source of truth, and as the source code that defines our model.
The Swagger specification file of our API is, in that sense, part of the codebase. 
Even though the generated `Pet` case class is managed by the plugin, and not us, it can (of course) 
be used in our application codebase after being imported.

```scala
import simple.petstore.api.yaml._

val pet = Pet(0L, "Tucker", Some("Greyhound"))
```

## Specification Cross-References

A `$ref` element of the specification is allowed to contain a name of file as it's part. Because of this, it is possible to split 
a single specification into multiple files as shown in [`cross_spec_references.yaml`](https://github.com/zalando/play-swagger-service/blob/master/conf/examples/cross_spec_references.yaml) 
example. It is also possible to reference a definition in one specification from another specification. 
In this case for each reference an independent copy of the class definition will be created for each referencing specification. 
The definition is then placed into the appropriate package for each specification. 

Thus, even if multiple classes with  the same name and structure might be generated, they all will coexist in their 
own separate namespaces and won't be interchangeable.


## Primitive Types

Swagger version 2.0 allows for primitive data types based on the types defined by 
[JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8).

When generated as Scala, the following mapping applies:

| Common Name | Swagger Type | Swagger Format | Scala Type                                  |
|-------------|--------------|----------------|---------------------------------------------|
| integer     | integer      | int32          | scala.Int                                   |
| long        | integer      | int64          | scala.Long                                  |
| float       | number       | float          | scala.Float                                 |
| double      | number       | double         | scala.Double                                |
| big int     | integer      |                | scala.math.BigInt                           |
| big decimal | number       |                | scala.math.BigDecimal                       |
| boolean     | boolean      |                | scala.Boolean                               |
| string      | string       |                | scala.String                                |
| byte        | string       | byte           | de.zalando.play.controllers.Base64String    |
| binary      | string       | binary         | de.zalando.play.controllers.BinaryString    |
| date        | string       | date           | org.joda.time.LocalDate                     |
| datetime    | string       | date-time      | org.joda.time.DateTime                      |
| password    | string       | password       | scala.String                                |
| file        | file         |                | java.io.File                                |

Additionally, if a validation of type "enum" is defined for some primitive type, a trait and a set of case objects forming an ADT 
will be generated for this enum.

## Complex Types

Complex types are made up of primitive objects, or nested objects.

### Objects

Complex object types are defined in Swagger model definitions as either objects or arrays.

Objects are, again, based on the [JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8) specification 
and defined as Swagger [Schema Objects](https://github.com/Swagger-api/Swagger-spec/blob/master/versions/2.0.md#schema-object) 
for parameter definitions of `type: "object"`.
For example, given a Swagger API definition file `api.yaml` containing a model that defines a `person` as an object 
with the properties `name` and `age` of the primitive types `string` and `integer` subsequently, 
this object will be mapped on a Scala case class, and generated in a Scala package object (namespace) with the same name 
as the extension of the file the specification is read from and in a package with the same name as the 
Swagger definition file in which the model is defined—that is, `api`

```yaml
definitions:
  person:
    type: object
    required:
      - name
      - age
    properties:
      name:
        type: string
      age:
        type: integer
        format: int32
```

Is generated into:

```scala
package api
package object yaml {
    case class Person(name: String, age: Int) 
}
```

### Nested Objects

Nested objects are generated adjourned but referenced hierarchically. E.g.

```yaml
definitions:
  parent:
    type: object
    required:
      - child
    properties:
      child:
        type: object
        required:
          - name
        properties:
          name:
            type: string
```

Is generated into:

```scala
package api
package object yaml {
    case class Parent(child: ParentChild) 
    case class ParentChild(name: String) 
}

```

### Optionality

Swagger, by default, defines object properties to be optional, which can be overridden by providing a list of `required` 
object properties as already used in the examples above. Optional properties are mapped upon Scala's `Option` type, 
for which a type alias is generated for each property that is optional. E.g.

```yaml
definitions:
  product:
    required:
      - name
    properties:
      name:
        type: string
      tag:
        type: string
```

Which is generated as:

```scala
package api
package object yaml {
    type ProductTag = Option[String]
    case class Product(name: String, tag: ProductTag) 
}

```

As objects can be nested, so can object property optionality. To facilitate for nested optionality, we generate a nested scala `Option` type alias. E.g.

```yaml
definitions:
  Basic:
    properties:
      optional:
        type: object
        properties:
          nested:
            type: string
```

Which is generated as:

```scala
package api
package object yaml {
    type BasicOptional = Option[BasicOptionalOpt]
    type BasicOptionalNested = Option[String]

    case class BasicOptionalOpt(nested: BasicOptionalNested) 
    case class Basic(optional: BasicOptional) 
}
```


### Parameter optionality

As object properties can be optional, so can be query, header, body or form parameters. 
In the case if they are not required, they are mapped to the Scala's `Option` type. 

Path parameters are _must_ be declared as required.

In the case, if a parameter is _not_ required, it is allowed to have a default value.


### Extension

Objects can extend other objects via employment of Swagger's `allOff` property. In the example below, the `ExtendedErrorModel` inherits _all of_ the properties of the `ErrorModel` which it refers to—that is, the properties `message` and `code`—and _extends_ this model with the property `rootCause`. Swagger object extension is mapped by duplicating inherited properties in the object that extends. E.g.

```yaml
definitions:
  ErrorModel:
    type: object
    required:
    - message
    - code
    properties:
      message:
        type: string
      code:
        type: integer
  ExtendedErrorModel:
    allOf:
    - $ref: '#/definitions/ErrorModel'
    - type: object
      required:
      - rootCause
      properties:
        rootCause:
          type: string
```

Which is generated as:

```scala
package api
package object yaml {
  import scala.math.BigInt
  case class ErrorModel(message: String, code: BigInt) 
  case class ExtendedErrorModel(message: String, code: BigInt, rootCause: String) 
}

```

### Polymorphism

Polymorphic object definitions are possible through employment of the Swagger `discriminator` property. 
In the example definition below, an abstract `Pet` defines what concrete `Cat` and `Dog`s have in common. 
Swagger object models define data, so a discriminator property is required to distinguish concrete cat and dog 
instances as they are serialised to and from the API. In this sense, the discriminator property works 
in the same way as a discriminator column works in ORM frameworks when mapping a class hierarchy onto a single table. 
It simply contains a value that maps onto one of the concrete types—for example, `petType: "Cat"` or `petType: "Dog"`.

```yaml
definitions:
  Pet:
    discriminator: petType
    properties:
      name:
        type: string
      petType:
        type: string
    required:
    - name
    - petType
  Cat:
    allOf:
    - $ref: '#/definitions/Pet'
    - properties:
        huntingSkill:
          type: string
          default: lazy
          enum:
          - clueless
          - lazy
          - adventurous
          - aggressive
      required:
      - huntingSkill
  Dog:
    allOf:
    - $ref: '#/definitions/Pet'
    - properties:
        packSize:
          type: integer
          format: int32
      required:
      - packSize
```

Which is generated as:

```scala
package api

package object yaml {

    trait IPet {
        def name: String
        def petType: String
    }

    case class Cat(name: String, petType: String, huntingSkill: CatHuntingSkill) extends IPet
    case class Dog(name: String, petType: String, packSize: Int) extends IPet
    case class Pet(name: String, petType: String) extends IPet

    sealed trait CatHuntingSkill { def value: String }
    case object Clueless extends CatHuntingSkill { val value = "clueless" }
    case object Lazy extends CatHuntingSkill { val value = "lazy" }
    case object Adventurous extends CatHuntingSkill { val value = "adventurous" }
    case object Aggressive extends CatHuntingSkill { val value = "aggressive" }
    implicit def stringToCatHuntingSkill(in: String): CatHuntingSkill = in match {
        case "clueless" => Clueless
        case "lazy" => Lazy
        case "adventurous" => Adventurous
        case "aggressive" => Aggressive
    }
}

```

Please note how the enumeration of cat's `huntingSkill`'s get's translated into the ADT with a sealed trait `CatHuntingSkill`
and four case objects implementing that trait.

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
can be added to your API definition. The Play-Swagger plugin will generate validations for these parameter 
constraints and make sure that your controller methods are only called if the input of your service 
complies to those constraints. 

In the example below, the API definition of the `token` parameter of 
type `Base64String`, as the form parameter, contains validation rules for the lenght of the perameter as well as a regexp pattern the value of the parameter must confirm to.
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

## Test Generators

Having an API definition as the single source of truth in your codebase—with formal type specification of the in- and output values, 
including their constraints—provides for a powerful feature when it comes to testing. 
The Play-Swagger plugin automates the creation of test data generators that can drive property checks directly 
from the API specification. Play-Swagger derives data generators and unit tests directly from your Swagger API specification.

Property-based testing using generator-driven property checks is a cool way to test the validity of your application 
according to the rules or properties that apply to your application. Properties, in this sense, are high-level 
specifications that should always hold for a range of data values. The idea is to generate a range of data 
values for your data types and let (also generated) tests assert that the properties of these data types hold. 
A Swagger API definition contains formal type definitions _and_ constraints for all data values, and the Play-Swagger 
plugin maps these types on managed Scala source code that represents the data types, so it is also possible to map 
these API definitions on test data generators that provide a range of data values for these types. 
The plugin does exactly that: It creates managed test data generators and unit tests that assert whether your 
application still complies to your specification. It does so in a single-source-of-truth manner, 
taking the Swagger API definition as the source.

We employ the ScalaTest [property-based testing](http://www.scalatest.org/user_guide/property_based_testing) 
functionality as the framework to generate the data values, and map the data types of our API definition on 
the test data generators that are created by the plugin. ScalaTest provides 
`org.scalacheck.Gen` and `org.scalacheck.Arbitrary` objects with utility methods that help generate a range of 
(possibly arbitrary) data values for common Scala types and primitives. The Play-Swagger plugin uses these 
methods to create test data generators specific for the data types of our API definition. When necessary, 
it composes generators from primitive types into generators for complex types, so that you end up with a 
set of generators that provide test data for your complete API.

As an example, let's take the API definition for the simple pet store—trimmed down to the parts defining 
parameter types, and (for brevity) omitting any non-data definitions and error definitions:

```yaml
paths:
  /pets:
    get:
      parameters:
        - name: limit
          in: query
          required: false
          type: integer
          format: int32
      responses:
        default:
          description: error payload
    post:
      parameters:
        - name: pet
          in: body
          required: true
          schema:
            $ref: '#/definitions/newPet'
      responses:
        default:
          description: error payload
  /pets/{id}:
    get:
      parameters:
        - name: id
          in: path
          required: true
          type: integer
          format: int64
      responses:
        default:
          description: error payload
    delete:
      parameters:
        - name: id
          in: path
          required: true
          type: integer
          format: int64
      responses:
        default:
          description: error payload
definitions:
  pet:
    required:
      - id
      - name
    properties:
      id:
        type: integer
        format: int64
      name:
        type: string
      tag:
        type: string
  newPet:
      required:
        - name
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
        tag:
          type: string
```

The `get` method on path `/pets` takes an optional `limit` parameter of common type `integer`. 
The `post` method takes a `newPet` body parameter comprising of the primitive attributes `id`, `name` and `tag`, 
subsequently of common types `long` and `string` (twice). Of these, only the `name` attribute is mandatory. 
The `get` method on the path `/pets/{id}` takes the path parameter `id` of common type `long` and returns 
an array of `pet`s consisting of the same attributes and primitive types as a `newPet` - but this time 
with both `name` and `id` being mandatory. This specification maps to the following managed Scala domain model code:


```scala
package example

package object yaml {

    import de.zalando.play.controllers.PlayPathBindables

    type PetsIdDeleteResponsesDefault = Null
    type NewPetTag = Option[String]
    type PetsIdDeleteId = Long
    type PetsGetLimit = Option[Int]
    type NewPetId = Option[Long]

    case class Pet(id: Long, name: String, tag: NewPetTag) 
    case class NewPet(name: String, id: NewPetId, tag: NewPetTag) 

    implicit val bindable_OptionIntQuery = PlayPathBindables.createOptionQueryBindable[Int]
}
```

We want to have test data generators that generate an arbitrary range of values for the model 
code shown above - composed from primitive, and sometimes optional, data definitions. 
The Play-Swagger plugin does this by generating two Scala objects: one for the Swagger API definition, 
and one for the API path parts. Each object contains generator factory methods for the defined data types, 
prefixed by `create`, which returns a generator function. A generator function takes a given integer count 
and returns a generated amount of test data for the data type it was created for.

Data types are composed from primitive types, Scala optional types, and possibly more complex types. 
Test data values for the primitive types are generated arbitrarily, employing the ScalaCheck 
`org.scalacheck.Arbitrary.arbitrary[T]` method (the type parameter, replaced with Scala's primitive type, 
on which the Swagger common type is mapped).

In the code shown below, starting with primitive leaf data values, the `pet` parameter's attribute `id` 
of common type `long` is arbitrarily generated from a `scala.Long`.  Note that the `id` attribute is optional, 
though, for the `newPet` definition. As with the generated model, we created a `NewPetIdGenerator` value that 
takes an arbitrarily generated `scala.Long` id value and generates an option value from it, employing the 
ScalaCheck `org.scalacheck.Gen.option[T]`.  This generator will generate test data values comprising of `None` 
and `Some` arbitrarily id value. It's probably best to let the Scala generator code speak for itself. 
Note how it composes according to the same structure as the Scala model code.

```scala
package example.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {

    def createNullGenerator = _generate(NullGenerator)
    def createNewPetTagGenerator = _generate(NewPetTagGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createPetsGetLimitGenerator = _generate(PetsGetLimitGenerator)
    def createNewPetIdGenerator = _generate(NewPetIdGenerator)

    def createPetGenerator = _generate(PetGenerator)
    def createNewPetGenerator = _generate(NewPetGenerator)

    def NullGenerator = arbitrary[Null]
    def NewPetTagGenerator = Gen.option(arbitrary[String])
    def LongGenerator = arbitrary[Long]
    def PetsGetLimitGenerator = Gen.option(arbitrary[Int])
    def NewPetIdGenerator = Gen.option(arbitrary[Long])

    def PetGenerator = for {
        id <- arbitrary[Long]
        name <- arbitrary[String]
        tag <- NewPetTagGenerator
    } yield Pet(id, name, tag)
    def NewPetGenerator = for {
        name <- arbitrary[String]
        id <- NewPetIdGenerator
        tag <- NewPetTagGenerator
    } yield NewPet(name, id, tag)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
}
```

A `PetGenerator` and `NewPetGenerator` are created and implemented by the plugin as a for comprehension 
that generates data values for each attribute, yielding an instance of a test pet. Other generators follow 
the same pattern but, if necessary, delegate to different child generators. From this we acquire a set of 
test data generators to implement our property-based testing.

Running the test is as simple as running a test set from sbt. Just type `test` from your `sbt` prompt.

# Building a Play-Swagger Plugin

To build a plugin, do the following:

- Clone the repository to your local filesystem
- Run ```sbt +publishLocal``` in the Play-Swagger directory. This will publish the plugin into your local ivy repository

To use the plugin in a plain Play project:

- Create a new Play-Swagger project using activator template, for example: ```activator new hello-world play-swagger-service```
- Take a look at the `project/plugins.sbt` of the generated project and add required plugins and resolvers to the `project/plugins.sbt` of your Play project
- Do the same for `build.sbt`
- Put a Swagger specification with a ```.yaml``` or ```.json``` extension into the ```conf``` directory
- Add a specification link (`->`) to the play's routes file


## Plugin Architecture

Ths Play-Swagger plugin has a three-tier architecture:

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

Because of the modular plugin architecture, all modules must be enabled separatly in sbt's build.sbt. 
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
    * `play_scala_controller_base.mustache` - for play controller bases 
    * `play_scala_controller_security.mustache` - for security adapters used by controller bases
    * `play_scala_form_parser.mustache` - for form parsers used by the controller bases
    * `play_scala_controller.mustache` - for play controller skeletons supposed to be augmented by the programmer
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

The play-swagger plugin provides a couple of commands useful for development: 

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
