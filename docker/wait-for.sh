#!/bin/sh
# wait-for.sh

set -e

host="$1"
shift
cmd="$@"

until mysql -h "$host" -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "select 1" >/dev/null 2>&1; do
  echo "Waiting for MySQL at $host..."
  sleep 2
done

echo "MySQL is up - executing command"
exec $cmd
