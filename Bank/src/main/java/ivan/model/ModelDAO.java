package ivan.model;

import ivan.build.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class ModelDAO {
    private final String PATH = "src\\main\\resources\\Models";
    private static final String CSV_SEPARATOR = ",";

    private int createNextCSV(){
        try {
            int counter = 0;
            File newFile = new File(PATH + "\\" + counter + ".csv");
            while (newFile.exists()) {
                counter++;
                newFile = new File(PATH + "\\" + counter + ".csv");
            }
            newFile.createNewFile();
            return counter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeModelToCSV(Model model, String file){
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(model.toCSVString(CSV_SEPARATOR));
            writer.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public String save(Model model){
        int i = createNextCSV();
        String s = PATH + "\\" + i + ".csv";
        writeModelToCSV(model, s);
        return i + ".csv";
    }

    public Model getModel(int i) throws Exception {
        List<String> readFile = readFile(PATH + "\\" + i + ".csv");
        List<Application> applications = new ArrayList<>();
        Model res = parseModel(readFile.get(0));
        for (int j = 1; j < readFile.size(); j++) {
            applications.add(parseApplication(readFile.get(j)));
        }
        res.setApplications(applications);
        return res;
    }

    public List<String> readFile(String filename){
        List<String> res = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public Model parseModel(String s) {
        String[] tokens = s.split(CSV_SEPARATOR);
        int clerkCount = Integer.parseInt(tokens[0]);
        int maxClientCount = Integer.parseInt(tokens[1]);
        int countOfServedClients = Integer.parseInt(tokens[2]);
        int countOfLostClients = Integer.parseInt(tokens[3]);
        int maxQueue = Integer.parseInt(tokens[4]);
        int minQueue = Integer.parseInt(tokens[5]);
        int averageQueue = Integer.parseInt(tokens[6]);
        int averageWaitTime = Integer.parseInt(tokens[7]);
        int profit = Integer.parseInt(tokens[8]);

        return new Model(new ArrayList<>(), clerkCount, maxClientCount, countOfServedClients, countOfLostClients, maxQueue, minQueue, averageQueue, averageWaitTime, profit);
    }

    public Application parseApplication(String s) throws ParseException {
        String[] tokens = s.split(CSV_SEPARATOR);

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss ZZZ yyyy", Locale.ENGLISH);
        Date timeOfReceipt = formatter.parse(tokens[0]);
        int serviceTime = Integer.parseInt(tokens[1]);
        int profit = Integer.parseInt(tokens[2]);

        return new Application(timeOfReceipt, serviceTime, profit);
    }
}
