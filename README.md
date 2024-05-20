## Olympic games

We are developing a platform to track the performances of athletes at the Olympic Games, specifically for track and field athletes. Through the platform, we can record the performances of athletes in various track and field disciplines and view the results on a scoreboard.

Some remarks to consider during your implementation:

- Ensure you do not add any System.out.println statements in your code. If you need to log information, use the logging framework Log4j and apply an appropriate log level. Log4j2 can be used in the application without extra dependencies or configuration.
- The project is already configured to use a file-based H2 database. This file is located in the _database_ folder. If you delete this file, you will start with an empty database.
- Ensure that you create a good separation between the persistence layer, business layer, and presentation layer in your implementation.
- You are allowed to change the port number of your application server.
- In the _resources_ folder, you will find the script validate_endpoints.sh that calls the REST endpoints and the servlet via curl. The expected output of the script is provided in the file _expected_result.md_.
- Implement the specified functionality and also add unit tests for the endpoints, the business logic in the service layer and the custom queries.


### Tasks
- A. Create a new race
- B. Retrieve the status of a race
- C. Upload athletes for a race
- D. Download race data in CSV format
- E. Register the result of an athlete for a race
- F. Simulate a race
- G. Job to complete a race
- H. Servlet for the scoreboard of a race


### A. Create a new race
#### Entity-classes
**Race**

| Property      | Type                         |
|---------------|------------------------------|
| id            | long                         |
| dateTime      | LocalDateTime                |
| discipline    | Discipline (enum as String)  |
| athlete       | Athlete (relation)           |


**RaceStatus**

| Property         | Type                       |
|------------------|----------------------------|
| id               | long                       |
| race             | Race (relation)            |
| modifiedDateTime | LocalDateTime              |
| phase            | RacePhase (enum as String) |
| description | String |

Implement a REST endpoint to create a new race. 
Both dateTime and discipline are required fields. 
The provided date must be in the future. If not, a 400 BAD REQUEST is given.
Each race goes through different phases (see RacePhase enum). 
A newly created race gets the phase CREATED. 
Track all phases a race goes through using a bi-directional one-to-many relationship between the Race and RaceStatus entity classes.

POST http://localhost:8082/olympicgames/races

Request body
```
{
"dateTime": "2021-09-09 13:15:00",
"discipline": "SPRINT_100M"
}
```

Responses
- 201 CREATED with the ID of the newly created race in the response
- 400 BAD_REQUEST if data is missing or the date is in the past

### B. Retrieve the status of a race

Provide in the Race class the method getCurrentStatus() which returns the most recent RaceStatus (use the Stream API and lambda expressions for this).
Write unit tests to test this method.
Complete the class RaceStatusDto for creating the response.

GET http://localhost:8082/olympicgames/races/{raceId}/status

Responses:
- 404 NOT_FOUND when the raceId is not valid
- 200 OK with response

```
{
"id": 1,
"dateTime": "2021-09-09T13:15:00",
"discipline": "SPRINT_100M",
"status": "FINISHED",
"statusDateTime": "2024-05-16T10:41:28.003677",
"description": null
}
```

```
{
"id": 2,
"dateTime": "2021-09-09T13:15:00",
"discipline": "HORDES_400M",
"status": "UPLOADING_ATHLETES",
"statusDateTime": "2024-05-19T12:02:41.414369",
"description": null
}
```

```
{
"id": 2,
"dateTime": "2021-09-09T13:15:00",
"discipline": "HORDES_400M",
"status": "UPLOAD_OK",
"statusDateTime": "2024-05-19T12:07:42.543082",
"description": "10 athletes participating in race."
}
```

```
{
"id": 2,
"dateTime": "2021-09-09T13:15:00",
"discipline": "HORDES_400M",
"status": "UPLOAD_FAILED",
"statusDateTime": "2024-05-20T08:46:32.585545",
"description": "Line [Britney;Feil;BI;abcdef;9;18] has invalid data."
}
```

### C. Upload athletes for a race

#### Entity-klassen

**Athlete**

| Property    | Type      |
|-------------|-----------|
| firstName   | String    |
| lastName    | String    |
| country     | String    |
| dateOfBirth | LocalDate |

**Participant**

