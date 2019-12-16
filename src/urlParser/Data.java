package urlParser;

public class Data {
    String name;
    String period;
    String date;
    float openValue;
    float lowValue;
    float highValue;
    float closeValue;


    public Data(String name, String period, String date, float openValue, float lowValue, float highValue, float closeValue) {
        this.name = name;
        this.period = period;
        this.date = date;
        this.openValue = openValue;
        this.lowValue = lowValue;
        this.highValue = highValue;
        this.closeValue = closeValue;
    }

    public String getDate() {
        return date;
    }

    public float value(String v) {
        switch (v) {
            case ("Open"):
                return getOpenValue();
            case ("Close"):
                return getCloseValue();
            case ("Low"):
                return getLowValue();
            case ("High"):
                return getHighValue();
            default:
                return 0;

        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOpenValue() {
        return openValue;
    }

    public void setOpenValue(float openValue) {
        this.openValue = openValue;
    }

    public float getLowValue() {
        return lowValue;
    }

    public void setLowValue(float lowValue) {
        this.lowValue = lowValue;
    }

    public float getHighValue() {
        return highValue;
    }

    public void setHighValue(float highValue) {
        this.highValue = highValue;
    }

    public float getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(float closeValue) {
        this.closeValue = closeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
