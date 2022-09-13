package com.jsmi.tflapp.arrivals;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class ArrivalEntry {
    // This class holds an entry of a bus arrival prediction received from the TfL API.
    // There can be many entries for one bus arrival prediction. This is so that the database holds a chronological
    // record of all arrival predictions received from the TfL API.

    // The bus arrival prediction entry is uniquely identified by the Long entryId. This field is also the primary key
    // in the MySQL database table of the entries. It identifies a record of an arrival prediction at a specific
    // point in time.
    // The bus arrival prediction (which can have many entries in the database table) is identified by
    // the Long arrivalId taken from the API response itself, and it identifies a specific bus arrival.


    // The id of the entry in the database
    @Id
    @SequenceGenerator(
            name = "arrival_entry_sequence",
            sequenceName = "arrival_entry_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "arrival_entry_sequence"
    )
    private Long entryId;

    // Time when added to the database
    private LocalDateTime timestamp;

    // The id of the bus arrival (in the TFL API)
    private Long id;

    // The following are values directly from an API response
    private Integer operationType;
    private String vehicleId;
    private String naptanId;
    private String stationName;
    private String lineId;
    private String lineName;
    private String platformName;
    private String direction;
    private String bearing;
    private String destinationNaptanId;
    private String destinationName;
    private Integer timeToStation;
    private String currentLocation;
    private String towards;
    private LocalDateTime expectedArrival;
    private LocalDateTime timeToLive;
    private String modeName;

    public ArrivalEntry(Long id, Integer operationType, String vehicleId, String naptanId, String stationName,
                        String lineId, String lineName, String platformName, String direction, String bearing,
                        String destinationNaptanId, String destinationName, LocalDateTime timestamp,
                        Integer timeToStation, String currentLocation, String towards, LocalDateTime expectedArrival,
                        LocalDateTime timeToLive, String modeName) {
        this.id = id;
        this.operationType = operationType;
        this.vehicleId = vehicleId;
        this.naptanId = naptanId;
        this.stationName = stationName;
        this.lineId = lineId;
        this.lineName = lineName;
        this.platformName = platformName;
        this.direction = direction;
        this.bearing = bearing;
        this.destinationNaptanId = destinationNaptanId;
        this.destinationName = destinationName;
        this.timestamp = timestamp;
        this.timeToStation = timeToStation;
        this.currentLocation = currentLocation;
        this.towards = towards;
        this.expectedArrival = expectedArrival;
        this.timeToLive = timeToLive;
        this.modeName = modeName;
    }

    public ArrivalEntry() {
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getArrivalId() {
        return id;
    }

    public void setArrivalId(Long id) {
        this.id = id;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNaptanId() {
        return naptanId;
    }

    public void setNaptanId(String naptanId) {
        this.naptanId = naptanId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getDestinationNaptanId() {
        return destinationNaptanId;
    }

    public void setDestinationNaptanId(String destinationNaptanId) {
        this.destinationNaptanId = destinationNaptanId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTimeToStation() {
        return timeToStation;
    }

    public void setTimeToStation(Integer timeToStation) {
        this.timeToStation = timeToStation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }

    public LocalDateTime getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(LocalDateTime expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public LocalDateTime getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(LocalDateTime timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    @Override
    public String toString() {
        return "BusArrivalEntry{" +
                "entryId=" + entryId +
                ", arrivalId=" + id +
                ", operationType=" + operationType +
                ", vehicleId='" + vehicleId + '\'' +
                ", naptanId='" + naptanId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", direction='" + direction + '\'' +
                ", bearing='" + bearing + '\'' +
                ", destinationNaptanId='" + destinationNaptanId + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", timestamp=" + timestamp +
                ", timeToStation=" + timeToStation +
                ", currentLocation='" + currentLocation + '\'' +
                ", towards='" + towards + '\'' +
                ", expectedArrival=" + expectedArrival +
                ", timeToLive=" + timeToLive +
                ", modeName='" + modeName + '\'' +
                '}';
    }
}
