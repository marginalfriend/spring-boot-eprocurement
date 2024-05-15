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