package com.company;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Uc4 {
    public static void FindCheapestHotel(ArrayList<Hotel> hotelsList){
        Map<Integer,String> days = new HashMap<>();
        String[] day = {"Sun","Mon","Tues","Wed","Thurs","Fri","Sat"};

        LocalDate localDate = LocalDate.of(2021, 8, 10);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int a = dayOfWeek.getValue();

        LocalDate dateBefore = LocalDate.of(2021, Month.SEPTEMBER, 10);
        LocalDate dateAfter = LocalDate.of(2021, Month.SEPTEMBER, 11);
        long noOfDays = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        // Store date With its day
        int i = 10;
        int Total = (int) (noOfDays + i + 1);
        while ( i != Total){
            int j = a;
            days.put(i, day[j]);
            i++;
            if(j == 6)
                a = 0;
            a++;
        }

        // Store WeekDays and WeekEnds in separately
        int count1 = 0;
        int count2 = 0;

        for (Map.Entry<Integer, String> entry : days.entrySet()) {
            String day1 = entry.getValue();
            if(day1.equals(day[0]) || day1.equals(day[1]) || day1.equals(day[2]) || day1.equals(day[3]) || day1.equals(day[4]))
                count1++;
            else
                count2++;
        }

        // getHotel of Minimum Per Day for WeekEnds and WeekDays
        Hotel hotelcheap1 = hotelsList.stream().min(Comparator.comparingInt(Hotel::getWeekDayRate)).get();
        Hotel hotelcheap2 = hotelsList.stream().min(Comparator.comparingInt(Hotel::getWeekEndRate)).get();

        System.out.println("\nCheapest Hotel = " + hotelcheap1.getHotelName() +" and "+ hotelcheap2.getHotelName() );
        System.out.println("Total Rate     = " + ((hotelcheap1.getWeekDayRate() * count1) + (hotelcheap2.getWeekEndRate() * count2)) );

    }
}

