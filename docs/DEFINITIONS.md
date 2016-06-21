# [Model Definitions](#model-definitions)

Scala domain model definitions are generated for all data types defined as swagger parameters in an api specification.  Swagger parameters can be of path, query, header, form or body types and consist of either primitive data types or more complex types composed from objects and arrays with primitives as leaves.  Both primitive types and complex types are mapped to scala.  As an example, lets look at the swagger api specification file ```simple.petstore.api.yaml``` that defines the api of a simple pet store.  It contains a model definition for a pet.

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

This definition consists of an object ```pet``` containing the required properties ```id``` and ```name```, and the optional property ```tag```, the swagger primitive types of these properties are a 64 bit ```integer``` and twice a ```string``` successively.  The play swagger plugin will map this definition on to a generated scala model.
  
```scala
package simple.petstore.api.yaml
object definitions {
    type PetTag = Option[String]
    case class Pet(id: Long, name: String, tag: PetTag) 
}
```

This generated model contains a type definition ```PetTag``` that declares a type alias for the optional ```tag``` property and a ```Pet``` case class with the properties as named in the swagger api definition and mapped on the subsequent scala primitive or declared types.  The case class and type alias are generated in an object ```definitions```, taken from swagger's api specification root property with the same name.  This object itself is contained in the package ```simple.petstore.api.yaml```, which is taken from the api's filename.

Note that models are generated within a play application as _unmanaged_ code in the target folder.  Generated model code is not intended to be altered, we should instead look upon the swagger definition as the single source of truth, and indeed, as the source code that defines our model.  The swagger specification file of our api is in that sense part of the codebase.  Even though the generated ```Pet``` case class is not managed by us but by the plugin instead, it can of course, after being imported, be used in our application codebase.
 
```
import simple.petstore.api.yaml.definitions._

val pet = Pet(0L, "Tucker", Some("Greyhound"))
```        
         
## [Primitive Types](#primitive-types)
         
Swagger version 2.0 allows for primitive data types based on the types defined by [JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8).  When generated as scala the following mapping applies.
          
| Common Name | Swagger Type  | Swagger Format  | Scala Type           |
| ----------- | ------------- | --------------- | -------------------- |
| integer     | ```integer``` | ```int32```     | ```scala.Int```      |
| long        | ```integer``` | ```int64```     | ```scala.Long```     |
| float       | ```number```  | ```float```     | ```scala.Float```    |
| double      | ```number```  | ```double```    | ```scala.Double```   |
| boolean     | ```boolean``` |                 | ```scala.Boolean```  |
| string      | ```string```  |                 | ```scala.String```   |
| byte        | ```string```  | ```byte```      | ```scala.Byte```     |
| binary      | ```string```  | ```binary```    | _unsupported_        |
| date        | ```string```  | ```date```      | ```java.util.Date``` |
| datetime    | ```string```  | ```date-time``` | ```java.util.Date``` |
| password    | ```string```  | ```password```  | ```scala.String```   |
| file        | ```file```    |                 | ```java.io.File```   |

## [Complex Types](#complex-types)

Complex types are defined in swagger model definitions as either objects or arrays.

### [Objects](#objects)

