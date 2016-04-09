import ratpack.groovy.handling.GroovyChainAction
import ratpack.handling.Chain
import ratpack.test.handling.HandlingResult
import spock.lang.Specification;
import ratpack.groovy.test.handling.GroovyRequestFixture

class ProfileLoadingHandlingSpec extends Specification {
  // tag::registry[]
  def 'handler should populate context registry with Profile'() {
    when:
    HandlingResult result = GroovyRequestFixture.handle(new ProfileLoadingHandler()) {
      header('role', 'admin') // <1>
      registry { add(String, 'secret-token') } // <2>
    }

    then:
    result.registry.get(Profile) == new Profile('admin', 'secret-token') // <3>
  }
  // end::registry[]

  // tag::chain[]
  def 'should be able to render Profile as map from Registry'() {
    when:
    HandlingResult result = GroovyRequestFixture.handle(new GroovyChainAction() { // <1>
      @Override
      void execute() throws Exception {
        all(new ProfileLoadingHandler()) // <2>
        get { // <3>
          Profile profile = get(Profile)
          render([profile: [role: profile.role, token: profile.token]])
        }
      }
    }) {
      header('role', 'admin')
      registry { add(String, 'secret-token') }
    }

    then:
    result.rendered(Map) == [profile: [role: 'admin', 'token': 'secret-token']] // <4>
  }
  // end::chain[]
}
