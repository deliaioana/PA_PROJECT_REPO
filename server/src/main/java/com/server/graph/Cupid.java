package com.server.graph;

public class Cupid {
    public Cupid(BipartiteGraph graph) {
        while(graph.getNumberOfFreeMen() > 0) {
            Man man = getAFreeMan(graph);
            for (Woman woman : man.getOrderedPreferences()) {
                if(woman.getPartner() == null) {
                    pairCouple(man, woman);
                    graph.setNumberOfFreeMen(graph.getNumberOfFreeMen()-1);
                    break;
                }
                else {
                    String currentPartner = woman.getPartner();
                    if(prefersFirstMan(woman, man.getName(), currentPartner)) {
                        makeSingle(woman);
                        makeSingle(getManFromName(currentPartner, graph));
                        pairCouple(man, woman);
                        break;
                    }
                }
            }
        }
    }

    private Person getManFromName(String currentPartner, BipartiteGraph graph) {
        for (Man man : graph.getMen()) {
            if(man.getName().equals(currentPartner)) {
                return man;
            }
        }
        return null;
    }

    private void makeSingle(Person person) {
        person.setPartner(null);
    }

    private boolean prefersFirstMan(Woman woman, String firstMan, String currentPartner) {
        for(Man man : woman.getOrderedPreferences()) {
            if(man.getName() == firstMan) {
                return true;
            }
            if(man.getName() == currentPartner) {
                return false;
            }
        }
        return false;
    }

    private void pairCouple(Man man, Woman woman) {
        man.setPartner(woman.getName());
        woman.setPartner(man.getName());
    }

    private Man getAFreeMan(BipartiteGraph graph) {
        for (Man man : graph.getMen()) {
            if(man.getPartner() ==  null) {
                return man;
            }
        }
        return null;
    }
}
