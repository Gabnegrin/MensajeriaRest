#!/usr/bin/env bash
# wait-for-receivers.sh

set -e

hosts_ports="$1"
shift
cmd="$@"

# Convert hosts_ports string to an array
IFS=',' read -r -a array <<< "$hosts_ports"

# Loop through each host:port pair and wait until it is available
for host_port in "${array[@]}"; do
  host=$(echo $host_port | cut -d: -f1)
  port=$(echo $host_port | cut -d: -f2)
  
  while ! nc -z $host $port; do
    echo "Waiting for $host:$port..."
    sleep 1
  done
  
  >&2 echo "$host:$port is available"
done

exec $cmd
