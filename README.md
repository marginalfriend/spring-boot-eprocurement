# Gacor eProcurement API Docs
[Product Management Link](https://www.figma.com/board/Y0J2v3GLyJMZZD3nCFWswn/Live-Code-eProcurement?node-id=0%3A1&t=aMdt6qmLDDbBYERj-1)

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

## Order Management
```[GET] /order```

Response :
```json
{
    "statusCode": 200,
    "message": "Data found",
    "data": [
        {
            "id": "be0a12ec-8b70-499a-8b86-62269b7ef601",
            "orderDate": "2024-05-16T05:09:02.543+07:00",
            "orderDetails": [
                {
                    "id": "2fc0c219-25b7-466d-a88f-7d9c8b596c60",
                    "price": 50000,
                    "quantity": 100
                }
            ]
        }
    ],
    "paging": {
        "totalPages": 1,
        "totalElements": 1,
        "page": 1,
        "size": 50,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

```[POST] /order```
Request :
```json
{
    "orderDetails" : [
        {
            "productId" : "59b16904-a0cf-48ad-b0d9-61989cd643ac",
            "vendorId"  : "a067403c-f25a-449a-946d-9fae724548bc",

            "quantity"  : 100,
            "price"     : 50000
        }
    ]
}
```
Or :
```json
{
    "orderDetails" : [
        {
            "supplyId"  : 52,
            "quantity"  : 100,
            "price"     : 50000
        }
    ]
}
```
Response :
```json
{
    "statusCode": 201,
    "message": "Data created successfully",
    "data": {
        "id": "be0a12ec-8b70-499a-8b86-62269b7ef601",
        "orderDate": "2024-05-16 05:09:02.543",
        "totalAmount": 50000,
        "orderDetails": [
            {
                "id": "2fc0c219-25b7-466d-a88f-7d9c8b596c60",
                "supplyId": 52,
                "productName": "Ethiopia Bombe Flower Bomb",
                "quantity": 100,
                "price": 50000,
                "totalAmount": 5000000
            }
        ]
    },
    "paging": null
}
```

## Report
```[GET] /report```

Response :

```json
{
    "statusCode": 200,
    "message": "Data found",
    "data": [
        {
            "id": "2fc0c219-25b7-466d-a88f-7d9c8b596c60",
            "productName": "Ethiopia Bombe Flower Bomb",
            "vendorName": "PT Suka Cuan",
            "purchaseDate": "2024-05-16 05:09:02.543",
            "quantity": 100,
            "totalPrice": 5000000,
            "pagingResponse": null
        }
    ],
    "paging": {
        "totalPages": 1,
        "totalElements": 1,
        "page": 0,
        "size": 50,
        "hasNext": false,
        "hasPrevious": false
    }
}
```