/*
 * Sponsored by In-Game Event, A Red Falg Syndicate LLC.
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
