<div align="center">

# 🎲 Monopoly Java

**Implementazione del classico gioco da tavolo Monopoly, in Java puro, giocabile da terminale con una componente grafica JavaFX opzionale.**

[![Language](https://img.shields.io/badge/Java-17-orange.svg?logo=java)](https://openjdk.org/projects/jdk/17/)
[![UI](https://img.shields.io/badge/UI-JavaFX%20%7C%20Terminale%20ANSI-1f6feb.svg)]()
[![Status](https://img.shields.io/badge/status-completato-brightgreen.svg)]()

</div>

---

## 📑 Indice

- [Panoramica](#-panoramica)
- [Funzionalità](#-funzionalità)
- [Architettura](#-architettura)
- [Struttura del progetto](#-struttura-del-progetto)
- [Regole implementate](#-regole-implementate)
- [Requisiti](#-requisiti)
- [Esecuzione](#-esecuzione)
- [Autore](#-autore)

---

## 📖 Panoramica

**Monopoly Java** è una riproduzione del gioco da tavolo Monopoly interamente sviluppata in Java, come progetto per il corso di *Fondamenti di Informatica* alla SUPSI. Il gameplay principale si svolge da **terminale**, con un tabellone e delle caselle colorate tramite codici ANSI; a questo si affianca una piccola interfaccia **JavaFX** (realizzata con SceneBuilder) dedicata al lancio dei dadi e alle schermate iniziale/finale del gioco.

---

## ⚙️ Funzionalità

- 🧩 **Tabellone dinamico** — righe e caselle per riga configurabili, con nomi e colori delle proprietà assegnati in parte in modo casuale ad ogni partita.
- 👥 **Fino a 10 giocatori** in una singola partita, con turni gestiti a rotazione.
- 🏠 **Acquisto e gestione proprietà** — terreni, stazioni e società, con costruzione di case e hotel e relativo aumento progressivo dei prezzi.
- 🎲 **Lancio dadi** (2 dadi, gestione dei doppi) e movimento automatico dei giocatori sul tabellone.
- 🃏 **Carte Imprevisti e Probabilità**, caricate da file di testo (`Imprevisti.txt`, `Probabilita.txt`), con effetti di spostamento (`VaiA`) o variazione del budget (`ModificaBudget`).
- 💰 **Gestione economica completa** — pagamenti di affitti, tasse (di lusso e patrimoniale), stipendio al passaggio dal Via, e fallimento (bancarotta) di un giocatore.
- 🚔 **Prigione** — con tentativi limitati per uscirne e pagamento della cauzione.
- 🅿️ **Casella Parcheggio** e casella "Vai in prigione".
- 🖥️ **Menu testuali interattivi** per acquisti, miglioramenti dei terreni e azioni di gioco.
- 🎨 **Modulo grafico JavaFX** (`monopoly.visuale`) — schermata iniziale, animazione dei dadi e schermata del vincitore.

---

## 🏗️ Architettura

```
                ┌───────────────────┐
                │       Main          │  Avvio partita testuale
                └─────────┬───────────┘
                          │
                ┌─────────▼───────────┐
                │        Gioco          │  Game loop, gestione turni e giocatori
                └─────────┬───────────┘
       ┌──────────────────┼──────────────────────┐
       ▼                  ▼                       ▼
 ┌───────────┐    ┌───────────────┐      ┌─────────────────┐
 │ Tabellone   │    │   Giocatore    │      │      Banca        │
 │  + Caselle   │    │  (movimento,    │      │ (fondi, pagamenti)  │
 │  + Carte      │    │   pagamenti)     │      └─────────────────┘
 └───────────┘    └───────────────┘

                ┌───────────────────┐
                │    MainVisuale       │  Avvio modulo grafico (JavaFX)
                └─────────┬───────────┘
                          │
                ┌─────────▼───────────┐
                │    GiocoVisuale        │  Dadi animati, schermata iniziale/finale
                └───────────────────┘
```

---

## 🗂 Struttura del progetto

```
Monopoly-java/
├── src/monopoly/
│   ├── Main.java                    → Entry point versione testuale
│   ├── Gioco.java                   → Game loop principale
│   ├── Coordinate.java
│   │
│   ├── componentigioco/
│   │   ├── Banca.java / Dado.java / Tabellone.java
│   │   ├── carte/                   → Carta, Imprevisti/Probabilità, effetti (VaiA, ModificaBudget)
│   │   ├── casella/                 → Proprietà, Stazione, Società, Tasse, Prigione, Parcheggio...
│   │   └── giocatore/                → Giocatore + movimento e pagamenti
│   │
│   ├── menus/                       → Menu testuali (acquisti, miglioramenti terreni)
│   ├── schermate/                   → Schermata iniziale/finale (testuale)
│   ├── utilita/                     → Costanti di gioco, colori ANSI, utility scanner
│   │
│   └── visuale/                     → Modulo grafico JavaFX (MainVisuale, GiocoVisuale, controller)
│
├── risorse/
│   ├── img/                          → Asset grafici (dadi, sfondi, loghi)
│   └── monopoly/visuale/             → File FXML (dadi, schermata iniziale, schermata vincitore)
│
└── src/module-info.java              → Modulo Java (dipendenze javafx.controls / javafx.fxml)
```

---

## 📜 Regole implementate

Il regolamento segue il Monopoly classico: partenza con un budget iniziale per ciascun giocatore, incasso al passaggio dal Via, acquisto libero delle proprietà non possedute, pagamento dell'affitto ai proprietari, costruzione di case/hotel con relativo aumento di valore, tasse fisse (Tassa di Lusso) e percentuali (Tassa Patrimoniale sul patrimonio), e uscita dalla partita in caso di bancarotta. Il numero di giocatori, gli importi e le regole economiche sono centralizzati nella classe `Costanti`, facilmente configurabili.

---

## 📦 Requisiti

- **JDK 17** o superiore
- **JavaFX SDK 17** (solo per eseguire il modulo grafico `MainVisuale`)

Il progetto non usa un build tool (Maven/Gradle): è pensato per essere aperto ed eseguito direttamente da un IDE (IntelliJ IDEA / Eclipse) con le librerie JavaFX configurate come modulo.

---

## ▶️ Esecuzione

**Versione testuale** (gameplay completo da terminale):

```bash
java --module-path src -d out
java -cp out monopoly.Main
```

**Versione grafica** (dadi e schermate iniziale/finale, richiede JavaFX):

```bash
java --module-path <percorso-javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml -cp out monopoly.visuale.MainVisuale
```

> Il modo più semplice resta comunque importare il progetto in IntelliJ IDEA o Eclipse, configurare le librerie JavaFX ed eseguire `Main` (testuale) o `MainVisuale` (grafico) direttamente dall'IDE.

---

## 👤 Autore

Progetto sviluppato per il corso di *Fondamenti di Informatica* alla SUPSI.

<div align="center">

---

Fatto con 🎓 alla SUPSI

</div>