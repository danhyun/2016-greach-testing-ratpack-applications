import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.embed.EphemeralBaseDir
import spock.lang.Specification

import java.nio.file.Path

class EphemeralSpec extends Specification {
  def 'can supply ephemeral basedir'() {
    expect:
    EphemeralBaseDir.tmpDir().use { baseDir ->
      baseDir.write("mydir/.ratpack", "")
      baseDir.write("mydir/assets/message.txt", "Hello Ratpack!")
      Path mydir = baseDir.getRoot().resolve("mydir")

      ClassLoader classLoader = new URLClassLoader((URL[]) [mydir.toUri().toURL()].toArray())
      Thread.currentThread().setContextClassLoader(classLoader);

      GroovyEmbeddedApp.of { serverSpec ->
        serverSpec
          .serverConfig { c -> c.baseDir(mydir) }
          .handlers { chain ->
          chain.files { f -> f.dir("assets") }
        }
      }.test {
        String message = getText("message.txt")
        assert "Hello Ratpack!" == message
      }
    }
  }
}
