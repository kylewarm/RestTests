package RestAssured;

public class Robot {
    int coordX;
    int coordY;
    int moonX;
    int moonY;
    String robotName = "Unknown";

    public Robot () {
        this.coordX = 0;
        this.coordY = 0;
        this.moonX = 0;
        this.moonY = 0;
    }

    public Robot (String robotName)
    {
        this.coordX = 0;
        this.coordY = 0;
        this.moonX = 0;
        this.moonY = 0;
    }

    private void validateRobotMovement(int moveX, int moveY) {
        if ((moonX <= 0) || (moonY <= 0)) throw  new  IllegalArgumentException("Robot is not landed or moon size is invalid");
        if ((moveX > moonX-1) || (moveX < 0)) throw  new  IllegalArgumentException("Robot moves outside the moon on X coord");
        if ((moveY > moonY-1) || (moveY < 0)) throw  new  IllegalArgumentException("Robot moves outside the moon on Y coord");
    }

    public int getX () {
        return this.coordX;
    }

    public int getY () {
        return this.coordY;
    }

    public void moveX (int newX) {
        validateRobotMovement(newX,0);
        coordX = newX;
    }

    public void moveY (int newY) {
        validateRobotMovement(0,newY);
        coordY = newY;
    }


}

