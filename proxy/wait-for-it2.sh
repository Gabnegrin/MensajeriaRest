#!/bin/bash

# Usar para esperar a que un servicio esté disponible
# Uso: ./wait-for-it.sh <host>:<port> -- <command>

HOST_PORT="$1"
shift
CMD="$@"

HOST="${HOST_PORT%%:*}"
PORT="${HOST_PORT##*:}"

while ! nc -z "$HOST" "$PORT"; do
  echo "Esperando que $HOST:$PORT esté disponible..."
  sleep 3
done

echo "$HOST:$PORT está disponible."
exec $CMD
