[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=463153&assignment_repo_type=GroupAssignmentRepo)
# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer.

Oppgaven er levert av følgende studenter:

* Elzat Arkin, s354390, s354390@oslomet.no
* Knut Høie Guettler, s350775, s350775@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:

* Elzat har hatt ansvaret for oppgave 1, 3, 4 og 5.
* Knut har hatt ansvaret for oppgave 2, 6, 7, og 8.
* All kode har blitt sett gjennom av begge to, og der vi har stått fast har den andre hjulpet.

# Oppgavebeskrivelse

## Oppgave 1
Lager først metodene "antall()" og "tom()", med tilsvarende returverdier. 
Lager så konstruktøren for dobbeltlenketliste, som skal opprette en dobbeltlenketliste med verdier fra tabellen a.
Konstruktøren skal kaste exceoptions ved feilsituasjoner, som for eksempel at tabellen er tom. I tillegg vil variablen "antall" øke for hvert 
element som legges inn i listen fra tabellen.
 
## Oppgave 2

### a)

Oppgaven er løst ved å iterere gjennom listen (fra enten "hode" eller "hale", avhengig av ønsket rekkefølge), og for hver node legge til verdien i et 'StringJoiner'-objekt. StringJoiner-objektet har satt riktig prefiks, sufiks og delimiter. Når alt er lagt til returneres StringJoiner-objektet sin toString()-metode.

### b)

Det blir opprettet en node med ønsket verdi. Denne settes så inn etter det siste elementet i listen. Dette vil si at noden som pekes på av "hale" (og "hale" selv), blir satt til å peke på den nye noden.

Hvis listen er tom fra før settes både "hode" og "hale" til å peke på den nye noden.

## Oppgave 3

### a)
Her lagde vi hjelpemetoden "finnNode()", som skal finne og returnere en node med gitt indeks. For å løse oppgaven brukte vi indeksKontroll() metoden som sjekker om den gitte indeksen er gyldig. 
Etter å ha funnet ut hvilken side av liste indeksen ligger, har vi iterert over listen vha neste- eller forrige- pekere. Tilslutt returnerte noden som ligger i den gitte posisjonen. 

Lagde deretter "hent()" metoden, som henter verdien til en node med gitt indeks. Her sjekket vi også først om indeksen er gyldig. Deretter brukte vi "finnNode()" metoden til å finne noden, og returnerte verdien til noden tilslutt. 

I "oppdater()" metoden, sjekket vi om indeksen er gyldig og om gitt verdi er null først. Dermed fant vi noden ved gitt indeks, og lagret vi verdien i en temp node. Deretter erstatt vi den opprinnelige verdien med den nye verdien. Tilslutt returnerte vi den lagrede verdien (den opprinnelige).

### b)
Her lagde vi metoden "subliste()", hvor vi sjekket om intervallet er gyldig først. Deretter lagde instantierte vi en subliste. Dersom lengden av intervallet var 0 returnerte vi en tom liste. Brukte "finnNode()" metoden til å finne noden ved start posisjon, og så itererte over intervallet ved bruk av neste pekeren, la inn noden i sublisten vha "leggInn()" metoden. 
Til slutt returnerte vi sublisten.  


## Oppgave 4
I metoden "inndeksTil()" returnerte vi -1 ved feilsituasjoner først. Dermed itererte vi over listem ved hjelp av neste pekere, og returnerte indeksen dersom den aktuelle noden har samme verdi som den passerte verdien. Dersom den verdien ikke fantes i listen, ble -1 returnert. 

I metoden "innheholder()", brukte vi "indeksTil()" metoden for å sjekke om en verdi finnes i listen. Dermed returnerte vi boolean verdiene true eller false. 

## Oppgave 5

Her  sjekket først om det gitte intervallet er gyldig, og om verdien er null. Dermed har vi løste vi hvert tilfellet for seg. Når indeksen er lik "antall" la vi bare inn verdien, slik at den automatisk får den siste posisjonen i listen. 
Når indeksen er 0, så la vi verdien i første posisjon, dermed oppdaterte vi pekeren til det opprinnelige hodet, slik at den nye verdien blir hodet, i tillegg økte vi "antall" og "endringer". Når den gitte indeksen har noder både foran og bak,
så fant vi brukte finnNode() metoden til å finne noden før den gitte indeksen.Deretter brukte vi neste og forrige pekere til å plassere den nye verdien mellom de to opprinnelige nodene. 
Til slutt økte vi "antall" og "endringer".
 

## Oppgave 6

Metoden for å fjerne fra en gitt indeks fungerer ved at den relevante noden hentes ved et kall på finnNode(indeks). Deretter oppdateres nodene før og etter til å peke på hverandre.
Hvis noden som fjernes er enten første eller siste i listen blir dette tatt hensyn til. Hvis listen kun har ett element, settes bare "hode" og "hale" til null.

Metoden for å fjerne gitt en verdi itererer gjennom listen til den finner verdien gitt som input. Deretter oppdateres nodene rundt på samme måte som i metoden over.

## Oppgave 7

Oppgaven løses ved å iterere gjennom listen, og for hver node settes verdi og pekerene til 'null'. Når det ikke lenger er flere noder i listen settes også "hode" og "hale" til 'null', og "antall" settes til 0.

Det er valgt å bruke denne metoden over gjenntatte kall til fjern()-metoden med 0 som argument. Dette fordi den er størrelsesordener bedre enn alternativet.

## Oppgave 8

### a)

Oppgaven løses ved å sjekke at alle argumenter og variabler er som de skal være. Hvis det er tilfellet lagres verdien for den gjellende noden, "denne"-pekeren flyttes en frem, og den lagrede verdien returneres.

### b)

Oppretter og returnerer en ny instans av DobbeltLenketListeIterator-klassen.

### c)

Finner noden på indeks-poisjonen gitt som argument, og peker "denne" på den. Setter så de variabler som skal settes.

### d)

Sjekker at indeksen gitt som argument er gyldig. Returnerer så et DobbeltLenketListeIterator-objekt som starter på gitt indeks.