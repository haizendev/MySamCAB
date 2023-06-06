package fr.haizen.manager;

import fr.haizen.Application;
import fr.haizen.object.Client;
import fr.haizen.object.Course;
import fr.haizen.object.Driver;
import fr.haizen.type.CourseState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ApplicationManager {

    @Getter
    private static ApplicationManager instance;
    private Application application;
    private List<Course> courses;

    public ApplicationManager() {
        instance = this;
        application = Application.getInstance();
        courses = new ArrayList<>();
    }

    public void needCourse(Client client) {
        if (client.getDriver() != null) {
            System.out.println("Le client " + client.getClientName() + " a déjà un chauffeur.");
            return;
        }

        if (client.getCredit() < this.application.getDriveCost()) {
            System.out.println("Le client " + client.getClientName() + " n'a pas assez de crédit.");
            return;
        }

        Course course = new Course(UUID.randomUUID(), client, null, CourseState.WAITING, null);
        this.courses.add(course);

        System.out.println("Un chauffeur va vous être affecté.");
        this.findDriver(client, course);
    }

    public void findDriver(Client client, Course course) {
        List<Driver> foundedDriver = new ArrayList<>();
        for (Driver driver : this.application.getDrivers()) {
            if (driver.isOnCourse()) continue;
            if (!driver.getCity().equalsIgnoreCase(client.getCity())) continue;
            foundedDriver.add(driver);
        }

        if (foundedDriver.isEmpty()) {
            System.out.println("Désolé, aucun chauffeur n'est disponible.");
            return;
        }

        Driver driver = foundedDriver.get(0);
        driver.setOnCourse(true);

        client.setDriver(driver);
        client.setNeedCourse(false);

        course.setDriver(driver);
        course.setCourseState(CourseState.AFFECTED);

        System.out.println("Le client " + client.getClientName() + " a été affecté au chauffeur " + driver.getDriverName() + ".");

        // START COURSE
        this.startDrive(client, course);
    }

    public void startDrive(Client client, Course course) {
        if (course.getCourseState() != CourseState.AFFECTED) {
            System.out.println("Le client " + client.getClientName() + " n'a pas de chauffeur.");
            return;
        }

        course.setCourseState(CourseState.IN_PROGRESS);
        System.out.println("Le client " + client.getClientName() + " est en cours de trajet.");

        // FINISH COURSE
        this.onDestination(client, course);
    }

    public void onDestination(Client client, Course course) {
        if (course.getCourseState() != CourseState.IN_PROGRESS) {
            System.out.println("Le client " + client.getClientName() + " n'est pas en cours de trajet.");
            return;
        }

        course.setCourseState(CourseState.FINISHED);
        System.out.println("Le client " + client.getClientName() + " est arrivé à destination.");

        Driver driver = course.getDriver();
        driver.setOnCourse(false);

        client.setDriver(null);

        // START TRANSACTION
        this.application.startTransaction(course);
    }

    // NEVER USED
    private Course getCourse(Client client) {
        for (Course course : this.courses)
            if (course.getClient().getClientName().equalsIgnoreCase(client.getClientName())) return course;
        return null;
    }
}
