package ivan.model;

import java.util.List;

public class Model {
    private int id = 0;
    private List<Application> applications;
    private int clerkCount;
    private int maxClientCount;
    private int countOfServedClients;
    private int countOfLostClients;
    private int maxQueue;
    private int minQueue;
    private int averageQueue;
    private int averageWaitTime;
    private int profit;

    public Model() {
    }

    public Model(List<Application> applications, int clerkCount, int maxClientCount, int countOfServedClients, int countOfLostClients, int maxQueue, int minQueue, int averageQueue, int averageWaitTime, int profit) {
        this.applications = applications;
        this.clerkCount = clerkCount;
        this.maxClientCount = maxClientCount;
        this.countOfServedClients = countOfServedClients;
        this.countOfLostClients = countOfLostClients;
        this.maxQueue = maxQueue;
        this.minQueue = minQueue;
        this.averageQueue = averageQueue;
        this.averageWaitTime = averageWaitTime;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public int getClerkCount() {
        return clerkCount;
    }

    public void setClerkCount(int clerkCount) {
        this.clerkCount = clerkCount;
    }

    public int getMaxClientCount() {
        return maxClientCount;
    }

    public void setMaxClientCount(int maxClientCount) {
        this.maxClientCount = maxClientCount;
    }

    public int getCountOfServedClients() {
        return countOfServedClients;
    }

    public void setCountOfServedClients(int countOfServedClients) {
        this.countOfServedClients = countOfServedClients;
    }

    public int getCountOfLostClients() {
        return countOfLostClients;
    }

    public void setCountOfLostClients(int countOfLostClients) {
        this.countOfLostClients = countOfLostClients;
    }

    public int getMaxQueue() {
        return maxQueue;
    }

    public void setMaxQueue(int maxQueue) {
        this.maxQueue = maxQueue;
    }

    public int getMinQueue() {
        return minQueue;
    }

    public void setMinQueue(int minQueue) {
        this.minQueue = minQueue;
    }

    public int getAverageQueue() {
        return averageQueue;
    }

    public void setAverageQueue(int averageQueue) {
        this.averageQueue = averageQueue;
    }

    public int getAverageWaitTime() {
        return averageWaitTime;
    }

    public void setAverageWaitTime(int averageWaitTime) {
        this.averageWaitTime = averageWaitTime;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String toCSVString(String CSV_SEPARATOR){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(clerkCount);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(maxClientCount);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(countOfServedClients);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(countOfLostClients);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(maxQueue);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(minQueue);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(averageQueue);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(averageWaitTime);
        stringBuffer.append(CSV_SEPARATOR);
        stringBuffer.append(profit);
        for (Application m : applications) {
            stringBuffer.append("\n");
            stringBuffer.append(m.toCSVString(CSV_SEPARATOR));
        }
        return stringBuffer.toString();
    }
}
