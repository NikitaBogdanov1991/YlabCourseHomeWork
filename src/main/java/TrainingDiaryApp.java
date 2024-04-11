import model.User;
import model.Workout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TrainingDiaryApp {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) throws ParseException {

        User admin = new User("admin", "admin");
        users.put("admin", admin);

        User ivan = new User("ivan", "ivanov");
        users.put("ivan", ivan);
        Workout workout1 = new Workout("running",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.04.07 12:00:00"),
                60, 200, "distance = 10 km");
        Workout workout2 = new Workout("swimming",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.04.07 18:00:00"),
                30, 300, "number of pools swum = 30 km");
        Workout workout3 = new Workout("yoga",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.04.10 16:00:00"),
                40, 150);
        Workout workout4 = new Workout("cardio",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.03.30 17:00:00"),
                40, 150);
        ivan.addWorkout(workout1);
        ivan.addWorkout(workout2);
        ivan.addWorkout(workout3);
        ivan.addWorkout(workout4);


        User petr = new User("petr", "petrov");
        users.put("petr", petr);
        workout1 = new Workout("running",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.03.10 12:00:00"),
                120, 400, "distance = 20 km");
        workout2 = new Workout("swimming",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.03.20 18:00:00"),
                30, 300, "number of pools swum = 30 km");
        workout3 = new Workout("yoga",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.04.05 17:00:00"),
                60, 150);
        workout4 = new Workout("cardio",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2024.04.08 17:00:00"),
                40, 150);
        petr.addWorkout(workout1);
        petr.addWorkout(workout2);
        petr.addWorkout(workout3);
        petr.addWorkout(workout4);



        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Training Diary App!");

        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("--------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using Training Diary App!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("User already exists. Please try again.");
        } else {
            User user = new User(username, password);
            users.put(username, user);
            System.out.println("User registered successfully. Please login.");
        }
    }

    private static void login(Scanner scanner) throws ParseException {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            currentUser = users.get(username);
            System.out.println("Login successful. Welcome, " + username + "!");
            if (currentUser.getUsername().equals("admin")) {
                showMenuForAdmin(scanner);
            } else {
                showMenu(scanner);
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void showMenuForAdmin(Scanner scanner) {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1. View all workouts");
            System.out.println("2. Logout");
            System.out.println("--------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllWorkouts();
                    break;
                case 2:
                    currentUser = null;
                    System.out.println("Logout successful. Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }


    private static void viewAllWorkouts() {
        for (User user : users.values()) {
            List<Workout> workouts = user.getWorkouts();
            if (user.getUsername().equals("admin")) {
                continue;
            } else {
                if (workouts.isEmpty()) {
                    System.out.println(user.getUsername() + " has no workouts.");
                } else {
                    Collections.sort(workouts, Comparator.comparing(Workout::getDate));
                    System.out.println(user.getUsername() + "'s workouts:");
                    for (Workout workout : workouts) {
                        System.out.println(workout);
                    }
                }
            }
        }
    }


    private static void showMenu(Scanner scanner) throws ParseException {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1. Add new workout");
            System.out.println("2. View previous workouts");
            System.out.println("3. Edit or delete workout");
            System.out.println("4. Get workout statistics");
            System.out.println("5. Logout");
            System.out.println("--------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addWorkout(scanner);
                    break;
                case 2:
                    viewWorkouts();
                    break;
                case 3:
                    editOrDeleteWorkout(scanner);
                    break;
                case 4:
                    getWorkoutStatistics();
                    break;
                case 5:
                    currentUser = null;
                    System.out.println("Logout successful. Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addWorkout(Scanner scanner) {
        System.out.println("Enter workout type:");
        String type = scanner.nextLine();

        Date date = null;
        try {
            System.out.println("Enter workout date in format 'yyyy.MM.dd HH:mm:ss':");
            String dateString = scanner.nextLine();


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            dateFormat.setLenient(false);

            date = dateFormat.parse(dateString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1; // Месяцы в Calendar начинаются с 0
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);

            if (year < 1900 || year > 2100 || month < 1 || month > 12 || day < 1 || day > 31 ||
                    hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
                throw new ParseException("Invalid date", 0);
            } else if (year > Calendar.getInstance().get(Calendar.YEAR) ||
                    month > Calendar.getInstance().get(Calendar.MONTH) ||
                    day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                System.out.println("Entered date is in the future. Please try again.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        if (currentUser.hasWorkoutTypeForDate(type, date)) {
            System.out.println("You have already added a workout of type " + type + " for this date.");
            return;
        }

        System.out.println("Enter workout duration:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter number of calories burned:");
        int calories = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter additional information:");
        String additionalInformation = scanner.nextLine();

        Workout workout = new Workout(type, date, duration, calories, additionalInformation);
        currentUser.addWorkout(workout);
        System.out.println("Workout added successfully.");
    }

    private static void viewWorkouts() {
        List<Workout> workouts = currentUser.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts found for this user.");
        } else {
            Collections.sort(workouts, Comparator.comparing(Workout::getDate));
            System.out.println("Previous workouts:");
            for (Workout workout : workouts) {
                System.out.println(workout);
            }
        }
    }

    private static void editOrDeleteWorkout(Scanner scanner) throws ParseException {
        List<Workout> workouts = currentUser.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts found for this user.");
        } else {
            System.out.println("Select a workout to edit or delete:");
            for (int i = 0; i < workouts.size(); i++) {
                System.out.println((i + 1) + ". " + workouts.get(i));
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= workouts.size()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Select an option:");
                System.out.println("1. Edit workout");
                System.out.println("2. Delete workout");
                System.out.println("--------------------------------------------------");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        editWorkout(scanner, workouts.get(choice - 1));
                        break;
                    case 2:
                        deleteWorkout(workouts.get(choice - 1));
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid workout choice. Please try again.");
            }
        }
    }

    private static void editWorkout(Scanner scanner, Workout workout) throws ParseException {
        System.out.println("Enter new workout type:");
        String type = scanner.nextLine();

        Date date = null;
        try {
            System.out.println("Enter workout date in format 'yyyy.MM.dd HH:mm:ss':");
            String dateString = scanner.nextLine();


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            dateFormat.setLenient(false);

            date = dateFormat.parse(dateString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1; // Месяцы в Calendar начинаются с 0
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);

            if (year < 1900 || year > 2100 || month < 1 || month > 12 || day < 1 || day > 31 ||
                    hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
                throw new ParseException("Invalid date", 0);
            } else if (year > Calendar.getInstance().get(Calendar.YEAR) ||
                    month > Calendar.getInstance().get(Calendar.MONTH) ||
                    day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                System.out.println("Entered date is in the future. Please try again.");
                return;
            }

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        System.out.println("Enter new workout duration:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new number of calories burned:");
        int calories = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new additional information:");
        String additionalInformation = scanner.nextLine();

        workout.setType(type);
        workout.setDate(date);
        workout.setDuration(duration);
        workout.setCalories(calories);
        workout.setAdditionalInformation(additionalInformation);

        System.out.println("Workout edited successfully.");
    }

    private static void deleteWorkout(Workout workout) {
        currentUser.deleteWorkout(workout);
        System.out.println("Workout deleted successfully.");
    }

    private static void getWorkoutStatistics() {
        List<Workout> workouts = currentUser.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts found for this user.");
        } else {
            int totalCalories = 0;
            int totalTime = 0;

            for (Workout workout : workouts) {
                totalCalories += workout.getCalories();
                totalTime += workout.getDuration();
            }

            System.out.println("Total calories burned: " + totalCalories);
            System.out.println("Total time spent working out: " + totalTime + " minutes");
        }
    }
}
