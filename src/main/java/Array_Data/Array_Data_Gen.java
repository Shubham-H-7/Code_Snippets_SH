package Array_Data;

import org.apache.commons.csv.*;
import org.json.*;

import javax.swing.text.SimpleAttributeSet;
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
    static String filePath1;
    static long timeStart;
    static long timeEnd;
    static JSONArray arrayvalueC1 = new JSONArray();
    static  JSONArray arrayvalueC2 = new JSONArray();
    static  JSONArray arrayvalueC3 = new JSONArray();
    static  String Column1Header;
    static  String  Column2Header;
    static  String Column3Header;


    static {
        try {
            loadConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadConfig() throws FileNotFoundException {

        Properties properties= new Properties();
        String workingDir = System.getProperty("user.dir");
        // Create a path to the config.properties file using the relative path
        String configPath = workingDir + "/src/main/java/Array_Data/config.properties";
        // Create a FileInputStream using the computed path
        FileInputStream input = new FileInputStream(configPath);
        try{
            properties.load(input);
            ageStart = Integer.parseInt(properties.getProperty("age.start"));
            ageEnd = Integer.parseInt(properties.getProperty("age.end"));
            N = Integer.parseInt(properties.getProperty("n"));
            filePathID = properties.getProperty("file.path.id");
            idFileHeaderName = properties.getProperty("id.file.header.name");
            filePath1 = properties.getProperty("filepath1");
            timeStart = Long.parseLong(properties.getProperty("time.start"));
            timeEnd = Long.parseLong(properties.getProperty("time.end"));
            //Column1 Calling
            Column1Header = properties.getProperty("Column1Header");
            for (int i= 1; i <= N; i++ ){
                String arrayvalues = properties.getProperty("Column1value" + i);
                if(arrayvalues != null){
                    arrayvalueC1.put(arrayvalues);
                }
            }
            //Column2 Calling
            Column2Header = properties.getProperty("Column2Header");
           for (int j=1; j<=N; j++){
               String stringvalues = properties.getProperty("Column2value" + j);
               if (stringvalues != null){
                   double doublevalues = Double.parseDouble(stringvalues);
                   arrayvalueC2.put(doublevalues);
               }
           }
           //Column3 calling
            Column3Header = properties.getProperty("Column3Header");
           for (int k=1; k<=N; k++){
               String stringvalues = properties.getProperty("Column3value" + k);
               if (stringvalues != null){
                   double intvalues = Double.parseDouble(stringvalues);
                   arrayvalueC3.put(intvalues);
               }
           }
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
        String filepath = filePath1;
        try (BufferedWriter wrt= new BufferedWriter(new FileWriter(filepath))){
            wrt.write(formattedJsonArray);
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        Accepted_JSON_Format_script.finalMethod();
    }


    private static JSONObject createSampleJsonObject(List<String> Ids) {
        JSONObject jsonObject = new JSONObject();

        int j;
        for (j = 0; j < i; j++) {
            String Id = Ids.get(j);
            jsonObject.put(idFileHeaderName, Id);
        }
        // Generate random age between 20 and 30 (inclusive)
        int age = generateRandomAge( ageStart, ageEnd);
        jsonObject.put("age", age);
        jsonObject.put("isConsent", generateRandomYesNo());
        jsonObject.put("isMarketingPreference", generateRandomYesNo());

        // Add an array of values (Transcations) [Data Type - Double]
        jsonObject.put(Column2Header, arrayvalueC2);

        // Add an array of values (Banks) [Data Type - String]
        jsonObject.put(Column1Header, arrayvalueC1);

        // Add an array of values (Credit) [Data Type - Integer]
        jsonObject.put(Column3Header, arrayvalueC3);

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