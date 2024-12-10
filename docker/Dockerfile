# Copyright (C) 2024 In-Game Event, A Red Flag Syndicate LLC
#
# This program is free software: you can redistribute it and/or modify it under the terms of the Server Side Public License, version 1, as published by MongoDB, Inc., with the following additional terms:
#
# - Any use of this software in a commercial capacity requires a commercial license agreement with In-Game Event, A Red Flag Syndicate LLC. Contact licence_request@igearfs.com for details.
#
# - If you choose not to obtain a commercial license, you must comply with the SSPL terms, which include making publicly available the source code for all programs, tooling, and infrastructure used to operate this software as a service.
#
# This program is distributed WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Server Side Public License for more details.
#
# For licensing inquiries, contact: licence_request@igearfs.com

# Dockerfile for AWS Lambda Java function testing
# This uses the AWS Lambda base image for Java 11

# Use AWS Lambda Java base image
FROM public.ecr.aws/lambda/java:11

# Copy the built JAR file from the Maven target directory
# Ensure you have run 'mvn package' before building the Docker image
COPY code /var/task/

# Specify the Lambda handler class and method
CMD ["com.igearfs.lambda.Hl7ToFhirLambda::handleRequest"]
