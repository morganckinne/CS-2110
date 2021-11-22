import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 * 
 * 
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private JTextField titleField;
    private JTextField descField;
    private JTextField capField;
    private JComboBox vertBox;
    
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);


        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();
        
        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        // TODO The button needs a listener
        OpenButtonListener bgListen = new OpenButtonListener();
        backgroundImageButton.addActionListener(bgListen);
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setPreferredSize(new Dimension(100,20));
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(350,20));
        
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);
        
        backgroundImagePanel.add(titlePanel, BorderLayout.CENTER);
        
        JLabel descLabel = new JLabel("Description: ");
        descLabel.setPreferredSize(new Dimension(100,20));
        descField = new JTextField();
        descField.setPreferredSize(new Dimension(350,20));
        
        JPanel descPanel = new JPanel();
        descPanel.add(descLabel);
        descPanel.add(descField);
        
        backgroundImagePanel.add(descPanel, BorderLayout.SOUTH);
        
        
        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
        
        JLabel capLabel = new JLabel("Caption: ");
        capLabel.setPreferredSize(new Dimension(100,20));
        capField = new JTextField();
        capField.setPreferredSize(new Dimension(350,20));
        
        JPanel capPanel = new JPanel();
        capPanel.add(capLabel);
        capPanel.add(capField);
        
        memePanel.add(capPanel, BorderLayout.NORTH);
        
        JLabel vertLabel = new JLabel("Vertical Align: ");
        vertLabel.setPreferredSize(new Dimension(100,20));
        String[] options = {"top", "middle", "bottom"};
        vertBox = new JComboBox(options);
        vertBox.setPreferredSize(new Dimension(350,20));
        JPanel vertPanel = new JPanel();
        vertPanel.add(vertLabel);
        vertPanel.add(vertBox);
        
        memePanel.add(vertPanel, BorderLayout.CENTER);
        
        //buttons
        JButton genButt = new JButton("Generate");
        JButton saveButt = new JButton("Save");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(genButt, BorderLayout.WEST);
        buttonPanel.add(saveButt, BorderLayout.EAST);
        
        //save button listener
        SaveButtonListener saveListen = new SaveButtonListener();
        saveButt.addActionListener(saveListen);
        
        //generate button listener
        GenerateButtonListener genListen = new GenerateButtonListener();
        genButt.addActionListener(genListen);
        
        
                
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        controlPanel.add(memePanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }
        }
    }
    
    private class GenerateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            String title = titleField.getText();
            String caption = capField.getText();
            String desc  = descField.getText();
            String vert = vertBox.getSelectedItem().toString();
            String image = backgroundImageFileNameLabel.getText();
            BackgroundImage bg = new BackgroundImage(image, title, desc);
            currentMeme = new GraphicalMeme(bg, caption, user);
            currentMeme.setCaptionVerticalAlign(vert);
            try {
                BufferedImage bimage = currentMeme.compileMeme();
                imageDisplayLabel = new JLabel(new ImageIcon(bimage));
                memeViewPanel.add(imageDisplayLabel);
                memeViewPanel.repaint();
                pack();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println("The error is " + e);
            }
        }
    }
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                try {
                    ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println("The error is: " + e);
                }

            }

        }
    }
}
