A Rest application integration with codahalemetrics api

This is a Jersey restlet application and the codahalemetrics api is injected into it. codahalemetrics api is used for collecting the application metrics.

Clone this repo and compile it with Maven then run it by mvn exec:java

The actual service urls are http://localhost:8082/userApi/users to fetch all the users
                            http://localhost:8082/userApi/users/{UID} to fetch a particular user by user id

The metrics service url is http://localhost:8082/userApi/metrics.


