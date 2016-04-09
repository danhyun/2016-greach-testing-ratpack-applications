import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class FunctionalSpec extends Specification {

  @Shared
  @AutoCleanup
  ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest() // <1>

  @Delegate
  TestHttpClient client = aut.httpClient // <2>

  @Shared
  @AutoCleanup
  GroovyEmbeddedApp api = GroovyEmbeddedApp.fromHandler { // <3>
    render 'Greach, GR8conf, Gradle Conf'
  }

  def setup() {
    System.setProperty('ratpack.api.url', api.address.toURL().toString()) // <4>
  }

  def 'can get best conferences'() { // <5>
    when:
    get()

    then:
    response.statusCode == 200

    and:
    response.body.text.contains('Greach')
  }
}
