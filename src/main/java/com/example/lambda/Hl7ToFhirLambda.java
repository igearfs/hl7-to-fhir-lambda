
package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.github.linuxforhealth.hl7.HL7ToFHIRConverter;

public class Hl7ToFhirLambda implements RequestHandler<String, String> {

    /**
     *
     * @param hl7Message - The HL7 Message to convert. If it is not in the list for the product then we will need to branch off
     *                   the product and add it for conversion. @see https://github.com/LinuxForHealth/hl7v2-fhir-converter
     * @param context - null for local testing and not null when in AWS Lambda.
     * @return JSON format or - "Conversion failed: " + e.getMessage() if there is an error. Does not throw back an exception.
     *                      Monitor logs for odd HL7 that gets through.
     */
    @Override
    public String handleRequest(String hl7Message, Context context) {
        try {
            // Use LinuxForHealth HL7 to FHIR Converter
            HL7ToFHIRConverter converter = new HL7ToFHIRConverter();
            String fhirJson = converter.convert(hl7Message);

            // Log and return the FHIR JSON

            if(context != null) {
                context.getLogger().log("FHIR JSON: " + fhirJson);
            }
            return fhirJson;
        } catch (Exception e) {
            if(context != null) {
                context.getLogger().log("Error converting HL7 to FHIR: " + e.getMessage());
            }
            return "Conversion failed: " + e.getMessage();
        }
    }
}
