import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by jatin on 18/10/16.
 */
public class ReadFile {

    public static boolean READ_FILE = true;
    public static boolean BROLD = false;
    public static Gson gson = new Gson();
    public static ArrayList<Tweet> readFile(String file) throws IOException {

        BufferedReader bf = new BufferedReader((new FileReader(file)));
        String line = "";
        ArrayList<Tweet> tweets = new ArrayList<>();
        while (READ_FILE)
        {
            line = bf.readLine();
            try
            {
                if(line!=null && line!=" ") {
                    BROLD = true;
                    System.out.println(line);
                    storeJson(line, tweets);

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
        return tweets;
    }
    private static void storeJson(String s, ArrayList<Tweet> tweets)
    {
        Tweet tweet = gson.fromJson(s, Tweet.class);
        tweet.json = s;
        tweets.add(tweet);
    }

    private static void emptyFile(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

}
