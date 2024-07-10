# ADP-WAREHOUSE

## Overview

ADP-WAREHOUSE is a project that involves publishing sensor data using Kafka. This guide will help you set up and run the necessary components to get the system up and running.

## Prerequisites

Ensure you have the following installed:
- Docker
- Docker Compose

## Setup and Execution

Follow these steps to start the ADP-WAREHOUSE system:

### 1. Start Kafka

First, run `docker-compose` to start Kafka:

```bash
docker-compose up -d
```

### 2. Run ADP-WAREHOUSE project
### 3. Run MS-CENTRAL project
### 4. Send UDP request to adp-warehouse

```bash
chmod +x send_udp_requests.sh

./send_udp_requests.sh
```



