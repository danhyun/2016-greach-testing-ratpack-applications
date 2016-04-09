import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get(new ImportantHandler()) // <1>
  }
}
