import groovy.transform.Canonical
import ratpack.exec.Operation
import ratpack.exec.Promise

class ProfileService {
  final List<Profile> profiles = []
  Promise<List<Profile>> getProfiles() {
    Promise.value(profiles)
  }

  Operation add(Profile p) {
    profiles.add(p)
    Operation.noop()
  }

  Operation delete() {
    profiles.clear()
    Operation.noop()
  }
}

@Canonical
class Profile {
  String role
  String token
}
