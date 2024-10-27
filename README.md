# Bookstore Microservices

This project consists of three microservices for a bookstore application: `order-service`, `catalog-service`, and `notification-service`. These services work together to provide a complete e-commerce solution for book ordering, catalog management, and customer notifications.

## Services Overview

### Order Service

The Order Service is responsible for managing customer orders. It handles order creation, processing, and status management.

Key Features:
- Create new orders
- Manage order status (NEW, IN_PROCESS, DELIVERED, CANCELLED, ERROR)
- Integrate with Catalog Service to fetch product details
- Use RabbitMQ to communicate with Notification Service for order events

### Catalog Service

The Catalog Service manages the product catalog, including books and their details.

Key Features:
- Retrieve product information
- Paginated product listing
- Product search by code

### Notification Service [TODO]

The Notification Service handles sending notifications to customers about their orders.

## Technologies Used

- Java 21
- Spring Boot 3.2.7
- Spring Data JPA
- PostgreSQL
- RabbitMQ
- Resilience4j
- Flyway (for database migrations)
- JUnit and Testcontainers (for testing)
- Maven


## Getting Started
1. Clone the repository:
   ```
   git clone git@github.com:achyuthreddyi/book-store.git
   ```
2. Navigate to each service directory and build the project:
   ```
   cd order-service
   ./mvnw clean install

   cd ../catalog-service
   ./mvnw clean install
   ```

3. Set up PostgreSQL and RabbitMQ (you can use Docker for this).

4. Run each service:
   ```
   cd order-service
   ./mvnw spring-boot:run

   cd ../catalog-service
   ./mvnw spring-boot:run
   ```
## API Endpoints

### Order Service

- `POST /api/orders`: Create a new order
- (Add other endpoints as implemented)

### Catalog Service

- `GET /api/products`: Get paginated list of products
- `GET /api/products/{code}`: Get product by code


## Running Services with Docker Compose

To run the services using Docker containers, we use Docker Compose. The configuration is split into two files: `infra.yml` for infrastructure services and `apps.yml` for application services.

### Prerequisites

- Docker and Docker Compose installed on your system

### Steps to Run

1. Navigate to the `deployment/docker-compose` directory:   ```
   cd deployment/docker-compose   ```

2. Start the infrastructure services (databases and RabbitMQ):   ```
   docker-compose -f infra.yml up -d   ```

3. Once the infrastructure siervices are up and healthy, start the application services:   ```
   docker-compose -f apps.yml up -d   ```

4. To check the status of all services:   ```
   docker-compose -f infra.yml -f apps.yml ps   ```

5. To view logs of a specific service (e.g., catalog-service):   ```
   docker-compose -f apps.yml logs -f catalog-service   ```

6. To stop all services:   ```
   docker-compose -f infra.yml -f apps.yml down   ```

### Service URLs

- Catalog Service: http://localhost:8081
- Order Service: http://localhost:8082
- RabbitMQ Management UI: http://localhost:15672 (username: guest, password: guest)

### Notes

- The `infra.yml` file sets up PostgreSQL databases for both catalog and order services, as well as RabbitMQ.
- The `apps.yml` file configures and runs the catalog-service and order-service containers (Notification service to be implemented).
- Make sure to wait for the infrastructure services to be fully up before starting the application services.

   
