# WSSonar

Web Service Sonar(WSSonar) is a web application that constantly monitors web services and generates reports based on it's uptime.
By individual methods, it takes account not only if the web service is online, but if it's working correctly by it's response.

Features includes:
* Charts (morris.js)
* Full downtime reports with ocurred error 
* Report download (.xls - Apache POI)
* Warning by e-mail when web service goes down
* User management
* Password encryption (BCrypt)

Besides what was already mentioned this application was created using Java, JBoss AS 7.1.1, Spring MVC, Spring Data JPA, JSTL, Bootstrap and Postgres.

TODO:
* Enable monitoring on server start instead of the current mechanism (!)
* Enhance security
* Create better logs

+ Database scripts and model in the utils folder
+ Information from web-services was excluded for obvious reasons.


Moved from Bitbucket.