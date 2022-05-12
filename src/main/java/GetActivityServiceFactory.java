import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetActivityServiceFactory {
    public ActivityService getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/api/activity/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ActivityService.class);
    }
}
