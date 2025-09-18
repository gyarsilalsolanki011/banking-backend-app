#!/bin/sh
set -e

host="$1"
shift
cmd="$@"

echo "⏳ Waiting for MySQL ($host:3306)..."

# Wait until the MySQL port is open
while ! nc -z "$host" 3306; do
  echo "🔄 Waiting for MySQL ($host:3306)..."
  sleep 2
done

echo "✅ MySQL is up - starting backend"
exec $cmd

