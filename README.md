# TfL Arrivals Web Application

A web application showing real-time bus arrival estimates at the *Waterloo Station / York Road* bus stop in London. 

The Java Spring Boot back-end calls the Transport for London API to receive the arrival estimates every minute. It stores all the responses it received in a MySQL database. It also serves a RESTful API with endpoints responding with the most recent arrival estimates, 500 most recent arrival estimates, and 500 most recent arrival estimates for one bus line.

The React.js + Bulma front-end displays the data received from the back-end API in an approachable, human-readable way.

## Setting Up and Starting the Application

### 1. Creating the Database

The database can be created using the following MySQL code on the MySQL server:
```SQL
CREATE USER 'tflapp_user'@'localhost' IDENTIFIED BY '5vGLonpno1wsw2at';
CREATE DATABASE tflapp;
GRANT ALL PRIVILEGES on tflapp.* TO 'tflapp_user'@'localhost';
FLUSH PRIVILEGES;
```

Essentially, a new user called `tflapp_user` needs to be added to the MySQL server, identified by the password `5vGLonpno1wsw2at`. Then a database called `tflapp` needs to be created. Finally, all priviliged to the `tflapp` database should be given to the `tflapp_user` user.

### 2. Running the Application

After initialising the database, navigate to the Build folder in the repository and execute the command:

```
java -jar tflapp-0.0.1-SNAPSHOT.jar --server.port=80
```

Wait for the back-end to start.

### 3. Opening the Web Application

Open a web browser and navigate to http://localhost.

# Documentation

## Back-End

The TfL API endpoint called by the back-end is https://api.tfl.gov.uk/StopPoint/490000254W/arrivals. Each received response is stored in the MySQL database.

### Endpoints

All endpoints follow the same schema. Every response is a list of estimates, where each estimate corresponds to a specific vehicle going towards the bus stop. A vehicle is identified by `vehicleId`.

The fields correspond directly to the fields given by the TfL API.

