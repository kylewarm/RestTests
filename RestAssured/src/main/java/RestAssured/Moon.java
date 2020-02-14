package RestAssured;

public class Moon {
    int width;
    int height;
    Robot robot;

    public Moon(int width, int height) {
        validateMoonSize(width,height);
        this.width = width;
        this.height = height;

    }

    public Moon(int width, int height, Robot robot) {
        validateMoonSize(width,height);
        this.width = width;
        this.height = height;
        this.robot = robot;
        this.robot.moonX = width;
        this.robot.moonY = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void landRobot(Robot robot) {
        this.robot = robot;
        this.robot.moonX = width;
        this.robot.moonY = height;
    }


    private void validateMoonSize(int moonX, int moonY) {
        if ((moonX <= 0) || (moonY <= 0)) throw  new  IllegalArgumentException("Moon Size is Invalid");
    }

    public String returnRobotCoordinates() {
        String searchResult = "";
        try{
            int x = robot.getX();
            int y = robot.getY();
            searchResult = "Robot exists! x:" + x + " y:" + y;
        } catch (Exception e) {
            searchResult = e.getMessage();
        }
       return searchResult;
    }

}
