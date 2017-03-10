## [Test Generators](#test-generators)

When it comes to testing, having an API definition as the single source of truth in your codebase—with formal type specification of the input and output values, including their constraints—provides for a powerful feature. The Api-First-Hand plugin automates the creation of test data generators that can drive property checks directly from the API specification. 

Api-First-Hand derives data generators and unit tests directly from your Swagger API specification.

### [Property-Based Testing](#property-based-testing)

Property-based testing using generator-driven property checks is a cool way to test the validity of your application according to the rules or properties that apply to your application. Properties, in this sense, are high-level specifications that should always hold for a range of data values. The idea is to generate a range of data values for your data types and let (also generated) tests assert that the properties of these data types hold. 

A Swagger API definition contains formal type definitions _and_ constraints for all data values. The Api-First-Hand plugin maps these types on managed Scala source code that represents the data types. The plugin creates managed test data generators and unit tests that assert whether your application still complies to your specification. It does this in a single-source-of-truth manner, taking the Swagger API definition as the source.
 
We use ScalaTest's [property-based testing](http://www.scalatest.org/user_guide/property_based_testing) 
functionality as the framework for generating the data values, and map the data types of our API definition on 
the test data generators that API-First-Hand creates. You can use ScalaTest's `org.scalacheck.Gen` and `org.scalacheck.Arbitrary` objects (with utility methods) to generate a range of (possibly arbitrary) data values for common Scala types and primitives. Api-First-Hand uses these methods to create test data generators specific for the data types of your API definition. When necessary, it composes generators from primitive types as generators for complex types, so that you end up with a generator set that provides test data for your entire API.

### [Example API Definition](#example-api-definition)

As an example, let's take the API definition for the simple pet store—trimmed down to the parts defining 
parameter types, and omitting non-data definitions and error definitions:
 
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

### [Generated Data Types](#generated-data-types)

- The `get` method on path `/pets` takes an optional `limit` parameter of common type `integer`. 
- The `post` method takes a `newPet` body parameter comprising of the primitive attributes `id`, `name` and `tag`, subsequently of common types `long` and `string` (twice). Of these, only the `name` attribute is mandatory. 
- The `get` method on the path `/pets/{id}` takes the path parameter `id` of common type `long` and returns 
an array of `pet`s, consisting of the same attributes and primitive types as a `newPet`. But this time, both `name` and `id` are mandatory. 

This specification maps to the following managed Scala domain model code:
  
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

### [Generated Test Data Generators](#generated-test-data-generators)

We want test data generators that generate an arbitrary range of values for the model code shown above, composed from primitive, and sometimes optional, data definitions. Api-First-Hand does this by generating two Scala objects: one for the Swagger API definition, and one for the API path parts. Each object contains generator factory methods for the defined data types, prefixed by `create`, which returns a generator function. A generator function takes a given integer count 
and returns a generated amount of test data for the data type it was created for.

Data types are composed from primitive types, Scala optional types, and possibly more complex types. Test data values for the primitive types are generated arbitrarily, using ScalaCheck's `org.scalacheck.Arbitrary.arbitrary[T]` method. The type parameter is replaced with Scala's primitive type, on which the Swagger common type is mapped.

In the code shown below, starting with primitive leaf data values, the `pet` parameter's attribute `id` of common type `long` is arbitrarily generated from a `scala.Long`.  The `id` attribute is optional for the `newPet` definition. As with the generated model, we created a `NewPetIdGenerator` value that takes an arbitrarily generated `scala.Long` id value and generates an option value from it—using ScalaCheck's `org.scalacheck.Gen.option[T]`.  This generator will generate test data values comprising of `None` and `Some` arbitrarily id value. It's probably best to let the Scala generator code speak for itself. 

Note how it composes according to the same structure as the Scala model code:

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

API-First-Hand creates and implements a `PetGenerator` and `NewPetGenerator` as a for comprehension that generates data values for each attribute, yielding an instance of a test pet. Other generators follow the same pattern but, if necessary, delegate to different child generators. From this we acquire a set of test data generators to implement our property-based testing.

Running the test is as simple as running a test set from sbt. Just type `test` from your `sbt` prompt.
