package com.example.power.studentenfutter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warenkorbinhalt  implements Serializable {
    //attributes
    private static List<List<String>> warenkorblist;

    public Warenkorbinhalt()
    {
        warenkorblist = new ArrayList<List<String>>();
    }

    public static void AddtoWarenkorbList(List<String> addlist)
    {
        warenkorblist.add(addlist);
    }

    public static List<List<String>> GetWarenkorbList()
    {
        return warenkorblist;
    }
}
