import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Visual representation of how stack frames relate to method calls
 * using clothing analogy in <link>vid2</link>
 *
 * Note: This is not a good example of using swing, in fact
 * for that, it is a bad example. =] It is only meant to supply
 * a visual alongside the debugger.
 */
public class GetDressed extends JFrame {

    static final int _TICK = 1000;
    JFrame frame;
    JLabel stackLabel;
    JLabel picLabel;
    BufferedImage stack;
    BufferedImage body;

    String[] stackImages = {"resources/baseMainFrameStack.png", "resources/sweaterStack.png", "resources/puffyStack.png"};
    static String[] bodyImages = {"resources/baseLayer.png","resources/middleLayer.png","resources/topLayer.png"};

    public GetDressed(int i) {
        frame = new JFrame("Getting Dressed!");
        changeState(stackImages, bodyImages, i);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,720);

        frame.setVisible(true);



    }

    private void changeState(String[] s, String[] b, int i) {
        try {
            stack = ImageIO.read(new File(s[i]));
            stackLabel = new JLabel(new ImageIcon(stack));
            frame.add(stackLabel, BorderLayout.EAST);

            body = ImageIO.read(new File(b[i]));
            picLabel = new JLabel(new ImageIcon(body));
            frame.add(picLabel, BorderLayout.WEST);
        }
        catch (IOException e){
            System.out.println("derderp");
            e.getMessage();
        }



    }

    public void removeLayer() throws InterruptedException{
        setVisible(false);
        frame.dispose();
        Thread.sleep(_TICK);

    }

    /**
     * Demo starts here.
     * Set a breakpoint on line 77. 'step over' all code except where comment
     * says to step in. Check out the Debugger window pane as you go.
     *
     * Remember, if you step in to a method you didn't mean to, you can use 'step out'
     * to go back to the calling frame. 👍
     */
    public static void main(String[] args) throws InterruptedException {
        GetDressed a = new GetDressed(0); //create base layer in main frame
        String myStacksClothes = setMessage(0);
        String myThings = "";
        Thread.sleep(_TICK*2);
        myStacksClothes = putOnSweater(1, myThings); //step in
        Thread.sleep(_TICK);
        System.exit(0);
    }

    public static String putOnSweater(int i, String myThings) throws InterruptedException{
        GetDressed newLayer = new GetDressed(i); //create visual of putOnSweater change
        String myStacksClothes = setMessage(i);
        Thread.sleep(_TICK);

        myThings = myThings + "keys wallet";

        myStacksClothes = putOnPuffyJacket(2, myThings); //step in

        newLayer.removeLayer();

        return myStacksClothes;
    }

    public static String putOnPuffyJacket(int i, String myThings) throws InterruptedException{
        GetDressed newLayer = new GetDressed(i); //create visual of putOnPuffyJacket frame
        String myStacksClothes = setMessage(i);
        Thread.sleep(_TICK);

        myThings = myThings + " phone";

        /*
            Notice at this point in the code, you have three frames in the debug window.
            This can be shown in the visual as well. The top frame in the stack is our current
            frame, it is all that is being pointed to ta the moment in the code, for the
            purposes of this demo.

            Notice you can view the other stack frames in the debugger and that each string
            value is related to its layer of clothing.

            Those frames are just chilling waiting for us to continue through this puffy jacket
            frame. Let's do that. Step over the rest of the code and watch how the return
            value in each frame updates the string value in the frame.
         */

        newLayer.removeLayer();

        return myStacksClothes;
    }

    //this could be used if you just wanted to call them all recursively in one method, but then
    //you wouldn't see the names in the stack trace while debugging.
    public static void getNewLayer(int i) throws InterruptedException{
        GetDressed newLayer = new GetDressed(i);
        String myStacksClothes = setMessage(i);
        Thread.sleep(_TICK);
        if(++i < 3){
            getNewLayer(i);
        }

    }

    public static String setMessage(int i ){
        System.out.println(bodyImages[i].substring(10,bodyImages[i].indexOf(".")));
        return bodyImages[i].substring(10,bodyImages[i].indexOf("."));
    }
}
