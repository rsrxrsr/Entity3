#!/bin/bash
 
echo " Java y Maven "

# Verifica la instalación
java -version
mvn -v

# Esperar a que PostgreSQL esté listo
#while ! pg_isready -U postgres -h localhost -p 5432; do
  sleep 1
#done

# Crear usuario y base de datos
#psql -U postgres "CREATE DATABASE Entity3DB;"
#psql -U postgres -d Entity3DB -c "CREATE ROLE IF NOT EXISTS sa WITH LOGIN PASSWORD 's3cr3t0';"

# cd entity3
# mvn spring-boot:run

