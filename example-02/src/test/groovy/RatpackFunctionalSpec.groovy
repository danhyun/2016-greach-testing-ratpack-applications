import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class RatpackFunctionalSpec extends Specification {

  @AutoCleanup @Shared
  GroovyRatpackMainApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

  def 'Should render \'Very important Handler'() {
    expect:
    aut.httpClient.text == 'Very important handler'
  }
}
