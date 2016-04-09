import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.MainClassApplicationUnderTest
import ratpack.test.http.TestHttpClient

import static org.testng.Assert.assertEquals

public class HelloTestNGTest {
  static GroovyRatpackMainApplicationUnderTest groovyScriptApplicationunderTest
  static MainClassApplicationUnderTest mainClassApplicationUnderTest

  @BeforeClass
  static void setup() {
    groovyScriptApplicationunderTest = new GroovyRatpackMainApplicationUnderTest()
    mainClassApplicationUnderTest = new MainClassApplicationUnderTest(MainClassApp)
  }

  @AfterClass
  static void cleanup() {
    groovyScriptApplicationunderTest.close()
    mainClassApplicationUnderTest.close()
  }

  @Test
  def void testHelloWorld() {
    [
      groovyScriptApplicationunderTest,
      mainClassApplicationUnderTest
    ].each { aut ->
      TestHttpClient client = aut.httpClient
      assertEquals('Hello Greach 2016!', client.getText())
    }
  }
}
