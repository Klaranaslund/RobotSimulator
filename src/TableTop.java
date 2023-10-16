/**
 * Tabletop-class that can move a robot across its surface as long
 * as it does not move outside of its max size of 5x5 units, and report a placed robots current position.
 *
 * @author Klara Näslund
 */

public class TableTop {
    private final Robot robot;
    private final int MAXSIZE = 5;
    private int xCoordinate;
    private int yCoordinate;

    public TableTop() {
        robot = new Robot();
    }

    private boolean canBePlaced(int x, int y) {
        return x <= MAXSIZE && x > 0 && y <= MAXSIZE && y > 0;
    }

    /**
     * Place a robot on the tabletop.
     *
     * @param x         x-coordinate of tabletop where robot is placed
     * @param y         y-coordinate of tabletop where robot is placed
     * @param direction direction robot is facing when placed
     */
    public void placeRobot(int x, int y, Direction direction) {
        if (canBePlaced(x, y)) {
            xCoordinate = x;
            yCoordinate = y;
            robot.setDirection(direction);
            robot.setPlaced();
        }
    }

    /**
     * Move a robot placed on the tabletop one unit in the direction
     * it is currently facing.
     */
    public void move() {
        switch (robot.getDirection()) {
            case NORTH: {
                if (yCoordinate < MAXSIZE)
                    yCoordinate++;
                break;
            }
            case EAST: {
                if (xCoordinate < MAXSIZE)
                    xCoordinate++;
                break;
            }
            case SOUTH: {
                if (yCoordinate >= 2)
                    yCoordinate--;
                break;
            }
            case WEST: {
                if (xCoordinate >= 2)
                    xCoordinate--;
                break;
            }
        }
    }

    /**
     * Prints the position where a robot is currently placed on the tabletop and the direction the robot is facing.
     */
    public void report() {
        System.out.print("Robot is at: " + xCoordinate + ", " + yCoordinate + " facing " + robot.getDirection() + "\n");
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public Robot getRobot() {
        return robot;
    }

}
