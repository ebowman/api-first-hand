package de.zalando.model
import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ParameterPlace
import de.zalando.apifirst.naming._
import de.zalando.apifirst.Hypermedia._
import de.zalando.apifirst.Http._
import de.zalando.apifirst.Security
import java.net.URL
import Security._ 
//noinspection ScalaStyle
object full_petstore_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿User") → 
		TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())),
	Reference("⌿definitions⌿Order") → 
		TypeDef(Reference("⌿definitions⌿Order"), 
			Seq(
					Field(Reference("⌿definitions⌿Order⌿shipDate"), Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿quantity"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿petId"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿complete"), Opt(Bool(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿status"), Opt(Str(None, TypeMeta(Some("Order Status"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿definitions⌿Tag") → 
		TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
					Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Category") → 
		TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
					Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Pet") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv")),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿paths⌿/users/{username}⌿get⌿username") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿post⌿status") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users/login⌿get⌿username") → 
		Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿post⌿body") → 
		Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv")),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿put⌿body") → 
		Opt(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order⌿post⌿body") → 
		Opt(TypeDef(Reference("⌿definitions⌿Order"), 
			Seq(
					Field(Reference("⌿definitions⌿Order⌿shipDate"), Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿quantity"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿petId"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿complete"), Opt(Bool(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿status"), Opt(Str(None, TypeMeta(Some("Order Status"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿post⌿petId") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿delete⌿petId") → 
		Lng(TypeMeta(Some("int64"), List())),
	Reference("⌿paths⌿/pets/{petId}⌿delete⌿api_key") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users/login⌿get⌿password") → 
		Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿post⌿name") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿put⌿username") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users/createWithList⌿post⌿body") → 
		Opt(Arr(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByStatus⌿get⌿status") → 
		Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "multi"), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿put⌿body") → 
		Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv")),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByTags⌿get⌿tags") → 
		Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "multi"), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿get⌿petId") → 
		Lng(TypeMeta(Some("int64"), List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿orderId") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿orderId") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users⌿post⌿body") → 
		Opt(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿delete⌿username") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users/createWithArray⌿post⌿body") → 
		Opt(Arr(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())),
	Reference("⌿paths⌿/users/createWithList⌿post⌿responses⌿default") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByTags⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿put⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/login⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByStatus⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(ArrResult(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), ArrResult(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿delete⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order⌿post⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿Order"), 
			Seq(
					Field(Reference("⌿definitions⌿Order⌿shipDate"), Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿quantity"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿petId"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿complete"), Opt(Bool(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿status"), Opt(Str(None, TypeMeta(Some("Order Status"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿paths⌿/users/login⌿get⌿responses⌿200") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order⌿post⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿Order"), 
			Seq(
					Field(Reference("⌿definitions⌿Order⌿shipDate"), Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿quantity"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿petId"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿complete"), Opt(Bool(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Order⌿status"), Opt(Str(None, TypeMeta(Some("Order Status"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿paths⌿/pets⌿put⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/logout⌿get⌿responses⌿default") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿post⌿responses⌿405") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿put⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿delete⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(ArrResult(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), ArrResult(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByTags⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(ArrResult(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
						Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿photoUrls"), ArrResult(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
						Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿put⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/createWithArray⌿post⌿responses⌿default") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/findByStatus⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿responses⌿400") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{petId}⌿post⌿responses⌿405") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users/{username}⌿delete⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿put⌿responses⌿405") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users⌿post⌿responses⌿default") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿get⌿petId")) → Parameter("petId", Lng(TypeMeta(Some("int64"), List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿post⌿name")) → Parameter("name", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿post⌿body")) → Parameter("body", Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
		Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
			Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
			Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿photoUrls"), Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv")),
		Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
			Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
			Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿orderId")) → Parameter("orderId", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿post⌿petId")) → Parameter("petId", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿delete⌿petId")) → Parameter("petId", Lng(TypeMeta(Some("int64"), List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{username}⌿put⌿body")) → Parameter("body", Opt(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
		Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿delete⌿api_key")) → Parameter("api_key", Str(None, TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("header")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿put⌿body")) → Parameter("body", Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
		Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿tags"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Tag"), 
			Seq(
			Field(Reference("⌿definitions⌿Tag⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
			Field(Reference("⌿definitions⌿Tag⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿photoUrls"), Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv")),
		Field(Reference("⌿definitions⌿Pet⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿status"), Opt(Str(None, TypeMeta(Some("pet status in the store"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿category"), Opt(TypeDef(Reference("⌿definitions⌿Category"), 
			Seq(
			Field(Reference("⌿definitions⌿Category⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
			Field(Reference("⌿definitions⌿Category⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/users/{username}⌿put⌿username")) → Parameter("username", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/createWithList⌿post⌿body")) → Parameter("body", Opt(Arr(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
		Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/users/{username}⌿get⌿username")) → Parameter("username", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{username}⌿delete⌿username")) → Parameter("username", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/login⌿get⌿password")) → Parameter("password", Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/stores/order⌿post⌿body")) → Parameter("body", Opt(TypeDef(Reference("⌿definitions⌿Order"), 
			Seq(
		Field(Reference("⌿definitions⌿Order⌿shipDate"), Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Order⌿quantity"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Order⌿petId"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Order⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Order⌿complete"), Opt(Bool(TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Order⌿status"), Opt(Str(None, TypeMeta(Some("Order Status"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 6"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/users/createWithArray⌿post⌿body")) → Parameter("body", Opt(Arr(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
		Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/users⌿post⌿body")) → Parameter("body", Opt(TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
		Field(Reference("⌿definitions⌿User⌿email"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿username"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿userStatus"), Opt(Intgr(TypeMeta(Some("User Status"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿lastName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿firstName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿id"), Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿phone"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿User⌿password"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 8"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/pets/findByStatus⌿get⌿status")) → Parameter("status", Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "multi"), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/users/login⌿get⌿username")) → Parameter("username", Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/pets/{petId}⌿post⌿status")) → Parameter("status", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/pets/findByTags⌿get⌿tags")) → Parameter("tags", Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "multi"), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿orderId")) → Parameter("orderId", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path"))
) 
 def basePath: String = "/v2" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	"api_key" -> ApiKey(None, "api_key", ParameterPlace.withName("header")),
	"petstore_auth" -> OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" ))
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿pets⌿findByTags")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"findPetsByTags",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/findByTags⌿get⌿tags"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets/findByTags⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/pets/findByTags⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					400 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(POST, Path(Reference("⌿stores⌿order")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"placeOrder",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/stores/order⌿post⌿body"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/stores/order⌿post⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/stores/order⌿post⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿users")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"createUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users⌿post⌿body"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(Map.empty[Int, ParameterRef], Some(	ParameterRef(Reference("⌿paths⌿/users⌿post⌿responses⌿default")))), 
		StateResponseInfo(Map.empty[Int, State], Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿users⌿createWithList")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"createUsersWithListInput",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/createWithList⌿post⌿body"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(Map.empty[Int, ParameterRef], Some(	ParameterRef(Reference("⌿paths⌿/users/createWithList⌿post⌿responses⌿default")))), 
		StateResponseInfo(Map.empty[Int, State], Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users⌿{username}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"getUserByName",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{username}⌿get⌿username"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					200 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿users⌿{username}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"updateUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{username}⌿put⌿username")),
				ParameterRef(Reference("⌿paths⌿/users/{username}⌿put⌿body"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿put⌿responses⌿404")),
			400 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿put⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿users⌿{username}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"deleteUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{username}⌿delete⌿username"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿delete⌿responses⌿404")),
			400 -> ParameterRef(Reference("⌿paths⌿/users/{username}⌿delete⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿pets")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"updatePet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿put⌿body"))
				)
			), 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			405 -> ParameterRef(Reference("⌿paths⌿/pets⌿put⌿responses⌿405")),
			404 -> ParameterRef(Reference("⌿paths⌿/pets⌿put⌿responses⌿404")),
			400 -> ParameterRef(Reference("⌿paths⌿/pets⌿put⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					405 -> Self,
					404 -> Self,
					400 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(POST, Path(Reference("⌿pets")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"addPet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿post⌿body"))
				)
			), 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			405 -> ParameterRef(Reference("⌿paths⌿/pets⌿post⌿responses⌿405"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					405 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(POST, Path(Reference("⌿users⌿createWithArray")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"createUsersWithArrayInput",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/createWithArray⌿post⌿body"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(Map.empty[Int, ParameterRef], Some(	ParameterRef(Reference("⌿paths⌿/users/createWithArray⌿post⌿responses⌿default")))), 
		StateResponseInfo(Map.empty[Int, State], Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿stores⌿order⌿{orderId}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"getOrderById",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿orderId"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					200 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿stores⌿order⌿{orderId}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"deleteOrder",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿orderId"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿responses⌿404")),
			400 -> ParameterRef(Reference("⌿paths⌿/stores/order/{orderId}⌿delete⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users⌿logout")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"logoutUser",parameters = 
			Seq(

				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(Map.empty[Int, ParameterRef], Some(	ParameterRef(Reference("⌿paths⌿/users/logout⌿get⌿responses⌿default")))), 
		StateResponseInfo(Map.empty[Int, State], Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿pets⌿{petId}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"getPetById",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿get⌿petId"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					404 -> Self,
					200 -> Self,
					400 -> Self
			), None), 
		Set(
			ApiKeyConstraint("api_key", ApiKey(None, "api_key", ParameterPlace.withName("header"))),
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(POST, Path(Reference("⌿pets⌿{petId}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"updatePetWithForm",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿post⌿petId")),
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿post⌿name")),
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿post⌿status"))
				)
			), 
		Set(MimeType("application/x-www-form-urlencoded")), 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			405 -> ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿post⌿responses⌿405"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					405 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(DELETE, Path(Reference("⌿pets⌿{petId}")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"deletePet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿delete⌿api_key")),
				ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿delete⌿petId"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			400 -> ParameterRef(Reference("⌿paths⌿/pets/{petId}⌿delete⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					400 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(GET, Path(Reference("⌿pets⌿findByStatus")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"findPetsByStatus",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/findByStatus⌿get⌿status"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets/findByStatus⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/pets/findByStatus⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					400 -> Self
			), None), 
		Set(
			OAuth2Constraint("petstore_auth", OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "write_pets" -> "modify pets in your account" ,  "read_pets" -> "read your pets" )), Set("write_pets", "read_pets"))
		)), 
	ApiCall(GET, Path(Reference("⌿users⌿login")), 
		HandlerCall(
			"full.petstore.api.yaml",
			"FullPetstoreApiYaml",
			instantiate = false,
			"loginUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/login⌿get⌿username")),
				ParameterRef(Reference("⌿paths⌿/users/login⌿get⌿password"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/xml")), 
		Map("405" -> Seq(classOf[java.lang.IllegalArgumentException], classOf[java.lang.IndexOutOfBoundsException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/users/login⌿get⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/users/login⌿get⌿responses⌿400"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					400 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("full.petstore.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 