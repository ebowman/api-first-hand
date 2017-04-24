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
object foodpanda_yaml extends WithModel {

	def types = Map[Reference, Type](
		Reference("⌿definitions⌿VendorsResponse") →
			TypeDef(Reference("⌿definitions⌿VendorsResponse"),
				Seq(
					Field(Reference("⌿definitions⌿VendorsResponse⌿total_hits"), Intgr(TypeMeta(Some("int32"), List("min(0.toInt, false)")))),
					Field(Reference("⌿definitions⌿VendorsResponse⌿vendors"), Opt(ArrResult(TypeRef(Reference("⌿definitions⌿Vendor")), TypeMeta(None, List())), TypeMeta(None, List())))
				), TypeMeta(Some("Named types: 2"), List())),
		Reference("⌿definitions⌿Location") →
			TypeDef(Reference("⌿definitions⌿Location"),
				Seq(
					Field(Reference("⌿definitions⌿Location⌿latitude"), Flt(TypeMeta(Some("float"), List("max(90.toFloat, false)", "min(-90.toFloat, false)")))),
					Field(Reference("⌿definitions⌿Location⌿longitude"), Flt(TypeMeta(Some("float"), List("max(180.toFloat, false)", "min(-180.toFloat, false)"))))
				), TypeMeta(Some("Named types: 2"), List())),
		Reference("⌿definitions⌿Vendor") →
			TypeDef(Reference("⌿definitions⌿Vendor"),
				Seq(
					Field(Reference("⌿definitions⌿Vendor⌿id"), Lng(TypeMeta(Some("int64"), List("min(1.toLong, false)")))),
					Field(Reference("⌿definitions⌿Vendor⌿location"), Opt(TypeRef(Reference("⌿definitions⌿Location")), TypeMeta(None, List())))
				), TypeMeta(Some("Named types: 2"), List())),
		Reference("⌿definitions⌿VendorQuery") →
			TypeDef(Reference("⌿definitions⌿VendorQuery"),
				Seq(
					Field(Reference("⌿definitions⌿VendorQuery⌿vendor_codes"), Opt(ArrResult(Str(None, TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿VendorQuery⌿includes"), Opt(TypeRef(Reference("⌿definitions⌿VendorQuery⌿includes⌿Option⌿Enum")), TypeMeta(None, List())))
				), TypeMeta(Some("Named types: 2"), List())),
		Reference("⌿definitions⌿VendorQuery⌿includes⌿Option⌿Enum") →
			EnumTrait(Str(None, TypeMeta(None, List("""enum("menus,payments")"""))), TypeMeta(None, List("""enum("menus,payments")""")),
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("menus,payments")"""))), "menus", TypeMeta(Some("menus"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("menus,payments")"""))), "payments", TypeMeta(Some("payments"), List()))

				)),
		Reference("⌿paths⌿/vendors⌿get⌿responses⌿200") →
			Intgr(TypeMeta(Some("int32"), List())),
		Reference("⌿definitions⌿VendorQuery⌿includes⌿Option⌿Enum⌿menus") →
			EnumObject(Str(None, TypeMeta(None, List("""enum("menus,payments")"""))), "menus", TypeMeta(Some("menus"), List())),
		Reference("⌿definitions⌿VendorQuery⌿includes⌿Option⌿Enum⌿payments") →
			EnumObject(Str(None, TypeMeta(None, List("""enum("menus,payments")"""))), "payments", TypeMeta(Some("payments"), List()))
	)

	def parameters = Map[ParameterRef, Parameter](
		ParameterRef(	Reference("⌿paths⌿/vendors⌿get⌿query")) → Parameter("query", TypeRef(Reference("⌿definitions⌿VendorQuery")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
	)
	def basePath: String = "/api"
	def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
	def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](

	)
	def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
	def calls: Seq[ApiCall] = Seq(
		ApiCall(GET, Path(Reference("⌿vendors")),
			HandlerCall(
				"com.foodpanda.popsey.api",
				"FoodpandaYaml",
				instantiate = false,
				"searchVendors",parameters =
					Seq(
						ParameterRef(Reference("⌿paths⌿/vendors⌿get⌿query"))
					)
			),
			Set.empty[MimeType],
			Set(MimeType("application/json")),
			Map.empty[String, Seq[Class[Exception]]],
			TypesResponseInfo(
				Map[Int, ParameterRef](
					200 -> ParameterRef(Reference("⌿paths⌿/vendors⌿get⌿responses⌿200"))
				), None),
			StateResponseInfo(
				Map[Int, State](
					200 -> Self
				), None),
			Set.empty[Security.Constraint]))

	def packageName: Option[String] = Some("com.foodpanda.popsey.api")

	def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)

}
