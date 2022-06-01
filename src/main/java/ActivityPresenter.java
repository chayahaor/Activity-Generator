import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Provider;

public class ActivityPresenter {
    private Provider<ActivityFrame> viewProvider;
    private ActivityService model;

    @Inject
    public ActivityPresenter(Provider<ActivityFrame> viewProvider, ActivityService model) {
        this.viewProvider = viewProvider;
        this.model = model;
    }


    public void loadActivityFromInput(String type, int numPeople) {
        Disposable disposable = model.getActivity(type, numPeople)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::onNext, this::onError);
    }


    public void onNext(Activity activity) {
        String people = " people";
        if (activity.getParticipants() == 1)
        {
            people = " person";
        }

        String nextActivity = activity.getNextActivity() + " can be done with "
                + activity.getParticipants() + people;
        System.out.println(nextActivity);
        ActivityFrame view = viewProvider.get();
        view.setActivity(nextActivity);
        view.setLink(activity.getLink());
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        viewProvider.get().showError();
    }

}
