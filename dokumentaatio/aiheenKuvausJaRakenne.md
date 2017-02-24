**Aihe: Shakki**

Shakkipeli, jota pystyy pelaamaan kaveria vastaan.

Peli toimii normaalin shakin tavoin, jossa pelaajat vuorotellen liikuttavat pelinappuloita. Omalla vuorollaan pelaaja voi klikata nappulaa nähdäkseen mahdolliset siirrot, jotka kyseisellä nappulalla voi tehdä.

**Ohjelman rakenne**
Jokaisella erilaisella nappulalla on oma luokkansa joka perii luokan Piece, mikä pitää sisällään mm. nappulan sijainnin ja pelilaudan. Jokaisela nappulalla on myös ENUM-luokan Colour antama väri: joko musta (BLACK) tai valkoinen (WHITE) 

Luokka Board puolestaan pitää kirjaa nappuloista ja niiden sijainnista. Luokka Game sisältää yhden Board olion jota se käyttää itse pelin pelaamisessa. Game-luokka huolehtii esimerkiksi, vuoroista, voittamisesta ja uuden pelin aloittamisesta.

ChessGUI-luokka pitä sisällään graafisen käyttöliittymän, sekä ChessListener-luokan ilmentymän. ChessListener toteuttaa pelilaudan muutokset, hiiren klikkausten pohjalta. Luokka main käynnistää graafisen käyttliittymän.

**Käyttäjät:** pelaaja(t) (1 tai 2)

**Kaikkien käyttäjien toiminnot:**

* Mahdollisten siirtojen näkeminen
* Nappuloiden liikuttaminen omalla vuorollaan

**Luokkakaavio**

![Luokkakaavio](ClassDiagram.png)

**Sekvenssikaaviot**

![Sekvenssikaavio](OnnistunutSiirto.png)

![Sekvenssikaavio](EpäonnistunutSiirto.png)
