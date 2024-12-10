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

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestLambdaAfterDockerIsUp {
    public static void main(String[] args) {
        try {
            // URL of the local Lambda runtime API
            URL url = new URL("http://localhost:9000/2015-03-31/functions/function/invocations");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");  // Content-Type is still json
            connection.setDoOutput(true);

            // Sample HL7 message payload
            String hl7Message = "MSH|^~\\\\&|HOSPITAL|LAB|HL7TOFHIR|202312060830|||ADT^A08|MSG00001|P|2.5\r\nPID|1|123456|7890||DOE^JOHN\r\n";

            // Wrap the HL7 message directly in the JSON payload as a string
            String jsonPayload = "\"" + hl7Message + "\"";  // Escape quotes inside the message

            // Send the raw HL7 message as a JSON string (without wrapping in an object)
            try (OutputStream os = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(os, true, StandardCharsets.UTF_8)) {
                writer.println(jsonPayload);
            }

            // Read and print the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
