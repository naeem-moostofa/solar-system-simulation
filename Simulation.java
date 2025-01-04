import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Defines a simulation as a JFrame with an ArrayList of JLabels containing images to display, a speed, distanceScale,
 * default speed, and default scale all of which are double values. A simulation also includes a String for a
 * background image path and integer values of the greatest and smallest object dimensions in pixels for
 * objects being displayed in the simulation. Operations include calculating the scale of the simulation, how many
 * meters translates to 1 pixel, creating JLabels based on the imagePath in celestial bodies, scaling the size of
 * JLabels given the greatest and smallest object dimensions, and displaying the simulation.
 */
public class Simulation {
    private JFrame frame;
    // a JLabel is an object that can contain information, like images and can be displayed on a JFrame
    private ArrayList<JLabel> labels;
    // the number of meters that translates to one pixel in the simulation
    private double distanceScale;
    // The speed field determines how many times faster/ slower the simulation is displayed from the default speed.
    // For example, a speed of 2 signify that the simulation should be twice as fast as the default speed, while a
    // speed of 0.5 would signify that the simulation should be half as fast.
    private double speed;
    private static final String BACKGROUND_IMAGE_PATH = "Images/background.jpg";
    // object dimensions are the width and height in pixels assuming that all objects displayed are squares
    private static final int GREATEST_OBJECT_DIMENSIONS = 90;
    private static final int SMALLEST_OBJECT_DIMENSIONS = 25;
    // this is a speed determined experimentally for the main solar system for the project submission
    private static final double DEFAULT_SPEED = 10;
    private static final double DEFAULT_SCALE = 1;

    /**
     * Creates a new Simulation
     *
     * @param name The name of the simulation
     * @param imagePath The image path for the icon on the simulation
     * @param width The width, in pixels of the simulation
     * @param height The height, in pixels of the simulation
     * @return None
     */
    public Simulation(String name, String imagePath, int width, int height){
        // creates a new JFrame object with the name, name
        frame = new JFrame(name);
        frame.setIconImage(new ImageIcon(imagePath).getImage());

        // when the 'x' is pressed on the window created the window will be disposed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // makes it so that the user can't change the size of the frame (the simulation would need to detect and account
        // for this otherwise)
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLayout(null);

        speed = DEFAULT_SPEED;
        distanceScale = DEFAULT_SCALE;

        labels = new ArrayList<>();

    }

    /**
     * Calculates the distance scale for the simulation
     *
     * @param furthestDisplacement the greatest displacement, in meters of an object that will be displayed
     */
    public void calculateDistanceScale(double furthestDisplacement){
        int smallest;

        // if the frame is a rectangle the object should be able to fit on either axis
        if (frame.getWidth() < frame.getHeight()){
            smallest = frame.getWidth();
        } else {
            smallest = frame.getHeight();
        }

        // multiply by 2 since displacement is from the center of the frame, and we are looking and the width/ height of
        // the whole frame
        distanceScale = (furthestDisplacement * 2) / smallest;
    }

    /**
     * Calculates the (square) dimensions that an image should have, linearly scaling given the greatest and smallest
     * object dimensions
     *
     * @param objectSize the size of the object, in meters
     * @param greatestObject the size of the greatest object, in meters
     * @param smallestObject the size of the smallest object, in meters
     * @return the dimensions for an image
     */
    public int calculateImageDimensions(double objectSize, double greatestObject, double smallestObject){

        // if the greatest object is the same as the smallest object (all objects are the same size) or there are
        // less than two objects the smallest object dimensions are returned
        if (greatestObject == smallestObject){
            return SMALLEST_OBJECT_DIMENSIONS;
        }

        // otherwise, creates an equation of a line where x values are a radius in meters and y values are image dimensions in
        // pixels

        // cast to an int because pixels can't be double values
        return (int)(((GREATEST_OBJECT_DIMENSIONS - SMALLEST_OBJECT_DIMENSIONS) / (greatestObject - smallestObject)) *
                (objectSize - smallestObject) + SMALLEST_OBJECT_DIMENSIONS);
    }

    /**
     * Converts a distance in the x direction to pixels
     *
     * @param distance A distance in meters
     * @return the number of pixels
     */
    public int xDistanceToPixels(double distance){
        // distance is the distance from the center of the frame

        // cast to an int because pixels can't be double values
        int distanceFromCenter = (int)(distance / distanceScale);

        int middle = frame.getWidth() / 2;

        return middle + distanceFromCenter;
    }

    /**
     * Converts a distance in the y direction to pixels
     *
     * @param distance A distance in meters
     * @return the number of pixels
     */
    public int yDistanceToPixels(double distance){
        // distance is the distance from the center of the frame

        // cast to an int because pixels can't be double values
        int distanceFromCenter = (int)(distance / distanceScale);

        int middle = frame.getHeight() / 2;

        // in the frame y = 0 is the top left, the middle is subtracted from the distance from center so that
        // y = 0 is treated as the bottom left
        return middle - distanceFromCenter;
    }

