GET /persons -> list of persons in <persons><person id="123" firstName="edi" lastName="dd" birthdate="2004-07-12"/></persons>

```bash
curl \
  -X GET \
  -vvv \
  http://localhost:8080/persons
```
GET /persons/:personId -> the details for the person <person id="123" firstName="edi" lastName="dd" birthdate="2004-07-12"/>

```bash
curl \
  -X GET \
  -vvv \
  http://localhost:8080/persons/123
```


POST /persons -> creates a new person
request body: <person firstName="edi" lastName="dd" birthdate="2004-07-12"/>
response body: <person id="123" firstName="edi" lastName="dd" birthdate="2004-07-12"/>

```bash
curl \
-X POST \
-H 'Content-Type:text/xml' \
-d '<person firstName="Alex" lastName="Jitareanu" birthDate="2004-09-25"/>' \
-vvv \
http://localhost:8080/persons
```
PUT /persons/:personId -> update an existing person
request body: <person firstName="edi" lastName="dd" birthdate="2004-07-12"/>
response body: <person id="123" firstName="edi" lastName="dd" birthdate="2004-07-12"/>
```bash
curl \
  -X PUT \
  -H 'Content-Type:text/xml' \
  -d '<person firstName="Alex" lastName="Jitareanu" birthDate="2004-09-25"/>' \
  -vvv \
  http://localhost:8080/persons/123
```

DELETE /persons/personID -> deletes an existing person
response body: <person id="123" firstName="edi" lastName="dd" birthdate="2004-07-12"/>
```bash
curl \
-X DELETE \
-vvv \
http://localhost:8080/persons/123
```

