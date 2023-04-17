import feign.RequestLine;

import java.util.List;

public interface PersonService {
    @RequestLine("GET /persons")
    List<Person> getPerson();
}
