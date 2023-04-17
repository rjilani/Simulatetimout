import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/persons")
                .build();
        try {

            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(response.body().string());
            String prettyJson = objectMapper.writeValueAsString(jsonNode);
            System.out.println(prettyJson);
        } catch (IOException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

    }
}
