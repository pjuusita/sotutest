

Local setup:

- Install javaJDK
- Install maven
- Install eclipse
- Install TomCat
- Clone code from github
- Run SpringBootApp.java


Curl-examples

Endpoint: validate_ssn
- curl --request POST "http://localhost:8080/validate_ssn?ssn=131052-308T&country_code=FI"

Endpoint: exchange_amount
- curl --request GET "http://localhost:8080/exchange_amount?from=EUR&to=USD&from_amount=150"

Endpoint: fetch_rates
- curl -- request GET "http://localhost:8080/fetch_rates"


