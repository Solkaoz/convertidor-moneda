package controller;

import com.google.gson.Gson;
import model.Currency;
import java.net.URI;
import java.net.http.*;

public class Connection {

    public static String response(String currencyOrigin, String currency, double value) {

        try {

            final String API_KEY = "5b51c1b9055e1b9c9f4ddce5";

            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + currencyOrigin + "/" + currency + "/" + value);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            double result;
            result = new Gson().fromJson(response.body(), Currency.class).getConversion_result();

            return "$" + value + " " + currencyOrigin + " = $" + String.format("%.2f",result) + " " + currency;

        } catch (Exception e) {

            return "Error: " + e.getMessage();
        }
    }
}
