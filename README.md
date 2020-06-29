  ##is absent
- no user pagination
- no filter by new/old users
- no swagger

live version https://task-tracker-test.herokuapp.com

- model for register new user
```
{
  "username": "user0",
  "firstName": "First",
  "lastName": "Last",
  "email": "example@gmail.com",
  "password":"123456"
}
```
- model for get token

```
{
  "username": "user1",
  "password":"123456"
}
```

- model for create task
```
{
    "title": "TestTestCreate",
    "description": "Test task for user",
    "status": "DONE",
    "userId": 2
}
```

