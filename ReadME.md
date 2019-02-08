## Hotel Service
- all APIs need private key

### creating hotel

#### request
```text
curl -X POST \
  http://localhost:8082/create \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 16c133e4-2c77-45a7-9cb8-4690930b4804' \
  -H 'X-API-Key: abcdef12345' \
  -H 'cache-control: no-cache' \
  -d '{
	"requiredBonusPoints":100,
	"name":"casa blanca",
	"availableRooms": 10
}'
```
#### response
```text
b3163e2b-d5e6-44bb-adbd-fee3ed74565d
```
### add available rooms

#### request
```text
curl -X PUT \
  http://localhost:8082/rooms/add \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345' \
  -d '{
	"id":"b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
	"rooms": 1
}'
```

### deduct available rooms

#### request
```text
curl -X PUT \
  http://localhost:8082/rooms/deduct \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 97dd7c92-89eb-484c-a4a1-0ddb938e999f' \
  -H 'X-API-Key: abcdef12345' \
  -H 'cache-control: no-cache' \
  -d '{
	"id":"b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
	"rooms": 3
}'
```

### requesting the Hotel details

#### request
```text
curl -X GET \
  http://localhost:8082/hotel/b3163e2b-d5e6-44bb-adbd-fee3ed74565d \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0b451492-5d39-4d50-a17f-0cf31b52d2dc' \
  -H 'X-API-Key: abcdef12345' \
  -H 'cache-control: no-cache' \
  -d '{
	"id":"b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
	"rooms": 3
}'
```
#### response
```text
{
    "id": "b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
    "requiredBonusPoints": 100,
    "name": "casa blanca",
    "availableRooms": 8
}
```


### requesting all changes to the Hotel

#### request
```text
curl -X GET \
  http://localhost:8082/events/b3163e2b-d5e6-44bb-adbd-fee3ed74565d \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345'
}'
```
#### response
```text
[
    {
        "id": "b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
        "availableRooms": 10,
        "message": "initialize hotel",
        "creationTime": "2019-02-08T08:53:01.288Z",
        "pointsNeeded": 100,
        "name": "casa blanca"
    },
    {
        "id": "b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
        "availableRooms": 1,
        "message": "add rooms",
        "creationTime": "2019-02-08T08:55:14.481Z"
    },
    {
        "id": "b3163e2b-d5e6-44bb-adbd-fee3ed74565d",
        "availableRooms": 3,
        "message": "deducted rooms",
        "creationTime": "2019-02-08T08:56:07.803Z"
    }
]
```