
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Save {

    public static void saveGame(Card[][] cards) {

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            System.out.println("The game is saving..");

            out.writeObject(cards);

        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static Card[][] getTheSave() {

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.bin"))) {


            Card[][] obj = (Card[][]) in.readObject();

            return obj;


        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
