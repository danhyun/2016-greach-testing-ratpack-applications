import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.MainClassApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HelloWorldSpec extends Specification {

  // tag::GroovyScriptAUT[]
  @AutoCleanup
  @Shared
  GroovyRatpackMainApplicationUnderTest groovyScriptApplicationunderTest = new GroovyRatpackMainApplicationUnderTest()
  // end::GroovyScriptAUT[]

  // tag::MainClassAUT[]
  @AutoCleanup
  @Shared
  MainClassApplicationUnderTest mainClassApplicationUnderTest = new MainClassApplicationUnderTest(MainClassApp)
  // end::MainClassAUT[]

  @Unroll
  def 'Should render \'Hello Greach 2016!\' from #type'() {
    when:
    def getText = aut.httpClient.getText()

    then:
    getText == 'Hello Greach 2016!'

    where:
    aut                              | type
    groovyScriptApplicationunderTest | 'ratpack.groovy'
    mainClassApplicationUnderTest    | 'MainClassApp.groovy'
  }
}
