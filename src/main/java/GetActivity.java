import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetActivity {
    private final ActivityService service;

    public GetActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/api/activity/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(ActivityService.class);
    }

    /**
     * @return an activity
     */
    public Observable<Activity> getActivity(String type, int participants) {
        Observable<Activity> observable = service.getNewActivity(type, participants);
        return observable;
    }

}

