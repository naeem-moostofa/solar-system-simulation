/**
 * A class that defines a planet as a celestial body with a type of planet which is a String, and a velocity vector.
 * Operations include nicely printing out the information for a planet.
 */
public class Planet extends CelestialBody{
    private String type;
    private Vector velocity;

    /**
     * Creates a new planet
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
    public Planet(String name, double mass, double radius, String imagePath, int displayPriority,
                  double xDisplacement, double yDisplacement, String type, double xVelocity, double yVelocity){
        super(name, mass, radius, imagePath, displayPriority, xDisplacement, yDisplacement);
        this.type = type;
        velocity = new Vector(xVelocity, yVelocity);
    }

    /**
     * Gets the type of the planet
     *
     * @return the type of the planet
     */
    public String getType(){
        return type;
    }

    /**
     * Sets the type of the planet
     *
     * @param type the type of the planet
     * @return None
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Gets the x-velocity of a planet
     *
     * @return the x-velocity of a planet
     */
    public double getXVelocity(){
        return velocity.getX();
    }

    /**
     * Gets the y-velocity of a planet
     *
     * @return the y-velocity of a planet
     */
    public double getYVelocity(){
        return velocity.getY();
    }

    /**
     * Sets the x-velocity of a planet
     *
     * @param xVelocity the x-velocity of a planet
     * @return None
     */
    public void setXVelocity(double xVelocity){
        velocity.setX(xVelocity);
    }

    /**
     * Sets the y-velocity of a planet
     *
     * @param yVelocity the y-velocity of a planet
     * @return None
     */
    public void setYVelocity(double yVelocity){
        velocity.setY(yVelocity);
    }

    /**
     * Nicely prints out the information for a planet
     *
     * @return None
     */
    public void printCelestialBodyInformation(){
        System.out.println();
        System.out.println("--------------- PLANET ---------------");
        System.out.println("Name: " + name);
        System.out.println("Image Path: " + imagePath);
        System.out.println("Display priority: " + displayPriority);
        System.out.printf("Mass: %.2f kg \n", mass);
        System.out.printf("Radius: %.2f m \n", radius);
        System.out.printf("Current distance from the sun: %.2f m \n", super.getDisplacementMagnitude());
        System.out.println("Type of planet: " + type);
        System.out.printf("Current speed: %.2f m/s \n", velocity.getMagnitude());
        System.out.println();
    }

    /**
     * Nicely formats a string with a planet's name, image path, mass, radius, and type
     *
     * @return the planet's name, image path, mass, radius, and type
     */
    @Override
    public String toString(){
        return super.toString() + ", "  + type;
    }

}
