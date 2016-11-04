import GoogleSchema.LocationResults;
import GoogleSchema.Result;

import java.util.ArrayList;

/**
 * Created by jatin on 18/10/16.
 */
public class StartClass {
    public static void main(String args[]) throws Exception
    {
        ArrayList<Tweet> tweets =   ReadFile.readFile("/home/jatin/IdeaProjects/TravelLog/src/twt.txt");
        LocationResults locationResults = GetLocationGoogleAPI.executeRequest("52.1326","5.2913");
        System.out.println(GetLocationGoogleAPI.getCountry(locationResults));
    }
}
