# [Model Definitions](#model-definitions)

Table of Contents
-----------------

- [Swagger Domain Definitions](#swagger-domain-definitions)
  - [Example](#example)
- [Primitive Types](#primitive-types)
- [Complex Types](#complex-types)
  - [Objects](#objects)
    - [Nested Objects](#nested-objects)
    - [Optionality](#optionality)
    - [Parameter optionality](#parameter-optionality)
    - [Object Extension](#object-extension)
    - [Polymorphism](#polymorphism)
    - [Additional Properties](#additional-properties)
  - [Arrays](#arrays)
    - [Nested Arrays](#nested-arrays)

## Swagger Domain Definitions
Scala domain model definitions are generated for all data types defined as Swagger parameters in an API specification.  Swagger parameters can be of path, query, header, form or body types, and consist of either primitive data types or more complex types composed from objects and arrays with primitives as leaves.  Both primitive types and complex types are mapped to Scala.

### Example
Let's consider a Swagger API specification file that defines the API of a simple pet store. It contains a model definition for a pet:

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

This definition consists of an object ```pet``` containing the required properties ```id``` and ```name```, and the optional property ```tag```. The Swagger primitive types of these properties are a 64-bit ```integer``` and (twice) a ```string```, successively.  The API-First-Hand plugin will map this definition on to a generated Scala model:
  
```scala
package simple.petstore.api
package object yaml {
    type PetTag = Option[String]
    case class Pet(id: Long, name: String, tag: PetTag)
}
```

This generated model contains a type definition ```PetTag```, which declares a type alias for the optional ```tag``` property, and a ```Pet``` case class with the properties as named in the Swagger API definition and mapped on the subsequent Scala primitive or declared types. The case class and type alias are generated in an package object ```yaml```.  This package object is contained in the package ```simple.petstore.api```, so that the full object name corresponds to the API filename.

Note that models are generated within a Play application as _managed_ code in the target folder. Generated model code is not intended to be altered. We should instead look upon the Swagger definition as the single source of truth, and as the source code that defines our model. The Swagger specification file of our API is, in that sense, part of the codebase. Even though the generated ```Pet``` case class is managed by the plugin, and not us, it can (of course) be used in our application codebase after being imported.
 
```
import simple.petstore.api.yaml._

val pet = Pet(0L, "Tucker", Some("Greyhound"))
```        
         
## [Primitive Types](#primitive-types)

Swagger version 2.0 allows for primitive data types based on the types defined by 
[JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8). When generated as Scala, the mapping indicated in [this chart](https://github.com/zalando/api-first-hand/blob/master/docs/DEFINITIONS.md#primitive-types) applies:
         
          
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
| date        | string       | date           | java.time.LocalDate                         |
| datetime    | string       | date-time      | java.time.ZonedDateTime                     |
| password    | string       | password       | scala.String                                |
| uuid        | string       | uuid           | java.util.UUID                              |
| file        | file         |                | java.io.File                                |

Additionally, if a validation of type "enum" is defined for some primitive type, a trait and a set of case objects forming an ADT will be generated for this enum.

## [Complex Types](#complex-types)

Complex types are made up of either primitive objects or nested objects.

### [Objects](#objects)

Complex object types are defined in Swagger model definitions as either objects or arrays. Again, objects are based on the [JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8) specification and defined as Swagger [Schema Objects](https://github.com/Swagger-api/Swagger-spec/blob/master/versions/2.0.md#schema-object) for parameter definitions of `type: "object"`. 

For example: Given a Swagger API definition file `api.yaml`, containing a model that defines a `person` as an object 
with the properties `name` and `age` of the primitive types `string` and `integer`, this object will be mapped on a Scala case class. It will be generated in a Scala package object (namespace) with the same name as the extension of the file that the specification is read from, and in a package with the same name as the Swagger definition file in which the model is defined. That is, `api`:
 
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

#### [Nested Objects](#nested-objects)

Nested objects are generated as adjourned but referenced hierarchically. For example:

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
Is generated as:

```scala
package api
package object yaml {
    case class Parent(child: ParentChild) 
    case class ParentChild(name: String) 
}

```

#### [Optionality](#optionality)

Swagger defines object properties as optional by default. You can override this by providing a list of `required` 
object properties as already used in the examples above. Optional properties are mapped upon Scala's `Option` type, 
for which a type alias is generated for each property that is optional. For example:

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
Is generated as:

```scala
package api
package object yaml {
    type ProductTag = Option[String]
    case class Product(name: String, tag: ProductTag) 
}

```

Objects can be nested, and object property optionality can be, too. To facilitate for nested optionality, we generate a nested Scala ```Option``` type alias. For example:

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
#### Parameter optionality

Object properties can be optional. Query, header, body and form parameters can be, too. If they are not required, they are mapped to Scala's `Option` type. 

Path parameters _must_ be declared as required. If a parameter is _not_ required, it can have a default value.

#### [Object Extension](#object-extension)

Objects can extend other objects via employment of Swagger's `allOff` property. In the example below, the `ExtendedErrorModel` inherits _all_ of the properties of the `ErrorModel` which it refers to—that is, the properties `message` and `code`—and _extends_ this model with the property `rootCause`. Swagger object extension is mapped by duplicating inherited properties in the object that extends. For example:

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

#### [Polymorphism](#polymorphism)

The Swagger `discriminator` property makes polymorphic object definitions possible. In the example definition below, an abstract `Pet` defines what concrete `Cat`s and `Dog`s have in common. Swagger object models define data, so a discriminator property is required to distinguish concrete cat and dog instances as they are serialised to and from the API. In this sense, the discriminator property works in the same way as a discriminator column works in ORM frameworks when mapping a class hierarchy onto a single table. It contains a value that maps onto one of the concrete types: For example, `petType: "Cat"` or `petType: "Dog"`. 

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

Please note how the enumeration of cat's `huntingSkill`'s becomes translated into the ADT with a sealed trait `CatHuntingSkill`, and four case objects implementing that trait.

#### [Additional Properties](#additional-properties)

Swagger's model language allows the additional properties of objects to be loosely defined, employing the `additionalProperties` annotation in order to model dictionaries. These dictionaries are mapped to Scala's `Map` type, for which a type alias is generated following the same (by now) well-known pattern as for optional properties. The map's key parameter type is a Scala `String`.

A Swagger additional property definition takes as its type property the element type of the dictionary, which can be of primitive or complex type and  mapped on Scala as the map's value parameter type. Swagger allows for one `additionalProperties` annotation per object definition, so we can generate this Scala parameter with the static name `additionalProperties`.

The following example defines a Swagger model object definition ```KeyedArray```, useing the ```additionalProperties``` annotation to provide the object with a set of key value mappings from string to array:
   
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

### [Arrays](#arrays)

Swaggers model ```type: array``` is used to define properties that hold sets or lists of model values, possibly of a primitive type, but complex element types are also allowed.  We map swagger array types on scala's ```Seq``` type, parameterised for the element type that it contains.
  
For example, in the snippet below, an ```Activity``` object definition is referred as item element in the ```messages``` property of ```type: array``` of the containing object definition ```Example```.  A scala type alias will be generated for the array type, just as we've seen before with for optional properties, after which the array containing property can be generated within the case class as being of this alias type. E.g. in the swagger definition and code

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

```scala
package api.yaml
object definitions {
  type ExampleMessagesArr = scala.collection.Seq[Activity]
  case class Activity(actions: ActivityActions)
  case class Example(messages: ExampleMessagesArr)
}
```

#### [Nested Arrays](#nested-arrays)

Array definition types can be nested and possibly optional. The following (contrived) snippet depicts the generated scala code when both definition types are employed in a somewhat unuseful manner.  The intend of this example is to show that the case class definitions are rather concisely generated, even though a stack of type aliases is needed to make sure that we still refer in scala code to an aptly named swagger definition, especially in conjunction with the object properties being optional.  Next to its benefits, type safety against ```null``` pointers does have an associated cost as well.
      
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

```scala
package api.yaml
object definitions {
  type ActivityActions = Option[String]
  type ExampleNested = Option[ExampleNestedOpt]
  type ExampleNestedOpt = scala.collection.Seq[ExampleNestedOptArr]
  type ExampleNestedOptArr = scala.collection.Seq[ExampleNestedOptArrArr]
  type ExampleNestedOptArrArr = scala.collection.Seq[ActivityActions]
  type ExampleMessages = Option[ExampleMessagesOpt]
  type ExampleMessagesOpt = scala.collection.Seq[ExampleMessagesOptArr]
  type ExampleMessagesOptArr = scala.collection.Seq[Activity]
  case class Activity(actions: ActivityActions)
  case class Example(messages: ExampleMessages, nested: ExampleNested)
}
```
