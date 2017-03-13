An in-depth explanation how we work with JSON references in the plugin's source code, meant to be useful for plugin developers.

## Swagger Reference `$ref`

Within Swagger, a Reference Object is a JSON reference that uses JSON Pointer as its string value. Values that the Reference Object points to are canonically dereferenced.

### JSON Reference
A JSON Object containing a `$ref` attribute with a string value. The "$ref" string value contains a URI that identifies the location of the JSON value being referenced. The JSON Reference [specification](https://tools.ietf.org/html/draft-pbryan-zyp-json-ref-03) in itself does not require the string value to be a JSON Pointer, but Swagger definitions limit valid JSON References to those that are. The Swagger Reference Object's string value contains a URI with a URI fragment part containing a JSON Pointer; or a URI without a fragment part that identifies some rooted JSON document value. The URI may be relative.

### JSON Pointer
A JSON Pointer is a string syntax for identifying a specific value within a JSON document. It's a Unicode string containing a sequence of zero or more reference tokens, each prefixed by a `/` character. Token values can be strings or integers: Strings identify (possibly nested) JSON Object fields, and integers identify JSON Array indices. The characters `/` and `~` are escaped. See the JSON Pointer [specification](http://tools.ietf.org/html/rfc6901) for more information.

### Canonical Dereferencing
A Swagger implementation should canonically dereference all resolved JSON Reference URI values. Relative URIs should be made absolute before values are dereferenced. See the JSON Schema [specification](http://json-schema.org/latest/json-schema-core.html#anchor27), paragraph 7.2.3, for more information on the difference between canonical dereferencing and inline dereferencing.

### Examples

```yaml
$ref: 'pet.yaml'                            // relative rooted reference
$ref: '#/definitions/Pet'                   // relative fragment reference
$ref: 'definitions.yaml#/Pet'               // relative file fragment reference

$ref: 'file:///pet.yaml'                    // absolute rooted file reference
$ref: 'file:///definitions.yaml#/Pet'       // absolute file fragment reference
$ref: 'http://some.host/definitions#/Pet'   // absolute remote fragment reference
```

## Methods on JSON Pointer and JSON Reference

The methods on JSON Pointer and JSON Reference provide for hierarchical naming for AST nodes, particularly from within the different Swagger to AST converters. You can use them to build names for these nodes when traversing the Swagger model. The intent is to make all AST node names fully qualified—i.e., the references are absolute, including the base Swagger `file:/` or `http://`, from which the Swagger model was parsed.

For example, the minimal `file:/echo.yaml` specification containing ...:

```yaml
paths:
  /:
    get:
      responses:
        200:
          description: Echo GET
    post:
      responses:
        200:
          description: Echo POST
      parameters:
        - name: name
          in: formData
          description: name
          type: string
        - name: year
          in: formData
          description: year
          type: string
  /test-path/{id}:
    get:
      parameters:
        - name: id
          in: path
          description: ID
          type: string
          required: true
      responses:
        200:
          description: Echo test-path
```

... has path definitions for the root `/` and `/test-path/{id}` accepting `GET` and `POST` operations taking a body or different primitive types of path and form data parameters. This definition will be converted into the following, fully qualified named-type definitions:

```
file:/echo.yaml#/paths//get/responses/200 ->
	Null
file:/echo.yaml#/paths//post/name ->
	Opt(Str)
file:/echo.yaml#/paths//post/responses/200 ->
	Null
file:/echo.yaml#/paths//post/year ->
	Opt(Str)
file:/echo.yaml#/paths/test-path/{id}/get/id ->
	Str
file:/echo.yaml#/paths/test-path/{id}/get/responses/200 ->
	Null
```

### Reference

The following methods and fields on `Reference` enable the creation and navigation of names from within the converter classes.

#### `def /(token: String): Reference`

This method creates a new JSON Reference, postpending the given JSON Pointer token. Given an existing `val bar = Reference("file:/echo.yaml#/foo") / "bar"` will create the reference name `file:/echo.yaml#/foo/bar`.

#### `def /(pointer: Pointer): Reference`

This method creates a new JSON Reference, postpending given JSON Pointer. Given an existing `val bar = Reference("file:/echo.yaml#/foo") / Pointer("/bar/baz")` will create the reference name `file:/echo.yaml#/foo/bar/baz`.

#### `def prepend(token: String): Reference`

This method creates a new JSON Reference, prepending given JSON Pointer token. Given an existing `val bar = Reference("file:/echo.yaml#/foo") prepend "bar"` will create the reference name `file:/echo.yaml#/bar/foo`.

#### `implicit def uriToReference(uri: URI):  Reference`

This implicit method creates a JSON Reference from a given URI. The use case is to instantiate each converter class with a `base: URI` parameter containing the absolute `file:/` or `http:/` Swagger definition from which the Swagger model was parsed, enabling each converter class to instantiate a "root" reference name. Given the `base = URI.create("file:/echo.yaml")`example above, `val paths = base / "paths"` creates the reference name `file:/echo.yaml#/paths`.

#### `val parent: Reference`

This value returns a new reference either to this reference's parent pointer token or to a reference to its base—the whole document, if no such parent token exists: for example, `Reference("file:/echo.yaml#/bar/foo").parent == Reference("file:/echo.yaml#/bar")` and `Reference("file:/echo.yaml#/foo").parent == Reference("file:/echo.yaml#")`. 

Pointers allow for the `/` token, so the parent reference of `Reference("file:/echo.yaml#/")` also equals `Reference("file:/echo.yaml#")`, just like in the previous example.

#### `def simple:  String`

This utility value contains the last token string as a name or the empty string if no token exists, i.e. `Reference("file:/echo.yaml#/foo/bar") == "bar"`, `Reference("file:/echo.yaml#/") == "/"` and `Reference("file:/echo.yaml#") == ""`.

#### `val pointer: Pointer`

The pointer value gives access to the reference its pointer, providing a way to create new reference names to the same pointer but in different documents, e.g.:

```scala
val ref1 = Reference("file/a.yaml#/foo/bar")
val ref2 = Reference("file/b.yaml")
println(ref2 / ref1.pointer) // prints file/b.yaml#/foo/bar
```
