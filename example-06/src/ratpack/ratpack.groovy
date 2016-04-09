import static ratpack.groovy.Groovy.ratpack
import ratpack.jackson.Jackson

ratpack {
  bindings {
    bindInstance(new ProfileService())
  }

  handlers {
    get { ProfileService service ->
      service.profiles
        .map(Jackson.&json)
        .then(context.&render)
    }
    get('add') { ProfileService service ->
      service.add(new Profile('admin'))
        .then { redirect '/'}
    }
  }
}
