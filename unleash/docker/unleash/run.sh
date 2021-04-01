#!/bin/bash

while ! nc -z ${DATABASE_HOST} ${DATABASE_PORT} </dev/null;
do
    echo \"http://${DATABASE_HOST}:${DATABASE_PORT} is not ready yet.\";
    sleep 1;
done;

node index.js