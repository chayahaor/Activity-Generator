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
        if (numPeople == 0) //Make default API calls without setting the number of people
        {
            Disposable disposable = model.getActivity(type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .subscribe(this::onNext, this::onError);
        } else
        {
            Disposable disposable = model.getActivity(type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .subscribe(this::onNext, this::onError);
        }
    }


    public void onNext(Activity activity) {
        String people = " people";
        if (activity.getParticipants() == 1)
        {
            people = " person";
        }

        String nextActivity = activity.getNextActivity() + " can be done with " + activity.getParticipants() + people;
        System.out.println(nextActivity);
        view.setActivity(nextActivity);
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.showError();
    }

}
