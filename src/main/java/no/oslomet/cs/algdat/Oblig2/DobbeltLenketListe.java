package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */

    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {

        // Lager konstruktør for null-liste, hvor alle hodet og halen er null og
        // dermed er antallet og endringene 0.
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;

    }

    // Oppgave 1 ///////
    public DobbeltLenketListe(T[] a) {
        // Lager konstuktør til lenket liste med sjekkliste

        // Kaster feilmelding om tabellen er null
        if (a == null){
            throw new NullPointerException("Tabellen er null");
        }
        // Gjør ingenting hvis tabellen er på lengde 0.
        else if (a.length == 0) {
            return;
        }

        // Hjelpevariabel.
        int forste_indeks = -1;

        // Itererer over tabellen, og finner indeksen til første verdi som ikke er null
        int i = 0;
        while (i < a.length){
            if (a[i] != null){
                forste_indeks = i;
                break;
            }
            i++;
        }

        // Hvis "forste_indeks" er '-1' vet vi at alle verdier er 'null',
        // og vi returnerer uten å gjøre noe mer.
        if (forste_indeks == -1) {
            return;
        }

        // Setter denne som hode, og pekeren til forrige som null.
        Node<T> ny_node = new Node<>(a[forste_indeks]);
        // Pekeren til forrige settes som null
        hode = ny_node;
        // Antallet her vil være 1.
        antall++;

        //Hjelpevariabel.
        Node<T> forrige_node;

        // Itererer over listen.
        for (i = forste_indeks+1; i < a.length; i++){
            if (a[i] != null){
                // Setter "forrige_node" til den forrige som ble opprettet.
                forrige_node = ny_node;
                // Oppretter en ny node, med verdi fra tabellen, og med peker til "forrige_node".
                ny_node = new Node<T>(a[i], forrige_node, null);
                // Oppdaterer "forrige_node" til å peke på den nye noden.
                forrige_node.neste = ny_node;
                
                // Øker antall og endringer med 1.
                antall++;
                endringer++;
            }
        }
        // Oppretter hale-noden, og peker denne til den siste noden som ble opprettet.
        hale = ny_node;
    }

    // Hjelpemetode /////
    private void fraTilKontroll(int antall, int fra, int til){
        if (fra < 0){
            throw new IndexOutOfBoundsException("Fra er negativt");
        }
        if (til > antall){
            throw new IndexOutOfBoundsException("Til er større enn tabellens lengde, altså utenfor tabellen");
        }

        if (fra > til){
            throw new IllegalArgumentException("Fra er større enn til, illegalt intervall");
        }
    }
    // Oppgave 3b ///////
    public Liste<T> subliste(int fra, int til) {
        // Sjekker at intervallet er gyldig.
        fraTilKontroll(antall, fra, til);

        // Oppretter/Instantierer sublisten.
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();
        // Finner lengden av sublisten.
        int lengde = til - fra;
        // Om lengden er 0 returnerer tom liste.
        if (lengde == 0){
            return subliste;
        }

        // Instantierer en temp node, med verdien til fra.
        Node<T> temp = finnNode(fra);

        // Itererer over listen i intervallet [fra - til>;
        for (int i = fra; i < til; i++){
            // Legger inn temp i listen ved hjelp av leggInn() metonden
            subliste.leggInn(temp.verdi);
            // Peker temp til temp.neste.
            temp = temp.neste;
        }
        // Returnerer sublisten.
        return subliste;
    }

    // Oppgave 1 ///////
    @Override
    public int antall() {
        // returner antall verdier i a som ikke er null
        return antall;
    }

    // Oppgave 1 ///////
    @Override
    public boolean tom() {
          if (hode == null){
              return true;
          }
          else{
              return false;
          }
    }

    // Oppgave 2b ///////
    @Override
    public boolean leggInn(T verdi) {
        // Sjekker at verdien ikke er null.
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt.");

        // Oppretter en ny node med verdien gitt som argument.
        Node<T> ny_node = new Node<T>(verdi);
        
        // Sjekker om listen er tom.
        if (tom()) {
            // Hvis det er tilfellet opprettes "hode"- og "hale"-nodene refereranser til "ny_node".
            hode = ny_node;
            hale = ny_node;

            // Øker "antall" og "endringer" med 1.
            antall++;
            endringer++;

            return true;
        }
        else {
            // Hvis listen ikke er tom,
            // settes "ny_node" sin 'forrige'-peker til det (tidligere) siste elementet i listen.
            ny_node.forrige = hale;
            // Det (tidligere) siste elementet sin 'neste'-peker settes til "ny_node".
            hale.neste = ny_node;
            // Hale sine 'forrige'-peker settes til ny node.
            hale = ny_node;

            // Øker "antall" og "endringer" med 1.
            antall++;
            endringer++;

            return true;
        }
    }


    // Oppgave 5 ///////
    @Override
    public void leggInn(int indeks, T verdi) {
        // Sjekker at verdien ikke er 'null'.
        Objects.requireNonNull(verdi);
        // Sjekker at indeksen er gylding.
        indeksKontroll(indeks, true);


        // Tilfellet der indeks er lik antall, altså er siste elementet.
        if (indeks == antall){
            // Bruker "leggInn-metoden", siden denne legger inn legger til elementet på slutten av listen.
            leggInn(verdi);
            return;
    }

        // Tilfellet der indeks er 0
        else if (indeks == 0){
            // Lager input noden, med peker på den tidligere første noden.
            Node<T> input = new Node<>(verdi, null, hode);
            
            // Oppdaterer pekere for "hode" og den tidligere første noden.
            hode.forrige = input;
            hode = input;

            // Øker "antall" og "endringer".
            antall++;
            endringer++;
            return;
        }

        // Hvis verdien skal legges inn et annet sted i listen : 
        else {
            // Finner det som vil bli nodene før og etter den nye noden respektivt.
            Node<T> før_node = finnNode(indeks-1);
            Node<T> etter_node = før_node.neste;

            // Oppretter den nye noden med riktige referanser og verdi.
            Node<T> ny_node = new Node<T>(verdi, før_node, etter_node);

            // Fikser oppdaterer pekerene i nodene før og etter den nye noden.
            før_node.neste = ny_node;
            etter_node.forrige = ny_node;

            // Øker "antall" og "endringer."
            antall++;
            endringer++;
        }
    }

    // Oppgave 4 ///////
    @Override
    public boolean inneholder(T verdi) {
        // Dersom indeksen til verdien er større enn -1 og mindre enn antall.
        if (indeksTil(verdi) > -1 && indeksTil(verdi) < antall){
            // Returnerer trur;
            return true;
        }
        else {
            // Hvis ikke det finnes returnerer vi false.
            return false;
        }
    }

    // Oppgave 3a ///////
    // Hjelpemetode /////
    private Node<T> finnNode(int indeks){
        // Instantierer ny node, som skal returneres.
        Node<T> currentNode;

        // Sjekker at indeksen er gyldig.
        indeksKontroll(indeks, false);

        // Finner ut fra hvilken ende vi starter å søke ved å
        // finne ut hvilken side av listen indeksen ligger.
        int side = antall / 2;

        // Dersom indeksen er på venstre siden fra midten av listen:
        if (indeks < side){
            // Setter vi current node som hode,
            currentNode = hode;
            // Itererer over listen fra starten altså hode, frem til index
            for (int i = 0; i < indeks; i++){
                // Flytter en node til høyre for hver iterasjon vha neste pekeren
                currentNode = currentNode.neste;
            }
        }
        
        // Om indeksen er større enn det punktet, starter vi fra halen.
        else{
            currentNode = hale;
            // Itererer over listen fra siste node og mot venstre.
            for (int j = antall - 1; j > indeks; j--){
                // Flytter en enhet til høyre for hver iterasjon vha forrige pekeren.
                currentNode = currentNode.forrige;
            }
        }
        
        // Returnerer noden.
        return currentNode;
    }

    // Oppgave 3a ///////
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> returVerdi = finnNode(indeks);
        return  returVerdi.verdi;
    }

    // Oppgave 4 ///////
    @Override
    public int indeksTil(T verdi) {
        // Dersom verdien er null eller listen er tom returneres -1
        if (verdi == null || tom()) {
            return -1;
        }

        // Setter temp node lik første verdien
        Node<T> temp = hode;
        // Itererer over listen
        for (int i = 0; i < antall; i++){
            // Dersom temp (hodet.verdi) er lik verdien returnerer vi indeksen
            if (temp.verdi.equals(verdi)){
                return i;
            }
            // Hvis ikke flytter til neste node vha neste pekeren.
            temp = temp.neste;
        }
        // Hvis ikke verdien finnes i listen returnerer vi -1
        return -1;
    }

    // Oppgave 3a ///////
    @Override
    public T oppdater(int indeks, T nyverdi) {

        // Kjører indekskontroll for å sjekke at indeksen er gyldig.
        indeksKontroll(indeks, false);

        // Sjekker at nyverdi er gyldig.
        Objects.requireNonNull(nyverdi);

        // Henter først verdien fra listen med gitt indeks.
        Node<T> old = finnNode(indeks);

        // Lagrer den opprinnelige verdien.
        T temp = old.verdi;

        // Bytter verdiene ( verdien til den gamle med den nye)
        old.verdi = nyverdi;

        // Øker "endringene"
        endringer++;

        // Returnerer den lagrede verdien
        // Kan eventuelt returnere "old.verdi" også.
        return temp;
    }

    // Oppgave 6 ///////
    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }
        
        Node<T> gjell_node = hode;

        // Iterer gjennom listen så lenge den gjellende noden har en neste node.
        while (gjell_node != null) {
            // Hvis noden har riktig verdi, sjekkes det om den er på starten / slutten av listen eller ikke.
            if (gjell_node.verdi.equals(verdi)) {
                // Hvis verdien ligger på indeks 0 sjekkes det om det listen har 1 element.
                if (gjell_node.forrige == null) {
                    // Hvis det er tilfelle settes hode og hale til 'null.'
                    if (antall() == 1) {
                        hode = null;
                        hale = null;
                    }
                    // Hvis det ikke er tilfellet, fjernes noden, og "hode" pekes mot den nye første-noden.
                    else {
                        hode = gjell_node.neste;
                        hode.forrige = null;
                        gjell_node = null;
                    }
                }
                // Hvis verdien er den siste i listen, oppdateres "hale" og den nye sisteverdien i listen.
                else if (gjell_node.neste == null) {
                    hale = gjell_node.forrige;
                    hale.neste = null;
                    gjell_node = null;
                }
                // Hvis verdien ligger et sted i listen, fjernes noden,
                // og nodene rundt oppdateres til å peke på hverandre.
                else {
                    Node<T> forrige_node = gjell_node.forrige;
                    forrige_node.neste = gjell_node.neste;
                    forrige_node.neste.forrige = forrige_node;
                }

                // Til slutt oppdateres "antall" og "endringer" med -1 og 1 respektivt.
                antall--;
                endringer++;

                return true;
            }
            // Går til neste node.
            gjell_node = gjell_node.neste;
        }
        // Hvis verdien ikke finnes i liste, returneres 'null'.
        return false;
    }

    // Oppgave 6 ///////
    @Override
    public T fjern(int indeks) {
        // Sjekker at gitt indeks er gyldig.
        indeksKontroll(indeks, false);

        // Hjelpevariabel.
        T fjernet_verdi;

        // Hvis det kun er et element i listen.
        if (antall == 1) {
            fjernet_verdi = hode.verdi;
            hode = null;
            hale = null;
        }
        // Hvis det første elementet skal fjernes.
        else if (indeks == 0) {
            fjernet_verdi = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        }
        // Hvis det siste elementet skal fjernes.
        else if (indeks == antall-1) {
            fjernet_verdi = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        }
        // Hvis et annet element skal fjernes.
        else {
            Node<T> gjell_node = finnNode(indeks);
            Node<T> forrige_node = gjell_node.forrige;
            Node<T> neste_node = gjell_node.neste;

            forrige_node.neste = neste_node;
            neste_node.forrige = forrige_node;

            fjernet_verdi = gjell_node.verdi;
        }
        
        antall--;
        endringer++;
        return fjernet_verdi;
    }

    // Oppgave 7 ///////
    @Override
    public void nullstill(){
        // Hvis listen allerede er tom, avslutter vi bare funksjonen.
        if (tom()) {
            return;
        }

        // Setter hjelpevariabler.
        Node<T> gjell_node = hode;
        Node<T> neste_node = hode.neste;

        // Iterer over listen så lengde det finnes en neste node.
        while (gjell_node.neste != null) {
            gjell_node = neste_node;
            endringer++;

            // Nuller "forrige" og "verdi".
            gjell_node.forrige = null;
            gjell_node.verdi = null;

            // Hvis det ikke finnes en neste node, brytes løkken.
            if (gjell_node.neste == null) {
                break;
            }
            // Hvis ikke, lagres den neste node, før "gjell_node.neste" blir nullet ut.
            else {
                neste_node = gjell_node.neste;
                gjell_node.neste = null;
            }
        }

        // Når hele listen er kjørt gjennom, settes "hode" og "hale" til null, og "antall" settes til 0.
        hode = null;
        hale = null;
        antall = 0;
    }

    // Metode for å nullstille ved bruk av "fjern()"-metoden.
    // Denne brukes ikke, da den er betydlig mindre effektiv.
    /* @Override
    public void nullstill() {
        if (tom()) {
            return;
        }

        Node<T> gjell_node = hode;
        while (gjell_node.neste != null) {
            gjell_node = gjell_node.neste;
            fjern(0);
        }
        antall = 0;
    } */

    // Oppgave 2a ///////
    @Override
    public String toString() {
        // Hvis listen er tom, returneres "[]".
        if (tom()) {
            return "[]";
        }   

        // Oppretter et nytt StringJoiner-objekt, med "," som delimiter, og "[" og "]" som prefiks og sufiks respektivt.
        StringJoiner out_string = new StringJoiner(", ", "[", "]");

        // Starter på "hode"-noden.
        Node<T> curr_node = hode;
    
        // Iterer så lenge den gjeldende noden har en "neste"-node.
        while (curr_node != null) {
            // Legger til en String-representasjon av verdien i StringJoiner-objektet.
            out_string.add(curr_node.verdi.toString());
            // Finner neste node.
            curr_node = curr_node.neste;
        }

        // Returnerer en String-representasjon av StringJoiner-objektet.
        return out_string.toString();
    }

    // Oppgave 2a ///////
    public String omvendtString() {
        // Hvis listen er tom, returneres "[]".
        if (antall == 0) {
            return "[]";
        }

        // Oppretter et nytt StringJoiner-objekt, med "," som delimiter, og "[" og "]" som prefiks og sufiks respektivt.
        StringJoiner out_string = new StringJoiner(", ", "[", "]");

        // Starter på "hale"-noden.
        Node<T> curr_node = hale;
        
        // Iterer så lenge den gjeldende noden har en "forrige"-node.
        while (curr_node != null) {
            // Legger til en String-representasjon av verdien i StringJoiner-objektet.
            out_string.add(curr_node.verdi.toString());
            
            // Finner node før den gjellende noden.
            curr_node = curr_node.forrige;
        }

        // Returnerer en String-representasjon av StringJoiner-objektet.
        return out_string.toString();
    }

    // Oppgave 8b ///////
    @Override
    public Iterator<T> iterator() {
        // Returnerer et DobbeltLenketListeIterator-objekt.
        return new DobbeltLenketListeIterator();
    }

    // Oppgave 8d ///////
    public Iterator<T> iterator(int indeks) {
        // Sjekker at indeksen er gyldig.
        indeksKontroll(indeks, false);
        // Returnerer et DobbeltLenketListeIterator-objekt med start-indeks.
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        // Oppgave 8c ///////
        private DobbeltLenketListeIterator(int indeks) {
            // Finnder startnoden i listen.
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        // Oppgave 8a ///////
        @Override
        public T next() {
            // Sjekker at antall endringer er korrekt.
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            }
            // Sjekker at det finnes en neste node.
            else if (!hasNext()) {
                throw new NoSuchElementException();
            }

            fjernOK = true;
            // Lagrer verdien til gjellende node.
            T retur_verdi = denne.verdi;
            // Setter "denne" til neste node.
            denne = denne.neste;
            // Returnerer den gjellende noden.
            return retur_verdi;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe
