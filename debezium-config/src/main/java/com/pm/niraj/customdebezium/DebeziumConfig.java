package com.pm.niraj.customdebezium;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configuration
@ConditionalOnProperty(name = "debezium.enabled", havingValue = "true", matchIfMissing = false)
@ComponentScan("com.pm.niraj.customdebezium")
public abstract class DebeziumConfig {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private DebeziumEngine<ChangeEvent<String, String>> engine;
    private Future<?> engineTask;



    @Bean
    public DebeziumEngine<ChangeEvent<String, String>> debeziumEngine() {

        Properties props = new Properties();
        props.setProperty("name", "embedded-engine");
        props.setProperty("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.port", "3306");
        props.setProperty("database.user", "debezium");
        props.setProperty("database.password", "dbzpassword");
        props.setProperty("database.server.id", "85744");
        props.setProperty("database.server.name", "myserver");
        props.setProperty("database.include.list", "testdb");
        props.setProperty("table.include.list", "testdb.offer");

        // Use Kafka for schema history storage
        props.setProperty("schema.history.internal", "io.debezium.storage.kafka.history.KafkaSchemaHistory");
        props.setProperty("schema.history.internal.kafka.bootstrap.servers", "localhost:9092");
        props.setProperty("schema.history.internal.kafka.topic", "schema-changes.myapp");

        // Topic prefix for CDC events
        props.setProperty("topic.prefix", "myapp");

        // Offset storage to track binlog progress
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
        props.setProperty("offset.storage.file.filename", "/tmp/debezium-offsets.dat");

        // Recommended converters
        props.setProperty("key.converter", "org.apache.kafka.connect.json.JsonConverter");
        props.setProperty("value.converter", "org.apache.kafka.connect.json.JsonConverter");
        props.setProperty("key.converter.schemas.enable", "false");
        props.setProperty("value.converter.schemas.enable", "false");

        engine = DebeziumEngine.create(Json.class)
                .using(props)
                .notifying(this::handleChangeEvent)
                .build();

        engineTask = executor.submit(engine);
        return engine;
    }

    private void handleChangeEvent(ChangeEvent<String, String> changeEvent) {
        getCustomDebeziumParams().handleChangeEvent(changeEvent.key(), changeEvent.value());
    }

    protected abstract CustomDebeziumParams getCustomDebeziumParams();


    @PreDestroy
    public void shutdown() throws IOException {
        if (engine != null) {
            engine.close();
        }
        executor.shutdownNow();
    }
}
