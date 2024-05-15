# Gacor eProcurement API Docs
```Root URL : gacortech.com/api/v0```

## Categories Management
```[GET] /categories```
Request Param :
- name : To search categories by name (case ignored)

Response :
```json
{
  "statusCode": 200,
  "message": "Data found",
  "data": [
    {
      "id": 1,
      "name": "Tea Leaf"
    },
    {
      "id": 2,
      "name": "Category"
    }
  ],
  "paging": null
}
```

```[POST] /categories```

Request :
```json
{
  "name" : "Category Name"
}
```

Response :
```json
{
  "statusCode": 201,
  "message": "Data created successfully",
  "data": {
    "id": 3,
    "name": "Category Name"
  },
  "paging": null
}
```

```[PUT] /categories```

Request :
```json
{
  "id" : 1,
  "name" : "New Name"
}
```

Response :
```json
{
    "statusCode": 200,
    "message": "Data updated successfully",
    "data": {
        "id": 3,
        "name": "New Name"
    },
    "paging": null
}
```

## Products Management
```[GET] /products```  Request Param :
- name : Filter by name
- categoryId : Filter by category

Response :
```json
{
    "statusCode": 200,
    "message": "Data found",
    "data": [
        {
            "id": "59b16904-a0cf-48ad-b0d9-61989cd643ac",
            "name": "Ethiopia Bombe Flower Bomb",
            "categoryId": 1
        },
        {
            "id": "2e47e811-8f86-48f5-bfe3-1c12af0b0f82",
            "name": "Halu Pink Banana",
            "categoryId": 1
        },
        {
            "id": "d7a690dc-f5d7-46cc-bf18-e3b92915fb22",
            "name": "Kintamani Bali",
            "categoryId": 1
        }
    ],
    "paging": {
        "totalPages": 1,
        "totalElements": 3,
        "page": 0,
        "size": 5,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

```[POST] /products```

Request :

```json
{
    "name": "Ethiopia Bombe Flower Bomb",
    "categoryId" : 1
}
```

Response :
```json
{
    "statusCode": 201,
    "message": "Data created successfully",
    "data": {
        "id": "59b16904-a0cf-48ad-b0d9-61989cd643ac",
        "name": "Ethiopia Bombe Flower Bomb",
        "categoryId": 1
    },
    "paging": null
}
```

```[PUT] /products```

Request :
```json
{
    "id": "f016feb7-a72d-4817-a1d6-b6a04a1549ea",
    "name": "New Name",
    "categoryId": 2 // New Category
}
```

Response :
```json
{
    "statusCode": 200,
    "message": "Data updated successfully",
    "data": {
        "id": "d7a690dc-f5d7-46cc-bf18-e3b92915fb22",
        "name": "New Name",
        "categoryId": 2
    },
    "paging": null
}
```

## Vendor Management
```[GET] /vendors```

Request Params :
- name : Filter by name

Response :
```json
{
    "statusCode": 200,
    "message": "Data found",
    "data": [
        {
            "id": "a067403c-f25a-449a-946d-9fae724548bc",
            "name": "PT Suka Cuan"
        }
    ],
    "paging": null
}
```