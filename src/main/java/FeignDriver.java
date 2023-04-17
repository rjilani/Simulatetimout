import feign.Feign;
import feign.FeignException;
import feign.Request;
import feign.RetryableException;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeignDriver {
    public static void main(String[] args) {

        try {
            PersonService personService = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .options(new Request.Options(10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true))
                    .target(PersonService.class, "http://localhost:8080");

            List<Person> personList = personService.getPerson();
            System.out.println(personList.size());
            personList.forEach(i -> System.out.println(i.toString()));
        } catch (FeignException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }


    }
}
