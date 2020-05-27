# student-system
1. git clone https://github.com/simonasstipinas/student-system.git
2. cd student-system
3. git clone https://github.com/augkik/contacts.git
4. docker-compose up -d --build

# REST

### GET ALL
http://localhost:5000/school
### GET
http://localhost:5000/school/CLASSCODE1
### POST
http://localhost:5000/school
BODY:
```json
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
```

### DELETE
http://localhost:5000/school/CLASSCODE1

### UPDATE 
http://localhost:5000/school/updateContact/CLASSCODE1
```json
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
```
___
# SOAP

### GET ALL
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sim="http://simonas.stipinas.lt">
   <soapenv:Header/>
   <soapenv:Body>
      <sim:getAllPupilContactRequest />
   </soapenv:Body>
</soapenv:Envelope>
```

### GET
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sim="http://simonas.stipinas.lt">
   <soapenv:Header/>
   <soapenv:Body>
      <sim:getByIdPupilContactRequest>
         <sim:code>CLASSCODE1</sim:code>
      </sim:getByIdPupilContactRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### INSERT
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sim="http://simonas.stipinas.lt">
   <soapenv:Header/>
   <soapenv:Body>
      <sim:insertPupilContactRequest>
         <sim:pupilContact>
            <sim:code>CLASSCODE50</sim:code>
            <sim:name>JOHN</sim:name>
            <sim:field>Biology</sim:field>
            <sim:contact>
               <sim:id>1224</sim:id>
               <sim:surname>LLA</sim:surname>
               <sim:name>LLA</sim:name>
               <sim:number>+37L065841778</sim:number>
               <sim:email>jakLevan123@mail.com</sim:email>
            </sim:contact>
         </sim:pupilContact>
      </sim:insertPupilContactRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### UPDATE
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sim="http://simonas.stipinas.lt">
   <soapenv:Header/>
   <soapenv:Body>
      <sim:updatePupilContactRequest>
         <sim:code>CLASSCODE2</sim:code>
         <sim:updatedPupilContact>
            <sim:code>CLASSCODE2</sim:code>
            <sim:name>UPDATED name</sim:name>
            <sim:field>UPDATED field</sim:field>
            <sim:contact>
               <sim:id>74638</sim:id>
               <sim:surname>UPDATED surname</sim:surname>
               <sim:name>UPDATED name</sim:name>
               <sim:number>UPDATED +370534343434</sim:number>
               <sim:email>UPDATED@gmail.com</sim:email>
            </sim:contact>
         </sim:updatedPupilContact>
      </sim:updatePupilContactRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### DELETE
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sim="http://simonas.stipinas.lt">
   <soapenv:Header/>
   <soapenv:Body>
      <sim:deletePupilContactRequest>
         <sim:code>CLASSCODE2</sim:code>
      </sim:deletePupilContactRequest>
   </soapenv:Body>
</soapenv:Envelope>
```