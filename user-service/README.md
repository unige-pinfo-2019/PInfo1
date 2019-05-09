# User-service

User microservice allow to GET a splecific user and modify his own profile.

# Some commands

To retrieve all users:

```
/user/alluser
```

**localhost:8080/user/alluser**

To add an user:

```
/user/adduser
```

**localhost:8080/user/adduser**

To remove an user:

```
/user/removeuser?id=...
```

**localhost:8080/user/removeuser?id=1234**

To update an user:

```
/user/updateuser?id=...&name=...&surname=...&username=...&email=...&report=...
```

**localhost:8080/user/updateuser?id=1234&name=Adrien&surname=Chabert&username=Chabibi&email=&report=0**


To get the information of a specific user:

```
/user/getuser?name=...&surname=...
```

**localhost:8080/user/getuser?name=Adrien&surname=Chabert**
