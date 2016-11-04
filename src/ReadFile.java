import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jatin on 18/10/16.
 */
public class ReadFile {

    public static Gson gson = new Gson();
    public static ArrayList<Tweet> readFile(String file) throws IOException {

        BufferedReader bf = new BufferedReader((new FileReader(file)));
        String line = "";
        ArrayList<Tweet> tweets = new ArrayList<>();
        Integer.parseInt(bf.readLine());
        StringBuffer stringBuffer =  new StringBuffer();
        while ((line = bf.readLine()) != null)
        {
            try
            {
                Integer.parseInt(line);
                storeJson(stringBuffer,tweets);
                stringBuffer = new StringBuffer();
            }
            catch (Exception e)
            {
                 stringBuffer.append(line);
            }
        }
        return tweets;
    }
    private static void storeJson(StringBuffer s, ArrayList<Tweet> tweets)
    {
        Tweet tweet = gson.fromJson(s.toString(), Tweet.class);
        if(tweet.)
        tweet.json = s.toString();
        tweets.add(tweet);
    }

}
