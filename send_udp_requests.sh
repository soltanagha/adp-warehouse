#!/bin/bash

# Number of requests to send
num_requests=10

# Function to generate a random value
generate_random_value() {
  echo $((RANDOM % 100))
}

# Send UDP requests sequentially
for ((i=1; i<=num_requests; i++))
do
  sensor_t_value=$(generate_random_value)
  sensor_h_value=$(generate_random_value)

  echo "sensor_id=t1; value=${sensor_t_value}" | nc -u -w1 127.0.0.1 3344
  echo "sensor_id=h1; value=${sensor_h_value}" | nc -u -w1 127.0.0.1 3355

  echo "Sent request $i: t1=${sensor_t_value}, h1=${sensor_h_value}"
done