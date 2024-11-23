#!/bin/bash

# Database details
DB_NAME="moviedb"
SCHEMA_NAME="movieschema"
DB_USER="postgres"

# Drop and recreate schema
sudo -i -u postgres <<EOF

psql -d $DB_NAME -c "
\c moviedb;
DROP SCHEMA IF EXISTS $SCHEMA_NAME CASCADE;
CREATE SCHEMA $SCHEMA_NAME;
GRANT ALL PRIVILEGES ON SCHEMA $SCHEMA_NAME TO $DB_USER;
"

EOF

echo "Schema $SCHEMA_NAME has been dropped and recreated in database $DB_NAME."
