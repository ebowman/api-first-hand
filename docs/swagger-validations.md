## Swagger Validations

Swagger API definitions allow you to impose constraints on parameter types. You can use the `required` constraint to mark a parameter or specific field within a domain definition as required upon input. You can also add to your API definition more constraints, as defined by the [Parameter Object](https://github.com/swagger-api/swagger-spec/blob/master/versions/2.0.md#parameterObject). API-First-Hand will generate validations for these parameter constraints and make sure that your controller methods are only called if the input of your service complies to those constraints.

### Examples
Here, the API definition of the `token` parameter of type `Base64String`, as the form parameter, contains validation rules for the length of the parameter plus a regexp pattern to which the value of the parameter must conform. The parameter is also required:

```yaml
...
parameters:
      - name: token
        in: formData
        description: oauth2 token
        type: string
        format: byte
        pattern: "[A-Za-z0-9]*"
        minLength: 5
        maxLength: 100
        required: true
...
```

Another example:

```yaml
...
    get:
      parameters:
      - name: state
        in: query
        description: Any application state to be forwarded back to the frontend
        type: string
        minLength: 1
        maxLength: 110
        required: false
...
```

The `state` parameter is of type string. It's not required, and has no default value. You can only have a length state between 1 and 110; otherwise, it won't pass validation. (For demo purposes, let's change its type to `integer` and make it required.) 

Now that the parameter is required, the `default` value can't be present. The `maxLength` and `maxLength` validations 
aren't allowed for integer parameters, so let's replace them with `minimum` and `maximum` values:
    
```yaml
...
    get:
      parameters:
      - name: state
        in: query
        description: Any application state to be forwarded back to the frontend
        type: integer
        format: int32
        required: true
        minimum: 2000
        maximum: 2100      
...
```    

We've just changed the parameter type, so refreshing Swagger UI will generate validations for that parameter type. It will also force regeneration of the model consistent with the validation. That's nice! Note, however, that it will break the current implementation of the controller class, because the implementation of the `postAction` expects `state` to be of type `String`:

![Validation screenshot](/docs/validations-01.png)

Let's change the implementation. The second parameter `state` is no longer of type `Option[String]` but of type `Int`. We'll change the implementation to take this fact into account:

```scala
...
val tokenGet = tokenGetAction { input: (String, String, String, Int) =>
    val (redirect_uri, scope, response_type, state) = input
    // ----- Start of unmanaged code area for action  TokenService.tokenGet
    val statePart = s"""state=$state"""
...
}
```

Refreshing the Swagger UI and trying out a couple of integer values for `state` shows that the service now accepts values within the range `[2000..2100]`, but returns a descriptive error when outside:

```json
[
  {
    "messages": [
      "error.max"
    ],
    "args": [
      2100
    ]
  }
]
```
