import io.remotecontrol.client.UnserializableResultStrategy
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.guice.BindingsImposition
import ratpack.impose.ImpositionsSpec
import ratpack.remote.RemoteControl
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class RemoteControlSpec extends Specification {

  @Shared
  @AutoCleanup
  ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest() {
    @Override
    protected void addImpositions(ImpositionsSpec impositions) {
      impositions.add(BindingsImposition.of {
        it.bindInstance RemoteControl.handlerDecorator() // <1>
      })
    }
  }

  @Delegate
  TestHttpClient client = aut.httpClient

  ratpack.test.remote.RemoteControl remoteControl = new ratpack.test.remote.RemoteControl(aut, UnserializableResultStrategy.NULL) // <2>

  def 'should render profiles'() {
    when:
    get()

    then:
    response.body.text == '[]'

    when:
    remoteControl.exec { // <3>
      get(ProfileService)
        .add(new Profile('admin'))
    }

    and:
    get()

    then:
    response.body.text.startsWith('[{')
  }
}
