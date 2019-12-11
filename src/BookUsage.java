public class BookUsage {
    private String bookId;
    private int January;
    private int February;
    private int March;
    private int April;
    private int May;
    private int June;
    private int July;
    private int August;
    private int September;
    private int October;
    private int November;
    private int December;
    private int bookTotal;

    public BookUsage(String bookId, int january, int february, int march,
                     int april, int may, int june, int july, int august,
                     int september, int october, int november,
                     int december, int bookTotal) {
        this.bookId = bookId;
        January = january;
        February = february;
        March = march;
        April = april;
        May = may;
        June = june;
        July = july;
        August = august;
        September = september;
        October = october;
        November = november;
        December = december;
        this.bookTotal = bookTotal;
    }

    public String toString() {
        if (bookId.equals("Total")) {
            return bookId + "\t|" + January + "\t\t\t|" + February + "\t\t\t|" + March + "\t\t |" + April + "\t\t\t|" + May + "\t\t|" + June + "\t\t|" + July + "\t\t|" + August + "\t\t\t|" + September + "\t\t\t|" + October + "\t\t\t|" + November + "\t\t\t|" + December + "\t\t\t|" + bookTotal + "\t\t |";
        }
        else {
            return bookId + "\t\t|" + January + "\t\t\t|" + February + "\t\t\t|" + March + "\t\t |" + April + "\t\t\t|" + May + "\t\t|" + June + "\t\t|" + July + "\t\t|" + August + "\t\t\t|" + September + "\t\t\t|" + October + "\t\t\t|" + November + "\t\t\t|" + December + "\t\t\t|" + bookTotal + "\t\t |";
        }
    }

    public int getBookIdAsInt() {
        return Integer.parseInt(bookId);
    }
}

