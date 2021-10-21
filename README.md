# account-manager

## How to run
```
mvn spring-boot:run
```

## request sample
check balance 

```
curl --location --request GET 'http://localhost:8090/user/balance?username=12345678'
```

transfer

```
curl --location --request POST 'http://localhost:8090/transferral' \
--header 'Content-Type: application/json; charset=utf8' \
--data-raw '{
    "senderUsername": "12345678",
    "receiverUsername": "88888888",
    "amount":1000.0100011
}'
```