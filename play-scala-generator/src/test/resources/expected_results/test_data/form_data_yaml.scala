package form_data.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt
import java.io.File

object Generators extends JsValueGenerators {
    

    
    def createOptionFileGenerator = _generate(OptionFileGenerator)
    def createStringGenerator = _generate(StringGenerator)
    def createFileGenerator = _generate(FileGenerator)
    def createOptionBigIntGenerator = _generate(OptionBigIntGenerator)
    

    
    def OptionFileGenerator = Gen.option(arbitrary[File])
    def StringGenerator = arbitrary[String]
    def FileGenerator = arbitrary[File]
    def OptionBigIntGenerator = Gen.option(arbitrary[BigInt])
    

    def createMultipartPostResponses200Generator = _generate(MultipartPostResponses200Generator)
    def createBothPostResponses200Generator = _generate(BothPostResponses200Generator)


    def MultipartPostResponses200Generator = for {
        name <- Gen.option(arbitrary[String])
        year <- Gen.option(arbitrary[BigInt])
        fileSize <- Gen.option(arbitrary[BigInt])
        fileName <- Gen.option(arbitrary[String])
    } yield MultipartPostResponses200(name, year, fileSize, fileName)
    def BothPostResponses200Generator = for {
        name <- Gen.option(arbitrary[String])
        year <- Gen.option(arbitrary[BigInt])
        avatarSize <- Gen.option(arbitrary[BigInt])
        ringtoneSize <- Gen.option(arbitrary[BigInt])
    } yield BothPostResponses200(name, year, avatarSize, ringtoneSize)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    implicit lazy val arbFile: Arbitrary[File] = Arbitrary(for {
        content <- arbitrary[String]
    } yield de.zalando.play.controllers.PlayPathBindables.tempFileFromString(content))
    

}