    /**
     * Gets the speed of the simulation
     *
     * @return the speed of the simulation
     */
    public double getSpeed(){
        return speed;
    }

    /**
     * Sets the speed of the simulation
     *
     * @param speed the speed of the simulation
     * @return None
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }

    /**
     * Gets the width of the frame
     *
     * @return the width of the frame
     */
    public int getWidth(){
        return frame.getWidth();
    }

    /**
     * Sets the width of the frame
     *
     * @param width the width of the frame
     * @retun None
     */
    public void setWidth(int width){
        // if only the width is changing the height will stay the same
        frame.setSize(width, frame.getHeight());
    }

    /**
     * Gets the height of the frame
     *
     * @return the height of the frame
     */
    public int getHeight(){
        return frame.getHeight();
    }

    /**
     * Sets the height of the frame
     *
     * @param height the height of the frame
     * @return None
     */
    public void setHeight(int height){
        // if only the height is changing the width will stay the same
        frame.setSize(frame.getWidth(), height);
    }

    public double getDistanceScale(){
        return distanceScale;
    }

    /**
     * Adds a label that can be displayed by the simulation
     *
     * @param imagePath the image path of the label
     * @param xDisplacement the x-displacement in meters of the label
     * @param yDisplacement the y-displacement in meters of the label
     * @param objectSize the size of the object in meters
     * @param greatestObjectSize the size of the greatest object that will be displayed in meters
     * @param smallestObjectSize the size of the smallest object that will be displayed in meters
     * @return None
     */
    public void addLabel(String imagePath, double xDisplacement, double yDisplacement, double objectSize,
                         double greatestObjectSize, double smallestObjectSize){
        // calculates the size of the label using the calculate image dimensions method
        int dimension = calculateImageDimensions(objectSize, greatestObjectSize, smallestObjectSize);
        JLabel label = new JLabel();

        // gets the image and resizes it to the calculated dimensions
        label.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage()
                        .getScaledInstance(dimension,dimension, Image.SCALE_SMOOTH)));

        // subtract dimensions / 2 because images are displayed where their x and y positions
        // is the top left of the image, this ensures that the center of the image is its displacement
        label.setBounds(xDistanceToPixels(xDisplacement) - dimension / 2,
                yDistanceToPixels(yDisplacement) - dimension / 2, dimension, dimension);

        // adds the new JLabel to the arrayList of JLabels
        labels.add(label);
    }

    /**
     * Updates the position of a label
     *
     * @param index the index of the label
     * @param xDisplacement the new x-displacement in meters of the label
     * @param yDisplacement the new y-displacement in meters of the label
     * @param objectSize the size of the object in meters
     * @param greatestObjectSize the size of the greatest object that will be displayed in meters
     * @param smallestObjectSize the size of the smallest object that will be displayed in meters
     * @return None
     */
    public void updateLabelPosition(int index, double xDisplacement, double yDisplacement, double objectSize,
                                    double greatestObjectSize, double smallestObjectSize){

        // calculates the size of the label using the calculate image dimensions method
        int dimension = calculateImageDimensions(objectSize, greatestObjectSize, smallestObjectSize);

        // sets the location of the label

        // dimension / 2 is subtracted from the x and y pixels so that the image is displayed centered on the x and y
        // distance
        labels.get(index).setLocation(xDistanceToPixels(xDisplacement) - dimension / 2 ,
                yDistanceToPixels(yDisplacement) - dimension / 2);
    }

    /**
     * Clears all labels in a simulation and resets the frame
     *
     * @return None
     */
    public void resetLabelsAndFrame(){
        labels = new ArrayList<>();
        frame.getContentPane().removeAll();
    }

    /**
     * Displays the simulation
     *
     * @return None
     */
    public void displaySimulation(){
        // a JLayeredPane will be able to store multiple JLabels and then just the JLayeredPane can be added to the frame
        // and displayed
        JLayeredPane allLabels = new JLayeredPane();

        // adds all labels to a JLayered pane
        for (JLabel label : labels) {
            allLabels.add(label, JLayeredPane.DEFAULT_LAYER);
        }

        // adds the background from the BACKGROUND_IMAGE_PATH last so that it will be behind all other JLabels
        JLabel background = new JLabel();
        // the size of the background will be the size of the frame
        background.setIcon(new ImageIcon(new ImageIcon(BACKGROUND_IMAGE_PATH).getImage()
                .getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH)));
        background.setBounds(0,0, frame.getWidth(), frame.getHeight());

        allLabels.add(background, JLayeredPane.DEFAULT_LAYER);

        allLabels.setBounds(0,0,frame.getWidth(),frame.getHeight());

        // adds all the JLayeredPane with all the JLabels to the frame
        frame.add(allLabels);

        // makes it so that the frame appears in a window for the user to view
        frame.setVisible(true);
    }

    /**
     * Checks whether the simulation is currently displayed
     *
     * @return whether the simulation is currently displayed
     */
    public boolean isDisplayed(){
        return frame.isVisible();
    }
}