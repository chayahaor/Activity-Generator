import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Provider;

import static org.mockito.Mockito.*;

class ActivityPresenterTest {
    @BeforeAll
    static void beforeAllTests() {
        RxJavaPlugins.setIoSchedulerHandler((scheduler) -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler((scheduler) -> Schedulers.trampoline());
    }

    @Test
    void loadActivityFromInput() {
        //given
        ActivityFrame view = mock(ActivityFrame.class);
        ActivityService model = mock(ActivityService.class);
        Provider<ActivityFrame> viewProvider = () -> view;
        ActivityPresenter presenter = new ActivityPresenter(viewProvider, model);
        Activity activity = mock(Activity.class);
        doReturn(2).when(activity).getParticipants();
        doReturn("Compliment someone").when(activity).getNextActivity();
        doReturn(Single.just(activity))
                .when(model).getActivity("social", 2);

        //when
        presenter.loadActivityFromInput("social", 2);

        //then
        verify(view).setActivity("Compliment someone can be done with 2 people");
    }

    @Test
    void noActivity() {
        //given
        ActivityFrame view = mock(ActivityFrame.class);
        Provider<ActivityFrame> viewProvider = () -> view;
        ActivityService model = mock(ActivityService.class);
        ActivityPresenter presenter = new ActivityPresenter(viewProvider, model);
        Activity activity = mock(Activity.class);
        doReturn(Single.just(activity)).when(model).getActivity("diy",5);

        //when
        presenter.loadActivityFromInput("diy", 5);

        //then
        verify(view).setLink(null);
    }
}