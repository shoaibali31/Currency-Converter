# Currency-Converter

A simple Java command-line application to convert currency amounts using real-time exchange rates from the ExchangeRate-API. This version is modular, environment-friendly, and user-interactive.

📋 Features
Converts between any two valid 3-letter currency codes (e.g., USD, EUR, INR)

Uses live exchange rates via ExchangeRate-API

Modular structure for better readability and maintainability

Error handling for invalid inputs, API failures, or network issues

Reads API key securely from environment variable

🛠️ Requirements
Java 8 or higher

Internet connection

A valid ExchangeRate-API key

📦 Dependencies
OkHttp – For HTTP requests

org.json – For JSON parsing

You can include these via Maven or download the JARs manually.

🔑 Environment Variable Setup
Before running the application, set your API key as an environment variable:

On Linux/macOS:
bash
Copy
Edit
export EXCHANGE_API_KEY=your-api-key-here
On Windows (CMD):
cmd
Copy
Edit
set EXCHANGE_API_KEY=your-api-key-here
On Windows (PowerShell):
powershell
Copy
Edit
$env:EXCHANGE_API_KEY="your-api-key-here"
▶️ How to Run
Compile the code:

bash
Copy
Edit
javac -cp ".;path/to/okhttp.jar;path/to/json.jar" CurrencyConverter.java
Run the program:

bash
Copy
Edit
java -cp ".;path/to/okhttp.jar;path/to/json.jar" CurrencyConverter
Follow prompts in the terminal:

text
Copy
Edit
Enter the currency to convert from (e.g. USD): USD
Enter the currency to convert to (e.g. EUR): INR
Enter the amount to convert: 100
Converted Amount: 8304.50 INR
📄 Code Overview
main()
Handles user input and overall program flow.

promptCurrency(...) and promptAmount(...)
Get validated input from user.

fetchExchangeRate(...)
Builds the API request

Parses the response

Returns the conversion rate

🛡️ Error Handling
Handles invalid currency codes

Checks for missing or incorrect API key

Handles API errors and network failures

Prevents crashes due to unhandled exceptions
