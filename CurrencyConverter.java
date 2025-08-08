public class CurrencyConverter {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = System.getenv("EXCHANGE_API_KEY");

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String sourceCurrency = promptCurrency(sc, "Enter the currency to convert from (e.g. USD): ");
            String targetCurrency = promptCurrency(sc, "Enter the currency to convert to (e.g. EUR): ");
            BigDecimal amount = promptAmount(sc, "Enter the amount to convert: ");

            BigDecimal rate = fetchExchangeRate(sourceCurrency, targetCurrency);
            if (rate != null) {
                BigDecimal converted = amount.multiply(rate);
                System.out.printf("Converted Amount: %.2f %s%n", converted, targetCurrency);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static String promptCurrency(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine().trim().toUpperCase();
    }

    private static BigDecimal promptAmount(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextBigDecimal();
    }

    private static BigDecimal fetchExchangeRate(String from, String to) {
        String url = API_BASE_URL + API_KEY + "/latest/" + from;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("API request failed: " + response.code());
                return null;
            }

            JSONObject json = new JSONObject(response.body().string());
            JSONObject rates = json.getJSONObject("conversion_rates");

            if (!rates.has(to)) {
                System.err.println("Invalid target currency: " + to);
                return null;
            }

            return rates.getBigDecimal(to);
        } catch (IOException e) {
            System.err.println("Network or parsing error: " + e.getMessage());
            return null;
        }
    }
}
