package com.foodpanda.popsey.api

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createVendorQueryIncludesOptionEnumGenerator = _generate(VendorQueryIncludesOptionEnumGenerator)
    def createIntGenerator = _generate(IntGenerator)
    

    
    def VendorQueryIncludesOptionEnumGenerator = { import VendorQueryIncludesOptionEnum._ ; Gen.oneOf(Seq(Menus, Payments)) }
    def IntGenerator = arbitrary[Int]
    

    def createVendorsResponseGenerator = _generate(VendorsResponseGenerator)
    def createLocationGenerator = _generate(LocationGenerator)
    def createVendorGenerator = _generate(VendorGenerator)
    def createVendorQueryGenerator = _generate(VendorQueryGenerator)


    def VendorsResponseGenerator = for {
        total_hits <- arbitrary[Int]
        vendors <- Gen.option(Gen.containerOf[List,Vendor](VendorGenerator))
    } yield VendorsResponse(total_hits, vendors)
    def LocationGenerator = for {
        latitude <- arbitrary[Float]
        longitude <- arbitrary[Float]
    } yield Location(latitude, longitude)
    def VendorGenerator = for {
        id <- arbitrary[Long]
        location <- Gen.option(LocationGenerator)
    } yield Vendor(id, location)
    def VendorQueryGenerator = for {
        vendor_codes <- Gen.option(Gen.containerOf[List,String](arbitrary[String]))
        includes <- Gen.option(VendorQueryIncludesOptionEnumGenerator)
    } yield VendorQuery(vendor_codes, includes)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}