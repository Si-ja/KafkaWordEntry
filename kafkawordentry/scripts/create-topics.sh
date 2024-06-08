echo "Waiting for Kafka to come online..."

# A bit more on the strange command further here:
# https://github.com/confluentinc/confluent-docker-utils/blob/master/confluent/docker_utils/cub.py
# -b is botstrap_broker_list' so assume further is indicated the port where kafka is
# Further had to lookup things here:
# https://docs.confluent.io/platform/current/installation/docker/development.html
# expected_brokers for value 1
# timeout for value 20

cub kafka-ready -b kafka:9092 1 20

# create the users topic
kafka-topics \
	--bootstrap-server kafka:9092 \
	--topic sentences \
	--replication-factor 1 \
	--partitions 1 \
	--create

# create a topic where everything will go after parcing and analyzing
kafka-topics \
	--bootstrap-server kafka:9092 \
	--topic word-count \
	--replication-factor 1 \
	--partitions 1 \
	--create

sleep infinity