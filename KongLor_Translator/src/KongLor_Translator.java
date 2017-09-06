import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.AudioClip;

/**
 * Kong Lor
 * CSci 1130
 * Fall 2016
 * Translator Project
 */
public class KongLor_Translator extends JApplet implements ActionListener {
    String[] animalName = {"dog", "cat", "chicken", "cow", "pig", "horse", "monkey", "tiger","bear","elephant"};
    String[] hmongName = {"Aub", "Miv", "Qaib", "Nyuj", "Npua", "Nees", "Liab","Tsov", "Dais","Ntxhw"};
    String[] animalPic = {"dog.png", "cat.png", "chicken.png", "cow.png", "pig.png",
            "horse.png", "monkey.png", "tiger.png","bear.png","elephant.png"};
    String [] animalSounds = {"dog2.wav","cat.wav","chicken.wav","cow.wav","pig.wav","horse.wav",
            "monkey.wav","tiger.wav","bear.wav","elephant.wav"};
    String errorSound = "error.wav";
    AudioClip ac;

    Image currentImage;
    ImageIcon imgIcon;

    JButton translate;
    JLabel title, imageLabel,bottomLabel,leftLabel, rightLabel;
    JTextArea output,input;
    JPanel titlePanel, inputOutputPanel, imagePanel, centerPanel, bottomPanel,
            leftPanel, rightPanel;

    public void init() {
        setLayout(new BorderLayout());
        setupTitle();
        setupCenter();
        setupBottom();
        setupLeftSide();
        setupRightSide();
    }
    public void setupTitle() {
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(Color.DARK_GRAY);
        title = new JLabel("<html><h1 style=\"font-family:comic sans ms;\"><font size=+10 color=red><b><i>Type " +
                "an Animal in English to get the Hmong translation!</i></b></font></h1></html>");;
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
    }
    public void setupBottom(){
        bottomPanel=new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomLabel = new JLabel("<html><h1 style=\"font-family:comic sans ms;\"><font size=+2 color=red><b><i>English to " +
                "Hmong Translator! (now with cool sound effects)</i></b></font></h1></html>");
        bottomPanel.add(bottomLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    public void setupLeftSide(){
        leftPanel=new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.RED,10));
        leftPanel.setBackground(Color.ORANGE);
        leftLabel= new JLabel("<html><h1 style=\"font-family:comic sans ms;\"><font size=+10 color=red><b><i>" +
                "<u>Animal List:</u><br><br>Dog" +
                "<br>Cat<br>Chicken<br>Cow<br>Pig</i></b></font></h1></html>");
        leftPanel.add(leftLabel);
        add(leftPanel, BorderLayout.WEST);
    }
    public void setupRightSide(){
        rightPanel=new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.RED,10));
        rightPanel.setBackground(Color.ORANGE);
        rightLabel= new JLabel("<html><h1 style=\"font-family:comic sans ms;\"><font size=+10 " +
                "color=red><b><i><u>Animal List:</u><br><br>Horse<br>" +
                "Monkey<br>Tiger<br>Bear<br>Elephant</i></b></font></h1></html>");
        rightPanel.add(rightLabel);
        add(rightPanel, BorderLayout.EAST);
    }
    public void setupCenter() {
        centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED,10));
        //SET INPUT & OUTPUT PANEL
        inputOutputPanel = new JPanel(new FlowLayout());
        inputOutputPanel.setBackground(Color.ORANGE);
        input = new JTextArea(2,15);
        input.setText("Please Enter Animal ");
        translate = new JButton("Translate");
        translate.addActionListener(this);
        output = new JTextArea("", 2, 25);
        inputOutputPanel.add(input);
        inputOutputPanel.add(translate);
        inputOutputPanel.add(output);
        //IMAGE PANEL
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(Color.ORANGE);
        imageLabel = new JLabel();
        currentImage = getImage(getCodeBase(), animalPic[0]);
        imgIcon = new ImageIcon(currentImage);
        imageLabel = new JLabel(imgIcon);
        imagePanel.add(imageLabel);
        // ADD TO CENTER PANEL
        centerPanel.add(inputOutputPanel);
        centerPanel.add(imagePanel);
        //junglePanel.add(centerPanel);
        add(centerPanel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == translate) {
            String inputString = input.getText();
            if (!inputString.equalsIgnoreCase("")) {
                int i = translate(inputString);
                if (i > -1) {
                    output.setText(inputString + " translated in Hmong is:   " + hmongName[i]);
                    currentImage= getImage(getCodeBase(),animalPic[i] );
                    imgIcon=new ImageIcon(currentImage);
                    imageLabel.setIcon(imgIcon);
                    ac = getAudioClip(getCodeBase(), animalSounds[i]);
                    ac.play();
                }
                else {
                    output.setText(inputString + " not found..TRY AGAIN! ");
                    currentImage= getImage(getCodeBase(),"error2.jpg");
                    imgIcon=new ImageIcon(currentImage);
                    imageLabel.setIcon(imgIcon);
                    ac = getAudioClip(getCodeBase(), errorSound);
                    ac.play();
                }
            } else {
                output.setText("ERROR!! ERROR!!");
                input.setText("Please enter animal ");
                currentImage= getImage(getCodeBase(),"error.jpg");
                imgIcon=new ImageIcon(currentImage);
                imageLabel.setIcon(imgIcon);
                ac = getAudioClip(getCodeBase(), errorSound);
                ac.play();
            }
        }
        repaint();
    }

    public int translate(String input) {
        int i = 0;
        while (i < animalName.length) {
            if (input.equalsIgnoreCase(animalName[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
