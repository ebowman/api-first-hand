# [Test Generators](#test-generators)

Having an api definition as the single source of thruth in our codebase, with formal type specification of our in and output values, including their constraints, provides for a powerful feature when it comes to testing.  The play swagger plugin automates the creation of test data generators which can drive property checks directly from the api specification.

## [Property Based Testing](#property-based-testing)

Property based testing using generator-driven property checks is an upcomming way to test the validity of your application according to the rules, or properties, that apply to your application.  Properties in this sense are high-level specifications that should always hold for a range of data values.  The idea is to generate a range of data values for our data types and let our (also generated) tests assert that the properties of these data types hold.  As a swagger api definition contains formal type definitions _and_ constraints for all data values, and as the play swagger plugin maps these types on _unmannaged_ scala source code that represent the data types, it is also possible to map these api definitions on test data generators that provide a range of data values for these types.  The plugin does exactly that, it creates unmannaged test data generators and unit tests that assert whether your application still complies to your specification, and it does so in a single source of thruth manner, taking the swagger api definition as the source.
 
We employ ScalaTest's [Property-based testing](http://www.scalatest.org/user_guide/property_based_testing) as the framework to generate the data values and map the data types of our api definition on the test data generators that are created by the plugin.  ScalaTest provides ```org.scalacheck.Gen``` and ```org.scalacheck.Arbitrary``` objects with utility methods that help generating a range of (possibly arbitrary) data values for common scala types and primitives.  The play swagger plugin uses this methods to create test data generators specific for the data types of our api definition, and when neccesarry composing generators from primitive types into generators for complex types, such that we end up with a set of generators that provide test data for our complete api.

### [Example API Definition](#example-api-definition)

As an example, lets take the api definition for the simple pet store, trimmed down to the parts defining parameter types, so ommiting any non-data definitions and error definitions for brevity.
 
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
    post:
      parameters:
        - name: pet
          in: body
          required: true
          schema:
            $ref: '#/definitions/newPet'
  /pets/{id}:
    get:
      parameters:
        - name: id
          in: path
          required: true
          type: integer
          format: int64
    delete:
      parameters:
        - name: id
          in: path
          required: true
          type: integer
          format: int64
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

The ```get`` method on path ```/pets``` takes an optional ```limit``` parameter of common type ```integer``` while the ```post``` method takes a ```newPet``` body parameter comprising of the primitive attributes ```id```, ```name``` and ```tag```, subsequently of common types ```long``` and ```string``` twice.  Of these only the ```name``` attribute is mandatory.  The ```get``` method on the path ```/pets/{id}``` takes the path parameter ```id``` of common type ```long``` and returns an array of ```pet```'s, consisting of the same attributes and primitive types as a ```newPet```, but this time with both ```name``` and ```id``` being mandatory.  This specification maps to the following unmannaged scala domain model code.
  
```scala
package simple.petstore.api.yaml
object definitions {
  type NewPetTag = Option[String]
  type NewPetId = Option[Long]
  case class Pet(id: Long, name: String, tag: NewPetTag) 
  case class NewPet(name: String, id: NewPetId, tag: NewPetTag) 
}
object paths {
  import definitions._
  type PetsGetLimit = Option[Int]
}
```

### [Generated Test Data Generators](#generated-test-data-generators)

We want to have test data generators that generate an arbitrary range of values for the unmanaged model code shown above, composed from primitive, and sometimes optional, data definitions.  The play swagger plugin does so by generating two scala objects, one for the swagger api definition and one for the api path parts.  Each object contains generator factory methods for the defined data types prefixed by ```create``` that returns a generator function.  Generator functions take a given integer count and return a generated amount of test data for the data type it was created for.
  
Data types are composed from primitive types, scala optional types, and possibly more complex types.  Test data values for the primitive types are generated arbitrarily employing ScalaCheck's ```org.scalacheck.Arbitrary.arbitrary[T]``` method, the type parameter replaced with scala's primitive type on which swagger common type is mapped.

Starting with primitive leaf data values, the ```pet``` parameter's attribute ```id``` of common type ```long``` is arbitrarily generated from a ```scala.Long``` in the code shown below.  Note that the ```id``` attribute is optional though for the ```newPet``` definition, and as with the generated model we created a ```NewPetIdGenerator``` value that takes an arbitrarily generated ```scala.Long``` id value and generates an option value from it, employing ScalaCheck's ```org.scalacheck.Gen.option[T]```.  This generator will generate test data values comprising of ```None``` and ```Some``` arbitrarily id value.  It's probably best to let the unmanaged scala generator code speak for itself, note how it composes according to the same structure as the unmanaged scala model code.

```scala
package simple.petstore.api.yaml
import org.scalacheck.Gen
import org.scalacheck.Arbitrary._

object definitionsGenerator {
    import definitions._

    def createNewPetTagGenerator = _generate(NewPetTagGenerator)
    def createNewPetIdGenerator = _generate(NewPetIdGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createNewPetGenerator = _generate(NewPetGenerator)

    val NewPetTagGenerator = Gen.option(arbitrary[String])
    val NewPetIdGenerator = Gen.option(arbitrary[Long])
    
    val PetGenerator =
        for {
            id <- arbitrary[Long]
            name <- arbitrary[String]
            tag <- NewPetTagGenerator
        } yield Pet(id, name, tag)
    
    val NewPetGenerator =
        for {
            name <- arbitrary[String]
            id <- NewPetIdGenerator
            tag <- NewPetTagGenerator
        } yield NewPet(name, id, tag)
    
    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
}

object pathsGenerator {
    import definitions._

    def createPetsGetLimitGenerator = _generate(PetsGetLimitGenerator)

    val PetsGetLimitGenerator = Gen.option(arbitrary[Int])

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
}
```

A ```PetGenerator``` and ```NewPetGenerator``` is created and implemented by the plugin as a for comprehension that generates data values for each attribute, yielding an instance of a test pet.  Other generators follow the same pattern but delegate to different child generators if necessary.  From this we acquire a set of test data generators to implement our property based testing.
 
 

