import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by jatin on 6/11/16.
 */
public class StartMining {

    public static void main(String args[]) throws Exception
    {

        int timewise = 1;
        try {

            BufferedReader bf = new BufferedReader((new FileReader("support.txt")));
            String line = bf.readLine();
            if (line != null && line!="\n" && line!= "") {
                Constants.MIN_SUPPORT = Double.parseDouble(line);
            }
            line = bf.readLine();

            if (line != null && line!="\n" && line!= "")
            {
                timewise = Integer.parseInt(line);
            }
            line = bf.readLine();
            if (line != null && line!="\n" && line!= "") {
                Constants.GUI_DATA_FILE = line;
            }

            line = bf.readLine();
            if (line != null && line!="\n" && line!= "") {
                Constants.GUI_SUPPORT_DATA_FILE = line;
            }

        }
        catch (Exception e)
        {

        }

        try {
            CreateTempFile createTempFile = new CreateTempFile(Constants.PRE_PROCESSED_FILE,Constants.TEMP_FILE,timewise);
            createTempFile.startProcess();
        }
        catch (Exception e)
        {e.printStackTrace();

            System.out.println(e.toString());
        }
    }
}
