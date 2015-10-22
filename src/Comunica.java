/*
 * Example of a Fenetre (JFrame) using awt and swing
 * @author your_name
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        initComponents();
        //On crée un FileWriter et un FileReader
        //pour utiliser A2B.txt et B2A.txt

        //les buffer permettent de d'acceder aux info
        //et mettre en place la communication

        // !!! BufferedWriter.flush() aprés chaque écriture !!!
        // pour vider le contenu du buffer.

        //Les fichiers pourront stocker à chaque envoie la ligne produit (message to send)
        //Les fichiers permettront de récupérer chaque ligne (received message)


        //Les opérations sur les entrées et les sorties sont susceptibles de produire des erreurs
        //(par exemple fichier inexistant). Les exceptions devront être capturés
        //(et en conséquence traités et au moins afficher sur la ligne de commande) ou propagés.
    }


    private void Send ()throws IOException
    {

        File outputFile = new File("A2B.txt");
        FileWriter out = new FileWriter(outputFile,true);
        BufferedWriter writer = new BufferedWriter(out);

        writer.write(textToSend.getText());
        writer.newLine();
        writer.flush();

        writer.close();
        out.close();

        System.out.println("bSend pressed !");
        textToSend.setText(""); // On clean le carré d'envoi
    }
    private void Receive()throws IOException
    {
        File inputFile = new File("A2B.txt");
        FileReader in = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(in);

        String line;
        line = reader.readLine();
        textRec.setText(line);


        reader.close();
        in.close();

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



        this.bSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO

                    try {
                        Send();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        });
        this.bReceive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //TODO
                try {
                    Receive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    /** main entry point */
    public static void main(String[] args) {

        Comunica f = new Comunica(null,null);

    }





}