package com.dayone.model.constants;

public enum Month {

    JAN("Jan", 1),
    FEB("Feb", 2),
    MAR("Mar", 3),
    APR("Apr", 4),
<<<<<<< HEAD
    MAY("May",5),
=======
    MAY("May", 5),
>>>>>>> c283280 (Initial commit)
    JUN("Jun", 6),
    JUL("Jul", 7),
    AUG("Aug", 8),
    SEP("Sep", 9),
    OCT("Oct", 10),
    NOV("Nov", 11),
    DEC("Dec", 12);

    private String s;
    private int number;

<<<<<<< HEAD
    Month(String s, int n){
=======
    Month(String s, int n) {
>>>>>>> c283280 (Initial commit)
        this.s = s;
        this.number = n;
    }

<<<<<<< HEAD
    public static int strToNumber(String s){
        for (var m : Month.values()){
            if (m.s.equals(s)){
=======
    public static int strToNumber(String s) {
        for (var m : Month.values()) {
            if (m.s.equals(s)) {
>>>>>>> c283280 (Initial commit)
                return m.number;
            }
        }

        return -1;
    }

}
