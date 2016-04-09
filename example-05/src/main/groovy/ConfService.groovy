import com.google.inject.Inject
import ratpack.exec.Promise
import ratpack.http.client.HttpClient

class ConfService {
  final HttpClient httpClient
  final ApiConfig apiConfig

  @Inject
  ConfService(ApiConfig apiConfig, HttpClient httpClient) { // <1>
    this.apiConfig = apiConfig
    this.httpClient = httpClient
  }

  Promise<List<String>> getConferences() { // <2>
    httpClient.get(new URI(apiConfig.url))
      .map { it.body }
      .map { it.text.split(',').collect { it } }
  }
}
