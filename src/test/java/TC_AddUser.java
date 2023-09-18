import com.apiframework.BaseTest;
import com.apiframework.model.User;
import com.apiframework.service.UserService;
import com.apiframework.utils.RandomGenerator;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TC_AddUser extends BaseTest{
    @Test
    public void TC03_AddUsers() throws JsonProcessingException {
        ExtentTestManager.startTest("Add User", "To verify that user is able to add users");
        User userToCreate = new User(RandomGenerator.randomNumeric(5));
        List<User> userList = new ArrayList<>();
        userList.add(userToCreate);
        Response response = (Response) UserService
                .init()
                .addUsers(userList).getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }
}
