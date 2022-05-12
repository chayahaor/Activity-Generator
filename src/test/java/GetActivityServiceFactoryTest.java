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
        Activity activity = service.getActivity("busywork").blockingGet();

        //then
        assertNotSame("", activity.getActivity());
        assertTrue(activity.getParticipants()>-1);
        assertNotSame("", activity.getType());


    }
}