package net.kafkawordentry.utils;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;

public class TopologyGenerator {

	public static StreamsBuilder GenerateDefaultWordCountingTopology(
		String nameOfTopicStreamedFrom,
		String nameOfTopicStreamedTo)
	{
		/*
		 * Our plan overall
		 * 1. Stream some sentences into our application from the topic "sentences"
		 * 2. We flatten them out, where we take 1 entry and just put it into multiple objects
		 * 3. Group words by values, where for us, we group same words together
		 * 4. We count how many words we have
		 * 5. We save everything into a small data store
		 * 6. We stream the answer of our calculations into a word-count-app topic we have set before
		 */
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		streamsBuilder.<String, String>stream(nameOfTopicStreamedFrom)
			// Lambda function that takes all the values and splits values based on a space in a whole input
			// Flat map requires to send further a collection, so we have to put everything into an Array
			.flatMapValues((readOnlyKey, value) -> Arrays.asList(value.toLowerCase().split(" ")))
			
			// Another lambda expression, where every value now is a separate word based on a previous step we did
			.groupBy((key, value) -> value)

			// Finally count all appearances of a word
			// Here we tell Kafka streams how to serialize something in a data store
			// String is just our word entry and long represents the counted value of it (how many times it appeared)
			.count(Materialized.with(Serdes.String(), Serdes.Long()))

			// Now we need to transform a created table from a step back into (because that's what count does here) a stream
			// One converts it into a KStream and the latter sends everything to a topic
			.toStream()
			.to(nameOfTopicStreamedTo, Produced.with(Serdes.String(), Serdes.Long()));

		return streamsBuilder;
	}
}
