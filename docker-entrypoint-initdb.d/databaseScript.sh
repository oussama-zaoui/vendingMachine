#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	INSERT INTO users (username,password,deposit,role) VALUES ('oussama', 'oussama', 0, 'ROLE_buyer')
EOSQL