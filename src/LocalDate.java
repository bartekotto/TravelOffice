//import java.util.Arrays;
//
//public class LocalDate {
//    private int day;
//    private int month;
//    private int year;
//
//    public LocalDate(int year, int month, int day) {
//        this.year = year;
//        this.month = month;
//        this.day = day;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    @Override
//    public String toString() {
//        return "LocalDate{" +
//                "day=" + day +
//                ", month=" + month +
//                ", year=" + year +
//                '}';
//    }
//
//    public boolean isLeapYear() {
//        if (this.year % 4 == 0 || this.year % 100 != 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}
