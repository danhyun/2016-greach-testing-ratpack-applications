import groovy.transform.CompileStatic
import ratpack.func.Action
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler
import ratpack.handling.Chain
import ratpack.server.RatpackServer
import ratpack.server.RatpackServerSpec

@CompileStatic
class MainClassApp {
  public static void main(String[] args) throws Exception {
    RatpackServer.start({ RatpackServerSpec serverSpec -> serverSpec
      .handlers({ Chain chain ->
        chain.get({GroovyContext ctx ->
          ctx.render 'Hello Greach 2016!'
        } as GroovyHandler)
      } as Action<Chain>)
    } as Action<RatpackServerSpec>)
  }
}
