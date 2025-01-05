import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class SolarSystemRunner {
    static final String SIMULATION_NAME = "Solar System";
    static final String SAVED_SOLAR_SYSTEM_PATH = "SolarSystems/";
    static final String IMAGES_PATH = "Images/";
    static final String SIMULATION_ICON_PATH = "Images/simulationIcon.png";
    static final int DEFAULT_SIMULATION_WIDTH = 700;
    static final int DEFAULT_SIMULATION_HEIGHT = 700;
    static final Scanner READER = new Scanner(System.in);
    static SolarSystem solarSystem = null;
    static Simulation simulation = new Simulation(SIMULATION_NAME, SIMULATION_ICON_PATH, DEFAULT_SIMULATION_WIDTH,
            DEFAULT_SIMULATION_HEIGHT);

    // throwing InterruptedException is needed for calling Thread.sleep to pause the program
    public static void mainMenu() throws InterruptedException {
        boolean running = true;

        int choice;

        while (running){
            // if solarSystem == null a solar system has not been loaded so load a solar system first
            if (solarSystem == null){
                solarSystem = new SolarSystem();
                System.out.println("There is no file loaded!");

                loadSolarSystem();
            } else {
                System.out.println();
                System.out.println("--- Main Menu ---");
                System.out.println();
                System.out.println("Choose an option from the menu:");
                System.out.println("1 Load Solar System from file");
                System.out.println("2 Save Solar System to file");
                System.out.println("3 Manage Solar System");
                System.out.println("4 Manage Simulation");
                System.out.println("0 Exit");

                choice = getInt("> ", 0, 4);

                switch (choice){
                    case 0:
                        running = false;
                        break;

                    case 1:
                        loadSolarSystem();
                        break;

                    case 2:
                        saveSolarSystem();
                        break;

                    case 3:
                        manageSolarSystemMenu();
                        break;

                    case 4:
                        manageSimulationMenu();
                        break;

                }
            }
        }
    }

    public static void loadSolarSystem(){

        String fileName = getImagePath(SAVED_SOLAR_SYSTEM_PATH, "Enter a filename to load (default location " + SAVED_SOLAR_SYSTEM_PATH + "): ");
        solarSystem.loadSolarSystem(fileName);

        System.out.println("Success!");
    }

    public static void saveSolarSystem(){

        System.out.println();

        System.out.print("Enter a filename to save the current solar system to (default location " + SAVED_SOLAR_SYSTEM_PATH + "): ");
        String fileName = READER.nextLine();

        solarSystem.saveSolarSystem(SAVED_SOLAR_SYSTEM_PATH + fileName);

        System.out.println("Success!");
    }

    public static void manageSolarSystemMenu(){
        boolean running = true;

        while (running){
            System.out.println();
            System.out.println("--- Manage Solar System Menu ---");
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1 List all celestial bodies");
            System.out.println("2 Print information for all celestial bodies");
            System.out.println("3 Print information for a celestial body");
            System.out.println("4 Add a planet");
            System.out.println("5 Remove a planet");
            System.out.println("6 Edit Celestial body name");
            System.out.println("7 Edit Celestial body mass");
            System.out.println("8 Edit Celestial body radius");
            System.out.println("9 Edit Celestial body image path");
            System.out.println("10 Edit Celestial body display priority");
            System.out.println("11 Edit a planet’s type");
            System.out.println("12 Edit the sun’s luminosity");
            System.out.println("0 Exit");

            int choice = getInt("> ", 0, 12);

            switch (choice){
                case 0:
                    running = false;
                    break;

                case 1:
                    listAllCelestialBodies();
                    break;

                case 2:
                    printAllCelestialBodiesInformation();
                    break;

                case 3:
                    printCelestialBodyInformation();
                    break;

                case 4:
                    addPlanet();
                    break;

                case 5:
                    removePlanet();
                    break;

                case 6:
                    editCelestialBodyName();
                    break;

                case 7:
                    editCelestialBodyMass();
                    break;

                case 8:
                    editCelestialBodyRadius();
                    break;

                case 9:
                    editCelestialBodyImagePath();
                    break;

                case 10:
                    editCelestialBodyDisplayPriority();
                    break;

                case 11:
                    editPlanetType();
                    break;

                case 12:
                    editSunLuminosity();
                    break;
            }
        }
    }

    public static void listAllCelestialBodies(){
        System.out.println();
        solarSystem.listAllCelestialBodies();

        waiting();
    }

    public static void printAllCelestialBodiesInformation(){
        solarSystem.printAllCelestialBodiesInformation();

        waiting();
    }

    public static void printCelestialBodyInformation(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        solarSystem.printCelestialBodyInformation(celestialBodyIndex);

        waiting();
    }

    public static void addPlanet(){
        System.out.println();
        // prompt the user to enter all information required for a planet
        System.out.print("Name of planet: ");
        String name = READER.nextLine();

        String imagePath = getImagePath(IMAGES_PATH, "Image name (default location " + IMAGES_PATH + "): ");

        double mass = getDouble("Mass of planet: ", 0, Double.MAX_VALUE);

        double radius = getDouble("Radius of planet: ", 0, Double.MAX_VALUE);

        int displayPriority = getInt("Display priority: ", 0, Integer.MAX_VALUE);

        System.out.println();
        System.out.print("Type of planet: ");
        String type = READER.nextLine();

        double xDisplacement = getDouble("Current x displacement (m): ", -Double.MAX_VALUE, Double.MAX_VALUE);

        double yDisplacement = getDouble("Current y displacement (m): ", -Double.MAX_VALUE, Double.MAX_VALUE);

        double xVelocity = getDouble("Current x velocity (m/s): ", -Double.MAX_VALUE, Double.MAX_VALUE);

        double yVelocity = getDouble("Current y velocity (m/s): ", -Double.MAX_VALUE, Double.MAX_VALUE);

        solarSystem.addPlanet(name, mass, radius, imagePath, displayPriority, xDisplacement, yDisplacement, type
                ,xVelocity, yVelocity);

        System.out.println("Planet had been added!");

        waiting();
    }

    public static void removePlanet(){
        int planetIndex = selectPlanet();

        System.out.println();

        solarSystem.removeCelestialBody(planetIndex);

        System.out.println("Planet has been removed!");

        waiting();
    }

    public static void editCelestialBodyName(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        System.out.println("Current name: " + solarSystem.getCelestialBodyName(celestialBodyIndex));

        System.out.println();
        System.out.print("New name: ");
        String name = READER.nextLine();

        solarSystem.setCelestialBodyName(celestialBodyIndex, name);
    }

    public static void editCelestialBodyMass(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        System.out.println("Current mass: " + solarSystem.getCelestialBodyMass(celestialBodyIndex));
        double mass = getDouble("New mass: ", 0, Double.MAX_VALUE);

        solarSystem.setCelestialBodyMass(celestialBodyIndex, mass);
    }

    public static void editCelestialBodyRadius(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        System.out.println("Current radius: " + solarSystem.getCelestialBodyRadius(celestialBodyIndex));
        double radius = getDouble("New radius: ", 0, Double.MAX_VALUE);

        solarSystem.setCelestialBodyRadius(celestialBodyIndex, radius);
    }

    public static void editCelestialBodyImagePath(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        System.out.println("Current image path: " + solarSystem.getCelestialBodyImagePath(celestialBodyIndex));
        String imagePath = getImagePath(IMAGES_PATH, "New file name (default location:" + IMAGES_PATH + "): ");

        solarSystem.setCelestialBodyImagePath(celestialBodyIndex, imagePath);
    }

    public static void editCelestialBodyDisplayPriority(){
        int celestialBodyIndex = selectCelestialBody();
        System.out.println();

        System.out.println("Current display priority: " + solarSystem.getCelestialBodyDisplayPriority(celestialBodyIndex));
        int displayPriority = getInt("New display priority: ", 0, Integer.MAX_VALUE);

        solarSystem.setCelestialBodyDisplayPriority(celestialBodyIndex, displayPriority);
    }

    public static void editPlanetType(){
        int planetIndex = selectPlanet();
        System.out.println();

        System.out.println("Current type: " + solarSystem.getPlanetType(planetIndex));
        System.out.println();
        System.out.print("New type of planet: ");
        String type = READER.nextLine();

        solarSystem.setPlanetType(planetIndex, type);
    }

    public static void editSunLuminosity(){
        System.out.println();
        System.out.println("Current sun luminosity: " + solarSystem.getSunLuminosity() + " W");
        double luminosity = getDouble("New sun luminosity: ", 0, Double.MAX_VALUE);

        solarSystem.setSunLuminosity(luminosity);

        waiting();
    }

    // throwing InterruptedException is needed for calling Thread.sleep to pause the program
    public static void manageSimulationMenu() throws InterruptedException {
        boolean running = true;

        while (running){
            System.out.println();
            System.out.println("--- Manage Simulation Menu ---");
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1 Print simulation information");
            System.out.println("2 Edit simulation width");
            System.out.println("3 Edit simulation height");
            System.out.println("4 Speed up simulation");
            System.out.println("5 Slow down simulation");
            System.out.println("6 Run simulation");
            System.out.println("0 Exit");

            int choice = getInt("> ", 0, 6);

            switch (choice){
                case 0:
                    running = false;
                    break;

                case 1:
                    printSimulationInformation();
                    break;

                case 2:
                    editSimulationWidth();
                    break;

                case 3:
                    editSimulationHeight();
                    break;

                case 4:
                    speedUpSimulation();
                    break;

                case 5:
                    slowDownSimulation();
                    break;

                case 6:
                    runSimulation();
                    break;
            }
        }
    }

    public static void printSimulationInformation(){
        System.out.println();
        System.out.println("Simulation width: " + simulation.getWidth() + " pixels");
        System.out.println("Simulation height: " + simulation.getHeight() + " pixels");
        System.out.printf("Current scale: %.2f meters per pixel \n", simulation.getDistanceScale());
        System.out.println("Simulation speed: " + simulation.getSpeed());

        waiting();

    }

    public static void editSimulationWidth(){
        System.out.println();
        System.out.println("Current simulation width: " + simulation.getWidth() + " pixels");

        // Using to Toolkit class to find the size of the screen to determine the maximum screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int simulationWidth = getInt("New simulation width: ", 0, screenSize.width);

        simulation.setWidth(simulationWidth);
    }

    public static void editSimulationHeight(){
        System.out.println();
        System.out.println("Current simulation height: " + simulation.getHeight() + " pixels");

        // Using to Toolkit class to find the size of the screen to determine the maximum screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int simulationHeight = getInt("New simulation height: ", 0, screenSize.height);

        simulation.setHeight(simulationHeight);
    }

    public static void speedUpSimulation(){
        System.out.println();

        System.out.println("Current simulation speed: " + simulation.getSpeed());

        // simulation.getMaxSpeed() / simulation.getSpeed() is the maximum factor that the speed can be multiplied by
        int speedScale = getInt("Increase the current speed of the simulation by a factor of: "
        ,0,Integer.MAX_VALUE);

        simulation.setSpeed(simulation.getSpeed() * speedScale);
    }

    public static void slowDownSimulation(){
        System.out.println();

        System.out.println("Current simulation speed: " + simulation.getSpeed());

        // simulation.getSpeed() / simulation.getMinimumSpeed() is the maximum factor that the speed can be divided by
        int speedScale = getInt("Decrease the current speed of the simulation by a factor of: "
                ,0, Integer.MAX_VALUE);

        simulation.setSpeed(simulation.getSpeed() * ((double) 1 / speedScale));
    }

    // throwing InterruptedException is needed for calling Thread.sleep to pause the program
    public static void runSimulation() throws InterruptedException {

        // reset labels and re-add all of them in case celestial bodies were added since the last time the simulation
        // was run

        simulation.resetLabelsAndFrame();

        // sort them by display priority before displaying
        solarSystem.sortCelestialBodiesByDisplayPriority();

        double greatestCelestialBodyRadius = solarSystem.getGreatestCelestialBodyRadius();
        double smallestCelestialBodyRadius = solarSystem.getSmallestCelestialBodyRadius();

        // this buffer will ensure that the furthest celestial body is not displayed on the very edge of the screen
        final double SCALE_BUFFER = 1.5;
        simulation.calculateDistanceScale(solarSystem.getGreatestCelestialBodyDisplacement() * SCALE_BUFFER);

        for (int i = 0; i < solarSystem.getNumberOfCelestialBodies(); i++){
            simulation.addLabel(solarSystem.getCelestialBodyImagePath(i), solarSystem.getCelestialBodyXDisplacement(i),
                    solarSystem.getCelestialBodyYDisplacement(i), solarSystem.getCelestialBodyRadius(i),
                    greatestCelestialBodyRadius, smallestCelestialBodyRadius);
        }

        simulation.displaySimulation();

        int numberOfCalculationsEachStep;
        int waitTimeAfterEachStepInMillis;

        if (simulation.getSpeed() <= 1){
            waitTimeAfterEachStepInMillis = (int) (1 / simulation.getSpeed());
            numberOfCalculationsEachStep = 1;
        } else {
            waitTimeAfterEachStepInMillis = 1;
            numberOfCalculationsEachStep = (int) simulation.getSpeed();
        }

        System.out.println("Close the simulation window to exit the simulation");

        // the time interval in seconds that planets are being moved over, a smaller number will lead to more
        // precise calculations
        final double TIME_INTERVAL_SECONDS = 0.01;

        while (simulation.isDisplayed()){
            for (int i = 0; i < numberOfCalculationsEachStep; i++) {
                solarSystem.movePlanetsOverTime(TIME_INTERVAL_SECONDS);
            }

            for (int i = 0; i < solarSystem.getNumberOfCelestialBodies(); i++) {
                // the arrayList labels in the simulation class is a parallel arrayList with the celestial bodies array
                // in the solar system class

                // doing everything by index means that we do not need to do a linear search every time we are
                // updating the position of a celestial body
                simulation.updateLabelPosition(i, solarSystem.getCelestialBodyXDisplacement(i),
                        solarSystem.getCelestialBodyYDisplacement(i), solarSystem.getCelestialBodyRadius(i),
                        greatestCelestialBodyRadius, smallestCelestialBodyRadius);

            }

            // pauses the program for waitTimeAfterEachStepInMillis milliseconds
            Thread.sleep(waitTimeAfterEachStepInMillis);
        }
    }

    public static int selectCelestialBody(){
        while (true) {
            System.out.println();
            System.out.println("Select a Celestial Body by entering its number, name, or image path:");

            solarSystem.printNumberedCelestialBodies();

            System.out.println();
            System.out.print("> ");
            String input = READER.nextLine();

            int indexOfCelestialBody = solarSystem.getIndexOfCelestialBody(input);

            // check if the user entered the name or image path of a celestial body, if the index is -1 they did not
            // enter a valid name or image path
            if (indexOfCelestialBody != -1){
                return indexOfCelestialBody;
            }

            // check if the user selected a celestial body by number (index)
            indexOfCelestialBody = stringToInteger(input);

            // ensure that the number entered is within the appropriate bounds
            if (0 <= indexOfCelestialBody && indexOfCelestialBody <= solarSystem.getNumberOfCelestialBodies() - 1) {
                return indexOfCelestialBody;
            }

            System.out.println("Invalid selection");
            System.out.println();

        }
    }

    public static int selectPlanet(){
        int indexOfPlanet;
        String input;

        while (true){
            System.out.println();
            System.out.println("Select a planet by entering its number, name, or image path:");

            solarSystem.printNumberedPlanets();

            System.out.println();
            System.out.print("> ");
            input = READER.nextLine();

            indexOfPlanet  = solarSystem.getIndexOfCelestialBody(input);

            // check if the user entered the name or image path of a planet, ensure that the name or image path of the
            // sun was not entered
            if (indexOfPlanet != -1 && indexOfPlanet != solarSystem.getSunIndex()){
                return indexOfPlanet;
            }

            // check if the user selected a planet by number (index)
            indexOfPlanet = stringToInteger(input);

            // if the planet is after the sun in the arrayList of celestial bodies, increase planet's index by one since
            // the user would have entered an index one lower since they were only selecting from planets
            if (indexOfPlanet >= solarSystem.getSunIndex()){
                indexOfPlanet++;
            }

            // ensure that the number entered is within the appropriate bounds
            if (0 <= indexOfPlanet && indexOfPlanet <= solarSystem.getNumberOfPlanets()){
                return indexOfPlanet;
            }

            System.out.println("Invalid selection");
        }
    }

    public static int getInt(String prompt, int lowerBoundInclusive, int upperBoundInclusive){
        while (true){
            System.out.println();
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(READER.nextLine());

                // ensure that the input is within the specified bounds
                if (lowerBoundInclusive <= input && input <= upperBoundInclusive){
                    return input;
                } else {
                    System.out.println("Integer entered must be between " + lowerBoundInclusive + " and " + upperBoundInclusive
                    + " inclusive");

                }
                // if the input is not an integer
            } catch (NumberFormatException nef){
                System.out.println("Input must be an integer");

            }
        }
    }

    public static double getDouble(String prompt, double lowerBoundInclusive, double upperBoundInclusive){
        while (true){
            System.out.println();
            System.out.print(prompt);

            try {
                double input = Double.parseDouble(READER.nextLine());

                // ensure that the input is within the specified bounds
                if (lowerBoundInclusive <= input && input <= upperBoundInclusive){
                    return input;
                } else {
                    System.out.println("Double entered must be between " + lowerBoundInclusive + " and " + upperBoundInclusive
                            + " inclusive");

                }
                // if the input is not a double
            } catch (NumberFormatException nef){
                System.out.println("Input must be a double");

            }
        }
    }

    public static int stringToInteger(String s){
        // attempt to convert the string s to an integer
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe){
            // if converting fails return -1
            return -1;
        }
    }

    public static String getImagePath(String defaultLocation, String prompt){
        String imagePath = "";
        String fileName;
        File file = new File(imagePath);

        // file.exists() will return true if the file exists in the system
        // this lets us verify input for filenames without opening them first which is needed for image files
        while (!file.exists()){
            System.out.println();
            System.out.print(prompt);
            // assuming that the user enters the name of a file in the default location, this should be specified
            // in the prompt

            fileName = READER.nextLine();

            // the file will exist if the fileName is empty (since the folder where the files are stored exists)
            // this ensures that it will prompt the user again if they press enter without typing any text
            if (!fileName.isEmpty()){
                imagePath = defaultLocation + fileName;

                file = new File(imagePath);
            }

            if (!file.exists()){
                System.out.println("Invalid file name");
            }
        }

        return imagePath;
    }

    public static void waiting(){
        // this is used after printing out information so the user has time to read it
        System.out.println();
        System.out.print("press enter to proceed... ");
        READER.nextLine();
    }

    // throwing InterruptedException is needed for calling Thread.sleep to pause the program
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- SOLAR SYSTEM SIMULATION ---");
        System.out.println();
        mainMenu();
        System.out.println();
        System.out.println("Thank you for using SOLAR SYSTEM SIMULATION");
        System.out.println("Have a good day!");
    }
}
