#!/bin/bash

echo 'Enter the parallel call limit :'
read limit

time seq "$limit" | parallel -n0 -j"$limit" "curl -H 'Content-Type: application/json' http://localhost:8080/createAccount -X POST -d '{\"account\": {\"userId\": null,\"amount\": null,\"currency\": \"GBP\",\"accountType\": \"PERSONAL\",\"accountSubType\": \"CURRENT\"}}' | json_pp | pygmentize -l json"