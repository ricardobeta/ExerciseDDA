set -e  

echo "🔄 Compilando microservicio 'accounts'..."
cd accounts
./mvnw clean install -DskipTests
cd ..

echo "🔄 Compilando microservicio 'customers'..."
cd customers
./mvnw clean install -DskipTests
cd ..

echo "🐳 Construyendo imágenes Docker..."
docker-compose build

echo "🚀 Levantando contenedores..."
docker-compose up
