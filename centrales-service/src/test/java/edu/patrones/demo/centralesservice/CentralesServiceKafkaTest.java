package edu.patrones.demo.centralesservice;

import edu.patrones.demo.centralesservice.config.CentralesConfig;
import edu.patrones.demo.event.solicitud.SolicitudEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.awaitility.Awaitility.await;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = {"solicitud-event", "centrales-event"}, partitions = 1, controlledShutdown = false, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class CentralesServiceKafkaTest {

    private static final String TOPIC_EXAMPLE = "TOPIC_EXAMPLE";

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private CentralesConfig centralesConfig;

    @Test
    public void prueba(){
        // GIVEN
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        // simulation consumer
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker.getBrokersAsString());
        Producer<String, SolicitudEvent> producerTest = new KafkaProducer(producerProps, new StringSerializer(), new JsonSerializer<SolicitudEvent>());

        producerTest.send(new ProducerRecord(TOPIC_EXAMPLE, "", solicitudEvent));

        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {

        };
    }
}
