package net.kafkawordentry.utils;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

public class PropertiesGenerator {

	public static Properties GenerateDefaultPropertiesForKafkaStreams(
		String applicationIdConfig)
		{
			Properties props = new Properties();
			props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationIdConfig);                       // GroupId of our application:
			props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");                      // Location of the Kafka Server
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");							   // Tells which entry to read first in the queue      
			props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());       // How to serialize and deserialize message values in our topics (for keys)
			props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());     // How to serialize and deserialize message values in our topics (for values)                    
		
			return props;
		}
}
