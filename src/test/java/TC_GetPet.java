import com.apiframework.BaseTest;
import com.apiframework.model.Pet;
import com.apiframework.service.PetService;
import com.apiframework.service.UserService;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_GetPet extends BaseTest {

    @Test
    public void TC06_GetPetByStatus() throws JsonProcessingException {
        ExtentTestManager.startTest("Get Pet By Status", "To verify that user is able to get pet details through API if pet status is provided");
        Response response = (Response) PetService
                .init()
                .getPetByStatus(Pet.PetStatus.AVAILABLE).getResponse();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotNull(response.body());
    }

}
