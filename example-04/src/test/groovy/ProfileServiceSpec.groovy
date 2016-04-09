import ratpack.exec.ExecResult
import ratpack.exec.Promise
import ratpack.test.exec.ExecHarness
import spock.lang.AutoCleanup
import spock.lang.Specification

class ProfileServiceSpec extends Specification {

  @AutoCleanup
  ExecHarness execHarness = ExecHarness.harness() // <1>

  def 'can add/retrieve/remove profiles from service'() {
    given:
    ProfileService service = new ProfileService()

    when:
    ExecResult<Promise<List<Profile>>> result = execHarness.yield { service.profiles } // <2>

    then:
    result.value == []

    when:
    execHarness.yield { service.add(new Profile(role: 'admin', token: 'secret')) }
    and:
    List<Profile> profiles = execHarness.yield { service.profiles }.value

    then: profiles == [new Profile(role: 'admin', token: 'secret')]

    when:
    execHarness.yield { service.delete() }

    then:
    execHarness.yield { service.profiles }.value == []

  }
}
