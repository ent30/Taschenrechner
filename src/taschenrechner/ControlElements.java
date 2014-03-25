/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taschenrechner;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author debiananbr
 */
public class ControlElements implements ActionListener{

    
    public static StringBuffer output ;
    public static String  memory ;
    public static String operator ;
    public static String  memoryNumber ;
    public static boolean isNumberPicked ;
    public static boolean isOperatorSelected ;
    public static boolean isDoubled ;
    public static boolean isReadyForNextNumber ;
    public static boolean isMemoryAlive ;
    public static boolean isEditable = true ;
    
    
    
    private JPanel keyBoardPanel ;
    private JPanel calcOutputPanel;
    private JPanel mainPanel ;
    private  JTextField outputField ;
    private  JTextField memoryField ;
    
    
    /**
     * 
     */
    public ControlElements (){
       output = new StringBuffer();
       memory = new String() ;
       mainPanel = Rechner.theMainPanel ;
       keyBoardPanel = new JPanel() ;
       calcOutputPanel = new JPanel() ;
       init();
                              
    }

    
    /**
     * 
     */
    private void init() {
       addCalcOutput();
       setSettingsForKeyBoardPanel();
       setSettingsForCalcOutputPanel();
       addKeys(); 
    }

    
    
    
    ///////////////////
    //               //
    //    Setter     //
    //               //
    ///////////////////
    
    
    
    
    /**
     * Settings für das Jpanel in dem die Anzeigefenster angeordnet sind.
     */
    public void setSettingsForCalcOutputPanel(){
        calcOutputPanel.setBackground(Rechner.theMainPanelBackground);
    }
    
    /**
     * Settings für das JPanel in dem die Tasten angeordnet sind.
     */
    private void setSettingsForKeyBoardPanel() {
        keyBoardPanel.setBackground(Rechner.theMainPanelBackground);
        keyBoardPanel.setSize(mainPanel.getWidth(),mainPanel.getHeight());
        keyBoardPanel.setVisible(true);
        keyBoardPanel.setLayout(new GridBagLayout());
        keyBoardPanel.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
                
        mainPanel.add(keyBoardPanel) ;  
    }
    
    
    

    ///////////////////
    //               //
    //    Adder      //
    //               //
    ///////////////////
    
    
    
    
    /**
     * Fügt die Tasten des Taschenrechners hinzu.
     * Sind in einem Gridbaglayout aangeordnet.
     */
    private void addKeys() {
         //String[][] buts = new String[5][5] ;
        String buts [][] = {{"ce","ce","c","+-","WZL"},{"7","8","9","/","%"},{"4","5","6","*","1/x"},{"1","2","3","-","="},{"0","0",".","+"}};
        
        
        for(int i=0 ; i<5; i++){
            for(int j=0 ; j<buts[i].length ; j++){
                JButton theButton = new JButton(buts[i][j]) ;
                theButton.addActionListener(this);
                
                GridBagConstraints c = new GridBagConstraints() ;
                 
                if(i == 0 && j == 0){
                    c.fill = GridBagConstraints.HORIZONTAL ;
                    c.gridx= j ;
                    c.gridy= i ;
                    c.ipadx=10 ;
                    c.ipady=20 ;
                    c.gridwidth = 2 ;
                    c.insets = new Insets(5, 5, 5, 5) ;
                    keyBoardPanel.add(theButton,c);
                    j++ ;
                } else if(i == 3 && j == 4){
                    c.fill = GridBagConstraints.VERTICAL ;
                    c.gridx= j ;
                    c.gridy= i ;
                    c.gridheight = 2 ;
                    c.ipadx=30 ;
                    c.ipady=20 ;
                    c.insets = new Insets(5, 5, 5, 5) ;
                    keyBoardPanel.add(theButton,c);
                    //j++ ;
                } else if(i == 4 && j == 0 ){
                    c.fill = GridBagConstraints.HORIZONTAL ;
                    c.gridx= j ;
                    c.gridy= i ;
                    c.ipadx=10 ;
                    c.ipady=20 ;
                    c.gridwidth = 2 ;
                    c.insets = new Insets(5, 5, 5, 5) ;
                    keyBoardPanel.add(theButton,c);
                    j++ ;
                } else {
                    c.fill = GridBagConstraints.HORIZONTAL ;
                    c.gridx= j ;
                    c.gridy= i ;
                    c.ipadx=10 ;
                    c.ipady=20 ;
                    c.insets = new Insets(5, 5, 5, 5) ;
                    keyBoardPanel.add(theButton,c);
                }
                 
            }//end of for(j)
        }//end of for(i)
       
        
    }

    
    /**
     * Erstellt das JPanel in dem die Anzeigefelder angezeigt werden.
     */
    private void addCalcOutput() {
       //calcOutput.setBackground(Color.blue);
       calcOutputPanel.setSize(mainPanel.getWidth(), 200);
       calcOutputPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 0, 30));
       calcOutputPanel.setLayout(new BoxLayout(calcOutputPanel, BoxLayout.Y_AXIS));
       addTextFields();
       mainPanel.add(calcOutputPanel) ;
       
    }

    
    
    /**
     * Erstellt die beiden Displays.
     * 1. für die anzeige der aktuellen Eingabe.
     * 2. für die Zwischenanzeige. d.h. letzte Zahl und Rechenzeichen.
     */
    private void addTextFields() {
        Font f =  new Font(Font.SANS_SERIF, Font.BOLD , 20);
        
        outputField =  new JTextField("0") ;
        outputField.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        outputField.setHorizontalAlignment(JTextField.RIGHT);
        outputField.setFont(f);
        outputField.setEditable(false);
        outputField.setBackground(Color.white);
        
        memoryField =  new JTextField() ;
        memoryField.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        memoryField.setHorizontalAlignment(JTextField.RIGHT);
        memoryField.setEditable(false);
        memoryField.setBackground(Color.white);
        calcOutputPanel.add(memoryField);
        calcOutputPanel.add(outputField) ;
    }
    
    
    
    
    ///////////////////
    //               //
    //    Action-    //
    //    listener   //
    //               //
    ///////////////////
    
    
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       JButton clicked = (JButton) e.getSource() ;
        Calculate calc = new Calculate(clicked.getText(), outputField, memoryField) ;
    }

    
}
