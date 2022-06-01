import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivityServiceFactoryTest {

    @Test
    void getActivity() {
        //given
        ActivityServiceFactory factory = new ActivityServiceFactory();
        ActivityService service = factory.getInstance();
        //when
        Activity activity = service.getActivity("social", 2).blockingGet();

        //then
        assertNotSame("", activity.getNextActivity());
        assertTrue(activity.getParticipants() > 0);
        assertNotSame("", activity.getType());


    }
}