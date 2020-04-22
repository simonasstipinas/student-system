# student-system
1. git clone https://github.com/simonasstipinas/student-system.git
2. cd student-system
3. git clone https://github.com/augkik/contacts.git
4. docker-compose up -d --build

GET ALL
http://localhost:5001/school
GET
http://localhost:5001/school/CLASSCODE1
POST
http://localhost:5001/school
    BODY:
    {
    "code": "CLASSCODE4",
    "name": "JOHN",
    "field": "Biology"
    }
DELETE
http://localhost:5001/school/CLASSCODE1

ADD Contact
http://localhost:5001/school/addContact/CLASSCODE4
{
"id": 12,
"surname": "JOHNSON",
"name": "JOHN",
"number": "+38888",
"email": "JOHN.JOHNSON@MAIL.COM"
}
UPDATE Contract
http://localhost:5001/school/updateContact/CLASSCODE4
{
  "id": 12,
  "surname": "SMITH",
  "name": "SMITH",
  "number": "+38888",
  "email": "SMITH.SMITH@MAIL.COM"
}