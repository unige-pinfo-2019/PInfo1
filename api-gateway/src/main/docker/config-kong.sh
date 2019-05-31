#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ad-service" --data-urlencode "url=http://ad-service:8080/annonce"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=user-service" --data-urlencode "url=http://user-service:8080/user"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=messanger-service" --data-urlencode "url=http://messanger-service:8080/messanger"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=items-service" --data-urlencode "url=http://items-service:8080/item"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=statistic-service" --data-urlencode "url=http://statistic-service:8080/statistic"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/ad-service/routes --data-urlencode "paths[]=/api/ad"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/user-service/routes   --data-urlencode "paths[]=/api/user"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/messanger-service/routes   --data-urlencode "paths[]=/api/messanger"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/items-service/routes    --data-urlencode "paths[]=/api/items"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/statistic-service/routes   --data-urlencode "paths[]=/api/statistic"
#Enable the Open ID Plugin
curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=jwt" --data "enabled=true"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-proxied"   --data "custom_id=api-sso-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=https://localhost/auth/realms/apigw"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-not-proxied"   --data "custom_id=api-sso-not-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-not-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=http://iam:8080/auth/realms/apigw"
