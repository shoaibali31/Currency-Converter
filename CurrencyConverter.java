package com;

import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Currency to Convert From");
        String convertFrom = sc.nextLine().toUpperCase();
        System.out.println("Enter the Currency to Convert To");
        String convertTo = sc.nextLine().toUpperCase();
        System.out.println("Enter the Amount");
        BigDecimal amount = sc.nextBigDecimal();

        String urlString = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/history/USD/YEAR/MONTH/DAY?base=" + convertFrom;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("API request failed with code: " + response.code());
                return;
            }

            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);

            // Check if "rates" is present
            if (!jsonObject.has("rates")) {
                System.out.println("Unexpected API response: " + stringResponse);
                return;
            }

            JSONObject ratesObject = jsonObject.getJSONObject("rates");

            if (!ratesObject.has(convertTo)) {
                System.out.println("Invalid target currency: " + convertTo);
                return;
            }

            BigDecimal rate = ratesObject.getBigDecimal(convertTo);
            BigDecimal result = rate.multiply(amount);
            System.out.println("Converted Amount: " + result);
        } catch (Exception e) {
            System.out.println("Error during API call or parsing: " + e.getMessage());
        }
    }
}

