ссылкак на статью на хабре https://habr.com/ru/articles/543732/

1) создаем докер сеть для кафки: docker network create kafkanet
2) загружаем образ зоокипера: docker run -d --network=kafkanet --name=zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 -p 2181:2181 confluentinc/cp-zookeeper
3) загружаем образ кафки: docker run -d --network=kafkanet --name=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -p 9092:9092 confluentinc/cp-kafka

4) входим в кафку: docker exec -it kafka bash

5) Создам топик demo-topic: /bin/kafka-topics --create --topic demo-topic --bootstrap-server kafka:9092
6) список всех топиков: /bin/kafka-topics --bootstrap-server localhost:9092 --list Устаревшая версия: /bin/kafka-topics --list --zookeeper zookeeper:2181
7) описание топика /bin/kafka-topics --describe --topic demo-topic --bootstrap-server kafka:9092

8)Сгенерируем несколько сообщений /bin/kafka-console-producer --topic demo-topic --bootstrap-server kafka:9092
9)И после прочитаем эти сообщения /bin/kafka-console-consumer --topic demo-topic --from-beginning --bootstrap-server kafka:9092

10) Создадим 3 партишена в топике: /bin/kafka-topics --bootstrap-server localhost:9092 --alter --topic demo-topic --partitions 3 Устаревший вар: /bin/kafka-topics --zookeeper zookeeper:2101 --alter --topic demo-topic --partitions 3
11) Создадим консюмер группу(повт. команду в разных окнах несколько раз): docker exec -it kafka /bin/kafka-console-consumer --topic demo-topic --group demo-group --bootstrap-server kafka:9092
c группами плохевато работает получилось только один раз, а потом перебаллансировать не хочет