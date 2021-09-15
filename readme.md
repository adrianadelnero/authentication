OAuth2 com Spring Boot 2 

Install JDK 8

How to run:

Build:
```
mvn clean install
```

Start:
```
mvn springboot:run
```

Curl's test:

```
curl --location --request POST 'http://localhost:5000/oauth/token' \
--header 'Authorization: Basic Y2xpZW50OjEyMw==' \
--form 'grant_type="password"' \
--form 'password="secret"' \
--form 'username="admin"'
```

```
curl --location --request GET 'http://localhost:5000/user?page=0&size=1' \
--header 'Authorization: Bearer {{access-token}}'
```
