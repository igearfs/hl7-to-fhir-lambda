
package com.example.lambda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Hl7ToFhirLambdaTest {
    @Test
    void testHandleRequest() {
        Hl7ToFhirLambda lambda = new Hl7ToFhirLambda();
        String hl7Message = "MSH|^~\\&|HOSPITAL|LAB|HL7TOFHIR|202312060830|||ADT^A08|MSG00001|P|2.5\r\n" +
                "PID||123456|7890||DOE^JOHN";
        String result = lambda.handleRequest(hl7Message, null);
        System.out.println(result);
        assertNotNull(result);
        assertTrue(result.contains("Bundle"));
    }
}
