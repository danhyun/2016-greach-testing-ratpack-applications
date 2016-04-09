import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get {
      render 'Hello Greach 2016!'
    }
  }
}
