package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {
        System.out.println(FlightBuilder.createFlights());

        ArrBeforeDep arrBeforeDep = new ArrBeforeDep();
        DepInPast depInPast = new DepInPast();
        Ground2Hours ground2Hours = new Ground2Hours();

        System.out.println(ListProcessing.listFiltration(FlightBuilder.createFlights(),
                arrBeforeDep));

        System.out.println(ListProcessing.listFiltration(FlightBuilder.createFlights(),
                arrBeforeDep, depInPast));

        System.out.println(ListProcessing.listFiltration(FlightBuilder.createFlights(),
                arrBeforeDep, depInPast, ground2Hours));
    }
}
