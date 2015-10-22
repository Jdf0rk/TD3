/*
 * Example of a Fenetre (JFrame) using awt and swing
 * @author mdesousa
 */

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.io.*;


public class Comunica extends JFrame {

    /** a label for the name */
    private JLabel lmesssend,lmessrec;
    /** a textfield for the name */
    private JTextArea textToSend,textRec;
    /** a button to perform an action: e.g. say hello (TBD) */
    private JButton bSend,bReceive;

    /**Les fuckings buffers*/
    private BufferedWriter writer;
    private BufferedReader reader;



    /** Creates a Fenetre */
    public Comunica(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
        initComponents();
    }


    private void send ()throws IOException
    {

        writer.write(textToSend.getText());
        writer.newLine();
        writer.flush();

        writer.close();


        System.out.println("bSend pressed !");
        textToSend.setText(""); // On clean le carré d'envoi
    }
    private void receive()throws IOException
    {

        String line;
        line = reader.readLine();
        textRec.setText(line);


        reader.close();


        System.out.println("bReceive pressed");
    }


    /** Initializes the Fenetre components */

    private void initComponents() {

        //Ici on fait en sorte que la fenetre se ferme normalement
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // create the components
        // a new label with the "Nom" as value
        lmesssend = new JLabel("Message to send");
        lmessrec = new JLabel("Received message");
        // a new text field with 20 columns
        textToSend = new JTextArea();
        textRec = new JTextArea();
        // a new button identified as OK
        bSend = new JButton("Send");
        bReceive = new JButton("Receive");
        // configures the JFrame layout using a border layout
        this.setLayout(new GridLayout(3,2));
        // places the components in the layout
        this.add(lmesssend,"1");
        this.add(textToSend,"2");
        this.add(bSend,"3");
        this.add(bReceive,"4");
        this.add(lmessrec,"5");
        this.add(textRec,"6");
        this.textRec.setEditable(false);
        //this.add(new JScrollPane(textToSend));
        //this.add(new JScrollPane(textRec));
        // packs the fenetre: size is calculated
        // regarding the added components
        this.pack();
        this.setSize(600,250);
        // the JFrame is visible now
        this.setVisible(true);



        this.bSend.addActionListener(event -> {
             {
                try {
                    send();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        this.bReceive.addActionListener(event -> {
            {
                try {
                    receive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    /** main entry point */
    public static void main(String[] args) {

        String filename1 = "B2A.txt";
        String filename2 = "A2B.txt";

        if (0 == Integer.parseInt(args[0])) //Client
        {
            filename1 = "A2B.txt";
            filename2 = "B2A.txt";
        }


        try
        {
            FileWriter out = new FileWriter(new File(filename1), true);
            BufferedWriter writer = new BufferedWriter(out);
            FileReader in = new FileReader(new File(filename2));
            BufferedReader reader = new BufferedReader(in);
            Comunica f = new Comunica(reader, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

}





