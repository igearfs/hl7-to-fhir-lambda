Warning

This project has not yet been tested on AWS Lambda.Tested locally in docker.
While the code is designed for Lambda deployment, it is recommended to test thoroughly in an AWS environment before using it in production.
and for PHI Make sure you are over HTTPS and follow HIPAA. 

probably should add logging and storage into a database as well to follow compliance.

# HL7 to FHIR Lambda Function

This project provides an AWS Lambda function for converting HL7 v2 messages into FHIR-compliant JSON resources. It uses the [LinuxForHealth HL7 to FHIR Converter](https://github.com/LinuxForHealth/hl7v2-fhir-converter) for the transformation, enabling seamless integration and adherence to FHIR standards.

## Overview

The function is designed for healthcare systems that need to process HL7 v2 messages and convert them into the FHIR format. This is commonly required for interoperability between healthcare systems or for integration with FHIR-based systems.

### Features
- **Fast HL7 to FHIR conversion**: Built on top of the LinuxForHealth HL7 to FHIR Converter.
- **Scalable**: Leverages AWS Lambda to handle requests on demand.
- **Extensible**: Easily customize or extend the logic for additional HL7 segments or FHIR resources.

### Supported HL7 Message Types
The LinuxForHealth HL7 to FHIR Converter supports a wide variety of HL7 v2 message types, including:
- `ADT^A01` (Admit/Visit Notification)
- `ORM^O01` (Order Message)
- `ORU^R01` (Observation Result)

For a complete list, see the [LinuxForHealth documentation](https://github.com/LinuxForHealth/hl7v2-fhir-converter#supported-message-types).

---

## Project Structure

```plaintext
hl7-to-fhir-lambda/
├── pom.xml                    # Maven project configuration
├── docker
│   ├── code                   # this is where the jar from shade is extracted into
│   └── docker-compose.yml     # Runs the docker container
│   └── Dockerfile             # Docker file to build AWS Lambda container
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/lambda/
│   │           ├── Hl7ToFhirLambda.java     # Lambda function
│   │           └── Hl7ToFhirConverter.java  # (Optional) Additional conversion logic
│   └── test/
│       └── java/
│           └── com/example/lambda/
│               └── Hl7ToFhirLambdaTest.java # Unit tests
├── README.md                  # Project documentation
└── .gitignore                 # Git ignore file
```

---

## Getting Started LOCAL DOCKER
### Prerequisites
- Java 11 or later
- Maven
- Docker Desktop or if linux docker-compose and docker
- 
### Installation

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd hl7-to-fhir-lambda
   ```

2. Build the project using Maven:
   ```bash
   mvn clean package
   ```

3. Deploy the Lambda function:
   - create a folder called code in the docker folder to put the extracted package .jar files into
   - extract the hl7-to-fhir-lambda.jar from the target directory into docker/code directory
   - use your IDE to run the docker-compose.yml
     - IF no ide go into the docker folder and run docker-compose up --build
   - run TestLambdaAfterDockerIdUp to test.

---


## Getting Started AWS

### Prerequisites
- Java 11 or later
- Maven
- AWS account with permissions to create and deploy Lambda functions
- Basic knowledge of HL7 and FHIR

### Installation

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd hl7-to-fhir-lambda
   ```

2. Build the project using Maven:
   ```bash
   mvn clean package
   ```

3. Deploy the Lambda function:
   - Upload the shade `.jar` file in `target/` to AWS Lambda.
   - Set the handler to:
     ```plaintext
     com.igearfs.lambda.Hl7ToFhirLambda::handleRequest
     ```

---

## How to Use

1. **Send an HL7 v2 message** to the Lambda function:
   ```plaintext
   MSH|^~\&|HOSPITAL|LAB|HL7TOFHIR|202312060830||ADT^A01|MSG00001|P|2.5
   PID|1|123456|7890||DOE^JOHN
   ```

2. **Receive the FHIR JSON response**:
   ```json
   {
       "resourceType": "Bundle",
       "type": "message",
       "entry": [
           {
               "resource": {
                   "resourceType": "Patient",
                   "identifier": [
                       {
                           "system": "urn:oid:2.16.840.1.113883.4.1",
                           "value": "123456"
                       }
                   ],
                   "name": [
                       {
                           "family": "DOE",
                           "given": ["JOHN"]
                       }
                   ]
               }
           }
       ]
   }
   ```

---

## Acknowledgments

This project relies on the fantastic work done by the [LinuxForHealth HL7 to FHIR Converter](https://github.com/LinuxForHealth/hl7v2-fhir-converter). Their open-source library made the HL7 to FHIR transformation process seamless and efficient.

---

# Project Name

[Brief description of the project and its purpose.]

---

## License

This program is licensed under the **Server Side Public License (SSPL), Version 1**, with additional terms as outlined below:

- **Commercial Use**:
   - Any use of this software in a commercial capacity, including offering it as part of a managed service or SaaS product, requires a commercial license agreement with **In-Game Event, A Red Flag Syndicate LLC**.
   - To obtain a commercial license, contact: [licence_request@igearfs.com](mailto:licence_request@igearfs.com).

- **Non-Commercial Use**:
   - If you choose not to obtain a commercial license, you must comply with the SSPL terms. This includes making publicly available the source code for all programs, tooling, and infrastructure used to operate this software as a service.

---

## No Warranty

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

For licensing inquiries, contact:
- **License Requests**: [licence_request@igearfs.com](mailto:licence_request@igearfs.com)
- **General Inquiries**: [contact@igearfs.com](mailto:contact@igearfs.com)

For more details, refer to the full text of the **Server Side Public License (SSPL)** at [https://www.mongodb.com/licensing/server-side-public-license](https://www.mongodb.com/licensing/server-side-public-license).
