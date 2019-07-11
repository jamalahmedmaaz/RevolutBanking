#!/bin/bash

echo 'Enter account id :'
read accountId

while true
do
printf "\n"
seq 1 | parallel -n0 -j1 "curl -H 'Content-Type:application/json' http://localhost:8080/viewBalance -X POST -d '{\"sourceAccountId\":\""${accountId}"\"}' | json_pp | pygmentize -l json"
sleep 2
printf "\n"
done