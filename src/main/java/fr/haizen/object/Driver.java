package fr.haizen.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {

    private String driverName, city;
    private boolean onCourse;
    private double balance;

    public Driver(String driverName, String city) {
        this.driverName = driverName;
        this.city = city;
        this.onCourse = false;
        this.balance = 0;
    }

    public Driver(String driverName, String city, boolean onCourse) {
        this(driverName, city);
        this.onCourse = onCourse;
    }

    public void paid(double balance) {
        this.balance += balance;
    }
}
