/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taschenrechner;


import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author debiananbr
 */
public class Rechner implements WindowListener{
    
    public static JPanel theMainPanel;
    public static Color theMainPanelBackground;
    
    private JInternalFrame internalFrame ;
    private JFrame frame ;
    private JPanel mainPanel;
    
    
    
    /**
     * Konstruktor, um in einem eigenständigen Frame zu laufen. 
     */
    public Rechner (){
        frame = new JFrame();
        setSettingsForFrame();
        mainPanel = new JPanel() ;
        frame.add(mainPanel) ;
        frame.addWindowListener(this);
        mainPanel.setSize(frame.getWidth(),frame.getHeight());
        init();
        frame.setVisible(true);
    }
    
    
    
    /**
     * Konstruktor für die Desktop-Andwendung.
     */
    public Rechner(JInternalFrame _internalFrame){
        internalFrame = _internalFrame ;
        mainPanel =  new JPanel();
        internalFrame.add(mainPanel) ;
        mainPanel.setSize(internalFrame.getWidth(), internalFrame.getHeight());
        init();
    }
    
    
    
    
    
    /**
     * 
     */
    public void init(){
       setMainPanelSettings();
       addControlElements();
        
    }

    
    ///////////////////
    //               //
    //    Setter     //
    //               //
    ///////////////////

    
    /**
     * Alle Settings für das Main-JPanel.
     * Durch das setzten der staic theMainPanelBackground, werden nur 
     * die Backgrounds, der auf dem mainPanel befindlichen Panels diese 
     * Hintergrundfarbe annehmen.
     */
    private void setMainPanelSettings() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setBackColor(new Color(181,205,255));
        mainPanel.setBackground(theMainPanelBackground);
        theMainPanel = mainPanel ;
    }
    
    
    
    /**
     * Setzt die Farbe für den Hintergrund des Taschenrechners.
     * @param Color 
     */
    private void setBackColor(Color color) {
        theMainPanelBackground = color ;
    }
    
    
    
    /**
     * Settings für das Frame.
     * Sollte der Taschenrechner nicht in einem Desktop-Pane laufen.
     */
    private void setSettingsForFrame() {
        frame.setBounds(400, 400, 400,450);
        frame.setResizable(false);
        
    }
    
    
    
    /**
     * Setter für InternalFrame.
     */
    public void setInternalFrame(JInternalFrame _newIntenalFrame) {
        internalFrame = _newIntenalFrame ;
    }
    
    
    /**
     * Setter für mainPanel.
     */
    public void setMainPanel(JPanel _mainPanel) {
        mainPanel = _mainPanel ;
    }
   
    
    
    
    /**
     * Setter für Frame, fals es unabhängig laufen soll.
     */
    public void setFrame(JFrame _newFrame) {
        frame = _newFrame ;
    }
    
    ///////////////////
    //               //
    //    Getter     //
    //               //
    ///////////////////

    /**
     *
     */
    public JInternalFrame getInternalFrame() {
        return internalFrame ;
    }
    
    
    /**
     *
     */
    public JPanel getMainPanel() {
        return mainPanel ;
    }
    
    
    
    /**
     *
     */
    public JFrame getFrame() {
        return frame ;
    }
    
    
    
    ///////////////////
    //               //
    //    Adder      //
    //               //
    ///////////////////

    
    /**
     * Addet dem mainPanel die Tasten und Displays.
     */
    public void addControlElements(){
        ControlElements ce =  new ControlElements() ;
    }
    
    
    ///////////////////
    //               //
    //    Window-    //
    //    listener   //
    //               //
    ///////////////////

    
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
    System.exit(0);
        System.out.println("234");
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) { }
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) { }

    
    
}
