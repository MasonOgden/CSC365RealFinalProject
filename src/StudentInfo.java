public class StudentInfo {
    String sType;
    String sName;
    int numDays;
    int maxNumBooks;

    public StudentInfo(String sType, String sName, int numDays, int maxNumBooks) {
        this.sType = sType;
        this.sName = sName;
        this.numDays = numDays;
        this.maxNumBooks = maxNumBooks;
    }

    public String toString() {
        return "student type: " + sType +  ", type name: "
                + sName + ", number of days a checkout lasts: " + numDays
                + ", max number of books allowed to be checked out: "  + maxNumBooks;
    }

    public String getSType() {
        return sType;
    }

    public String getSName() {
        return sName;
    }

    public int getNumDays() {
        return numDays;
    }

    public int getMaxNumBooks() {
        return maxNumBooks;
    }
}
