import com.apiframework.BaseTest;
import com.apiframework.service.UserService;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_GetUser extends BaseTest {

    @Test
    public void TC01_GetUserByID() throws JsonProcessingException {
        ExtentTestManager.startTest("Get User By ID", "To verify that user is able to get user details through API if userid is provided");
        Response response = (Response) UserService
                .init()
                .getUserByID("USER001").getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }

    @Test
    public void TC02_GetUserByInvalidID() throws JsonProcessingException {
        ExtentTestManager.startTest("Get User By ID", "To verify that user is not able to get user details through API if userid provided does not exist");
        Response response = (Response) UserService
                .init()
                .getUserByID("XXXX001").getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        Assert.assertTrue(response.asString().contains("User not found"));
    }
}
