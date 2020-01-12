package come.revature.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.services.ReinburseServiceTest;
import com.revature.services.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ReinburseServiceTest.class, UserServiceTest.class})


public class AllTests {

}
