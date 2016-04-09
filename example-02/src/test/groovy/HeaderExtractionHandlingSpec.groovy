import ratpack.test.handling.HandlingResult
import spock.lang.Specification
import ratpack.groovy.test.handling.GroovyRequestFixture
import spock.lang.Unroll

class HeaderExtractionHandlingSpec extends Specification {

  @Unroll
  def 'should render #expectedValue with special header value'() {
    when:
    HandlingResult result = GroovyRequestFixture
      .handle(new HeaderExtractionHandler(), requestFixture)

    then:
    def rendered = result.rendered(CharSequence)
    rendered == "Special header: $expectedValue"

    where:
    expectedValue | requestFixture
    'greach2016'  | { header('special', 'greach2016') } // <1>
    'not special' | {}
  }
}
