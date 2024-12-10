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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.github.linuxforhealth.hl7.HL7ToFHIRConverter;

public class Hl7ToFhirLambda implements RequestHandler<String, String> {

    /**
     * @param hl7Message - The HL7 Message to convert. If it is not in the list for the product then we will need to branch off
     *                   the product and add it for conversion. @see https://github.com/LinuxForHealth/hl7v2-fhir-converter
     * @param context    - null for local testing and not null when in AWS Lambda.
     * @return JSON format or - "Conversion failed: " + e.getMessage() if there is an error. Does not throw back an exception.
     * Monitor logs for odd HL7 that gets through.
     */
    @Override
    public String handleRequest(String hl7Message, Context context) {
        try {
            System.out.println(hl7Message);
            // Use LinuxForHealth HL7 to FHIR Converter
            HL7ToFHIRConverter converter = new HL7ToFHIRConverter();
            String fhirJson = converter.convert(hl7Message);

            // Log and return the FHIR JSON

            if (context != null) {
                context.getLogger().log("FHIR JSON: " + fhirJson);
            }
            return fhirJson;
        } catch (Exception e) {
            e.printStackTrace();
            if (context != null) {
                context.getLogger().log("Error converting HL7 to FHIR: " + e.getMessage());
            }
            return "Conversion failed: " + e.getMessage();
        }
    }
}
