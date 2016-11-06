import GoogleSchema.LocationResults;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jatin on 18/10/16.
 */
public class CreateTempFile {
    public  Gson gson = new Gson();
    public  int dataValues = 0;
    public  boolean onlyInternational;
    public  int timewise;
    public String dataFile;
    public String tempFile;
    public HashMap<String,Integer> savingData;
    public List<int[]> result;

    CreateTempFile(String dataFile, String tempFile, boolean onlyInternational, int timewise) throws FileNotFoundException {
        this.onlyInternational  = onlyInternational;
        this.timewise = timewise;
        this.dataFile = dataFile;
        this.tempFile = tempFile;
        savingData = new HashMap<>();

    }

    public void startProcess() throws Exception {
        emptyFile(tempFile);
        readFile(dataFile);
        System.out.println("Effective Data Processed :" + dataValues++);
        runAprori();
        printResult();
    }

    private void printResult()
    {
        Iterator i = result.iterator();
        while (i.hasNext())
        {
            int[] resultingArray = (int[]) i.next();
                for(int k =0 ;k<resultingArray.length ;k++)
                {
                    System.out.print(searchInSavingData(resultingArray[k])+" ");
                }
            System.out.println("");
        }
    }
    private void runAprori() throws Exception
    {
        Apriori ap = new Apriori();
        result = ap.go();
    }


    private void readFile(String file) throws IOException {
        BufferedReader bf = new BufferedReader((new FileReader(file)));
        String line = "";
        while ((line = bf.readLine())!=null)
        {
            try
            {
                storeJson(line);
            }
            catch (Exception e)
            {
            }
        }
    }
    private  void storeJson(String s) throws IOException {
        dataValues++;
        ImpData data = gson.fromJson(s,ImpData.class);

        String effectiveDate = "";
        String date = data.date;
        String month = data.month;
        String year = data.year;
        String userCountry = data.userCountry;
        String tweetingCountry = data.tweetingCountry;
        int tweetingCountryNumber=0;
        int userCountryNumber=0;
        int effectiveDateNumber=0;


        if(onlyInternational==true)
        {
            if(userCountry==tweetingCountry)
                return;
        }

        if(timewise==0)
        {
            effectiveDate = date+month+year;
        }
        else if(timewise==1)
        {
            effectiveDate = month+year;
        }
        else if(timewise==2)
        {
            effectiveDate = year;
        }
        else
            return;

        if(!savingData.containsKey(effectiveDate))
            savingData.put(effectiveDate,savingData.size()+1);
        effectiveDateNumber = savingData.get(effectiveDate);


        if(!savingData.containsKey(userCountry))
            savingData.put(userCountry,savingData.size()+1);
        userCountryNumber = savingData.get(userCountry);


        if(!savingData.containsKey(tweetingCountry))
            savingData.put(tweetingCountry,savingData.size()+1);
        tweetingCountryNumber = savingData.get(tweetingCountry);


        String toStore = effectiveDateNumber+" "+userCountryNumber+" "+tweetingCountryNumber;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(tempFile, true));
            bw.write(toStore);
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
        }
    }



    private  void emptyFile(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }


    private String searchInSavingData(int i)
    {
        for ( String key : savingData.keySet() ) {
            int k = savingData.get(key);
                if(k==i)
                    return key;
        }
        return null;
    }

}
