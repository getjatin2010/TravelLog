import java.util.HashMap;
import java.util.List;

/**
 * Created by jatin on 6/11/16.
 */
public class AprioriResult {
    public HashMap<Integer,List<AprioriItemsetResult>> result;
    public int numOfTransactions;
    public AprioriResult()
    {
        result = new HashMap<>();
    }
}
