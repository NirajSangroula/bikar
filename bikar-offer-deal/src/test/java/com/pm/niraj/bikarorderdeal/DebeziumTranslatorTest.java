package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.niraj.sharedlib.debezium.DebeziumTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {OfferCreatedTransformerFactory.class, OfferDebeziumTranslator.class})
public class DebeziumTranslatorTest {
    @BeforeEach
    public void init(){
        debeziumTranslator.setTableName("offer");
    }
//    @MockitoBean -- This needs stubbing
    @MockitoSpyBean
    OfferCreatedTransformerFactory debeziumTransformerFactory;

    @MockitoSpyBean
    OfferDebeziumTranslator debeziumTranslator;


    @Test
    public void test_CanSourceTableName_FromBeforeEach(){
        assertEquals("offer", debeziumTranslator.getTableName());
    }

    @Test
    public void test_EmptyStringPayload_ReturnsNull() throws JsonProcessingException {
        assertNull(debeziumTranslator.translateCreated(""));
    }

    @Test
    public void test_AvoidsNullPointerException_If_OP_KeyIsMissing() throws JsonProcessingException {
        assertDoesNotThrow(() -> debeziumTranslator.translateCreated("""
                {
                "before":{},
                "after":{}
                }
                """));
    }

    @Test
    public void test_IsOfferCreateStatement_ReturnsFalseIfNoOp() throws Exception{
        Method isOfferCreateStatement = DebeziumTranslator.class.getDeclaredMethod("isOfferCreateStatement", JsonNode.class);
        isOfferCreateStatement.setAccessible(true);
        JsonNode payload = new ObjectMapper().readTree("""
                {
                "before":{},
                "after":{}
                }
                """);
        assertFalse((Boolean) isOfferCreateStatement.invoke(debeziumTranslator, payload));
    }
    @Test
    public void test_IsOfferCreateStatement_ReturnsFalseIfNotCreateStatement() throws Exception{
        Method isOfferCreateStatement = DebeziumTranslator.class.getDeclaredMethod("isOfferCreateStatement", JsonNode.class);
        isOfferCreateStatement.setAccessible(true);
        JsonNode payload = new ObjectMapper().readTree("""
                {
                "before":{},
                "after":{},
                "op":"u"
                }
                """);
        assertFalse((Boolean) isOfferCreateStatement.invoke(debeziumTranslator, payload));
    }
}
