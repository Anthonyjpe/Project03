import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.PriorityQueue;

public class NeighborhoodGUI extends JFrame {

    private static final int HEIGHT = 782, WIDTH = 761;
    private static final int MARKER_SIZE = 5;
    private static final int BLOCK_WIDTH = 40;
    private PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);
    private static final String TITLE = "Neighborhood Delivery Simulation";

    private int x = 90, dX;
    private int y = 91, dY;
    //private Timer tm = new Timer(5, this);
    private Color green = new Color(50, 205, 50);
    private Color red = new Color(205, 50, 50);

    NeighborhoodGUI(final Neighborhood neighborhood) {
        JFrame map = new JFrame();
        JPanel canvas = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                // draw streets
                for (int x = 0; x < 19; x++)
                    for (int y = 0; y < 19; y++)
                        g.drawRect(BLOCK_WIDTH * x, BLOCK_WIDTH * y, BLOCK_WIDTH, BLOCK_WIDTH);
                // draw distribution center
                g.setColor(Color.GREEN);
                g.fillRect(9*BLOCK_WIDTH - 2, 9*BLOCK_WIDTH + 2, MARKER_SIZE, MARKER_SIZE);

                // draw deliveries
                g.setColor(Color.RED);
                Iterator<Address> iterator = addresses.iterator();
                while (iterator.hasNext())
                {
                    Address address = iterator.next();
                    double x = (address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
                    double y = (!address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
                    g.fillOval(((int) x)*BLOCK_WIDTH - 2 + (int)(40.0 * (x % 1)), ((int) y)*BLOCK_WIDTH - 2 + (int)(40.0 * (y % 1)), MARKER_SIZE, MARKER_SIZE);
                }
            }
        };
            map.getContentPane().add(canvas);
            map.repaint();

            map.setTitle(NeighborhoodGUI.TITLE);
            map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            map.setResizable(false);
            map.setSize(WIDTH, HEIGHT);
            map.setLocationRelativeTo(null); // center on screen
            map.setVisible(true);

    }

    public void start()
    {
        PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);
        Direction d = Direction.Null;
        int partial = 0; // Stores number of moves left to reach a corner

        for (Address address : addresses) {
            dY = address.getStreetNum() * 10;
            dX = address.getHouseNum() / 10;

            while (x != dX || y != dY)
            {
                if (partial != 0) { //Finish moving to corner
                    if (d == Direction.North)
                        for (; partial > 0; partial--)
                            y--;
                    else if (d == Direction.South)
                        for (; partial > 0; partial--)
                            y++;
                    else if (d == Direction.East)
                        for (; partial > 0; partial--)
                            x++;
                    else if (d == Direction.West)
                        for (; partial > 0; partial--)
                            x--;
                }

                if (d == Direction.Null)
                {
                    d = Direction.North;
                    y--;
                    repaint();
                }
                else if (d == Direction.North) { // Y is correct (1), Y is above full (2), reverse (3), y is above partially (4)
                    if (y == dY) { //On this Y level *****1*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                    } else if (y - dY >= 10) { // Above this Y level by a full block or more *****2*****
                        for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                            repaint();
                            y--;
                        }
                    } else if (y < dY) { // Need to be facing south *****3*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                           /* if (neighborhood.getGridMarker(x + 1, y) != "  ") // If the East block is not out of bounds
                                d = Direction.East;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.West;*/
                        }
                    } else if (y > dY) { // above this Y level by less than a block *****4*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for (int i = 0; y != dY; i++) {
                                y--;
                                partial = 9 - i;
                            }
                        }
                    }
                }
                else if (d == Direction.South) { // Y is correct (1), Y is below full (2), reverse (3), y is above partially (4)
                    if (y == dY) { //On this Y level *****1*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                    } else if (y - dY <= -10) { // Below this Y level by a full block or more *****2*****
                        for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                            repaint();
                            y++;
                        }
                    } else if (y > dY) { // Need to be facing north *****3*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                          /*  if (neighborhood.getGridMarker(x + 1, y) != "  ") // If the East block is not out of bounds
                                d = Direction.East;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.West;*/
                        }
                    } else { // below this Y level by less than a block *****4*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for (int i = 0; y != dY; i++) {
                                y++;
                                partial = 9 - i;
                            }
                        }
                    }
                }
                else if (d == Direction.East) {// X is correct (1), X is below full (2), reverse (3), X is below partially (4)
                    if (x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX <= -10) { // Below this X level by a full block or more *****2*****
                        for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                            repaint();
                            x++;
                        }
                    } else if (x > dX) { // Need to be facing West *****3*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                          /*  if (neighborhood.getGridMarker(x, y + 1) != "  ") // If the East block is not out of bounds
                                d = Direction.South;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.North;*/
                        }
                    } else { // below this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for (int i = 0; x != dX; i++) {
                                x++;
                                partial = 9 - i;
                            }
                        }
                    }
                }
                else if (d == Direction.West) {// X is correct (1), X is above full (2), reverse (3), X is above partially (4)
                    if (x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX >= 10) { // Above this X level by a full block or more *****2*****
                        for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                            repaint();
                            x--;
                        }
                    } else if (x < dX) { // Need to be facing West *****3*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                           /* if (neighborhood.getGridMarker(x, y + 1) != "  ") // If the East block is not out of bounds
                                d = Direction.South;
                            else //If the east block is out of bounds, the right block is not
                                d = Direction.North;*/
                        }
                    } else { // above this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for (int i = 0; x != dX; i++) {
                                x--;
                                partial = 9 - i;
                            }
                        }
                    }
                }
            }
        }
    }

    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
        g.setColor(green);
        /*if (x != dX && y != dY)
            g.setColor(green);
        else {
            g.setColor(red);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {

            }
        }*/
        g.fillOval(x, y,5,5);
    }

}