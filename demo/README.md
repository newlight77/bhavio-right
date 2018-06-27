# Spring-boot demo with graph database


## Docker-compose

Use docker-compose to startup a database of choice :

```
docker-compose up -d neo4j
```



## Database

### Neo4j setup


```
curl -v -u neo4j:neo4j -X POST localhost:7474/userModel/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}

```

Note : Neo4j requires to change password at first login


## Run test

```
curl -X POST \
  http://localhost:8080/right-demo/users \
  -H 'Content-Type: application/json' \
  -d '{
	"firstname": "admin firstname",
	"lastname": "admin lastname",
	"username": "admin"
}'

#this would return an error
```

add rights 
```
curl -X POST \
  http://localhost:8080/right-demo/rights \
  -H 'Content-Type: application/json' \
  -H 'primary: master_user' \
  -H 'secondary: admin_resource' \
  -d '{
	"primary": "adminId",
	"secondary": "admin_resource",
	"rights": ["admin_read", "admin_write"]
}'

# response :
{
    "classname": "com.newlight77.exception.ForbiddenException",
    "date": "2018-06-27T21:26:20.893Z",
    "message": "You don't have access : filter = RightFilter(primary=, secondary=, rights=[ADMIN_WRITE])",
    "path": "/right-demo/users",
    "params": null
}
```


try again
```
curl -X POST \
  http://localhost:8080/right-demo/users \
  -H 'Content-Type: application/json' \
  -H 'primary: adminId' \
  -H 'secondary: admin_resource' \
  -d '{
	"firstname": "admin firstname",
	"lastname": "admin lastname",
	"username": "admin"
}'
```