| Property    | Type                         |
|-------------|------------------------------|
|athlete| Athlete (relation)           |
|race| Race (relation)              |
|scoreStatus| ScoreStatus (enum as String) |
| time | LocalTime as String (see instructions in class |

Initial Status: ENROLLED (when an athlete registers for a race)

**REST Endpoint**

POST http://localhost:8082/olympicgames/races/{raceId}/upload

_Description:_<br>
Upload a CSV file containing athlete information.<br>
_Content-Type:_<br>
multipart/form-data<br>
_Constraints:_<br>
You can only upload athletes if the race phase is UPLOAD_FAILED or CREATED. If the race phase is different, return BAD_REQUEST with the description "invalid race phase".<br>

Valid and invalid csv files are locatie Valid and invalid CSV files are located in the folder resources/participants.

**Processing Logic**<br>
- Ensure the race phase is either UPLOAD_FAILED or CREATED. If not, return BAD_REQUEST with "invalid race phase".<br>
- Change the race phase to UPLOADING_ATHLETES.<br>
- Process the CSV file asynchronously. Add a Thread.sleep() in the processing method to test asynchronous behavior.<br>
- If an athlete does not exist in the database, add them.
- If an athlete already exists, use the existing entity. An athlete can only participate in one race per day!
- Register the athlete for the race by creating a new Participant object with status ENROLLED.
- If processing is successful, set the race phase to UPLOAD_OK.<br>
- If there's an error, set the race phase to UPLOAD_FAILED with a clear error message.<br>


### D. Download csv-file with race data

**REST Endpoint**

GET /races/{raceId}/csv/download

_Description:_<br>
Download a CSV file containing the data of the race.<br>
_Constraints:_<br>
The data can only be downloaded if the race phase is UPLOAD_OK. If the race phase is different, return BAD_REQUEST with the description "invalid race phase".<br>

Once the file is successfully downloaded, change the race phase to STARTED.

**CSV Format**

| Type | Format |
|------|--------|
| Header |  <race_id>;<distance> |
| Data line| <athlete_id>;<firstname>;<lastname>;<country>|


An example of a csv-file can be found in the folder resources/race.

### E. Register the result of an athlete for a race

**REST Endpoint**

POST http://localhost:8082/olympicgames/races/{raceId}/{athleteId}<br>
_Description:_<br>
Update the status of an athlete for a race.

_Request Body:_<br>

    {
    "state": "DID_NOT_FINISH"
    }

    {
    "status": "QUALIFIED",
    "time": "00:00:09.800"
    }

_Responses_<br>
200 OK:<br>
The status of the athlete was successfully updated.<br>
404 NOT FOUND:<br>
The specified race or athlete was not found.

**Processing Logic**

- Verify if the specified race and athlete exist. If not, return 404 NOT FOUND.
- Verify if the race is in state STARTED. If not, return 400 BAD REQUEST.
- Verify the athlete is ENROLLED in the race.
- Update the Participant-object with the correct state and record the provided time.
- Write unit tests with mockito to verify the correct implementation the business logic.
- Write unit tests with mock mvc to verify the correct implementation of the REST endpoint.

### E. Multithreaded simulation of the race

The classes are provided in the package _be.pxl.multithreading_.

**AthleteThread**<br>
This class should be implemented as a thread.<br>
The run method should:<br>
- Record the current time at the start.
- Track the distance covered by the athlete.
- Continuously add a random distance between 2 and 1/3 of the race distance to the total distance covered.
- After updating the distance covered, a thread sleeps for 500 milliseconds.
- Include a 2% chance of the athlete crashing, simulated by throwing a DidNotFinishException.
- If the total distance exceeds the race distance, record the current time again.

The total race time for the athlete can now be calculated.

**Simulate Race**<br>

In the main program:
- Read the CSV file containing the race details.
- Create a RaceDetail object and instantiate AthleteThread objects based on the details in the file.
- Start all the athlete threads.
- Wait for all the threads to finish.
- Once all threads are finished, send the race results to the backend:
  - Send status DID_NOT_FINISHED for athletes that crashed.
  - Send status QUALIFIED and the race time for athletes that finished the race.

Use RestTemplate or WebClient to call the appropriate REST endpoint to send the race results.

### G. Job to complete a race

Create a scheduled job in the backend to change the status of a race from _STARTED_ 
to _FINISHED_.

Implementation Steps:
- Create a query to retrieve the races where the current status is STARTED.
- For each race retrieved:
  - If the race has at least one participant with status QUALIFIED, change the race phase to FINISHED.
  - Change the status of all participants who still have the status ENROLLED to DID_NOT_START.

Schedule the job to run every 5 minutes (for testing purposes, you can change it to run more frequently).

### H. Servlet for the scoreboard of a race

Convert the class OlympicGamesServlet to a real HttpServlet class that listens to the URL _RaceResults?race={raceId}_. 
The servlet should retrieve race results and display relevant information.

Retrieve a RaceResultDto using the  OlympicGamesService. If no race exists with the given id, display a clear message. 
If the race is found, display the date, the discipline, and a list of athletes sorted by their time (best first).

```
  SPRINT_100M 2024-06-03 15:00
  N. Turner	  VU	00:00:09.800
  A. Stoltenberg  LY	00:00:09.890
  O. Klein	  VG	DID_NOT_FINISH
  T. Douglas	  HR	DID_NOT_START
  E. Hauck	  GW	DID_NOT_START
  D. Fahey	  SZ	DID_NOT_START
  H. Koelpin	  BN	DID_NOT_START
  B. Hane	  TJ	DID_NOT_START
  B. Conn	  EE	DID_NOT_START
```

