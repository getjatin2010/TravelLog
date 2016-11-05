import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jatin on 18/10/16.
 */
public class StartClass {
    public static void main(String args[]) throws Exception
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //final  String  line = bf.readLine();
        final String  line = "/home/jatin/IdeaProjects/TravelLog/src/test.txt";



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
