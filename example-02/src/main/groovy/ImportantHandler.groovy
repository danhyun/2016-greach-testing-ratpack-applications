import groovy.transform.CompileStatic
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

@CompileStatic
class ImportantHandler extends GroovyHandler {
  @Override
  protected void handle(GroovyContext context) {
    context.render 'Very important handler'
  }
}
