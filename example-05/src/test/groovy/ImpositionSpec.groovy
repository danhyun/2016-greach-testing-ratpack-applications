import ratpack.exec.Promise
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.impose.UserRegistryImposition
import ratpack.impose.ImpositionsSpec
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import ratpack.guice.Guice

class ImpositionSpec extends Specification {

  @Shared
  @AutoCleanup
  ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest() {
    @Override
    protected void addImpositions(ImpositionsSpec impositions) { // <1>
      impositions.add(
        UserRegistryImposition.of(Guice.registry {
          it.add(new ConfService(null, null) {
            Promise<List<String>> getConferences() {
              Promise.value(['Greach'])
            }
          })
        }))
    }
  }

  @Delegate
  TestHttpClient client = aut.httpClient

  def 'can get list of gr8 conferences'() {
    when:
    get()

    then:
    response.statusCode == 200

    and:
    response.body.text.contains('Greach')
  }
}
