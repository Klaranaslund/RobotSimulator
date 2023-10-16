/**
 * Implements a robot that has a direction where it is facing. Can turn left and right to switch directions.
 *
 * @author Klara Näslund
 */
class Robot {
    private Direction direction;
    private boolean isPlaced = false;

    /**
     * Turn robot 90 degrees to the left.
     */
    public void leftTurn() {
            switch (direction) {
                case WEST: {
                    direction = Direction.SOUTH;
                    break;
                }
                case SOUTH: {
                    direction = Direction.EAST;
                    break;
                }
                case EAST: {
                    direction = Direction.NORTH;
                    break;
                }
                case NORTH: {
                    direction = Direction.WEST;
                }
            }
        }

    /**
     * Turn robot 90 degrees to the right.
     */
    public void rightTurn() {
            switch (direction) {
                case WEST: {
                    direction = Direction.NORTH;
                    break;
                }
                case SOUTH: {
                    direction = Direction.WEST;
                    break;
                }
                case EAST: {
                    direction = Direction.SOUTH;
                    break;
                }
                case NORTH: {
                    direction = Direction.EAST;
                }
            }
        }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPlaced() {
        isPlaced = true;
    }

    public boolean getPlaced() {
        return isPlaced;
    }

    public Direction getDirection() {
        return direction;
    }

}
