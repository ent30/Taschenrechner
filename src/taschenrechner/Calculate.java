/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taschenrechner;

import javax.swing.JTextField;

/**
 *
 * @author debiananbr
 */
public class Calculate {
    
    private JTextField outputField ;
    private JTextField memoryField ;
    private String selectedChar ;

    
    
    
    
    /**
     * Konstruktor.
     * @param _selectedChar <String>  
     * @param _outputField  <JTextField>
     * @param _memoryField  <JTextField>
     */
    public Calculate(String _selectedChar,  JTextField _outputField, JTextField _memoryField){
        selectedChar = _selectedChar ;
        outputField = _outputField ;
        memoryField = _memoryField ;    
        init();
        
    }

    
    /**
     * Init.
     */
    private void init() {
        bringTheAction();
    }

    
    
    /**
     * Managed die Action beim drücken eines Der Tasten auf dem Taschenrechner.
     */
    private void bringTheAction() {
        switch(selectedChar){
        //#### INPUT VON ZAHLEN ####//
            case "0":
                dispTheNumber();
                break;
            case "1":
                dispTheNumber();
                break;
            case "2":
                dispTheNumber();
                break;
            case "3":
                dispTheNumber();
                break;
            case "4":
                dispTheNumber();
                break;
            case "5":
                dispTheNumber();
                break;
            case "6":
                dispTheNumber();
                break;
            case "7":
                dispTheNumber();
                break; 
            case "8":
                dispTheNumber();
                break;
            case "9":
                dispTheNumber();
                break;
            case ".":
                // Wenn bereits schon ein Punkt gesetzt wurde, wird kein
                // weiterer mehr gesetzt.
                if(!ControlElements.isDoubled){
                    if(!ControlElements.isNumberPicked){
                        selectedChar = "0"+selectedChar ;
                        ControlElements.isNumberPicked = true;
                    }
                    dispTheNumber();
                    ControlElements.isDoubled = true;
                    
                }
                break;
        //#### OPERATOREN MIT ZWEI ZAHLEN ####//
            case "+": 
                multiNumberOperations();
                break;
            case "-":
                multiNumberOperations();
                break;
            case "*":
                multiNumberOperations();
                break;
            case "/":
                multiNumberOperations();
                break;
            case "%":
                multiNumberOperations() ;
                break;
         //#### OPERATOREN MIT EINER ZAHL ####//       
            case "+-":
                ControlElements.output = new StringBuffer(String.valueOf(Double.parseDouble(ControlElements.output.toString()) * (-1)) ) ;
                outputField.setText(ControlElements.output.toString());
                break;
              // Wurzel
            case "WZL":
                singleNumberOperations();
                break; 
            case "1/x":
                singleNumberOperations();
                break;
                
                
            case "c" :
                if(ControlElements.isEditable && ControlElements.output.toString().length() > 0)
                    deleteLastChar();
                break ;
            case "ce" :
                deletAll();
                break ;
            case "=":
                if(!ControlElements.isMemoryAlive && !ControlElements.isNumberPicked && !ControlElements.isOperatorSelected && !ControlElements.isReadyForNextNumber)
                    break;
                double result = getResult();
                reset(result);
                break;
            default :
                break;
        }//end of switch
        
    }
    
    
    /**
     * Fügt dem Anzeigedisplay ein Zeichen hinzu.
     * Außer es wurde Gleich-Zeichen gedrückt.
     * 
     * Komma wird nicht gesetzt wenn:
     * 1. Gleich-Zeichen gedrückt wurde
     * 2. in der AKTUELLEN Zahl bereits eins vorhanden ist.
     * 3. das Komma an der ersten Stelle stehen würde.
     */
    private void dispTheNumber(){
        if(!ControlElements.isEditable)
            return ;
        if(ControlElements.isReadyForNextNumber){
            ControlElements.output = new StringBuffer();
            ControlElements.isReadyForNextNumber = false ;
        }
        ControlElements.output.append(selectedChar);
        ControlElements.isNumberPicked = true ;
        outputField.setText(ControlElements.output.toString());
    }
    
    
    
