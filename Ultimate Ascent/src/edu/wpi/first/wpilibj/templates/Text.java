package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;

public class Text {
    private static DriverStationLCD dash;
    private static long offset;
    private static int currentLineNumber = 0;
    private static String blank = "                     ";
    
    public static final DriverStationLCD.Line[] DS_LINE_ORDER = {
    DriverStationLCD.Line.kMain6,
    DriverStationLCD.Line.kUser2,
    DriverStationLCD.Line.kUser3,
    DriverStationLCD.Line.kUser4,
    DriverStationLCD.Line.kUser5,
    DriverStationLCD.Line.kUser6};
    
    public Text () {}
    
    public static void consoleWrite(String s) {
        System.out.println((System.currentTimeMillis() - offset) + ": " + s);
    }
    
    public static void scrollWrite(String s) { //dont use this, scrolling txt sucks.
        if (currentLineNumber > 6) currentLineNumber  = 0;
        dsWrite(s, currentLineNumber);
        currentLineNumber++;
    }
    
    public static void dsWrite(String message, int lineNumber) {
        if(dash == null)
            dash = DriverStationLCD.getInstance();
        if (lineNumber >= DS_LINE_ORDER.length || lineNumber < 0) 
            lineNumber = 0;
        offset = System.currentTimeMillis();
        System.out.println((System.currentTimeMillis() - offset) + ": " + message);
        dash.println(DS_LINE_ORDER[lineNumber], 1, blank);
        dash.println(DS_LINE_ORDER[lineNumber], 1, message);
        //_dash.updateLCD();
        
        //_line_num = line_num;
    }
    
    public static void updateWith(String message, int lineNumber) {
        dsWrite(message,lineNumber);
        updateLCD();
    }
    
    public static void updateLCD() {
        dash.updateLCD();
    }    
}