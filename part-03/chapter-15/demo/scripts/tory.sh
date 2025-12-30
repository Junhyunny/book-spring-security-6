curl -u tory:123 http://localhost:8080/v2/management/employees

curl -u tory:123\
  -X POST\
  -H 'Content-Type: application/json'\
  -d 'paul'\
  http://localhost:8080/v2/management/employees

curl -u tory:123 http://localhost:8080/v2/management/employees

curl -u tory:123 http://localhost:8080/v2/management/employees/1

curl -u tory:123\
  -X DELETE\
  http://localhost:8080/v2/management/employees/1

curl -u tory:123 http://localhost:8080/v2/management/employees
