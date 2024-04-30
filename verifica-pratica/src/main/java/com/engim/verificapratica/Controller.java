package com.engim.verificapratica;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Controller {

    private static Sorteggio sorteggio = new Sorteggio();


  @GetMapping(value = "/addSquadra")
    public void aggiungiSquadra(@RequestParam(value="squadra")String squadra, @RequestParam(value="nazione")String nazione){
        sorteggio.aggiungi(squadra, nazione);
    }

    @GetMapping(value = "/listaSquadreNazione")
    public List<Squadra> visualizzaSquadreNazione(@RequestParam(value = "nazione") String nazione) {
        List<Squadra> squadre = sorteggio.getSquadre();
        List<Squadra> squadreNazione = new ArrayList<>();
        for (Squadra squadra : squadre) {
            if (squadra.getNazione().equals(nazione)) {
                squadreNazione.add(squadra);
            }
        }
        return squadreNazione;
    }

    @GetMapping(value="/sorteggia")
    public List<Squadra> sorteggiaSquadre() {
        Squadra squadra1 = sorteggio.next();
        Squadra squadra2 = sorteggio.next();
        List<Squadra> squadreEstratte = new ArrayList<>();
        while (squadra1.getNazione().equals(squadra2.getNazione())) {
            squadra2 = sorteggio.next();

        }
        squadreEstratte.add(squadra1);
        squadreEstratte.add(squadra2);
        return squadreEstratte;
    }


    @GetMapping(value="sorteggioTorneo")
      public List<Partita> sorteggiaTorneo() {
          List<Squadra> squadre = sorteggio.getSquadre();
          List<Partita> partite = new ArrayList<>();
            Collections.shuffle(squadre);
          if(Math.sqrt(squadre.size()) == (int) Math.sqrt(squadre.size())){
          while (!squadre.isEmpty()){
             Squadra squadra1 = squadre.get(0);
             Squadra squadra2 = squadre.get(1);
             partite.add(new Partita(squadra1,squadra2));
             squadre.remove(0);
             squadre.remove(0);
          }
          return partite;
          }
          else throw new RuntimeException("Torneo non realizzabile");
        }
    }

    /*
    * ES 1: get ("/add?nome=n&nazione=m") che aggiunge al sorteggio una squadra con nome n e nazione m (utilizzare
    * la classe Sorteggio) - 15 p
    *
    * ES 2: get ("/listaSquadre?nazione=n") che restituisce la lista delle squadre di nazione n - 20 p
    *
    * ES 3: get ("/sorteggia") che restituisce 2 squadre di nazioni diverse (utilizzare la classe Sorteggio, il metodo
//    * next. Consiglio: dopo aver sorteggiato la prima, continuare a sorteggiare finché la seconda
    * non è di una nazione diversa) - 20 p
    *
    * ES 4: implementare il sorteggio delle squadre di una fase finale di un torneo a eliminazione diretta.
    * Creare il metodo sorteggiaPartite che:
    * - controlla se il numero di squadre aggiunte è una potenza di 2. Se non lo è lancia una RuntimeException.
    * - Finché ci sono squadre non sorteggiate: sorteggia 2 squadre e le inserisce in un oggetto della classe Partita (da creare)
    * - restituisce la lista di Partite.
    * creare get ("/getPartite") che restituisce la lista appena creata - 30 p
    * */
