import org.parboiled.common.FileUtils;
import org.parboiled.common.ImmutableList;
import org.scalatestplus.testng.TestNGWrapperSuite;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class CoreTest extends TestNGWrapperSuite {

    public CoreTest() throws IOException {
        super(new org.parboiled.JavaCollectionWrapper(ImmutableList.of(getSuiteFileName())).toList());
    }
    
    public static String getSuiteFileName() throws IOException {
        File temp = File.createTempFile("parboiled_testng_suite", ".xml");        
        temp.deleteOnExit();

        String xml = "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n" +
                "<suite name=\"parboiled-core\">\n" +
                "  <test verbose=\"1\" name=\"parboiled-core\" annotations=\"JDK\">\n" +
                "    <packages>\n" +
                "      <package name=\"org.parboiled.*\" />\n" +
                "    </packages>\n" +
                "  </test>\n" +
                "</suite>";
        FileUtils.writeAllText(xml, temp);
        
        return temp.getCanonicalPath();
    }
}
