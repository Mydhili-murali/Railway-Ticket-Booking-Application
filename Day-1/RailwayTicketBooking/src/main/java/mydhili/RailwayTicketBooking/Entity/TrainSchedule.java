package mydhili.RailwayTicketBooking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class TrainSchedule {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String station;
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private Time departingTime;
    @Column(nullable = false)
    private Time arrivalTime;

    public TrainSchedule() {
    }

    public TrainSchedule( String station, String day, Time departingTime, Time arrivalTime) {
        this.station= station;
        this.day = day;
        this.departingTime = departingTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getDepartingTime() {
        return departingTime;
    }

    public void setDepartingTime(Time departingTime) {
        this.departingTime = departingTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
