import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jatin on 18/10/16.
 */
public class CreateTempFile {
    public  Gson gson = new Gson();
    public  int dataValues = 0;
    public  int timewise;
    public String dataFile;
    public String tempFile;
    public HashMap<String,Integer> savingData;
    public AprioriResult aprioriResult;

    public BufferedWriter gui_data_bw = null;

    public BufferedWriter gui_support_data_bw = null;

    CreateTempFile(String dataFile, String tempFile, int timewise) throws FileNotFoundException {
        this.timewise = timewise;
        this.dataFile = dataFile;
        this.tempFile = tempFile;
        savingData = new HashMap<>();

    }

    public void startProcess() throws Exception {

        /*

        IN CONSTANTS:-

         */

        emptyFile(tempFile);
        readFile(dataFile);
        System.out.println("Effective Data Processed :" + dataValues++);

        runAprori();

        printResult(aprioriResult.result.get(1),1);


        printResult(aprioriResult.result.get(3),3);


        //Poornima Here you can write the code to make the csv file.
        //Please look at the printResult which converts numbers to countries

        //aprioriResult contains numTransactions and
        //a HaspMap which contains result
        //1. which is integer and List of array of ints
        // ----(Integer represnts number of elements in the transaction)
        // ----(List of AproriItemSetResult contains arrays of ints  (Each array is a transaciton) and its support

    }

    private void printResult(List<AprioriItemsetResult> result,int resultType)
    {
        String data = "";

        if(resultType==1)
        {

            try {
                gui_data_bw = new BufferedWriter(new FileWriter(Constants.GUI_DATA_FILE, false));
                gui_data_bw.write("\"Country\",\"Support\"");
                gui_data_bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(resultType==3)
        {
            try {
                gui_support_data_bw = new BufferedWriter(new FileWriter(Constants.GUI_SUPPORT_DATA_FILE, false));
                gui_support_data_bw.write("\"Country1\",\"Country2\",\"Support\"");
                gui_support_data_bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Iterator i = result.iterator();
        int count = 0;
        while (i.hasNext())
        {
            data="";
            AprioriItemsetResult aprioriItemsetResult = (AprioriItemsetResult)i.next();
            int[] resultingArray = aprioriItemsetResult.result;

            for(int k =0 ;k<resultingArray.length ;k++)
            {

                if(resultType==3) {
                    System.out.print(searchInSavingData(resultingArray[k])+" ");
                    if (k != 0) {
                        data = data + "\"" + searchInSavingData(resultingArray[k]) + "\"" + ",";
                    }
                }

                if(resultType==1)
                {
                    data = "\"" + searchInSavingData(resultingArray[k]) + "\"" + ",";
                }
            }
            if(resultType==3) {
                System.out.print(" Support : " + aprioriItemsetResult.support);
            }
            data = data+aprioriItemsetResult.support;

            if(resultType==1)
            {
                if (count != 0) {
                    try {
                        gui_data_bw.write(data);
                        gui_data_bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            if(resultType==3)
            {
                try {
                    gui_support_data_bw.write(data);
                    gui_support_data_bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("");
            }
            count++;

        }

        if(resultType==1)
        {

            try {
                gui_data_bw.flush();
                gui_data_bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(resultType==3)
        {

            try {
                gui_support_data_bw.flush();
                gui_support_data_bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void runAprori() throws Exception
    {
        Apriori ap = new Apriori();
        aprioriResult = ap.go();
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



        if(userCountry.equals(tweetingCountry))
            return;


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