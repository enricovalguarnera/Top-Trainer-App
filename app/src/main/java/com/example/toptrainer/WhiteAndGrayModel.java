package com.example.toptrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhiteAndGrayModel {



    private Map<String, List<String>> GKWhiteGrayAbilities;

    public WhiteAndGrayModel() {
        setGKWhiteGrayAbilities();
    }


    public Map<String, List<String>> getGKWhiteGrayAbilities() {
        return GKWhiteGrayAbilities;
    }

    public void setGKWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("riflessi");
        whites.add("agilita");
        whites.add("anticipo");
        whites.add("scatto");
        whites.add("comunicazione");
        whites.add("lancio");
        whites.add("calcio");
        whites.add("pugni");
        whites.add("elevazione");
        whites.add("concentrazione");
        whites.add("forma");

        List<String> gray = new ArrayList<>();
        gray.add("forza");
        gray.add("aggressivita");
        gray.add("velocita");
        gray.add("creativita");

        data.put("white", whites);
        data.put("gray", gray);
        this.GKWhiteGrayAbilities = data;
    }


//    private void setGKMap() {
//        Map<String, List<String>> data = new HashMap<>();
//
//        this.GKWhiteGreyAbilities = data;
//    }

}


