STEPS

Install and set up docker
mkdir mondoDb_docker
cd mondoDb_docker
docker run -p 2717:27017 -v ~/mondoDb_docker:/data/db --name mongoDb -d mongo:latest

clone repository
Run the application

REQUESTS

GET
1) To get a show that is recommended for a user
    http:localhost:8080/get_show?id=<user_id>

2) To get a show that is streaming today
    http:localhost:8080/get_show_today

3) To get a user details
    http:localhost:8080/user?id=<user_id>

4) To login user
    http:localhost:8080/login?id=<user_id>&pass=<password>

PATCH

1) To reset the recommended shows
    http:localhost:8080/reset?id=<user_id>

2) To update the user details
    http:localhost:8080/update
    Body : User

POST

1) To register a user
   http:localhost:8080/register
   Body : User