package net.kafkawordentry;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import net.kafkawordentry.utils.PropertiesGenerator;
import net.kafkawordentry.utils.TopologyGenerator;

// Great tutorial from here: https://github.com/Programming-with-Mati/kafka-streams-word-count/tree/main
public class WordCount {
	public static void main(String[] args) {
		String nameOfTopicStreamedFrom = "sentences";
		String nameOfTopicStreamedTo = "word-count";

		// Set some properties used in the Kafka Streams
		Properties props = PropertiesGenerator.GenerateDefaultPropertiesForKafkaStreams("words-count");
		// Set the Kafka Streams topology (essentially how to process every message)
		StreamsBuilder streamsBuilder = TopologyGenerator.GenerateDefaultWordCountingTopology(nameOfTopicStreamedFrom, nameOfTopicStreamedTo);
		
		// Topology itself doesn't run, it's just a topology
		// We do need to make a Kafka streams application
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props);
		kafkaStreams.start();

		// Let's not forget to shut down our application when we request it to be shut down via a full shutdown of the app.
		// Yes, it would close anyways, but this should request a graceful shutdown.
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}
}
