import groovy.transform.Canonical
import groovy.transform.CompileStatic
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.registry.Registry

@CompileStatic
class ProfileLoadingHandler extends GroovyHandler {
  @Override
  protected void handle(GroovyContext context) {
    String role = context.request.headers.get('role') ?: 'guest' // <1>
    String secretToken = context.get(String) // <2>
    context.next(Registry.single(new Profile(role: role, token: secretToken))) // <3>
  }
}

@Canonical
class Profile {
  String role
  String token
}
