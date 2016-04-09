import groovy.transform.CompileStatic
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

@CompileStatic
class HeaderExtractionHandler extends GroovyHandler {
  @Override
  protected void handle(GroovyContext context) {
    String specialHeader = context.request.headers.get('special') ?: 'not special' // <1>
    context.render "Special header: $specialHeader"
  }
}
