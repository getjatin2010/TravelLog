
package GoogleSchema;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Viewport {

    @SerializedName("northeast")
    @Expose
    public Northeast_ northeast;
    @SerializedName("southwest")
    @Expose
    public Southwest_ southwest;

}
