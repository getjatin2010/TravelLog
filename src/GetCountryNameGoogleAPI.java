import GoogleSchema.AddressComponent;
import GoogleSchema.LocationResults;
import GoogleSchema.Result;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jatin on 3/10/16.
 */
public class GetCountryNameGoogleAPI {
    static Gson  gson = new Gson();
    static CloseableHttpClient   httpClient = HttpClients.createDefault(); ;
    public static LocationResults executeRequest(String locationName) throws IOException {
        int random = (int) (Math.random()*2);

        String requestURL ="";
        if(random==0)
            requestURL = "https://maps.googleapis.com/maps/api/geocode/json?address="+locationName+"&key="+Constants.GOOGLE_MAPS_API_KEY;
        else
            requestURL = "https://maps.googleapis.com/maps/api/geocode/json?address="+locationName+"&key="+Constants.GOOGLE_MAPS_API_KEY_2;

        requestURL = requestURL.replace(" ","%20");
        HttpGet request = new HttpGet(requestURL);
        HttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        LocationResults locationResults = gson.fromJson(result,LocationResults.class);
        return locationResults;
    }

    public static String getCountry(LocationResults locationResults)
    {
        List<Result> results = locationResults.results;
        Iterator i = results.iterator();
        String country = null;
        while (i.hasNext())
        {
            Result result  = (Result) i.next();
            if(result!=null)
            {
                List<AddressComponent> addressComponents =  result.addressComponents;
                Iterator addressComponentsIterator = addressComponents.iterator();
                while (addressComponentsIterator.hasNext())
                {
                    AddressComponent addressComponent = (AddressComponent)addressComponentsIterator.next();
                    List<String> types = addressComponent.types;
                    if( types.contains("country"))
                        country = addressComponent.longName;
                    if(country!=null)
                        break;
                }
            }
            if(country!=null)
                break;
        }
        return  country;
    }
}
