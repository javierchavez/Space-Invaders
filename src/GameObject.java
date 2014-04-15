import java.awt.Rectangle;

/**
 * @author Javier Chavez
 */
public abstract class GameObject implements Object2D, GameData{
    protected Rectangle rectangle;

    /**
     * Constructor forces any child to call this constructor! Which inhibits any chance of rectangle obj not being set (which
     * would cause a null pointer exception).
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected GameObject(int x, int y, int width, int height){
        rectangle = new Rectangle(x,y,width,height);
    }
    @Override
    public int getX() {
        return rectangle.x;
    }

    @Override
    public int getY() {
        return rectangle.y;
    }

    @Override
    public int getWidth() {
        return rectangle.width;
    }

    @Override
    public int getHeight() {
        return rectangle.height;
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return rectangle;
    }

    @Override
    public boolean intersects(Object2D other) {
        return rectangle.intersects(other.getBoundingRectangle());
    }

    @Override
    public boolean isOutOfBounds() {


        if (rectangle.x < 0 || rectangle.x > GAME_BOARD_WIDTH ||
            rectangle.y < 0 || rectangle.y > GAME_BOARD_HEIGHT) {
            return true;
        }

        int totalWidth = rectangle.x + rectangle.width;
        int totalHeight = rectangle.y + rectangle.height;

        if (totalWidth < 0 || totalWidth > GAME_BOARD_WIDTH ||
           totalHeight < 0 || totalHeight > GAME_BOARD_HEIGHT) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return getClass().getName()+ " at (" + rectangle.x+ ", " + rectangle.y +")";
    }
}
