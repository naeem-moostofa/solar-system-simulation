/**
 * A class that defines a celestial body as having a name which is a String, mass and radius which are double values,
 * an image path which is a String, an integer display priority, and a displacement vector from the center of the
 * solar system which is at (0,0). Operations include calculating the distance, and angle between celestial objects.
 * Celestial bodies also implement comparable based on their display priority.
 */
abstract class CelestialBody implements Comparable<CelestialBody>{
    protected String name;
    protected double mass;
    protected double radius;
    protected String imagePath;
    // a celestial body with a greater display priority will be displayed on top of one with a smaller display priority
    protected int displayPriority;
    protected Vector displacement;

    /**
     * Creates a new Celestial body
     *
     * @param name The name of the celestial body
     * @param mass The mass of the celestial body
     * @param radius The radius of the celestial body
     * @param imagePath The image path for a celestial body
     * @param displayPriority The display priority of a celestial body
     * @param xDisplacement The x-displacement for a celestial body
     * @param yDisplacement The y-displacement for a celestial body
     * @return None
     */
    public CelestialBody(String name, double mass, double radius, String imagePath, int displayPriority,
                         double xDisplacement, double yDisplacement){
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.imagePath = imagePath;
        this.displayPriority = displayPriority;
        displacement = new Vector(xDisplacement, yDisplacement);
    }

    /**
     * Gets the name of a celestial body
     *
     * @return the name of a celestial body
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of a celestial body
     *
     * @param name the name of the celestial body
     * @return None
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the mass of a celestial body
     *
     * @return the mass of a celestial body
     */
    public double getMass(){
        return mass;
    }

    /**
     * Sets the mass of a celestial body
     *
     * @param mass the mass of a celestial body
     * @return None
     */
    public void setMass(double mass){
        this.mass = mass;
    }

    /**
     * Gets the radius of a celestial body
     *
     * @return the radius of a celestial body
     */
    public double getRadius(){
        return radius;
    }

    /**
     * Sets the radius of a celestial body
     *
     * @param radius the mass of a celestial body
     * @return None
     */
    public void setRadius(double radius){
        this.radius = radius;
    }

    /**
     * Gets the image path for a celestial body
     *
     * @return the image path for a celestial body
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Sets the image path for a celestial body
     *
     * @param imagePath the image path for a celestial body
     * @return None
     */
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    /**
     * Gets the display priority for a celestial body
     *
     * @return the display priority for a celestial body
     */
    public int getDisplayPriority(){
        return displayPriority;
    }

    /**
     * Sets the display priority for a celestial body
     *
     * @param displayPriority the display priority for a celestial body
     * @return None
     */
    public void setDisplayPriority(int displayPriority){
        this.displayPriority = displayPriority;
    }

    /**
     * Gets the x-displacement of a celestial body
     *
     * @return the x-displacement of a celestial body
     */
    public double getXDisplacement(){
        return displacement.getX();
    }

    /**
     * Sets the x-displacement of a celestial body
     *
     * @param xDisplacement the x-displacement of a celestial body
     * @return None
     */
    public void setXDisplacement(double xDisplacement){
        displacement.setX(xDisplacement);
    }

    /**
     * Gets the y-displacement of a celestial body
     *
     * @return the y-displacement of a celestial body
     */
    public double getYDisplacement(){
        return displacement.getY();
    }

    /**
     * Sets the y-displacement of a celestial body
     *
     * @param yDisplacement the y-displacement of a celestial body
     * @return None
     */
    public void setYDisplacement(double yDisplacement){
        displacement.setY(yDisplacement);
    }

    /**
     * Calculates the magnitude of the displacement vector for a celestial body
     *
     * @return the magnitude of the displacement of a celestial body
     */
    public double getDisplacementMagnitude(){
        return displacement.getMagnitude();
    }

    /**
     * Calculates the distance between two celestial bodies
     *
     * @param other Another celestial body
     * @return the distance between two celestial bodies
     */
    public double distanceTo(CelestialBody other){
        return displacement.distanceBetween(other.displacement);
    }

    /**
     * Calculates the angle between the displacement vector of two celestial bodies
     *
     * @param other Another celestial body
     * @return the angle between the two celestial bodies in radians
     */
    public double getAngleBetweenDisplacements(CelestialBody other){
        return displacement.getAngleBetween(other.displacement);
    }

    /**
     * Nicely formats a string with a celestial body's name, image path, mass, and radius
     *
     * @return the celestial body's name, image path, mass, and radius
     */
    @Override
    public String toString(){
        return name + ": " + imagePath + ", " + mass + ", " + radius;
    }

    /**
     * Compares celestial bodies based on their display priority
     *
     * @param other The object to be compared.
     * @return the difference between the display priority of two celestial bodies
     */
    public int compareTo(CelestialBody other){
        return other.displayPriority - displayPriority;
    }

    /**
     * Checks if two celestial bodies are equal
     *
     * @param other Another celestial body
     * @return whether the two celestial bodies are equal
     */
    public boolean equals(CelestialBody other){
        return name.equals(other.name) && mass == other.mass && radius == other.radius &&
                imagePath.equals(other.imagePath) && displacement.equals(other.displacement);
    }

    /**
     * Nicely prints out the information for a celestial body
     *
     * @return None
     */
    abstract void printCelestialBodyInformation();
}
