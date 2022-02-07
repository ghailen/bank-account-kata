# bank-account-kata

Create bank account:

Method : POST

Curl: 

curl --location --request POST 'http://localhost:8080/api/accounting/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountId":1,
    "customerId": "098280778",
    "customerName": "ghailene"
}'

![image](https://user-images.githubusercontent.com/36199753/152818304-933f0828-8a89-4de2-b003-7ebd70bd2870.png)


User Stories

·         US 1:

In order to save money

As a bank client

I want to make a deposit in my account

=> Result:

Api created : http://localhost:8080/api/accounting/deposit

Method : POST

Body request : acountId and amount

Curl:

curl --location --request POST 'http://localhost:8080/api/accounting/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountId":1,
    "amount": 500
}'

![image](https://user-images.githubusercontent.com/36199753/152422237-2a949e33-6564-4f71-8a0d-91a43237f8e6.png)

In Java console the new balance will be printed :

![image](https://user-images.githubusercontent.com/36199753/152421021-be5296f8-d4bc-4ca3-b376-23a7536744d5.png)


·         US 2:

In order to retrieve some or all of my savings

As a bank client

I want to make a withdrawal from my account

=> Result:

Api created : http://localhost:8080/api/accounting/withdraw

Method : GET

Body request : acountId and amount

Curl :

curl --location --request GET 'http://localhost:8080/api/accounting/withdraw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountId":1,
    "amount": 800
}'

![image](https://user-images.githubusercontent.com/36199753/152422194-9543e10d-7298-461d-ba5a-5b6145840ec7.png)


In Java console the new balance will be printed :

![image](https://user-images.githubusercontent.com/36199753/152421732-c0e2d419-e991-41fe-936d-8c0fb6f4d072.png)

·         US 3:

In order to check my operations

As a bank client

I want to see the history (operation, date, amount, balance) of my operations

=> Result:

Api created : http://localhost:8080/api/accounting/operations-history/{accountId}

Method : GET

Curl:

curl --location --request GET 'http://localhost:8080/api/accounting/operations-history/1'

-Result in postman after the two operations : deposit and withdraw

![image](https://user-images.githubusercontent.com/36199753/152817590-07bb5c85-d884-4c88-8ab3-f600ce386902.png)

Unit tests :

![image](https://user-images.githubusercontent.com/36199753/152817307-fe395671-2d3b-488b-96f3-b72b4cfae764.png)

