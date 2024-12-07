
# Dockerfile for AWS Lambda Java function testing
# This uses the AWS Lambda base image for Java 11

# Use AWS Lambda Java base image
FROM public.ecr.aws/lambda/java:11

# Copy the built JAR file from the Maven target directory
# Ensure you have run 'mvn package' before building the Docker image
COPY code /var/task/

# Specify the Lambda handler class and method
CMD ["com.igearfs.lambda.Hl7ToFhirLambda::handleRequest"]
