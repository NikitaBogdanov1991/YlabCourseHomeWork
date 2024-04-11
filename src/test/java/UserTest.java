import org.junit.Test;
import model.User;
import model.Workout;

import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testAddWorkout() {
        User user = new User("testUser", "password");
        Workout workout = new Workout("test", new Date(), 60, 200, "Test workout");

        user.addWorkout(workout);

        assertTrue(user.getWorkouts().contains(workout));
    }

    @Test
    public void testDeleteWorkout() {
        User user = new User("testUser", "password");
        Workout workout = new Workout("test", new Date(), 60, 200, "Test workout");

        user.addWorkout(workout);
        user.deleteWorkout(workout);

        assertFalse(user.getWorkouts().contains(workout));
    }

    @Test
    public void testHasWorkoutTypeForDate() {
        User user = new User("testUser", "password");
        Workout workout = new Workout("test", new Date(), 60, 200, "Test workout");

        user.addWorkout(workout);

        assertTrue(user.hasWorkoutTypeForDate("test", new Date()));
        assertFalse(user.hasWorkoutTypeForDate("test", new Date(0)));
    }
}