    /**
     * Regelt die Aktionen wenn ein Operator gedrückt wurde.
     * 1)
     * Es passiert nichts wenn:
     * 1. das Rechenzeichen das Aller erste Zeichen wäre.
     * 
     * 2)
     * Wurde eine zahl eingegebn und es wird dann ein Operator gedrückt, wird die Zahl
     * und der Operator nach oben in das Zwischenspeicherdisplay gepackt.
     * Wird mehrmals ein Operator gedrückt, nachdem eine Zahl eingegeben wurde, bleibt die Zahl
     * erhalten und der es ändert sich NUR der Operator - auch das sieht man im Zwischenspeicherdisplay.
     * 
     * Wird ein Operator gedrückt wird nachdem man sich über das Gleich-Zeichen ein Ergebnis
     * hat anzeigen lassen, wird das Ergebnis als erste Zahl benutzt siehe 2).
     */
    private void multiNumberOperations(){
        
        if(!ControlElements.isDoubled && !ControlElements.isMemoryAlive && !ControlElements.isNumberPicked && !ControlElements.isOperatorSelected && !ControlElements.isReadyForNextNumber)
            return ;
        
            if(!ControlElements.isReadyForNextNumber && ControlElements.isNumberPicked && ControlElements.isMemoryAlive){
                double result ;
                result = getResult();
                ControlElements.memoryNumber = String.valueOf(result ) ;
                ControlElements.isDoubled = false ;
                ControlElements.isNumberPicked = false ;
                ControlElements.isOperatorSelected = true ;
                ControlElements.isReadyForNextNumber = true ;    
                ControlElements.isEditable = true ;
                ControlElements.operator = selectedChar ;
                ControlElements.memory = ControlElements.memoryNumber + ControlElements.operator ;
                outputField.setText("");
                memoryField.setText(ControlElements.memory.toString());  
            } else {
                ControlElements.isDoubled = false ;
                ControlElements.isNumberPicked = false ;
                ControlElements.isOperatorSelected = true ;
                ControlElements.isReadyForNextNumber = true ;   
                ControlElements.isEditable = true ;
                if(!ControlElements.isMemoryAlive){
                    ControlElements.memoryNumber = ControlElements.output.toString() ;
                }

                ControlElements.operator = selectedChar ;
                ControlElements.memory = ControlElements.memoryNumber + ControlElements.operator ;
                ControlElements.isMemoryAlive = true ;
                outputField.setText("");
                memoryField.setText(ControlElements.memory.toString());  
            }
        

    }

    
    
    /**
     * Rechner für Multi-Rechen-Operationen.
     * @return result als double, Ergebnis der Rechenoperation, abhängig vom ausgewählten Operator.
     */
    private double getResult() {
        double result;
        double firstNumber = Double.parseDouble(ControlElements.memoryNumber) ;
        double secondNumber = Double.parseDouble(ControlElements.output.toString()) ;
        
        switch (ControlElements.operator){
            case "+":
                result = firstNumber + secondNumber ;
                break;
            case "-" :
                result = firstNumber - secondNumber ;
                break; 
            case "*" :
                result = firstNumber * secondNumber ;
                break;
            case "/" :
                result = firstNumber / secondNumber ;
                break;
            case "%" :
                result = firstNumber *(secondNumber/100);
                break;
            default :
                System.err.println("Error in Class Calculate.java ..... Methode getResult() !!");
                result = 0 ;
                break;
        }
        return result ;
    }

    
    
    
    
    /**
     * Setzt die Anzeigedisplays zurück und zeigt das letzte Ergebnis, im ouputDisplay an.
     */
    private void reset(double result) {
        ControlElements.isDoubled = false ;
        ControlElements.isMemoryAlive = false ;
        ControlElements.isNumberPicked = true ;
        ControlElements.isOperatorSelected = false;
        ControlElements.isReadyForNextNumber = false ;
        ControlElements.isEditable = false ;
        ControlElements.output = new StringBuffer(String.valueOf(result)) ;
        ControlElements.memory =  "" ; 
        memoryField.setText("");
        outputField.setText(String.valueOf(result));
    }

    
    
    /**
     * Rechner für Single-Rechen-Operations.
     * Hier wird gleich das Ergebnis im outputDisplay angezeigt und
     * einmal die Methode reset() ausgeführt.
     */
    private void singleNumberOperations() {
       
        double result;
        double number = Double.parseDouble(ControlElements.output.toString()) ;
        
        switch (selectedChar){
            case "WZL" :
                if(number <= 0)
                    return ;
                
                result = Math.sqrt(number) ;
                
                break; 
            case "1/x" :
                result = 1/number ;
                break;
            default :
                System.err.println("Error in Class Calculate.java ..... Methode getResult() !!");
                result = 0 ;
                break;
        }
        
        reset(result) ;
        
    }

    
    
    /**
     * Setzt alles zurück.
     * Als wäre der Taschenrechner neugestartet.
     */
    private void deletAll() {
        ControlElements.isDoubled = false ;
        ControlElements.isMemoryAlive = false ;
        ControlElements.isNumberPicked = false ;
        ControlElements.isOperatorSelected = false;
        ControlElements.isReadyForNextNumber = false ;
        ControlElements.isEditable = true ;
        ControlElements.output = new StringBuffer() ;
        ControlElements.memory =  "" ; 
        memoryField.setText("");
        outputField.setText("");
    }

    
    /**
     * 
     */
    private void deleteLastChar() {
       ControlElements.output = new StringBuffer(ControlElements.output.toString().substring(0,ControlElements.output.toString().length()-1)) ;
       if(ControlElements.output.toString().length() >=0)
           ControlElements.isNumberPicked = false ;
       outputField.setText(ControlElements.output.toString());
    }
    
    
    
    
}
