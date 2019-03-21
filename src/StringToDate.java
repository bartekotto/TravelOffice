//import java.util.Arrays;
//
//public class StringToDate {
////    public LocalDate StringtoDate(String str) {
////        Character[] breaks = {'/', '-', ' '};
////        int[] date = {0, 0, 0};
////        int time = 0;
////        int now = 0;
////        int pow = 1;
////        for (int i = 0, n = str.length(); i < n; i++) {
////            char c = str.charAt(i);
////            if (Arrays.asList(breaks).contains(c)) {
////                time++;
////                pow = 1;
////                now = 0;
////                if (time == 2) {
////                    pow = 3;
////                }
////            } else if(c != '0') {
////                int kek = Character.getNumericValue(c) * ((int) Math.pow(10, pow));
////                now += kek;
////                date[time] = now;
////                pow--;
////            }
////        }
////        return new LocalDate(date[2], date[1], date[0]);
////    }
//
//    public static void main(String[] args) {
//        StringToDate std = new StringToDate();
//        System.out.println(std.StringtoDate("12 01 1990"));
//    }
//}
