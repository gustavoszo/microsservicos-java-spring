# **Microsserviços - comunicação assíncrona e mensageria com Kafka**

Este projeto implementa uma arquitetura de microserviços desenvolvidos com Java e Spring para simular transferências PIX. O sistema realiza a comunicação assíncrona entre os microserviços usando o **Apache Kafka**.

## **Visão Geral dos Microserviços**

O sistema é composto por dois principais microserviços:

1. **Serviço produtor de mensagens**: Responsável por realizar o envio de mensagens para um tópico do **Kafka**, isso ocorre quando um pix é realizado.
2. **Serviço consumidor de mensagens**: Responsável pelo consumo de mensagens do tópico que recebe as mensagens de pix emitidas pelo microsserviço produtor.

## **Tecnologias Usadas**

- **Java** e **Spring**
- **Apache Kafka**: Message Broker utilizado para a comunicação assíncrona entre os microserviços.
- **Confluent Kafka**
- **Schema Registry**: Para armazenar e gerenciar esquemas de dados em formato Avro entre os microserviços.
- **PostgreSQL**
