#!/bin/bash

echo 'Enter the parallel call limit :'
read limit
echo 'Enter account id :'
read accountId
echo 'Enter amount credit :'
read amount

while true
do
time seq "$limit" | parallel -n0 -j"$limit" "curl -H 'Content-Type:application/json' http://localhost:8080/creditMoney -X POST -d '{\"sourceAccountId\":\""${accountId}"\",\"amount\":"${amount}"}' | json_pp | pygmentize -l json"
sleep 2
done