import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ActivityModule {

    @Singleton
    @Provides
    public ActivityService activityService(ActivityServiceFactory factory) {
        return factory.getInstance();
    }
}
