package ivan.model;

import java.util.Date;

public class Application {
    private int id = 0;
    private Date timeOfReceipt;
    private int serviceTime;
    private int profit;

    public Application() {
    }

    public Application(int id, Date timeOfReceipt, int serviceTime, int profit) {
        this.id = id;
        this.timeOfReceipt = timeOfReceipt;
        this.serviceTime = serviceTime;
        this.profit = profit;
    }

    public Application(Date timeOfReceipt, int serviceTime, int profit) {
        this.timeOfReceipt = timeOfReceipt;
        this.serviceTime = serviceTime;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(Date timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String toCSVString(String CSV_SEPARATOR){
        return timeOfReceipt +
                CSV_SEPARATOR +
                serviceTime +
                CSV_SEPARATOR +
                profit;
    }
}


