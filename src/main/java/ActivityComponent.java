import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    ActivityFrame getActivityFrame();
}

