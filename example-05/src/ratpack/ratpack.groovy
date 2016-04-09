import static ratpack.groovy.Groovy.ratpack

ratpack {
  serverConfig {
    sysProps() // <1>
    require('/api', ApiConfig) // <2>
  }
  bindings {
    bind(ConfService) // <3>
  }
  handlers {
    get { ConfService confService ->
      confService.conferences.map { // <4>
        "Here are the best conferences: $it"
      } then(context.&render)
    }
  }
}
