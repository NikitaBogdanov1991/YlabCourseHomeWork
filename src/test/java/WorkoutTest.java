import org.junit.Test;
import model.Workout;

import static org.junit.Assert.*;

import java.util.Date;

public class WorkoutTest {

    @Test
    public void testWorkoutCreation() {
        Workout workout = new Workout("test", new Date(), 60, 200, "Test workout");

        assertEquals("test", workout.getType());
        assertEquals(60, workout.getDuration());
        assertEquals(200, workout.getCalories());
        assertEquals("Test workout", workout.getAdditionalInformation());
    }

    @Test
    public void testSetters() {
        Workout workout = new Workout("test", new Date(), 60, 200, "Test workout");

        workout.setType("new");
        workout.setDuration(45);
        workout.setCalories(150);
        workout.setAdditionalInformation("New workout");

        assertEquals("new", workout.getType());
        assertEquals(45, workout.getDuration());
        assertEquals(150, workout.getCalories());
        assertEquals("New workout", workout.getAdditionalInformation());
    }
}