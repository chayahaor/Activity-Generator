import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ActivityModule {

    @Singleton
    @Provides
    public ActivityService openWeatherMapService(GetActivityServiceFactory factory) {
        return factory.getInstance();
    }
}
