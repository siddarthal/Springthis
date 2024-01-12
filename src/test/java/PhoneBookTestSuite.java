import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages({"com.project.phoneBook.controller", "com.project.phoneBook.service" ,
//	"com.project.phoneBook.exception" , "com.project.phoneBook.model" })
@SelectPackages("com.project.phoneBook")
//@IncludePackages({"com.project.phoneBook.controller", "com.project.phoneBook.service" ,
//	"com.project.phoneBook.exception" , "com.project.phoneBook.model"})
public class PhoneBookTestSuite {
	

}
