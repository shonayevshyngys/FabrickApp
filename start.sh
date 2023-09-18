sudo systemctl start docker
mvn clean install -DskipTests
address=./Deployment/docker-compose.yaml
docker-compose -f $address  up --build