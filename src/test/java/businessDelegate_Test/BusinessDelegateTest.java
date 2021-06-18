package businessDelegate_Test;

import co.edu.icesi.FernandezDanielTaller1Application;
import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FernandezDanielTaller1Application.class)
public class BusinessDelegateTest {

    @Mock
    private BusinessDelgateI bd;

    @Autowired
    public BusinessDelegateTest(){

    }

}
