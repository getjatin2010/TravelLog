import java.util.HashMap;

/**
 * Created by jatin on 18/10/16.
 */
public class Constants {
    //Poornima's keys
    public static String GOOGLE_MAPS_API_KEY = "AIzaSyCbC9k0nhmaAZPTWLj5CUcmQ2167-bfYjQ";
    public static String GOOGLE_MAPS_API_KEY_2 = "AIzaSyBzrDGHMNmlVeQaBUC5l_rx7iUbyk4uG68";
    //Jatin's keys
    public static String GOOGLE_MAPS_API_KEY_NEW_JATIN = "AIzaSyDoqhzOnTkzUeFouUBL636BgLUWjZbyxdk";
    public static String GOOGLE_MAPS_API_KEY_OLD = "AIzaSyAMO2RcM8hGEj8gzq2iZOOrL4CnFYDX3ss";

    public static String PRE_PROCESSED_FILE = "mainData.txt";
    public static String COMMAND_SCRIPT = "python twitter_streaming.py";
    public static String TRAVEL_RAW_FILE = "travelLogData.txt";
    public static String TEMP_FILE = "TempFile.txt";
    public static String MAIN_DATA = "mainData.txt";
    public static String GUI_DATA_FILE = "/var/www/html/data.csv";
    public static String GUI_SUPPORT_DATA_FILE = "/var/www/html/support_data.csv";
    public static double MIN_SUPPORT = .01;
    public static HashMap<String,String > cityCountry = new HashMap();
}
