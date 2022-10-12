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

## Oppgave 2

### a)

Oppgaven er løst ved å iterere gjennom listen (fra enten "hode" eller "hale", avhengig av ønsket rekkefølge), og for hver node legge til verdien i et 'StringJoiner'-objekt. StringJoiner-objektet har satt riktig prefiks, sufiks og delimiter. Når alt er lagt til returneres StringJoiner-objektet sin toString()-metode.

### b)

Det blir opprettet en node med ønsket verdi. Denne settes så inn etter det siste elementet i listen. Dette vil si at noden som pekes på av "hale" (og "hale" selv), blir satt til å peke på den nye noden.

Hvis listen er tom fra før settes både "hode" og "hale" til å peke på den nye noden.

## Oppgave 3

## Oppgave 4

## Oppgave 5

## Oppgave 6

Metoden for å fjerne fra en gitt indeks fungerer ved at den relevante noden hentes ved et kall på finnNode(indeks). Deretter oppdateres nodene før og etter til å peke på hverandre.
Hvis noden som fjernes er enten første eller siste i listen blir dette tatt hensyn til. Hvis listen kun har ett element, settes bare "hode" og "hale" til null.

Metoden for å fjerne gitt en verdi itererer gjennom listen til den finner verdien gitt som input. Deretter oppdateres nodene rundt på samme måte som i metoden over.

## Oppgave 7

Oppgaven løses ved å iterere gjennom listen, og for hver node settes verdi og pekerene til 'null'. Når det ikke lenger er flere noder i listen settes også "hode" og "hale" til 'null', og "antall" settes til 0.

Det er valgt å bruke denne metoden over gjenntatte kall til fjern()-metoden med 0 som argument. Dette fordi den er størrelsesordener bedre enn alternativet.

## Oppgave 8

### a)



### b)

### c)

### d)
