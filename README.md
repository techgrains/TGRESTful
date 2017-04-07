# TG RESTful Examples
Purpose of TGRestful is to provide pure REST examples for any thin or thick client API integration. (Android app, iOS app, JS framework like AngularJS etc.)

## How to use RESTful services directly?
Project is being hosted @ http://amd.techgrains.com:8083/TGRESTful/ {...} for quick test without seeing the implementation.

## RESTful Examples
|No|Method|URI|Description|Success|Failure|
|:--:|:--:|:--|:--|:--|:--|
|1|`GET`|[/employee/1](http://amd.techgrains.com:8083/TGRESTful/employee/1)|Details of Employee having id 1|**200** OK|**404** Not Found|
|2|`GET`|[/employee/3/department/list](http://amd.techgrains.com:8083/TGRESTful/employee/3/department/list)|List of departments associated to Employee having id 3|**200** OK|**404** Not Found|
|3|`GET`|[/employee/list](http://amd.techgrains.com:8083/TGRESTful/employee/list)|List of employees|**200** OK|
|4|`GET`|[/employee/list?name=Vishal](http://amd.techgrains.com:8083/TGRESTful/employee/list?name=Vishal)|Filtered list of employees based on name contains {Vishal}|**200** OK|
|5|`POST`|/employee/create|Creates Employee based on request params |**200** OK or **201** Created|**409** Conflict|
|6|`POST`|/employee/1/department/add|Adds Departments based on request params |**200** OK or **201** Created|**403** Forbidden, **404** Not Found|
|7|`PUT`|/employee/5|Updates all the entity attributes based on request params. |**200** OK|**403** Forbidden, **404** Not Found, **409** Conflict|
|8|`PATCH`|/employee/5|Updates Employee for only provided request params.|**200** OK|**403** Forbidden, **404** Not Found, **409** Conflict|
|9|`DELETE`|/employee/5|Deletes Employee based on request params |**200** OK or **204** No Content|**404** Not Found|
|10|`DELETE`|/employee/5/department/12|Deletes Department based on request params |**200** OK or **204** No Content|**404** Not Found|

> `Head` and `Options` are available for any request.
> 
> `5XX` Status code may occur to any service based on connectivity and availablity of the instances which is indepedent to above examples.

## Request & Response
#### 1. `GET` /employee/{id}

**HTTP 200** : [/employee/1](http://amd.techgrains.com:8083/TGRESTful/employee/1)
```
{
  "id": 1,
  "name": "Vishal",
  "designation": "Director",
  "departments": [
    {
      "code": 10,
      "name": "IT"
    }
  ]
}
```
**HTTP 404** : [/employee/100](http://amd.techgrains.com:8083/TGRESTful/employee/100)
```
{
  "timestamp": 1491553092124,
  "status": "NOT_FOUND",
  "error": "Not Found",
  "message": "Employee not found for id - 100"
}
```

#### 2. `GET` /employee/{id}/department/list
**HTTP 200** : [/employee/3/department/list](http://amd.techgrains.com:8083/TGRESTful/employee/3/department/list)
```
[
  {
    "code": 12,
    "name": "Services"
  },
  {
    "code": 13,
    "name": "Products"
  },
  {
    "code": 16,
    "name": "Finance"
  }
]
```
**HTTP 404** : [/employee/100/department/list](http://amd.techgrains.com:8083/TGRESTful/employee/100/department/list)
```
{
  "timestamp": 1491548677115,
  "status": "NOT_FOUND",
  "error": "Not Found",
  "message": "Employee not found for id - 100"
}
```

#### 3. & 4. `GET` /employee/list
*Request params: name={name}, designation={designation}*

**HTTP 200** : [/employee/list?name=Snehal](http://amd.techgrains.com:8083/TGRESTful/employee/list?name=Snehal)
```
[
  {
    "id": 6,
    "name": "Snehal",
    "designation": "Manager",
    "departments": [
      {
        "code": 13,
        "name": "Products"
      }
    ]
  }
]
```
*Not putting link for further examples, considering those were not `GET` requests. It can be generated similary just by changing the uri path.*

#### 5. `POST` /employee/create 
*Request params: name={name}, designation={designation}*

**HTTP 200** : /employee/create (name=Karan, designation=Manager)
```
{
  "id": 999,
  "name": "Karan",
  "designation": "Manager",
  "departments": []
}
```
**HTTP 409** : /employee/create (name=Satish, designation=Manager)
```
{
  "timestamp": 1491553397034,
  "status": "CONFLICT",
  "error": "Conflict",
  "message" :"Employee already exists for name - Satish"
}
```

#### 6. `POST` /employee/{id}/department/add 

*Request params: department={departmentCode}*

**HTTP 200** : /employee/1/department/add (department=12)
```
{
  "id": 1,
  "name": "Vishal",
  "designation": "Director",
  "departments": [
    {
      "code": 10,
      "name": "IT"
    },
    {
      "code": 12,
      "name": "Services"
    }
  ]
}
```
**HTTP 404** : /employee/100/department/add (department=13)
```
{
  "timestamp": 1491548677115,
  "status": "NOT_FOUND",
  "error": "Not Found",
  "message": "Employee not found for id - 100"
}
```
**HTTP 403** : /employee/1/department/add (department=10)
```
{
  "timestamp": 1491556257871,
  "status": "FORBIDDEN",
  "error": "Forbidden",
  "message" :"Employee already has department code - 10"
}
```

#### 7. `PUT` /employee/{id}
*Request params: name={name}, designation={designation} (Required all the parameters of the entity for PUT)*

**HTTP 200** : /employee/5 (name=John, designation=Manager)
```
{
  "id": 5,
  "name": "John",
  "designation": "Manager",
  "departments": [
    {
      "code": 12,
      "name": "Services"
    },
    {
      "code": 13,
      "name": "Products"
    }
  ]
}
```
**HTTP 403** : /employee/1 (name=John, designation=Manager)
```
{
  "timestamp": 1491557483258,
  "status": "FORBIDDEN",
  "error": "Forbidden",
  "message": "System can't change designation of the Director."
}
```
**HTTP 409** : /employee/5 (name=Karan, designation=Manager)
```
{
  "timestamp": 1491557574187,
  "status": "CONFLICT",
  "error": "Conflict",
  "message" :"Employee already exists for name - Karan"
}
```

#### 8. `PATCH` /employee/{id}
*Request params: name={name} (Any subset of parameters of the entity for PATCH)*

**HTTP 200** : /employee/5 (name=John)
```
{
  "id": 5,
  "name": "John",
  "designation": "Architect",
  "departments": [
    {
      "code": 12,
      "name": "Services"
    },
    {
      "code": 13,
      "name": "Products"
    }
  ]
}
```
**HTTP 403** : /employee/1 (name=John)
```
{
  "timestamp": 1491557483258,
  "status": "FORBIDDEN",
  "error": "Forbidden",
  "message": "System can't change designation of the Director."
}
```
**HTTP 409** : /employee/5 (name=Vilesh)
```
{
  "timestamp": 1491557574187,
  "status": "CONFLICT",
  "error": "Conflict",
  "message" :"Employee already exists for name - Vilesh"
}
```

#### 9. `DELETE` /employee/{id}

**HTTP 200** : /employee/1
```
{Empty Response Body}
```

**HTTP 404** /employee/100
```
{
  "timestamp": 1491553092124,
  "status": "NOT_FOUND",
  "error": "Not Found",
  "message": "Employee not found for id - 100"
}
```

#### 10. `DELETE` /employee/{id}/department/{departmentCode}

**HTTP 200** : /employee/1/department/10
```
{
  "id": 1,
  "name": "Vishal",
  "designation": "Director",
  "departments": [],
  "created": 1491560326550,
  "modified": 1491560326550
}
```

**HTTP 404** /employee/1/department/12
```
{
  "timestamp": 1491560238501,
  "status": "NOT_FOUND",
  "error": "Not Found",
  "message": "Employee doesn't have department having code - 12"
}
```


### How to RUN the project? (Only if curious to see/run implementation)
If you want to run project, its simple Java Spring Boot MVC project with Gradle. (Java 1.8 & Gradle required to run)
``` 
$ gradle bootRun
```
By default it runs using port `8080` on local.

### Implementation
Code base already has example server side implementation of this RESTful services.

### Anything to say?
Any suggestions or recommendations are most welcome.
> Please email me on vishal@techgrains.com with subject "TGRESTful" for easier reference and to have in proper mailbox folder. Thanks in advance!

## License
#### Copyright 2017 Techgrains Technologies

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
