# Counting Words with Kafka Streams

This is a simple example of a program using Kafka streams to accept an input of information in a topic, count the amount of words that are present in that input and output the answer to another topic. This repository is based on the basic tutorial that Kafka Streams provide and this repository: https://github.com/Programming-with-Mati/kafka-streams-word-count/tree/main

## What's different here?
1. Components are arranged together, including the Java application and are configured to be runnable in a docker-compose arrangement.
2. Extra comments are added to explain the process of how the code functions and give extra notes that are often found to be missing in tutorial-based descriptions and require additional look ups.
3. Extra scripts are created that should start the docker terminals that allow to simulate activity in kafka streams, without requiring the user to fidget around starting processes themselves and entering extra commands for that.

## How to run
1. Start Docker application on your computer
2. Open a terminal window
3. Navigate to the `kafkawordentry` folder
4. Input `docker-compose up`
5. Open another terminal window
6. Navigate to the `kafkawordentry/scripts` folder
7. Run script `start-utilization.ps1` (if the script doesn't start - you might have insufficient privileges to run the code. To solve that problem run `powershell -ExecutionPolicy Bypass` command beforehand)
8. Two terminal windows should start. In one you should be able to type words/sentences and press enter. Your application will process them and output in a second terminal window with counts of how many particular words have already went through it. The window for the input will have a prefix: `>` symbol.

## TODO

- [ ] The current arrangement of code works only in a docker-compose setup. Some environmental variables should be introduced to allow for starting the application outside of the containers as well.
- [ ] Introduce scripts that allow for starting docker based terminals on Linux as well.