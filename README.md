# listings-service

A microservice for processing rental listings

# What it does

For now, it simply accepts a POST request, writes it to a temporary file, then sends that file to S3

# Dependencies

1. Java 8
2. Docker desktop (if you're into that sort of thing)

# Local setup

Set the following environment variables...

| Key      | Value |
| ----------- | ----------- |
| AWS_ACCESS_KEY_ID      | "access key id value" |
| AWS_SECRET_ACCESS_KEY   | "access key value" |
| AWS_REGION | "region" |
| BUCKET_NAME | "bucket name" |

# Launching listings-service (non containerized)

* `./mvnw package && java -jar target/listings-service-0.0.1-SNAPSHOT.jar`

# Launching listings-service (docker compose)

* `docker-compose up`

# Launching listings-service (EKS)

Note: This assumes you have the awscli and kubectl installed and properly configured

1. Update the credentials and bucket placeholders in listings-service-controller.yaml with your specific values
2. Create the cluster
    
    eksctl create cluster \
    > --name some-name \
    > --version 1.13 \
    > --nodegroup-name listings-service \
    > --node-type t2.micro \
    > --nodes 3 \
    > --nodes-min 1 \
    > --nodes-max 4 \
    > --node-ami auto
3. Create the ReplicaSet and Pods: `kubectl create -f listings-service-controller.yaml`
4. Create the service: `kubectl create -f listings-service.yaml`

# Testing

POST a json object representing a rental listing to the listings-service

| METHOD      | URI | HEADER | 
| ----------- | ----------- | --- |
| POST      |  hostname:8080/post       | Content-Type: application/json |

BODY
```json
{
  "beds": 2,
  "baths": 1,
  "sqft": 1000,
  "rent": 1600,
  "deposit": 500,
  "fees": 100,
  "address": "11642 91st Ln NE APT E, Kirkland, WA 98034",
  "description": "Gorgeous apartment with open floor plan in-unit laundry deck. Features A/C stainless appliances hardwood floors stunning counter tops and cabinets abundant closet space and great finishes all around. Apartment complex has community patio area with BBQ and covered parking. Walk 1 block to Juanita Beach. The location does not get any better than this! $300 non refundable pet fee (per pet) Security deposit due at signing (and any pet fee) Tenant responsible for electricity 100$ for utilities such as water garbage and sewer",
  "dateAvailable": "01-09-2019",
  "amenities": {
    "cooling": true,
    "heating": true,
    "laundryInUnit": true,
    "pets": ["dogs", "cats", "birds", "fish"]
  },
  "posted": "19-08-2019"
}
```