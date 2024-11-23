#!/bin/bash

# Switch to the postgres user
sudo -i -u postgres <<EOF

psql <<SQL
DROP DATABASE IF EXISTS moviedb;

-- CREATE ROLE admin WITH LOGIN SUPERUSER CREATEDB CREATEROLE PASSWORD 'admin';
-- CREATE USER developer WITH ENCRYPTED PASSWORD 'developer';
CREATE DATABASE moviedb;
GRANT ALL PRIVILEGES ON DATABASE moviedb TO developer;
\c moviedb;
CREATE SCHEMA movieschema;
GRANT ALL PRIVILEGES ON SCHEMA movieschema TO developer;
\l
\q
SQL
EOF
echo "Database and schema setup completed."