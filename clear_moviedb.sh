#!/bin/bash

# Switch to the postgres user
sudo -i -u postgres <<EOF

psql <<SQL

-- Connect to the moviedb database
\c moviedb;

-- Drop all tables in the movieschema schema
DO \$\$
DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'movieschema') LOOP
        EXECUTE 'DROP TABLE IF EXISTS movieschema.' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END
\$\$;

\q
SQL
EOF

echo "All tables in the movieschema schema have been dropped."