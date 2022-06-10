#!/bin/sh

./mvnw clean install -DskipTests && docker-compose build --no-cache && docker-compose up