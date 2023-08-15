package Array_Data;

import org.apache.commons.csv.*;
import org.json.*;
import java.io.*;
import java.util.*;

public class Array_Data_Gen {

    // This code helps to generate array data type data for testing
    static int i;
    static int ageStart;
    static int ageEnd;
    static int N;
    static String filePathID;
    static String idFileHeaderName;
    static String filePathDataPhase1;
    static long timeStart;
    static long timeEnd;

    static {
        loadConfig();
    }

    private static void loadConfig(){
        Properties properties= new Properties();
        try(FileInputStream input= new FileInputStream("/Users/shubham/IdeaProjects/Code_Snippets_SH/src/main/java/Array_Data/config.properties")){
            properties.load(input);
            ageStart = Integer.parseInt(properties.getProperty("age.start"));
            ageEnd = Integer.parseInt(properties.getProperty("age.end"));
            N = Integer.parseInt(properties.getProperty("n"));
            filePathID = properties.getProperty("file.path.id");
            idFileHeaderName = properties.getProperty("id.file.header.name");
            filePathDataPhase1 = properties.getProperty("file.path.data.phase1");
            timeStart = Long.parseLong(properties.getProperty("time.start"));
            timeEnd = Long.parseLong(properties.getProperty("time.end"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        JSONArray jsonArray = new JSONArray();

        List<String> Ids = readIdsFromCSV(filePathID);
        if (Ids.isEmpty()) {
            System.err.println("No Ids found in the CSV file.");
            return;
        }
        for (i = 1; i < N; i++) {
            JSONObject jsonObject = createSampleJsonObject(Ids);
            jsonArray.put(jsonObject);
        }

        String formattedJsonArray = jsonArray.toString(4); // Storing JSON with indentation of 4 spaces
        String filepath = filePathDataPhase1;
        try (BufferedWriter wrt= new BufferedWriter(new FileWriter(filepath))){
            wrt.write(formattedJsonArray);
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    private static JSONObject createSampleJsonObject(List<String> Ids) {
        JSONObject jsonObject = new JSONObject();

        int j;
        for (j = 0; j < i; j++) {
            String Id = Ids.get(j);
            jsonObject.put("ID", Id);
        }
        // Generate random age between 20 and 30 (inclusive)
        int age = generateRandomAge( ageStart, ageEnd);
        jsonObject.put("age", age);
        jsonObject.put("isConsent", generateRandomYesNo());
        jsonObject.put("isMarketingPreference", generateRandomYesNo());

        // Add an array of values (Transcations) [Data Type - Double]
        JSONArray transcations = new JSONArray();
        transcations.put(95000.00);
        transcations.put(97000.01);
        jsonObject.put("Transcations", transcations);

        // Add an array of values (Banks) [Data Type - String]
        JSONArray banks = new JSONArray();
        banks.put("JPMorgan");
        banks.put("Wells Fargo");
        jsonObject.put("Banks", banks);

        // Add an array of values (Credit) [Data Type - Integer]
        JSONArray credit = new JSONArray();
        credit.put(9000);
        credit.put(9500);

        jsonObject.put("credited", credit);

        long timestamp = generateRandomTimestamp(timeStart, timeEnd); // Example date range: 11 August 2023 to  21 August 2023
        jsonObject.put("timestamp", timestamp);

        return jsonObject;
    }

    private static int generateRandomAge(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static String generateRandomYesNo() {
        String[] options = {"yes", "no"};
        Random random = new Random();
        int randomIndex = random.nextInt(options.length);
        return options[randomIndex];
    }

    private static long generateRandomTimestamp(long min, long max) {
        Random random = new Random();
        long randomTimestamp = random.nextLong() % (max - min) + min;
        return randomTimestamp;
    }

    private static List<String> readIdsFromCSV(String filePathID) {
        List<String> Ids = new ArrayList<>();

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(filePathID));

            for (CSVRecord record : parser) {
                String Id = record.get(idFileHeaderName); // Change "AdId" to the column header for IDs
                Ids.add(Id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Ids;
    }
}