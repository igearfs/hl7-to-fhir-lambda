/*
 * Copyright (C) 2024 In-Game Event, A Red Flag Syndicate LLC
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Server Side Public License, version 1, as published by MongoDB, Inc., with the following additional terms:
 *
 * - Any use of this software in a commercial capacity requires a commercial license agreement with In-Game Event, A Red Flag Syndicate LLC. Contact licence_request@igearfs.com for details.
 *
 * - If you choose not to obtain a commercial license, you must comply with the SSPL terms, which include making publicly available the source code for all programs, tooling, and infrastructure used to operate this software as a service.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Server Side Public License for more details.
 *
 * For licensing inquiries, contact: licence_request@igearfs.com
 */

package com.igearfs.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Hl7ToFhirLambdaTest {
    @Test
    void testHandleRequest() {
        Hl7ToFhirLambda lambda = new Hl7ToFhirLambda();
        String hl7Message = "MSH|^~\\\\&|HOSPITAL|LAB|HL7TOFHIR|202312060830|||ADT^A08|MSG00001|P|2.5\r\n" +
                "PID||123456|7890||DOE^JOHN";
        String result = lambda.handleRequest(hl7Message, null);
        System.out.println(result);
        assertNotNull(result);
        assertTrue(result.contains("Bundle"));
    }
}
