
package GoogleSchema;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Geometry {

    @SerializedName("bounds")
    @Expose
    public Bounds bounds;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("viewport")
    @Expose
    public Viewport viewport;

}
