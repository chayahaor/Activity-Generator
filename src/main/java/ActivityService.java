import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ActivityService {

    @GET("activity/")
    //Single<Activity> getActivity(@Query("type") String type);

    Single<Activity> getActivity(@Query("type") String type, @Query("participants") int participants);
}
