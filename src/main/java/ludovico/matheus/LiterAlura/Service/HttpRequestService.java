package ludovico.matheus.LiterAlura.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class HttpRequestService {

    private final String API_URL = "https://gutendex.com/books";

    public JsonNode realizarRequisicaoAPI(String parametro) throws Exception {
        URL url = new URL(API_URL + "?search=" + parametro);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.toString());
    }
}