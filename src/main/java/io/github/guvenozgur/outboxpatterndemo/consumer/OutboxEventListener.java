package io.github.guvenozgur.outboxpatterndemo.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OutboxEventListener {

    @KafkaListener(topics = "master-db.public.outbox", groupId = "listener-1")
    void debeziumListener(@Payload Message<Object> msg,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                          @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Message from debezium connector: {}, offset: {}, partition: {}", msg, offset, partition);
    }

    @KafkaListener(topics = "postgresql-outbox", groupId = "listener-1")
    void jdbcListener(@Payload Message<Object> msg,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                          @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Message from JDBC connector: {}, offset: {}, partition: {}", msg, offset, partition);
    }
}
