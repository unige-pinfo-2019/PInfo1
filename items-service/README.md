# Items-service

Item microservice allow to GET a specific item form the database

# Some commands

To retrieve all items:

```
/all
```

**localhost:8080/all**

To add an item:

```
/additem?usrid=...&name=...&prize=...&category=...&description=...&state=...
```

**localhost:8080/additem?usrid=1234&name=chaise&prize=20&category=mobilier&description=unechaise&state=2**

To remove an item:

```
/removeitem?itemid=...
```

**localhost:8080/removeitem?itemid=1234**

To update an item:

```
/updateitem?itemid=...&field=...&change=...
```

**localhost:8080/updateitem?itemid=1234&field=name&change=etagere**

or 

**localhost:8080/updateitem?itemid=1234&field=prize&change=20**

or 

**localhost:8080/updateitem?itemid=1234&field=category&change=mobilite**

or

**localhost:8080/updateitem?itemid=1234&field=description&change=uneetagere**

or 

**localhost:8080/updateitem?itemid=1234&field=state&change=1**

To get the items of a specific user:

```
/getitem
```

**localhost:8080/getitem?usrid=1234**
