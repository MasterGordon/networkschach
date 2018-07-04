# networkschach

0 / false  ist immer schwarz
1 / true  ist immer weiß

## Protokoll

### Clientpakets
* m#x1,y1#x2,y2
* c#SESSION

### Serverpakets
* i#FarbeDieDranIst#SpielFeldReiheFürReihe#EigeneFarbe
* b#FarbeDieDranIst#SpielFeldReiheFürReihe
  - Q = Dame
  - K = König
  - R = Läufer
  - H = Springer
  - P = Bauer
  - T = Turm
  - kleiner Buchstabe = white
  - großer Buchstabe = schwarz
* e#0	0=Zug für Figur nicht möglich	1=König absichtlich Schach gesetzt
* r#0 0=Verloren						1=Gewonnen
*
