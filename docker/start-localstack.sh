#!/bin/bash

echo "########### Creating SQS ###########"
aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name novo_pedido --region us-east-1
aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name pagamento_processado --region us-east-1