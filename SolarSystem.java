import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines a solar system as an ArrayList of celestial bodies including only one Sun.
 * Operations include adding and removing celestial bodies, modifying celestial bodies, printing a celestial body,
 * printing all celestial bodies, calculating the net force of gravity on a celestial body, and moving celestial bodies
 * over a time interval.
 */
public class SolarSystem {
    private ArrayList<CelestialBody> celestialBodies;

    /**
     * Creates a new Solar System
     *
     * @return None
     */
    public SolarSystem(){
        celestialBodies = new ArrayList<>();
    }

    /**
     * Gets the index of a celestial body given its name or image path
     *
     * @param nameOrImagePath the name or image path of a celestial body
     * @return the index of a celestial body with the specified name or image path or -1 if there is no celestial body
     * with the name or image path
     */
    public int getIndexOfCelestialBody(String nameOrImagePath){
        int index = -1;

        for (int i = 0; i < celestialBodies.size(); i++){
            // checks if any celestial body has the same name or image path
            // assumes that all celestial bodies have a unique name and image path
            if (celestialBodies.get(i).getName().equals(nameOrImagePath) ||
                    celestialBodies.get(i).getImagePath().equals(nameOrImagePath)){
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Adds a planet to the solar system
     *
     * @param name The name of the planet
     * @param mass The mass of the planet
     * @param radius The radius of the planet
     * @param imagePath The image path for a planet
     * @param displayPriority The display priority of a planet
     * @param xDisplacement The x-displacement for a planet
     * @param yDisplacement The y-displacement for a planet
     * @param type The type of planet
     * @param xVelocity The x-velocity of a planet
     * @param yVelocity The y-velocity of a planet
     * @return None
     */
    public void addPlanet(String name, double mass, double radius, String imagePath, int displayPriority,
                          double xDisplacement, double yDisplacement, String type, double xVelocity, double yVelocity) {
        celestialBodies.add(new Planet(name, mass, radius, imagePath, displayPriority, xDisplacement, yDisplacement,
                type, xVelocity, yVelocity));
    }

    /**
     * Adds a sun to the solar system
     *
     * @param name The name of the sun
     * @param mass The mass of the sun
     * @param radius The radius of the sun
     * @param imagePath The image path for a sun
     * @param displayPriority The display priority of a sun
     * @param xDisplacement The x-displacement for a sun
     * @param yDisplacement The y-displacement for a sun
     * @param luminosity The luminosity of the sun
     * @return None
     */
    public void addSun(String name, double mass, double radius, String imagePath, int displayPriority,
                       double xDisplacement, double yDisplacement, double luminosity){
        celestialBodies.add(new Sun(name, mass, radius, imagePath, displayPriority, xDisplacement, yDisplacement,
                luminosity));
    }

    /**
     * Removes a celestial body from the solar system
     *
     * @param index the index of the celestial body to be removed
     * @return None
     */
    public void removeCelestialBody(int index){
        celestialBodies.remove(index);
    }

    /**
     * Calculates the number of celestial bodies in the solar system
     *
     * @return the number of celestial bodies in the solar system
     */
    public int getNumberOfCelestialBodies(){
        return celestialBodies.size();
    }

    /**
     * Calculates the number of planets in a solar system
     *
     * @return the number of planets in a solar system
     */
    public int getNumberOfPlanets(){
        int numPlanets = 0;

        // checks if a celestial body is an instance of the planet class
        for (CelestialBody c : celestialBodies){
            if (c instanceof Planet){
                numPlanets++;
            }
        }

        return numPlanets;
    }

    /**
     * Sorts celestial bodies by their display priority
     *
     * @return None
     */
    public void sortCelestialBodiesByDisplayPriority(){
        // since the celestial body class implements the comparable interface based on display priority, they can be
        // sorted used Collections.sort
        Collections.sort(celestialBodies);
    }

    /**
     * Loads a solar system
     *
     * @param filePath the file path of the solar system
     * @return None
     */
    public void loadSolarSystem(String filePath){
        BufferedReader reader;
        String line;

        // reset the celestial bodies in case the user is loading after already having loaded a file
        celestialBodies = new ArrayList<>();

        try{
            reader = new BufferedReader(new FileReader(filePath));
            line = reader.readLine();

            while (line != null){
                // each piece of information will be separated by commas
                // creates an array where each piece of information is an element for easy access
                String[] celestialBodyInformation = line.split(",");

                // the first element determines what type of celestial body is being added
                if (celestialBodyInformation[0].equals("Sun")){
                    // all other information is added according to the file format for a sun
                    celestialBodies.add(new Sun (celestialBodyInformation[1], Double.parseDouble(celestialBodyInformation[2]),
                            Double.parseDouble(celestialBodyInformation[3]), celestialBodyInformation[4],
                            Integer.parseInt(celestialBodyInformation[5]), Double.parseDouble(celestialBodyInformation[6]),
                            Double.parseDouble(celestialBodyInformation[7]), Double.parseDouble(celestialBodyInformation[8])));

                } else if (celestialBodyInformation[0].equals("Planet")){
                    // all other information is added according to the file format for a planet
                    celestialBodies.add(new Planet(celestialBodyInformation[1], Double.parseDouble(celestialBodyInformation[2]),
                            Double.parseDouble(celestialBodyInformation[3]), celestialBodyInformation[4],
                            Integer.parseInt(celestialBodyInformation[5]), Double.parseDouble(celestialBodyInformation[6]),
                            Double.parseDouble(celestialBodyInformation[7]), celestialBodyInformation[8],
                            Double.parseDouble(celestialBodyInformation[9]), Double.parseDouble(celestialBodyInformation[10])));
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException iox){
            System.out.println("Error loading file!");
        }
    }

    /**
     * Saves a solar system
     *
     * @param filePath the file path at which to save solar system
     */
    public void saveSolarSystem(String filePath){
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(filePath, false));

            for (CelestialBody c : celestialBodies){
                // checks if a celestial body is a sun or planet
                if (c instanceof Sun){
                    // writes a new line with the information of that celestial body in the specified format
                    out.write("Sun," + c.getName() + "," + c.getMass() + "," + c.getRadius() + "," + c.getImagePath()
                    + "," + c.getDisplayPriority() + "," + c.getXDisplacement() + "," + c.getYDisplacement() + "," + ((Sun) c).getLuminosity());

                } else if (c instanceof Planet){
                    out.write("Planet," +  c.getName() + "," + c.getMass() + "," + c.getRadius() + "," + c.getImagePath()
                            + "," + c.getDisplayPriority() + "," + c.getXDisplacement() + "," + c.getYDisplacement() + "," + ((Planet) c).getType()
                    + "," + ((Planet) c).getXVelocity() + "," + ((Planet) c).getYVelocity());
                }

                out.newLine();
            }

            out.close();
        } catch (IOException iox){
            System.out.println("Error Loading file!");
        }

    }

    /**
     * Gets the name of celestial body
     *
     * @param index the index of the celestial body
     * @return the name of the celestial body
     */
    public String getCelestialBodyName(int index){
        return celestialBodies.get(index).getName();
    }

    /**
     * Sets the name of a celestial body
     *
     * @param index the index of the celestial body
     * @param name the name of the celestial body
     * @return None
     */
    public void setCelestialBodyName(int index, String name){
        celestialBodies.get(index).setName(name);
    }

    /**
     * Gets the mass of a celestial body
     *
     * @param index the index of the celestial body
     * @return the mass of the celestial body
     */
    public double getCelestialBodyMass(int index){
        return celestialBodies.get(index).getMass();
    }

    /**
     * Sets the mass of a celestial body
     *
     * @param index the index of the celestial body
     * @param mass the mass of the celestial body
     * @return None
     */
    public void setCelestialBodyMass(int index, double mass){
        celestialBodies.get(index).setMass(mass);
    }

    /**
     * Gets the radius of a celestial body
     *
     * @param index the index of the celestial body
     * @return the radius of the celestial body
     */
    public double getCelestialBodyRadius(int index){
        return celestialBodies.get(index).getRadius();
    }

    /**
     * Sets the radius of a celestial body
     *
     * @param index the index of the celestial body
     * @param radius the radius of the celestial body
     * @return None
     */
    public void setCelestialBodyRadius(int index, double radius){
        celestialBodies.get(index).setRadius(radius);
    }

    /**
     * Gets the image path of a celestial body
     *
     * @param index the index of the celestial body
     * @return the image path of the celestial body
     */
    public String getCelestialBodyImagePath(int index){
        return celestialBodies.get(index).getImagePath();
    }

    /**
     * Sets the index of a celestial body
     *
     * @param index the index of the celestial body
     * @param imagePath the image path of the celestial body
     * @return None
     */
    public void setCelestialBodyImagePath(int index, String imagePath){
        celestialBodies.get(index).setImagePath(imagePath);
    }

    /**
     * Gets the display priority of a celestial body
     *
     * @param index the index of the celestial body
     * @return the display priority of a celestial body
     */
    public int getCelestialBodyDisplayPriority(int index){
        return celestialBodies.get(index).getDisplayPriority();
    }

    /**
     * Sets the display priority of a celestial body
     *
     * @param index the index of the celestial body
     * @param displayPriority the display priority of a celestial body
     */
    public void setCelestialBodyDisplayPriority(int index, int displayPriority){
        celestialBodies.get(index).setDisplayPriority(displayPriority);
    }

    /**
     * Gets the x-displacement of a celestial body
     *
     * @param index the index of the celestial body
     * @return the x-displacement of a celestial body
     */
    public double getCelestialBodyXDisplacement(int index){
        return celestialBodies.get(index).getXDisplacement();
    }

    /**
     * Gets the y-displacement of a celestial body
     *
     * @param index the index of the celestial body
     * @return the y-displacement of a celestial body
     */
    public double getCelestialBodyYDisplacement(int index){
        return celestialBodies.get(index).getYDisplacement();
    }

    /**
     * Gets the type of a planet
     *
     * @param index the index of the planet
     * @return the type of the planet
     */
    public String getPlanetType(int index){
        return ((Planet) celestialBodies.get(index)).getType();
    }

    /**
     * Sets the type of a planet
     *
     * @param index the index of the planet
     * @param type the type of the planet
     * @return None
     */
    public void setPlanetType(int index, String type){
        ((Planet) celestialBodies.get(index)).setType(type);
    }

    /**
     * Gets the luminosity of the sun in a solar system
     *
     * @return the luminosity of the sun in watts
     */
    public double getSunLuminosity(){
        double luminosity = -1;

        // assuming that there is exactly one sun object in the solar system class
        for (CelestialBody c : celestialBodies){
            if (c instanceof Sun){
                luminosity = ((Sun) c).getLuminosity();
                break;
            }
        }

        return luminosity;
    }

    /**
     * Sets the luminosity of the sun in a solar system
     *
     * @param luminosity the luminosity of the sun
     * @return None
     */
    public void setSunLuminosity(double luminosity){

        // assuming that there is exactly one sun object in the solar system class
        for (CelestialBody c : celestialBodies){
            if (c instanceof Sun){
                ((Sun) c).setLuminosity(luminosity);
            }
        }
    }

    /**
     * Gets the index of the sun in a solar system
     *
     * @return the index of the sun in a solar system
     */
    public int getSunIndex(){
        int index = -1;

        // assuming that there is exactly one sun object in the solar system class
        for (int i = 0; i < celestialBodies.size(); i++){
            if (celestialBodies.get(i) instanceof Sun){
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Lists all celestial bodies in a solar system
     *
     * @return None
     */
    public void listAllCelestialBodies(){
        for (CelestialBody c : celestialBodies){
            System.out.println(c);
        }
    }

    /**
     * Prints all celestial bodies, numbered with their name and image path
     *
     * @return None
     */
    public void printNumberedCelestialBodies(){
        for (int i = 0; i < celestialBodies.size(); i++){
            System.out.println(i + " - " + celestialBodies.get(i).getName() + ", " + celestialBodies.get(i).getImagePath());
        }
    }

    /**
     * Prints all planets, numbered with their name and image path
     *
     * @return None
     */
    public void printNumberedPlanets(){
        int counter = 0;
        for (CelestialBody c : celestialBodies){
            if (c instanceof Planet){
                System.out.println(counter + " - " + c.getName() + ", " + c.getImagePath());
                counter++;
            }
        }
    }

    /**
     * Prints the information for a celestial body
     *
     * @param index the index of a celestial body
     * @return None
     */
    public void printCelestialBodyInformation(int index){
        celestialBodies.get(index).printCelestialBodyInformation();
    }

    /**
     * Prints the information for all celestial bodies
     *
     * @return None
     */
    public void printAllCelestialBodiesInformation(){
        for (CelestialBody c : celestialBodies){
            c.printCelestialBodyInformation();
        }
    }

    /**
     * Calculates the displacement of the furthest celestial body
     *
     * @return the displacement of the furthest celestial body
     */
    public double getGreatestCelestialBodyDisplacement(){
        double displacement = 0;

        for (CelestialBody c : celestialBodies){
            if (c.getDisplacementMagnitude() > displacement){
                displacement = c.getDisplacementMagnitude();
            }
        }

        return displacement;
    }

    /**
     * Calculate the radius of the smallest celestial body
     *
     * @return the radius of the smallest celestial body
     */
    public double getSmallestCelestialBodyRadius(){
        double radius = celestialBodies.get(0).getRadius();

        for (CelestialBody celestialBody : celestialBodies){
            if (radius > celestialBody.getRadius()){
                radius = celestialBody.getRadius();
            }
        }

        return radius;
    }

    /**
     * Calculate the radius of the largest celestial body
     *
     * @return the radius of the largest celestial body
     */
    public double getGreatestCelestialBodyRadius(){
        double radius = celestialBodies.get(0).getRadius();

        for (CelestialBody celestialBody : celestialBodies){
            if (radius < celestialBody.getRadius()){
                radius = celestialBody.getRadius();
            }
        }

        return radius;
    }

    /**
     * Moves planets over time
     *
     * @param timeSeconds the amount of time that has elapsed in seconds
     * @return None
     */
    public void movePlanetsOverTime(double timeSeconds){
        Vector acceleration;
        for (CelestialBody c : celestialBodies){
            if (c instanceof Planet){
                // calculates the acceleration using Newton's second law, since the only force on a planet is the
                // gravity from other celestial bodies that can be divided by the mass of the planet to get its
                // acceleration

                acceleration = calculateNetGravity(c);
                acceleration.timesConstant(1 / (c.getMass()));

                // use kinematics equations to alter the velocities of planets
                ((Planet) c).setXVelocity(((Planet) c).getXVelocity() + acceleration.getX() * timeSeconds);
                ((Planet) c).setYVelocity(((Planet) c).getYVelocity() + acceleration.getY() * timeSeconds);

                // use kinematics equations with the modified velocities to alter the displacement of planets
                c.setXDisplacement(c.getXDisplacement() + ((Planet) c).getXVelocity() * timeSeconds);
                c.setYDisplacement(c.getYDisplacement() + ((Planet) c).getYVelocity() * timeSeconds);
            }
        }
    }

    /**
     * Calculates the net gravity acting on a celestial body
     *
     * @param c1 the celestial body whose net gravity is being calculated
     * @return the net gravity acting on a celestial body
     */
    private Vector calculateNetGravity(CelestialBody c1){

        final double GRAVITATION_CONSTANT = 6.6743E-11;

        double netGravityX = 0;
        double netGravityY = 0;

        for (CelestialBody c2 : celestialBodies){
            // ensure that c2 is not c1 so that there is not a divide by 0 error
            if (!c1.equals(c2)){
                // use Newton's law of universal gravitation (Fg = (Gm1m2 / r^2) to calculate the gravity that each
                // celestial body has on c1
                double magnitudeOfGravity = (GRAVITATION_CONSTANT * c1.getMass() *
                        c2.getMass()) / (Math.pow((c1.distanceTo(c2)) ,2));

                double angleBetween = c2.getAngleBetweenDisplacements(c1);

                // the net gravity is broken down into its components using the angle between c1 and c2
                netGravityX += magnitudeOfGravity * Math.cos(angleBetween);
                netGravityY += magnitudeOfGravity * Math.sin(angleBetween);
            }
        }

        // the x and y components are calculated separately and only combined at the end in order to avoid creating
        // a new instance of the Vector class for every celestial body
        return new Vector(netGravityX, netGravityY);
    }
}