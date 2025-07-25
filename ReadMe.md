# Bikar

## Overview

**Bikar** is a microservices-based application designed to facilitate the exchange of goods and services for cash or in-kind credit. It provides a modular and scalable backend infrastructure using Java and Spring Boot, enabling flexible interactions such as:

- Buying/selling products
- Providing and receiving services
- Managing recurring payments or credit-based exchanges

---

## Features

- 🧩 Modular architecture with Spring Boot microservices
- 🔁 Support for recurring payment models
- 🔄 Service-for-service or service-for-goods exchange
- 💳 Credit or cash-based transaction tracking
- 📦 Item and service listing, matching, and status management
- 🛡️ Secure communication between services (e.g., via REST or message queues)

---

## Tech Stack

- Java 21
- Spring Boot (Microservices)
- Spring Cloud (Config, Gateway, Service Registry)
- Redis (for caching or session handling)
- Maven
- Docker

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose
- Redis (if required by services)

### Run with Docker Compose

```bash
docker-compose up --build
