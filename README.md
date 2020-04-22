# student-system
1. git clone https://github.com/simonasstipinas/student-system.git
2. cd student-system
3. git clone https://github.com/augkik/contacts.git
4. docker-compose up -d --build

GET ALL
http://localhost:5000/school
GET
http://localhost:5000/school/CLASSCODE1
POST
http://localhost:5000/school
    BODY:
{
  "code": "CLASSCODE5",
  "name": "JOHN",
  "field": "Biology",
  "contact": {
    "id": 122,
    "surname": "LL",
    "name": "LL",
    "number": "+37L065841738",
    "email": "jakLevan@mail.com"
  }
}
DELETE
http://localhost:5000/school/CLASSCODE1

UPDATE 
http://localhost:5000/school/updateContact/CLASSCODE1
{
  "code": "CLASSCODE5",
  "name": "JOHN",
  "field": "Biology",
  "contact": {
    "id": 1122,
    "surname": "LLL",
    "name": "LLL",
    "number": "+37L065841738",
    "email": "jakLevan@mail.com"
  }
}