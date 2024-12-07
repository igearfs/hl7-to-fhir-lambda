/*
 * Sponsored by In-Game Event, A Red Flag Syndicate LLC.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.igearfs.lambda;

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
            System.out.println(hl7Message);
            // Use LinuxForHealth HL7 to FHIR Converter
            HL7ToFHIRConverter converter = new HL7ToFHIRConverter();
            String fhirJson = converter.convert(hl7Message);

            // Log and return the FHIR JSON

            if(context != null) {
                context.getLogger().log("FHIR JSON: " + fhirJson);
            }
            return fhirJson;
        } catch (Exception e) {
            e.printStackTrace();
            if(context != null) {
                context.getLogger().log("Error converting HL7 to FHIR: " + e.getMessage());
            }
            return "Conversion failed: " + e.getMessage();
        }
    }
}
