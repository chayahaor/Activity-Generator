import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPresenter {
    private ActivityFrame view;
    private ActivityService model;


    public ActivityPresenter(ActivityFrame view, ActivityService model) {
        this.view = view;
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
        if (activity.getNextActivity() == null)
        {
            nextActivity = "Seems there are no activities with that combination. "
                    + "Try changing your filters";
        }
        view.setActivity(nextActivity);
        view.setLink(activity.getLink());

    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.showError();
    }

}
