/**
 * Created by jatin on 6/11/16.
 */
public class StartMining {

    public static void main(String args[])
    {
        try {
            CreateTempFile createTempFile = new CreateTempFile(Constants.PRE_PROCESSED_FILE,Constants.TEMP_FILE,false,1);
            createTempFile.startProcess();
        }
        catch (Exception e)
        {e.printStackTrace();

            System.out.println(e.toString());
        }
    }
}
