import java.io.*;
import java.util.Map;

/**
 * Created by jatin on 18/10/16.
 */
public class StartPreProcessing {
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //final  String  line = bf.readLine();
//        final String  line = "/home/poornima/travelLogData.txt";
        final String  line = Constants.TRAVEL_RAW_FILE;



        //*******WRITES TWITTER DATA TO THE FILE*****************

        Thread runTweetScript = new Thread(new Runnable() {
            @Override
            public void run() {
                String s =null;
                BufferedWriter bw = null;

                try {

                    // run the Unix "ps -ef" command
                    // using the Runtime exec method:
                    bw = new BufferedWriter(new FileWriter(Constants.TRAVEL_RAW_FILE, true));
                    Process p = Runtime.getRuntime().exec(Constants.COMMAND_SCRIPT);

                    BufferedReader stdInput = new BufferedReader(new
                            InputStreamReader(p.getInputStream()));

                    BufferedReader stdError = new BufferedReader(new
                            InputStreamReader(p.getErrorStream()));

                    // read the output from the command
                    //System.out.println("Here is the standard output of the command:\n");

                    while ((s = stdInput.readLine()) != null) {
                        //System.out.println(s);
                        bw.write(s);
                        bw.newLine();
                    }



                    // read any errors from the attempted command
                    System.out.println("Here is the standard error of the command (if any):\n");
                    while ((s = stdError.readLine()) != null) {
                        System.out.println(s);
                    }

                    System.exit(0);
                }
                catch (IOException e) {
                    System.out.println("exception happened - here's what I know: ");
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });
        runTweetScript.start();

    //*******************************************************************

        Thread preprocessing = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PreProcessing.readFile(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        preprocessing.start();

//         LocationResults locationResults = GetLocationGoogleAPI.executeRequest("52.1326","5.2913");
//         System.out.println(GetLocationGoogleAPI.getCountry(locationResults));
//
//         LocationResults locationResults2 = GetCountryNameGoogleAPI.executeRequest("paris");
//         System.out.println(GetLocationGoogleAPI.getCountry(locationResults2));


    }



}
