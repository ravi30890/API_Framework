import com.apiframework.model.Pet;
import com.apiframework.model.User;
import com.apiframework.service.PetService;
import com.apiframework.service.UserService;
import com.apiframework.utils.RandomGenerator;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ModifyPet {
    @Test
    public void TC07_ModifyPet() {
        ExtentTestManager.startTest("Modify Pet", "To verify that user is able to modify pet data");
        Pet petToUpdate = new Pet(RandomGenerator.randomNumeric(5));
        Response response = (Response) PetService
                .init()
                .modifyPet(petToUpdate).getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }
}
