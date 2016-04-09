import groovy.transform.CompileStatic
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

@CompileStatic
class ImportantSendingHandler extends GroovyHandler {
  @Override
  protected void handle(GroovyContext context) {
    context.response.send('Very important handler')
  }
}
