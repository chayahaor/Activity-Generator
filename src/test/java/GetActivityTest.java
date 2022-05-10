import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetActivityTest {

    @Test
    void getActivity() {
        //given
        GetActivity getActivity = new GetActivity();

        //when
        Activity activity = getActivity.getActivity("busywork", 1).blockingFirst();

        //then
        assertNotSame("", activity.getActivity());
        assertTrue(activity.getParticipants()>-1);
        assertNotSame("", activity.getType());


    }
}