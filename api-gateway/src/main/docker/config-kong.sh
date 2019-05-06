#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ad-service" --data-urlencode "url=http://ad-service:8080/ad"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=user-service" --data-urlencode "url=http://user-service:8080/user"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=messanger-service" --data-urlencode "url=http://messanger-service:8080/messanger"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=items-service" --data-urlencode "url=http://items:8080/items"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=statistic-service" --data-urlencode "url=http://statistic-service:8080/statistic"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/ad-service/routes --data-urlencode "paths[]=/api/ad" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/user-service/routes   --data-urlencode "paths[]=/api/user"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/messanger-service/routes   --data-urlencode "paths[]=/api/messanger"  
curl -S -s -i -X POST  --url http://api-gateway:8001/services/items-service/routes    --data-urlencode "paths[]=/api/items" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/statistic-service/routes   --data-urlencode "paths[]=/api/statistic"
#Enable the Open ID Plugin
curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=jwt" --data "enabled=true"

