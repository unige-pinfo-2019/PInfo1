# Ad-service

Ad microservice allow to post/remove a sale or buy ad.

# Some commands 

To retrieve all annonces:
 
```
/annonce/allannonce
```

**localhost:8080/annonce/allannonce**

To add an annonce: 

```
/annonce/addannonce?usrid=...&name=...&category=...&state=...
```

**localhost:8080/annonce/addannonce?usrid=1234&name=chaise&category=mobilier&state=3**

To remove an annonce:
(the annonce must exist)

```
/annonce/removeannonce?wantedid=...
```

**localhost:8080/annonce/removeannonce?wantedid="acfgt-12dazuhd-azdvt"**

To update an annonce:
(the annonce must exist)

```
/annonce/updateannonce?wantedid=...&field=...&change=...
```

**localhost:8080/annonce/updateannonce?wantedid="acfgt-12dazuhd-azdvt"&field=name&change=etagere**

or 

**localhost:8080/annonce/updateannonce?wantedid="acfgt-12dazuhd-azdvt"&field=category&change=mobilite**

or 

**localhost:8080/annonce/updateannonce?wantedid="acfgt-12dazuhd-azdvt"&field=state&change=2**

