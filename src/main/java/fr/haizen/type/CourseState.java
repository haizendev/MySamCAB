package fr.haizen.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CourseState {

    WAITING("En attente", ""), AFFECTED("Affecté", "/api/trip/accept?tripId=XXX"), IN_PROGRESS("En cours", "/api/trip/start?tripId=XXX"), FINISHED("Terminée", "/api/trip/end?tripId=XXX");

    private String state;
    private String restEndpoint;

}
