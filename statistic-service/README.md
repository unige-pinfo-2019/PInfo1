# Statistics-service

Statistics microservice allow to store statistics about a user or item in a database.

# Some commands

To retrieve all user stats:

```
/statistic/alluserstats
```

**localhost:8080/statistic/alluserstats**

To retrieve all item stats:

```
/statistic/allitemstats
```

**localhost:8080/statistic/allitemstats**

To get the stats of a specific user:

```
/statistic/getuser
```

**localhost:8080/statistic/getuser?usrid=1234**

To get the stats of a specific item:

```
/statistic/getitem
```

**localhost:8080/statistic/getitem?itemid=1234**

To get the 3 most clicked categories in general:

```
/statistic/topcat
```

**localhost:8080/statistic/topcat?ncategories=3**

To get the 3 most clicked categories by a specific user:

```
/statistic/topusercat
```

**localhost:8080/statistic/topusercat?usrid=1234&ncategories=3**

To get the 3 most clicked items in general:

```
/statistic/topitem
```

**localhost:8080/statistic/topitem?nitems=3**

To get the 3 most clicked items in a specific category:

```
/statistic/topitemcat
```

**localhost:8080/statistic/topitemcat?category=mobilier&ncategories=3**


