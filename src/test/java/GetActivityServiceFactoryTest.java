import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetActivityServiceFactoryTest {

    @Test
    void getActivity() {
        //given
        GetActivityServiceFactory factory = new GetActivityServiceFactory();
        ActivityService service = factory.getInstance();
        //when
        Activity activity = service.getActivity("social",2).blockingGet();

        //then
        assertNotSame("", activity.getNextActivity());
        assertTrue(activity.getParticipants()>0);
        assertNotSame("", activity.getType());


    }
}