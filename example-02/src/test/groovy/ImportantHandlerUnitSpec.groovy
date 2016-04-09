import ratpack.groovy.test.handling.GroovyRequestFixture
import ratpack.test.handling.HandlingResult
import spock.lang.Specification

class ImportantHandlerUnitSpec extends Specification {

  // tag::rendering-spec[]
  def 'should render \'Very important handler\''() {
    when:
    HandlingResult result = GroovyRequestFixture.handle(new ImportantHandler()) {}

    then:
    String rendered = result.rendered(String) // <1>
    rendered == 'Very important handler'
  }
  // end::rendering-spec[]
}
