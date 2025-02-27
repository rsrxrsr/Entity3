#!/bin/bash
 
echo " Java y Maven "

# Verifica la instalación
java -version
mvn -v

# Esperar a que PostgreSQL esté listo
#while ! pg_isready -U postgres -h localhost -p 5432; do
  sleep 10
#done

# Crear usuario y base de datos
psql -U postgres -c "CREATE DATABASE entity3db;"
psql -U postgres -c "CREATE ROLE sa WITH LOGIN PASSWORD 's3cr3t0';"
psql -U postgres -c "ALTER ROLE sa WITH SUPERUSER;"
#psql -U postgres -c "GRANT ALL PRIVILEGES ON SCHEMA public TO sa;"
#psql -U postgres -c "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO sa;"
#psql -U postgres -c "GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO sa;"
#psql -U postgres -c "GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO sa;"

echo "* * Spring BOOT * *"

cd entity3
#mvn spring-boot:run ../mvn.log

echo "* * End post-create **"