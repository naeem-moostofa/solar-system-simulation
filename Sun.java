/**
 * Defines a sun as a celestial that has a luminosity (brightness) which is a double value. Operations include
 * nicely printing out the information for a sun.
 */
public class Sun extends CelestialBody{

    private double luminosity;

    /**
     * Creates a new sun
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
    public Sun(String name, double mass, double radius, String imagePath, int displayPriority,
               double xDisplacement, double yDisplacement, double luminosity) {
        super(name, mass, radius, imagePath, displayPriority,  xDisplacement, yDisplacement);
        this.luminosity = luminosity;
    }

    /**
     * Gets the luminosity of a sun
     *
     * @return the luminosity
     */
    public double getLuminosity(){
        return luminosity;
    }

    /**
     * Sets the luminosity of a sun
     *
     * @param luminosity the luminosity
     * @return None
     */
    public void setLuminosity(double luminosity){
        this.luminosity = luminosity;
    }

    /**
     * Nicely prints out the information for a sun
     *
     * @return None
     */
    public void printCelestialBodyInformation(){
        System.out.println();
        System.out.println("--------------- SUN ---------------");
        System.out.println("Name: " + name);
        System.out.println("Image Path: " + imagePath);
        System.out.println("Display priority: " + displayPriority);
        System.out.printf("Mass: %.2f kg \n", mass);
        System.out.printf("Radius: %.2f m \n", radius);
        System.out.printf("Luminosity: %.2f W \n", luminosity);
        System.out.println();
    }

    /**
     * Nicely formats a string with a sun's name, image path, mass, radius, and luminosity
     *
     * @return the sun's name, image path, mass, radius, and luminosity
     */
    @Override
    public String toString(){
        return super.toString() + ", " + luminosity;
    }

}