The most notable fields are:
- `entryId` - the ID of the entry as it is in the database (it's the primary key) 
- `timestamp` - the time when the TfL API call was made which responded with that estimate
- `vehicleId` - the value identifying a specific physical vehicle approaching the bus stop.
- `lineId` and `lineName` - the unique ID and human-readable name (respectively) of the line (for example the bus number).
- `destinationName` - the name of the bus stop at the end of the line.
- `timeToStation` - Number of seconds until the bus is expected to arrive at the bus stop.
- `expectedArrival` - The date and time when the bus is expected to arrive at the bus stop.

#### GET `/api/arrivals/current`

Responds with a list of arrival estimates from the most recent TfL API call. 

Example response:

```json
[
  {
    "entryId": 807,
    "timestamp": "2022-09-13T14:28:36",
    "operationType": 1,
    "vehicleId": "SN12AUL",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "381",
    "lineName": "381",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Waterloo",
    "timeToStation": 119,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T14:29:21",
    "timeToLive": "2022-09-13T13:29:51",
    "modeName": "bus",
    "arrivalId": null
  },
  {
    "entryId": 808,
    "timestamp": "2022-09-13T14:28:36",
    "operationType": 1,
    "vehicleId": "SN61CYH",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "381",
    "lineName": "381",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Waterloo",
    "timeToStation": 595,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T14:37:17",
    "timeToLive": "2022-09-13T13:37:47",
    "modeName": "bus",
    "arrivalId": null
  },
  //...
]
```

#### GET `/api/arrivals/all`

Responds with 500 most recent arrival estimates stored in the database.

Example response:

```json
[
  {
    "entryId": 880,
    "timestamp": "2022-09-13T14:33:36",
    "operationType": 1,
    "vehicleId": "YX18KXP",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "77",
    "lineName": "77",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Tooting Station",
    "timeToStation": 69,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T14:34:31",
    "timeToLive": "2022-09-13T13:35:01",
    "modeName": "bus",
    "arrivalId": null
  },
  {
    "entryId": 879,
    "timestamp": "2022-09-13T14:33:36",
    "operationType": 1,
    "vehicleId": "YX18KXM",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "77",
    "lineName": "77",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Tooting Station",
    "timeToStation": 757,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T14:45:59",
    "timeToLive": "2022-09-13T13:46:29",
    "modeName": "bus",
    "arrivalId": null
  }
  //...
]
```

#### GET `/api/arrivals/line/{lineId}`

Responds with 500 most recent arrival estimates stored in the database, for the bus line identified by the `{lineId}` parameter in the path.

If there are no estimates for a provided `{lineId}`, the response is an empty list.

Example response:
```json
[
  {
    "entryId": 909,
    "timestamp": "2022-09-13T14:36:36",
    "operationType": 1,
    "vehicleId": "SN61CYW",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "381",
    "lineName": "381",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Waterloo",
    "timeToStation": 974,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T14:52:36",
    "timeToLive": "2022-09-13T13:53:06",
    "modeName": "bus",
    "arrivalId": null
  },
  {
    "entryId": 908,
    "timestamp": "2022-09-13T14:36:36",
    "operationType": 1,
    "vehicleId": "SN61CYV",
    "naptanId": "490000254W",
    "stationName": "Waterloo Station   / York Road",
    "lineId": "381",
    "lineName": "381",
    "platformName": "W",
    "direction": "outbound",
    "bearing": "210",
    "destinationNaptanId": "",
    "destinationName": "Waterloo",
    "timeToStation": 1621,
    "currentLocation": "",
    "towards": "Vauxhall Or Victoria",
    "expectedArrival": "2022-09-13T15:03:23",
    "timeToLive": "2022-09-13T14:03:53",
    "modeName": "bus",
    "arrivalId": null
  }
  //...
]
```


### Architecture
#### Java

Project structure:
```
└───com.jsmi.tflapp
    │   TflappApplication.java
    │
    ├───arrivals
    │       ArrivalEntry.java
    │       ArrivalEntryController.java
    │       ArrivalEntryRepository.java
    │       ArrivalEntryService.java
    │
    ├───react
    │       ReactAppController.java
    │
    └───tflApiCaller
            TflApiCallerConfiguration.java
            TflApiCallerService.java
```

##### arrivals
- The application (and main function) is in `TfLappApplication.java`.
- The `ArrivalEntry.java` class describes the schema of one arrival estimate (for one vehicle at a point in time).
- The `ArrivalEntryRepository.java` is an interface extending the `JpaRepository`. It's the interface for the database with added functionality.
- The `ArrivalEntryController.java` class defines the endpoints of the `/arrivals` API.
- The `ArrivalEntryService.java` class works in between the `ArrivalEntryController.java` and `ArrivalEntryRepository.java`, fetching the `ArrivalEntry` objects from the repository to the controller.

##### react
- The `ReactAppController.java` class routes all non-api requests to the react front-end (at `index.html`). The front-end deals with the routing itself to make the web app more responsive and make it feel more like an app than a website.

##### tflApiCaller
- The `TflApiCallerService.java` class makes the calls to the TfL API.
- The `TflApiCallerConfiguration.java` class schedules the calls made by `TflApiCallerService.java` and saves the responses to the database.


#### MySQL

The MySQL database consists of just one table, where each row is exactly one arrival estimate. The arrival estimates from the most recent TfL API call can be distinguished by having the most recent value in the `timestamp` column.

```
> DESCRIBE Arrival_Entry;
+-----------------------+--------------+------+-----+---------+-------+
| Field                 | Type         | Null | Key | Default | Extra |
+-----------------------+--------------+------+-----+---------+-------+
| entry_id              | bigint       | NO   | PRI | NULL    |       |
| bearing               | varchar(255) | YES  |     | NULL    |       |
| current_location      | varchar(255) | YES  |     | NULL    |       |
| destination_name      | varchar(255) | YES  |     | NULL    |       |
| destination_naptan_id | varchar(255) | YES  |     | NULL    |       |
| direction             | varchar(255) | YES  |     | NULL    |       |
| expected_arrival      | datetime     | YES  |     | NULL    |       |
| id                    | bigint       | YES  |     | NULL    |       |
| line_id               | varchar(255) | YES  |     | NULL    |       |
| line_name             | varchar(255) | YES  |     | NULL    |       |
| mode_name             | varchar(255) | YES  |     | NULL    |       |
| naptan_id             | varchar(255) | YES  |     | NULL    |       |
| operation_type        | int          | YES  |     | NULL    |       |
| platform_name         | varchar(255) | YES  |     | NULL    |       |
| station_name          | varchar(255) | YES  |     | NULL    |       |
| time_to_live          | datetime     | YES  |     | NULL    |       |
| time_to_station       | int          | YES  |     | NULL    |       |
| timestamp             | datetime     | YES  |     | NULL    |       |
| towards               | varchar(255) | YES  |     | NULL    |       |
| vehicle_id            | varchar(255) | YES  |     | NULL    |       |
+-----------------------+--------------+------+-----+---------+-------+
```

This approach makes the database design 1NF, which is **not optimal**. This is elaborated upon in the *Possible Improvements* section.


## Front-End

The front-end has been developed in React.js and the Bulma CSS framework.
It consists of a page showing current arrival estimates in real time, and pages showing all historical estimates (together or for one line).

### Home Page (current arrivals)

This page shows all current arrival estimates of buses arriving at the *Waterloo Station / York Road* bus stop.

It shows a simplified colour coded bar with the most important information and a natural language description of the arrival. When the expected arrival time is below one minute, the colour coded bar turns green.

![current_arrivals](/assets/current_arrivals.png)

The user can click on the coloured bar to be taken to the page showing all historical arrival estimates of the specific line of this current arrival estimate.

The box also shows the vehicle ID, which identifies the specific bus the estimation corresponds to.

The arrivals are ordered from the one closest to *now*.

### Historical Arrival Estimates Page

This page shows the most recent 500 arrival estimated in a table view. This part of the web application is the presentation of the possibility to query the database by the user.

The table can be sorted by clicking on the column headers. By default, it is sorted by `entryId`, ascending. The column that the table is sorted by is indicated by being bolder and having a caret pointing up or down, depending on the direction of the sorting. This direction can be changed by clicking on the column header once again.

#### All

In the default view, the most recent 500 arrivals are shown. The menu at the top shows options to choose to refine the displayed table to show only entries about one bus line.

![all_arrivals](/assets/all_arrivals.png)

#### One Line

In the view showing the historical arrival estimates for one line, the API is queried for the most recent 500 arrival estimates for this line. A button can be clicked to go to the page displaying all historical arrival estimates.

![line381_arrivals](/assets/line381_arrivals.png)

# Possible Improvements

There is a lot of room for improvement in this web application. This section describes possible improvements if time for development was not limited.

## TDD

Automated tests should be implemented using testing frameworks for both the back-end and the front-end. This would make the code more robust and resilient, while also preventing accidents such as deploying the web application with a minor bug without noticing.

Testing has not been implemented as a compromise to implement as much as possible, as quickly as possible, including one of the bonus tasks. With more time given, the tests would have been written as the first step of the development process.

## Database 3NF

The current database schema is in 1NF, creating a lot of redundancy, repeated data, and allowing for anomalies.

With more time, the database would have been designed to follow the requirements of the Third Normal Form.

This database design also means that a lot of repeated data is sent in each API call. Restructuring the API responses would be easy with a 3NF database. The front-end could make many tiny API calls instead of few huge ones.

The overall 3NF schema that could be implemented:

### Estimate
- EntryID [Primary key]
- CallID [Foreign key -> API Call] (all estimates received from the same call would have the same CallID)
- Expected time of arrival
- Vehicle ID [Foreign key -> Vehicle]
- Line ID [Foreign key -> Line] (the line name and destination)

### API Call
- CallID [Primary key]
- Time when the estimates were received from the TfL API

### Vehicle
- Vehicle ID [Primary key]
- Mode (bus/train/boat according to the TfL API Documentation - under `modeName` in the API response)

### Line
- Line ID [Primary key]
- Line name
- API lineId (the `lineId` as in the TfL API response)
- destination (last stop of the line)


## Pagination of Responses

Currently, to not make the API responses too big and overwhelm the front-end table, the responses of historical arrival estimates are limited to 500 most recent entries. 

Instead, all the sorting (now done in the front-end table) should be done by the back-end and the responses should be sent in pages, which should be reflected in the front-end.

## Timezones

A more robust and reliable way of dealing with timezones should be implemented both in the back-end and the front-end. An example where that is necessary is in the `TflApiCallerConfiguration.java` class.