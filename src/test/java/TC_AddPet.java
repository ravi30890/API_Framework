import com.apiframework.BaseTest;
import com.apiframework.model.Pet;
import com.apiframework.model.User;
import com.apiframework.service.PetService;
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

public class TC_AddPet extends BaseTest{
    @Test
    public void TC05_AddPet(){
        ExtentTestManager.startTest("Add Pet", "To verify that user is able to add pet");
        Pet petToAdd = new Pet(RandomGenerator.randomNumeric(5));
        Response response = (Response) PetService
                .init()
                .addPet(petToAdd).getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }
}
