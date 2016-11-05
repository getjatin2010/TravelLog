import GoogleSchema.LocationResults;
import GoogleSchema.Result;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jatin on 18/10/16.
 */
public class StartClass {
    public static void main(String args[]) throws Exception
    {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ReadFile.readFile("/home/jatin/IdeaProjects/TravelLog/src/test.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        LocationResults locationResults = GetLocationGoogleAPI.executeRequest("52.1326","5.2913");
        System.out.println(GetLocationGoogleAPI.getCountry(locationResults));

         LocationResults locationResults2 = GetCountryNameGoogleAPI.executeRequest("paris");
         System.out.println(GetLocationGoogleAPI.getCountry(locationResults2));


    }
}
