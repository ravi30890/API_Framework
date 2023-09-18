import com.apiframework.model.User;
import com.apiframework.service.UserService;
import com.apiframework.utils.RandomGenerator;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ModifyUser {
    @Test
    public void TC04_ModifyUser() {
        ExtentTestManager.startTest("Modify User", "To verify that user is able to modify user data");
        User userToCreate = new User(RandomGenerator.randomNumeric(5));
        Response response = (Response) UserService
                .init()
                .modifyUser(userToCreate).getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }
}
