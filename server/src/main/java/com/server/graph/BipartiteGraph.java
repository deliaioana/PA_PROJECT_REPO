package com.server.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BipartiteGraph {
    private List<Woman> women = new ArrayList<>();
    private List<Man> men = new ArrayList<>();
    private int numberOfFreeMen;
    private int numberOfPairs;

    public void generateRandom(int size){
        setNumberOfPairs(size);
        setNumberOfFreeMen(size);

        IntStream womenStream = IntStream.rangeClosed(1, size);
        setWomen(womenStream.mapToObj(index -> new Woman((String.valueOf((char) (index + 64))))).collect(Collectors.toList()));

        IntStream menStream = IntStream.rangeClosed(1, size);
        setMen(menStream.mapToObj(index -> new Man((((Integer) index).toString()))).collect(Collectors.toList()));

        assignRandomPreferencesForWomen();
        assignRandomPreferencesForMen();

    }

    private void assignRandomPreferencesForMen() {
        for (Man man : men) {
            for (int i = 0; i < numberOfPairs; ++i) {
                man.orderedPreferences.add(getRandomWomanForMan(man));
            }
        }
    }

    private Woman getRandomWomanForMan (Man man) {
        boolean finished = false;
        while(!finished) {
            Random random = new Random();
            int index = random.nextInt(numberOfPairs);
            Woman woman = women.get(index);
            if(!man.orderedPreferences.contains(woman)){
                return woman;
            }
        }
        return null;
    }

    private void assignRandomPreferencesForWomen() {
        for (Woman woman : women) {
            for (int i = 0; i < numberOfPairs; ++i) {
                woman.orderedPreferences.add(getRandomManForWoman(woman));
            }
        }
    }

    private Man getRandomManForWoman (Woman woman) {
        boolean finished = false;
        while(!finished) {
            Random random = new Random();
            int index = random.nextInt(numberOfPairs);
            Man man = men.get(index);
            if(!woman.orderedPreferences.contains(man)){
                return man;
            }
        }
        return null;
    }

    public int getNumberOfPairs() {
        return numberOfPairs;
    }

    public void setNumberOfPairs(int numberOfPairs) {
        this.numberOfPairs = numberOfPairs;
    }

    public List<Man> getMen() {
        return men;
    }

    public void setMen(List<Man> men) {
        this.men = men;
    }

    public List<Woman> getWomen() {
        return women;
    }

    public void setWomen(List<Woman> women) {
        this.women = women;
    }

    @Override
    public String toString() {
        return "BipartiteGraph{" +
                "women=" + women +
                ", men=" + men +
                ", numberOfPairs=" + numberOfPairs +
                '}';
    }

    public int getNumberOfFreeMen() {
        return numberOfFreeMen;
    }

    public void setNumberOfFreeMen(int numberOfFreeMen) {
        this.numberOfFreeMen = numberOfFreeMen;
    }

    public void printPairings() {
        System.out.println("Men ------ Women");
        for (Man man : men) {
            if(man.getPartner() != null) {
                System.out.println(man.name + "   ------   " + man.getPartner());
            }
            else {
                System.out.println(man.name + "   ------   " + "null");
            }
        }
    }

    public String getInfoAsJson() {
        StringBuilder info = new StringBuilder();

        info.append("numberOfPairs.").append(numberOfPairs).append(".numberOfFreeMen.")
                .append(numberOfFreeMen).append(".women~").append(getWomenAsJson())
                .append("~men~").append(getMenAsJson()).append("~");

        return info.toString();
    }

    private String getMenAsJson() {
        StringBuilder info = new StringBuilder();

        for (Man man : men) {
            info.append(man.getJson());
        }

        return info.toString();
    }

    private String getWomenAsJson() {
        StringBuilder info = new StringBuilder();

        for (Woman woman : women) {
            info.append(woman.getJson());
        }

        return info.toString();
    }

    public void loadGraphFromString(String info) {
        System.out.println("Se va face load la graph din stringul: \n" + info);
    }
}
