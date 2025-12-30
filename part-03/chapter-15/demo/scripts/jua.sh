curl -u jua:123 http://localhost:8080/v2/management/employees

curl -u jua:123\
  -X POST\
  -H 'Content-Type: application/json'\
  -d 'paul'\
  http://localhost:8080/v2/management/employees

curl -u jua:123 http://localhost:8080/v2/management/employees

curl -u jua:123 http://localhost:8080/v2/management/employees/7

curl -u jua:123\
  -X DELETE\
  http://localhost:8080/v2/management/employees/7

curl -u jua:123 http://localhost:8080/v2/management/employees
