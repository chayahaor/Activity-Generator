
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ActivityService {
    @GET("api/activity/")
    Observable<Activity> getNewActivity(@Query("type") String type, @Query("participants") int participants);

}
