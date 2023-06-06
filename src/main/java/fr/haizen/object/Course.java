package fr.haizen.object;

import fr.haizen.type.CourseState;
import fr.haizen.type.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Course {

    private UUID courseID;
    private Client client;
    private Driver driver;
    private CourseState courseState;
    private TransactionState transactionState;

    public void setCourseState(CourseState courseState) {
        this.courseState = courseState;
        //Utils.handleRest(courseState, this.getCourseID()); // WORKED, BUT THE ENDPOINT DOESN'T EXIST, DODGE THE ERRORS :)
    }
}
