# Send port number in this command eg. ./kill :8080
kill -9 $(lsof -t -i $1)
