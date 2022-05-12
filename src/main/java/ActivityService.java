import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ActivityService {
    public static final String TYPE ="social";
    @GET("api/activity/type=:"+TYPE)
    Single<Activity> getActivity(@Query("type") String type);


   // Single<Activity> getActivity(@Query("type") String type, @Query("participants") int participants, String type);
}
