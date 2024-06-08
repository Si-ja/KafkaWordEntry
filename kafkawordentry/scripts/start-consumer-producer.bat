:: Use this script to simplify your life and start 2 terminal windows for producing and consuming messeges
:: Do note that the docker-compose up already had to be ran previously and all containers are up

:: EXTRA NOTE: They don't yet fully work as intended, just use these commands exclusing docker exec -it kafka bash on your own in the terminal windows

::docker exec -it kafka bash 'kafka-console-producer --topic sentences --bootstrap-server localhost:9092'
::docker exec -it kafka bash 'kafka-console-consumer --topic word-count --bootstrap-server localhost:9092 --from-beginning --property print.key=true --property key.separator=" : " --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"'