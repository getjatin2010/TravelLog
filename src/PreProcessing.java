import GoogleSchema.LocationResults;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by jatin on 18/10/16.
 */
public class PreProcessing {

    public static boolean READ_FILE = true;
    public static boolean BROLD = false;
    public static Gson gson = new Gson();
    public static void readFile(String file) throws IOException {

        BufferedReader bf = new BufferedReader((new FileReader(file)));
        String line = "";
        while (READ_FILE)
        {
            line = bf.readLine();
            try
            {
                if(line!=null && line!=" ") {
                    BROLD = true;
                    storeJson(line);

                }
                else {

                    if(BROLD) {
                        BROLD = false;
                        emptyFile(file);
                        bf = new BufferedReader((new FileReader(file)));
                    }
                        Thread.sleep(3000);
                }
                }
            catch (Exception e)
            {
            }
        }
    }
    private static void storeJson(String s) throws IOException {
        System.out.print("c");
        Tweet tweet = gson.fromJson(s, Tweet.class);
        boolean checkTweet = checkTweet(tweet);
        if(checkTweet==true) {
            System.out.println();
            System.out.println(tweet.created_at);
            LocationResults locationResults = null;
            String location =null;

            String tweetingLocation = null;
            if(tweet.place.country!=null)
            {
                location = tweet.place.country;

                if(Constants.cityCountry.containsKey(location))
                    tweetingLocation =   Constants.cityCountry.get(location);
                else
                    locationResults = GetCountryNameGoogleAPI.executeRequest(tweet.place.country);
            }


            else if(tweet.place.name!=null)
            {
                location = tweet.place.name;

                if(Constants.cityCountry.containsKey(location))
                    tweetingLocation =   Constants.cityCountry.get(location);
                else
                    locationResults = GetCountryNameGoogleAPI.executeRequest(tweet.place.name);

            }


            else if(tweet.place.full_name!=null)
            {
                location = tweet.place.full_name;

                if(Constants.cityCountry.containsKey(location))
                    tweetingLocation =   Constants.cityCountry.get(location);
                else
                locationResults = GetCountryNameGoogleAPI.executeRequest(tweet.place.full_name);

            }



            if(tweetingLocation ==null)
                tweetingLocation = GetLocationGoogleAPI.getCountry(locationResults);


            System.out.println("TweetingCountry: "+tweetingLocation);
            locationResults = GetCountryNameGoogleAPI.executeRequest(tweet.user.location);
            String userLocation = GetLocationGoogleAPI.getCountry(locationResults);
            System.out.println("UserCountry: "+userLocation);

            if(tweetingLocation!=null && userLocation!=null)
            {
                if(!Constants.cityCountry.containsKey(location))
                Constants.cityCountry.put(location,tweetingLocation);
                ImpData data = new ImpData();
                data.id = tweet.id;
                data.month = tweet.created_at.substring(4,7);
                data.year = tweet.created_at.substring(26,30);
                data.date = tweet.created_at.substring(8,10);
                data.tweetingCountry = tweetingLocation;
                data.userCountry = userLocation;
                storeResultInFile(data);
            }
        }
    }

    private static void storeResultInFile(ImpData data)
    {
        String json = gson.toJson(data);

        BufferedWriter bw = null;

        try {
            // APPEND MODE SET HERE
            bw = new BufferedWriter(new FileWriter(Constants.MAIN_DATA, true));
            bw.write(json);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {                       // always close the file
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                // just ignore it
            }
        } // end try/catch/finally

    }

    private static boolean checkTweet(Tweet tweet)
    {
        if(tweet==null)
            return false;
        if(tweet.created_at==null)
            return false;
        if(tweet.user==null)
            return false;
        if(tweet.user.location==null)
            return false;
        if(tweet.place==null)
            return false;
        if(tweet.place.name==null && tweet.place.country==null && tweet.place.full_name==null)
            return false;
        return true;
    }

    private static void emptyFile(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

}
