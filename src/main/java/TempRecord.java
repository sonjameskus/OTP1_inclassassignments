public class TempRecord {

    private int id;
    private double inputValue;
    private double resultValue;
    private int fromUnitId;
    private int toUnitId;

    public TempRecord(double inputValue, double resultValue,
                      int fromUnitId, int toUnitId) {
        this.inputValue = inputValue;
        this.resultValue = resultValue;
        this.fromUnitId = fromUnitId;
        this.toUnitId = toUnitId;
    }

    public int getId() {
        return id;
    }

    public double getInputValue() {
        return inputValue;
    }

    public double getResultValue() {
        return resultValue;
    }

    public int getFromUnitId() {
        return fromUnitId;
    }

    public int getToUnitId() {
        return toUnitId;
    }
}
