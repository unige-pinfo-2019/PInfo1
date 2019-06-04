# Messanger-service

Messanger microservice allow to GET a conversation with a specific user on a specific messenger and to POST new messages.

# Some commands

To retrieve all messages:

```
/messenger/allmessenger
```

**localhost:8080/messenger/allmessenger**

To add an messenger:

```
/messenger
```

**Use a POST**


To get the all the messages between 2 users:

```
/messenger/getmessenger?sendId=...&receiveId=...
```

**localhost:8080/messenger/getmessenger?sendId=1234&receiveId=1235**

To get the all users someone is talking with and the last messages:

```
/messenger/getinfo?usrId=...
```

**localhost:8080/messenger/getinfo?usrId=1234**
