/**
 * Defines a vector as its x and y components which are double values. Operations include Multiplying by a constant,
 * calculating the angle between the two components, the distance between two vectors, calculating the magnitude of
 * the vector, and checking equality.
 */
public class Vector {
    private double x;
    private double y;

    /**
     * Creates a new vector
     *
     * @param x The value of the x component
     * @param y The value of the y component
     * @return None
     */
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x component of a vector
     *
     * @return the x component
     */
    public double getX(){
        return x;
    }

    /**
     * Sets the value of the x component of a vector
     *
     * @param x the value of the x component
     * @return None
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Gets the y component of a vector
     *
     * @return the y component
     */
    public double getY(){
        return y;
    }

    /**
     * Sets the value of the y component of a vector
     *
     * @param y the value of the y component
     * @return None
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Calculates the magnitude of a vector using the pythagorean theorem
     *
     * @return the magnitude of the vector
     */
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    /**
     * Calculates the distance between two vectors using the pythagorean theorem
     *
     * @param other The second vector
     * @return the distance between the two vectors
     */
    public double distanceBetween(Vector other){
        // Displacement between two vectors is calculated by the difference of each of their components. To find
        // distance from displacement the magnitude is calculated using pythagorean theorem
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Multiples a vector by a constant
     *
     * @param c A constant
     * @return None
     */
    public void timesConstant(double c){
        x *= c;
        y *= c;
    }

    /**
     * Calculates the angle between two vectors in radians
     *
     * @param other The second vector
     * @return the angle between the two vectors in radians
     */
    public double getAngleBetween(Vector other){
        // calculates the components of a displacement vector, the direction of this vector will be the angle between
        // the two vectors
        double deltaX = x - other.x;
        double deltaY = y - other.y;

        // uses arc-tan to calculate the angle between the components
        double angle = Math.atan(Math.abs(deltaY)/Math.abs(deltaX));

        // uses the sign of each component to determine which quadrant the displacement vector is in and modifies the
        // angle accordingly
        if (deltaX < 0 && deltaY >= 0){
            angle = Math.PI - angle;
        } else if (deltaX < 0 && deltaY < 0){
            angle = Math.PI + angle;
        } else if (deltaX > 0 && deltaY < 0){
            angle = 2 * Math.PI - angle;
        }

        return angle;
    }

    /**
     * Checks if two vectors are equal
     *
     * @param other Another vector
     * @return whether the vectors are the same
     */
    public boolean equals(Vector other){
        return x == other.x && y == other.y;
    }
}
