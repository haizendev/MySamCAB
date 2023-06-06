package fr.haizen.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private String clientName, city;
    private double credit;
    private boolean needCourse;
    private Driver driver;

    public Client(String clientName, String city, double credit) {
        this.clientName = clientName;
        this.city = city;
        this.credit = credit;
    }
}
