package fr.haizen;

import fr.haizen.manager.ApplicationManager;
import fr.haizen.object.Client;
import fr.haizen.object.Course;
import fr.haizen.object.Driver;
import fr.haizen.type.CourseState;
import fr.haizen.type.TransactionState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Application {

    @Getter
    private static Application instance;

    private ApplicationManager applicationManager;
    private List<Driver> drivers;
    private List<Client> clients;

    private double driveCost, applicationBalance;

    public Application() {
        instance = this;

        applicationManager = new ApplicationManager();
        drivers = this.initDrivers(); // FOR THE TEST...
        clients = this.initClients(); // FOR THE TEST...

        driveCost = 20;
    }

    public void start() {
        Random random = new Random();
        int randomIndex = random.nextInt(this.getClients().size());

        Client client = this.getClients().get(randomIndex);
        this.applicationManager.needCourse(client);
    }

    public void startTransaction(Course course) {
        if (course.getCourseState() != CourseState.FINISHED) {
            System.out.println("La course n'est pas terminée.");
            return;
        }

        course.setTransactionState(TransactionState.CLIENT);

        Client client = course.getClient();
        if (client.getCredit() < driveCost) {
            System.out.println("Le client " + client.getClientName() + " n'a pas assez de crédit.");
            return;
        }

        // DEBIT CLIENT
        client.setCredit(client.getCredit() - driveCost);

        // PAID DRIVER
        Driver driver = course.getDriver();
        double cost = driveCost * (100 - TransactionState.DRIVER.getPourcentageValue()) / 100;
        driver.paid(cost);
        System.out.println("Le chauffeur " + driver.getDriverName() + " a été payé.");

        // PAID APPLICATION
        double applicationCost = driveCost * (100 - TransactionState.APPLICATION.getPourcentageValue()) / 100;
        applicationBalance += applicationCost;
        System.out.println("La majoration de l'application à été récupéré pour la course " + course.getCourseID() + ".");

        this.applicationManager.getCourses().remove(course);
        System.out.println("La course " + course.getCourseID() + " a été supprimé.");
    }

    // FOR THE TEST...
    public List<Driver> initDrivers() {
        List<Driver> temporaryDrivers = new ArrayList<>();
        temporaryDrivers.add(new Driver("Jean", "Toulouse"));
        temporaryDrivers.add(new Driver("Pierre", "Toulouse"));
        temporaryDrivers.add(new Driver("Paul", "Lyon"));
        temporaryDrivers.add(new Driver("Jacques", "Paris"));
        temporaryDrivers.add(new Driver("Jeanne", "Lyon", true));
        return temporaryDrivers;
    }

    public List<Client> initClients() {
        List<Client> temporaryClients = new ArrayList<>();
        temporaryClients.add(new Client("Jean", "Toulouse", 100));
        temporaryClients.add(new Client("Pierre", "Toulouse", 50));
        temporaryClients.add(new Client("Paul", "Toulouse", 20));
        temporaryClients.add(new Client("Jacques", "Toulouse", 10));
        temporaryClients.add(new Client("Jeanne", "Toulouse", 5));
        return temporaryClients;
    }
}
