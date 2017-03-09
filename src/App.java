import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class App extends JFrame {
    
    
    /******************************
    * Declared variables
    ********************************/
    private JPanel contentPane;
    
    //Radio Buttons
    private JRadioButton rbLowerCase;
    private JRadioButton rbUpperCase;
    //Radio Button Groups
    private final ButtonGroup buttonGroup = new ButtonGroup();

    //Buttons
    private JButton btnFormat;
    private JButton btnReset;
    
    //Text input
    private JTextArea textInput;
    
    //TextOutput
    private JTextArea textOutput1;
    private JTextArea textOutput2;
    
    //Check boxes
    private JCheckBox tenSpaces;
    
    //Drop down menus
    private JComboBox charPerLine;
    
    //Variables for calculation
    private int characters;
    private boolean spaces;
    private boolean cases;
    private String inputSequence;

    /********************************
     * Program run
     ********************************/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App frame = new App();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /********************************
     * Frame work
     ********************************/
    public App() {
        
        /********************************
         * Window parameters
         ********************************/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 476);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);
        
        /********************************
         * Scroll Panes
         ********************************/
        
        //Scroll Pane for text input
        JScrollPane scrollPaneInput1 = new JScrollPane();
        sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneInput1, 0, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneInput1, 10, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneInput1, 291, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneInput1, 504, SpringLayout.WEST, contentPane);
        scrollPaneInput1.setViewportBorder(null);
        contentPane.add(scrollPaneInput1);
        
        //Scroll Pane for text output1
        JScrollPane scrollPaneTextOutput1 = new JScrollPane();
        sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneTextOutput1, 0, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneTextOutput1, 6, SpringLayout.EAST, scrollPaneInput1);
        sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneTextOutput1, -10, SpringLayout.EAST, contentPane);
        scrollPaneTextOutput1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPaneTextOutput1);
        
        //Scroll Pane for text output1
        JScrollPane scrollPaneTextOutput2 = new JScrollPane();
        sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneTextOutput1, -6, SpringLayout.NORTH, scrollPaneTextOutput2);
        sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPaneTextOutput2, 6, SpringLayout.SOUTH, scrollPaneInput1);
        sl_contentPane.putConstraint(SpringLayout.WEST, scrollPaneTextOutput2, 311, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPaneTextOutput2, -10, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, scrollPaneTextOutput2, -5, SpringLayout.EAST, contentPane);
        scrollPaneTextOutput2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneTextOutput2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPaneTextOutput2);
        
        /********************************
         *  Text boxes
         ********************************/
        
        //Text box input1 = Enter sequence here
        textInput = new JTextArea();
        textInput.setLineWrap(true);
        textInput.setBackground(new Color(255, 255, 255, 255));
        scrollPaneInput1.setViewportView(textInput);
        
        //Text box output1 = reformatted sequenced
        textOutput1 = new JTextArea();
        textOutput1.setEditable(false);
        textOutput1.setBackground(new Color(240, 240, 240, 240));
        textOutput1.setLineWrap(true);
        scrollPaneTextOutput1.setViewportView(textOutput1);
        
        //Text box output2 = Statistics
        textOutput2 = new JTextArea();
        textOutput2.setBackground(new Color(240, 240, 240, 240));
        textOutput2.setEditable(false);
        scrollPaneTextOutput2.setViewportView(textOutput2);
        
        /********************************
         *  Labels
         ********************************/
        
        JLabel labelCharacter = new JLabel("Characters Per Line");
        sl_contentPane.putConstraint(SpringLayout.NORTH, labelCharacter, 292, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, labelCharacter, 10, SpringLayout.WEST, contentPane);
        contentPane.add(labelCharacter);

        JLabel labelSpace = new JLabel("Space per 10 chars");
        sl_contentPane.putConstraint(SpringLayout.NORTH, labelSpace, 17, SpringLayout.SOUTH, labelCharacter);
        sl_contentPane.putConstraint(SpringLayout.WEST, labelSpace, 0, SpringLayout.WEST, scrollPaneInput1);
        contentPane.add(labelSpace);
        
        JLabel labelLetterCase = new JLabel("Letter Case");
        sl_contentPane.putConstraint(SpringLayout.WEST, labelLetterCase, 0, SpringLayout.WEST, scrollPaneInput1);
        contentPane.add(labelLetterCase);
        
        /********************************
         *  Drop down menu for sequence line length
         ********************************/        
        
        charPerLine = new JComboBox();
        sl_contentPane.putConstraint(SpringLayout.NORTH, charPerLine, 292, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, charPerLine, 6, SpringLayout.EAST, labelCharacter);
        charPerLine.setModel(new DefaultComboBoxModel(new String[] {"40", "50", "60", "70"}));
        charPerLine.setSelectedIndex(2);
        contentPane.add(charPerLine);
        
        /********************************
         *  Spaces check box per 10
         ********************************/
        
        tenSpaces = new JCheckBox("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, tenSpaces, 6, SpringLayout.SOUTH, charPerLine);
        sl_contentPane.putConstraint(SpringLayout.WEST, tenSpaces, 0, SpringLayout.WEST, charPerLine);
        sl_contentPane.putConstraint(SpringLayout.EAST, tenSpaces, 0, SpringLayout.EAST, charPerLine);
        contentPane.add(tenSpaces);
       
        /********************************
         *  Radio buttons for case sensitivity
         ********************************/
        
        rbUpperCase = new JRadioButton("Upper Case");
        sl_contentPane.putConstraint(SpringLayout.NORTH, labelLetterCase, 4, SpringLayout.NORTH, rbUpperCase);
        buttonGroup.add(rbUpperCase);
        contentPane.add(rbUpperCase);
        
        rbLowerCase = new JRadioButton("Lower Case");
        sl_contentPane.putConstraint(SpringLayout.WEST, rbUpperCase, 6, SpringLayout.EAST, rbLowerCase);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, tenSpaces, -11, SpringLayout.NORTH, rbLowerCase);
        sl_contentPane.putConstraint(SpringLayout.NORTH, rbLowerCase, -4, SpringLayout.NORTH, labelLetterCase);
        sl_contentPane.putConstraint(SpringLayout.WEST, rbLowerCase, 6, SpringLayout.EAST, labelLetterCase);
        rbLowerCase.setSelected(true);
        buttonGroup.add(rbLowerCase);
        contentPane.add(rbLowerCase);
        
        /********************************
         *  Buttons : Reset + Reformat
         ********************************/
        
        btnReset = new JButton("Reset");
        sl_contentPane.putConstraint(SpringLayout.NORTH, rbUpperCase, 44, SpringLayout.SOUTH, btnReset);
        sl_contentPane.putConstraint(SpringLayout.NORTH, btnReset, 292, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnReset, -17, SpringLayout.WEST, scrollPaneTextOutput2);
        contentPane.add(btnReset);
        
        btnFormat = new JButton("Format");
        sl_contentPane.putConstraint(SpringLayout.WEST, btnFormat, 10, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnFormat, -10, SpringLayout.SOUTH, contentPane);
        contentPane.add(btnFormat);
        
        /********************************
         *  To trigger events
         ********************************/
        createEvents();
    }
    
    private void createEvents(){
        /********************************
         *  Button action : Reset
         ********************************/
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you want to reset?");
                if (reply == JOptionPane.YES_OPTION){
                    rbLowerCase.setSelected(true);
                    charPerLine.setSelectedIndex(2);
                    tenSpaces.setSelected(false);
                    textInput.setText(null);
                    textOutput1.setText(null);
                    textOutput2.setText(null);  
                }
            }
        });
        /********************************
         *  Button action : Format
         ********************************/
        btnFormat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /********************************
                 *  Determine if sequence is valid
                 ********************************/
                inputSequence = textInput.getText();
                Pattern pattern = Pattern.compile("[^acgtACGT]");
                Matcher matcher = pattern.matcher(inputSequence);    
   
                boolean isValid = matcher.find();
                
                if (isValid){
                    JOptionPane.showMessageDialog(null, "INVALID SEQUENCE");
                }
                else {
                    /********************************
                     *  Generation of output for textOutput1 and textOutput2 
                     ********************************/
                    
                    //check user parameters
                    characters = Integer.parseInt((String) charPerLine.getSelectedItem());
                    spaces = tenSpaces.isSelected();
                    cases = rbLowerCase.isSelected();
                    
                    //textOutput1 = reformated string
                    String output1 = processString(inputSequence,characters,spaces,cases);
                    textOutput1.setText(output1);
                    
                   //textOutput1 = statistics
                    String output2 = "Sequence length : " + inputSequence.length() + "\n" 
                                + basePercentCalculate("A",inputSequence.toUpperCase()) 
                                + basePercentCalculate("C",inputSequence.toUpperCase()) 
                                + basePercentCalculate("T",inputSequence.toUpperCase()) 
                                + basePercentCalculate("G",inputSequence.toUpperCase());
                    textOutput2.setText(output2);
               }
            }
        });
    }
    /**
     * processString : reformat string to match user defined parameters
     * @param inputSequence
     * @param characters
     * @param spaces
     * @param cases
     * @return
     */
    private String processString (String inputSequence,int characters,boolean spaces,boolean cases){
        
        //iterates through string 
        char [] stringToCharArray = inputSequence.toCharArray();
        
        //count per char
        int index = 0;
        
        //temporary string handler
        StringBuilder base = new StringBuilder();
        
        for(char current:stringToCharArray){
            //Add to array till index satisfies character length or requires spaces
            base.append(current);
            index++;
            if (index%characters == 0){
                base.append("\n");
            }
            else if((spaces == true)&&(index%10==0) ){
                base.append(" ");
            }
        }
        //Convert according to string sensitivity
        return (cases)? base.toString().toLowerCase() : base.toString().toUpperCase();
    }
    /**
     * basePercentCalculate : # of Bases / String length
     * @param base
     * @param input
     * @return 
     */
    private String basePercentCalculate(String base, String input){
        double original = input.length();
        double replace = input.replace(base, "").length();
        
        BigDecimal result= new BigDecimal((original-replace)/original*100);
        result = result.setScale(2, RoundingMode.CEILING);
        
        String output = "Calculated percentage of "+ base + " : " + result +"%\n";
        return output;
    }
}
