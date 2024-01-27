#!/bin/bash

echo "########### Creating profile ###########"

aws configure set aws_access_key_id dummy --profile=localstack
aws configure set aws_secret_access_key dummy --profile=localstack
aws configure set region us-east-1 --profile=localstack

echo "########### Listing profile ###########"
aws configure list --profile=localstack

echo "########### Creating SQS ###########"
awslocal sqs create-queue --endpoint-url=http://localhost:4566 --queue-name=novo_pedido --profile=localstack
awslocal sqs create-queue --endpoint-url=http://localstack:4566 --queue-name=pagamento_processado --profile=localstack