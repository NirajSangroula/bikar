trap "echo -e '\nCaught Ctrl+C! Stopping all microservices...'; kill 0; exit 1" SIGINT
echo "Starting API Gateway Server"
mvn spring-boot:run -f bikar-gateway/ &

echo "Starting Bikar-User-Account"
mvn spring-boot:run -f bikar-useraccount/ &

echo "Starting Bikar-Order-Deal"
mvn spring-boot:run -f bikar-order-deal/ &

echo "Starting Bikar-Match"
mvn spring-boot:run -f match-bikar/ &

echo "Starting Bikar-Orchrestrator"
mvn spring-boot:run -f orchrestrator-bikar/ &

wait