Objects are, again, based on the [JSON-Schema](http://json-schema.org/latest/json-schema-core.html#anchor8) specification and defined as swagger [Schema Objects](https://github.com/swagger-api/swagger-spec/blob/master/versions/2.0.md#schema-object) for parameter definitions of ```type: "object"```.  For example, given a swagger api definition file ```api.yaml``` containing a model that defines a ```person``` as an object with the properties ```name``` and ```age``` of the primitive types ```string``` and ```integer``` subsequently, this object will be mapped on a scala case class, generated in a scala object (namespace) with the same name as the root swagger property in which it occurs.  I.e. ```definitions```, and in a package with the same name as the swagger definition file in which the model is defined, that is, ```api.yaml```.
 
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
 
```scala
package api.yaml
object definitions {
  case class Person(name: String, age: Int)
}
```

#### [Nested Objects](#nested-objects)

Objects can be nested, in which case the scala case class parameter of the outer object that defines the nested object will be the scala case class that is generated for that parameter. E.g.

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

```scala
package api.yaml
object definitions {
  case class Parent(child: Child)
  case class Child(name: String)
}
```

#### [Optionality](#optionality)

Swagger, by default, defines object properties to be optional, which can be overridden by providing a list of ```required``` object properties as already used in the examples above.  Optional properties are mapped upon scala's ```Option``` type for which a type alias is generated for each property that is optional. E.g.

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

```scala
package api.yaml
object definitions {
    type ProductTag = Option[String]
    case class Product(name: String, tag: ProductTag)
}
```

As objects can be nested, so can be object property optionality.  To facilitate for nested optionality, we generate a nested scala ```Option``` type alias. E.g.

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

```scala
object definitions {
  type BasicOptional = Option[BasicOptionalOpt]
  type BasicOptionalNested = Option[String]
  case class Basic(optional: BasicOptional)
  case class BasicOptionalOpt(nested: BasicOptionalNested)
}
```

#### [Object Extension](#object-extension)

Objects can extend other objects via employment of swaggers' ```allOf``` property.  In the example below the ```ExtendedErrorModel``` inherits _all of_ the properties of the ```ErrorModel``` which it refers to, that is, the properties ```message``` and ```code```, and _extends_ this model with the property ```rootCause```.

Swagger object extension is mapped by duplicating inherited properties in the object that extends. E.g.

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

```scala
package api.yaml
object definitions {
  case class ErrorModel(message: String, code: Int)
  case class ExtendedErrorModel(message: String, code: Int, rootCause: String)
}
```

#### [Polymorphism](#polymorphism)

Polymorphic definitions are possible through employment of the swagger ```discriminator``` property.  In the example definition below an abstract ```Pet``` defines that what concrete ```Cat```s and ```Dog```s have in common.  As swagger object models are defining data, a discriminator property is required to distinguish concrete cat and dog instances as they are serialised to and from the api.  The discriminator property works in that sense the same way as a discriminator column works in ORM frameworks when mapping a class hierarchy onto a single table.  It simply contains a value that maps onto one of the concrete types, for example ```petType: “Cat”``` or ```petType: “Dog”```.

The generated scala code for polymorphic definitions do not have such ORM serialisation requirements as the generated case classes are data containers within the application’s codebase.  We would like to omit swagger’s discriminator property from the generated code and map the abstract type on an interface.  But as we are modelling data we cannot predict whether business logic depends on the value of of it, we therefor map the abstract swagger model definition on a scala trait which contains accessor methods for all the properties it defines, that is, including the discriminator property, concrete case classes extending from this trait implement it with concrete values. E.g.      

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

```scala
package api.yaml
object definitions {
  trait IPet {
    def name: String
    def petType: String
  }
  case class Cat(name: String, petType: String, huntingSkill: String) extends IPet
  case class Dog(name: String, petType: String, packSize: Int) extends IPet
}
```

### [Additional Properties](#additional-properties)

Swagger model definition objects allow for additional properties to be loosely defined employing the ```additionalProperties``` annotation in order to model dictionaries.  These dictionaries are mapped to scala's ```Map`` type for which a type alias is generated following the same (by now) well known pattern as for optional properties, with the map's key parameter type being a scala ```String```.

A swagger additional property definition takes as its type property the element type of the dictionary which can be of primitive or complex type and which is mapped on scala as the map's value parameter type.  Swagger allowss for one ```additionalProperties``` annotation per object definition so we can generate this scala parameter with the static name ```additionalProperties```.

In the following example we define a swagger model object definition ```KeyedArray``` that uses the ```additionalProperties``` annotation to provide the object with a set of key value mappings from string to array. E.g.
   
```yaml
definitions:
  KeyedArrays:
    type: object
    additionalProperties:
      type: array
      items:
        type: integer
```

```scala
package api.yaml
object definitions {
    type KeyedArraysAdditionalProperties = scala.collection.immutable.Map[String, KeyedArraysAdditionalPropertiesArr]
    type KeyedArraysAdditionalPropertiesArr = scala.collection.Seq[Int]
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