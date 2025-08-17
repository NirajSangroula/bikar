package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {OfferCreatedTransformerFactory.class})
public class DebeziumTranslatorTest {
//    @MockitoBean -- This needs stubbing
    @MockitoSpyBean
    OfferCreatedTransformerFactory debeziumTransformerFactory;
    @Test
    public void test_Transformer_Produces_Correct_Properties_For_Offer_Created() throws JsonProcessingException {
        OfferCreatedEvent event = debeziumTransformerFactory.createDebeziumTranslator().transform(getJsonPayload());
        assertEquals(101, event.getOfferId());
        assertEquals("2025-08-17T10:15:30Z", event.getCreatedAt().toString());
        assertEquals(1, event.getProviderId());
    }

    private JsonNode getJsonPayload() throws JsonProcessingException {
        String jsonString = """
            {
              "before": {
                "id": 101,
                "created_at": "2025-08-17T10:15:30Z",
                "provider_id": "1"
              },
              "after": {
                "id": 101,
                "created_at": "2025-08-17T10:15:30Z",
                "provider_id": "1"
              }
            }
            """;
        String actualString = """
        {
          "before": null,
          "after": {
            "status": 0,
            "created_at": 1755461128255674,
            "id": 1,
            "provider_id": 1,
            "description": "D",
            "title": "T",
            "offer_type": "ONE_TIME"
          },
          "source": {
            "version": "2.5.0.Final",
            "connector": "mysql",
            "name": "myapp",
            "ts_ms": 1755460697000,
            "snapshot": "false",
            "db": "testdb",
            "sequence": null,
            "table": "offer",
            "server_id": 1,
            "gtid": null,
            "file": "mysql-bin.000004",
            "pos": 260876,
            "row": 0,
            "thread": 621,
            "query": null
          },
          "op": "c",
          "ts_ms": 1755460697987,
          "transaction": null
        }
        """;
        return (new ObjectMapper()).readTree(jsonString);
    }

    private JsonNode getJsonCDC() throws JsonProcessingException {
        String jsonString = """
            {
            "payload" :
              {
              "before": {
                "id": 101,
                "created_at": "2025-08-17T10:15:30Z",
                "provider_id": "1"
              },
              "after": {
                "id": 101,
                "created_at": "2025-08-17T10:15:30Z",
                "provider_id": "1"
              }
              }
            }
            """;
        return (new ObjectMapper()).readTree(jsonString);
    }
}
