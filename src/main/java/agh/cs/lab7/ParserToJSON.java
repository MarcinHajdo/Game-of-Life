package agh.cs.lab7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ParserToJSON {
    private ArrayList<Integer> stats =  new ArrayList();
    private JSONObject data = new JSONObject();
    private int day = 1;
    public void statsDay(){
        day++;
        JSONArray jsonStats = new JSONArray();
        jsonStats.put(stats);
        data.put("Stats for "+day+" day",jsonStats);
    }

    public void printToJson() {
        try (FileWriter file = new FileWriter("stats.json")) {
            file.write(String.valueOf(data));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStats(ArrayList<Integer> stats) {
        this.stats = stats;
    }
    @Override
    public String toString() {
        if (stats != null)
            return "Day: " + stats.get(0) + "\nNumber of Animals: " + stats.get(1) + "\nMean Life Length: " + stats.get(2)
                    + "\nMean Children Number: " + stats.get(3) + "\nEnergy: " + stats.get(4)  + "\n";
        return "Print Error";
    }
}