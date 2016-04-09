import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.http.TestHttpClient
import spock.lang.Specification

class EmbeddedAppSpec extends Specification {
  // tag::hello-world[]
  def 'can create simple hello world'() {
    expect:
    GroovyEmbeddedApp.fromHandler { // <1>
      render 'Hello Greach 2016!'
    } test {
      assert getText() == 'Hello Greach 2016!' // <2>
    }
  }
  // end::hello-world[]

  // tag::client[]
  def 'demonstrate ByMethodSpec'() {
    given:
    GroovyEmbeddedApp app = GroovyEmbeddedApp.fromHandlers { // <1>
        path {
          byMethod {
            get {
              render 'GET'
            }
            post {
              render 'POST'
            }
          }
        }
      }

    and:
    TestHttpClient client = app.httpClient // <2>

    expect: // <3>
    client.getText() == 'GET'
    client.postText() == 'POST'

    client.put().status.code == 405
    client.delete().status.code == 405

    cleanup: // <4>
    app.close()
  }
  // end::client[]

  // tag::request-config[]
  def 'should handle redirects and cookies'() {
    expect:
    GroovyEmbeddedApp.fromHandlers { // <1>
      get {
        render request.oneCookie('foo') ?: 'empty'
      }
      get('set') {
        response.cookie('foo', 'foo')
        redirect '/'
      }
      get('clear') {
        response.expireCookie('foo')
        redirect '/'
      }
    } test {
      assert getText() == 'empty' // <2>
      assert getCookies('/')*.name() == []
      assert getCookies('/')*.value() == []

      assert getText('set') == 'foo'
      assert getCookies('/')*.name() == ['foo']
      assert getCookies('/')*.value() == ['foo']

      assert getText() == 'foo'

      assert getText('clear') == 'empty'
      assert getCookies('/')*.name() == []
      assert getCookies('/')*.value() == []

      assert getText() == 'empty'
      assert getCookies('/')*.name() == []
      assert getCookies('/')*.value() == []
    }
  }
  // end::request-config[]
}
