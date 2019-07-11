#!/bin/bash

echo 'Enter the parallel call limit :'
read limit
echo 'Enter source account id :'
read sourceAccountId
echo 'Enter destination amount credit :'
read destinationAccountId
echo 'Amount to transfer'
read amount

while true
do
time seq "$limit" | parallel -n0 -j"$limit" "curl -H 'Content-Type:application/json' http://localhost:8080/transferMoney -X POST -d '{\"sourceAccountId\":\""${sourceAccountId}"\",\"amount\":"${amount}", \"destinationAccountId\": \""${destinationAccountId}"\"}' | json_pp | pygmentize -l json"
sleep 2
done