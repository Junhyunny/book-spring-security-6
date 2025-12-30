curl -u junhyunny:123 http://localhost:8080/v2/management/employees

curl -u junhyunny:123\
  -X POST\
  -H 'Content-Type: application/json'\
  -d 'paul'\
  http://localhost:8080/v2/management/employees

curl -u junhyunny:123 http://localhost:8080/v2/management/employees

curl -u junhyunny:123 http://localhost:8080/v2/management/employees/5

curl -u junhyunny:123\
  -X DELETE\
  http://localhost:8080/v2/management/employees/5

curl -u junhyunny:123 http://localhost:8080/v2/management/employees