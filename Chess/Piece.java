public class Piece {
    protected int xPosition;
    protected int yPosition;
    protected String color;

    public Piece(int xPosition, int yPosition, String color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public String getColor() {
        return color;
    }

}
