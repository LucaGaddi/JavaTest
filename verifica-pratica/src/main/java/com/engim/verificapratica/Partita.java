package com.engim.verificapratica;

public class Partita {

    Squadra squadra1;
    Squadra squadra2;

    public Partita(Squadra squadra1, Squadra squadra2) {
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
    }

    public Squadra getSquadra1() {
        return squadra1;
    }

    public void setSquadra1(Squadra squadra1) {
        this.squadra1 = squadra1;
    }

    public Squadra getSquadra2() {
        return squadra2;
    }

    public void setSquadra2(Squadra squadra2) {
        this.squadra2 = squadra2;
    }
}
