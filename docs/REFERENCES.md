## Swagger Reference `$ref`

Within Swagger, a Reference Object is a JSON Reference that uses JSON Pointer as its string value. Values that the Reference Object points to are canonically dereferenced.

### JSON Reference
A JSON Object containing a `$ref` attribute with a string value.  The "$ref" string value contains a URI, which identifies the location of the JSON value being referenced.  The JSON Reference [specification](https://tools.ietf.org/html/draft-pbryan-zyp-json-ref-03) in itself does not require the string value to be a JSON Pointer, but Swagger definitions limit valid JSON References to those that are.  Informally spoken this means that the Swagger Reference Object's string value contains a URI with a URI fragment part containing a JSON Pointer or a URI without a fragment part, that identifies some rooted JSON document value.  Note that the URI may be relative.

### JSON Pointer
A JSON Pointer is a string syntax for identifying a specific value within a JSON document.  A JSON Pointer is a Unicode string containing a sequence of zero or more reference tokens, each prefixed by a `/` character.  Token values can be strings or integers, with strings identifying (possibly nested) JSON Object fields and integers identifying JSON Array indices.  The characters `/` and `~` are escaped, see the JSON Pointer [specification](http://tools.ietf.org/html/rfc6901) for more information.

### Canonical dereferencing
A Swagger implementation should canonically dereference all resolved JSON Reference URI values.  Informally spoken this means that relative URI's should be made absolute before values are dereferenced.  See the JSON Schema [specification](http://json-schema.org/latest/json-schema-core.html#anchor27) paragraph 7.2.3 for more information on the difference about canonical dereferencing and inline dereferencing.

Question: should the ID keyword (paragraph 7.2.2) also be handled?


### Examples

```yaml
$ref: 'pet.yaml'                            // relative rooted reference
$ref: '#/definitions/Pet'                   // relative fragment reference
$ref: 'definitions.yaml#/Pet'               // relative file fragment reference

$ref: 'file:///pet.yaml'                    // absolute rooted file reference
$ref: 'file:///definitions.yaml#/Pet'       // absolute file fragment reference
$ref: 'http://some.host/definitions#/Pet'   // absolute remote fragment reference
```


## Methods on Json Pointer and JSON Reference

The methods on JSON Pointer and Reference provide for hierarchical naming for AST nodes, particularly from within the different swagger to AST converters, and can be used to build names for these nodes when traversing the swagger model.  The intend is to make all AST node names fully qualified, i.e. the references are absolute, including the base swagger `file:/` or `http://` from which the swagger model was parsed.

For example, the minimal `file:/echo.yaml` specification containing:

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

Has path definitions for the root `/` and `/test-path/{id}` accepting `GET` and `POST` operations that accept a body or different primitive types of path and form data parameters. This definition will be converted into the following fully qualified named type definitions:

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

The following methods and fields on `Reference` enable the creation and navigation of names from within the converter classes.

### Reference

#### `def /(token: String): Reference`

This method created a new JSON Reference postpending given JSON Pointer token, e.g. given an existing `val bar = Reference("file:/echo.yaml#/foo") / "bar"` will create the reference name `file:/echo.yaml#/foo/bar`.

#### `def /(pointer: Pointer): Reference`

This method created a new JSON Reference postpending given JSON Pointer, e.g. given an existing `val bar = Reference("file:/echo.yaml#/foo") / Pointer("/bar/baz")` will create the reference name `file:/echo.yaml#/foo/bar/baz`.

#### `def prepend(token: String): Reference`

This method created a new JSON Reference prepending given JSON Pointer token, e.g. given an existing `val bar = Reference("file:/echo.yaml#/foo") prepend "bar"` will create the reference name `file:/echo.yaml#/bar/foo`.

#### `implicit def uriToReference(uri: URI):  Reference`

This implicit method creates a JSON Reference from a given uri. The use case is to instantiate each converter class with a `base: URI` parameter containing the absolute `file:/` or `http:/` swagger definition from which the swagger model was parsed, thus giving each converter class the possibility to instantiate a "root" reference name, e.g. given the `base = URI.create("file:/echo.yaml")`example above, `val paths = base / "paths"` creates the reference name `file:/echo.yaml#/paths`.

#### `val parent: Reference`

This value returns a new reference to this reference's parent pointer token or a reference to its base, i.e. the whole document, if no such parent token exists, e.g. `Reference("file:/echo.yaml#/bar/foo").parent == Reference("file:/echo.yaml#/bar")` and `Reference("file:/echo.yaml#/foo").parent == Reference("file:/echo.yaml#")`.  Note that as pointers allow for the `/` token, the parent reference of `Reference("file:/echo.yaml#/")` also equals `Reference("file:/echo.yaml#")`, just like the previous example.

#### `def simple:  String`

This utility value contains the last token string as a name or the empty string if no token exists, i.e. `Reference("file:/echo.yaml#/foo/bar") == "bar"`, `Reference("file:/echo.yaml#/") == "/"` and `Reference("file:/echo.yaml#") == ""`.

#### `val pointer: Pointer`

The pointer value gives access to the reference its pointer, providing a way to create new reference names to the same pointer but in different documents, e.g.:

```scala
val ref1 = Reference("file/a.yaml#/foo/bar")
val ref2 = Reference("file/b.yaml")
println(ref2 / ref1.pointer) // prints file/b.yaml#/foo/bar
``` 






