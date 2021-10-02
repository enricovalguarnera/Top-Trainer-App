package com.example.toptrainer.db;

import android.provider.BaseColumns;

public final class TopTrainerReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TopTrainerReaderContract() {}

    /* Inner class that defines the table contents */
    public static class SchedaGiocatoreEntry implements BaseColumns {
        public static final String TABLE_NAME_SCHEDA_GIOCATORE = "SCHEDA_GIOCATORE";
        public static final String COLUMN_PLAYER_NAME = "NOME_GIOCATORE";
        public static final String COLUMN_PLAYER_AGE = "ETA";
        public static final String COLUMN_ID_RUOLI = "ID_RUOLI"; //reference per la join con i ruoli del giocatore
        public static final String COLUMN_ID_ABILITA = "ID_ABILITA"; //reference per la join con le abilità: valori
        public static final String COLUMN_ID_ESERCIZI = "ID_ESERCIZI"; // reference per la join con gli esercizi
        public static final String COLUMN_NOME_ESERCIZIO_TOP = "ESERCIZIO_TOP";
        public static final String COLUMN_PGP_TOP = "PGP_TOP";
        public static final String COLUMN_BONTA_PGP = "BONTA_PGP";
    }

    public static final String SQL_CREATE_ENTRIES_SCHEDA_GIOCATORE =
            "CREATE TABLE " + SchedaGiocatoreEntry.TABLE_NAME_SCHEDA_GIOCATORE + " (" +
                    SchedaGiocatoreEntry._ID + " INTEGER PRIMARY KEY," +
                    SchedaGiocatoreEntry.COLUMN_PLAYER_NAME + " TEXT," +
                    SchedaGiocatoreEntry.COLUMN_PLAYER_AGE + " TEXT," +
                    SchedaGiocatoreEntry.COLUMN_ID_RUOLI + " NUMBER," +
                    SchedaGiocatoreEntry.COLUMN_ID_ABILITA + " NUMBER," +
                    SchedaGiocatoreEntry.COLUMN_ID_ESERCIZI + " NUMBER," +
                    SchedaGiocatoreEntry.COLUMN_NOME_ESERCIZIO_TOP + " TEXT," +
                    SchedaGiocatoreEntry.COLUMN_PGP_TOP + " NUMBER," +
                    SchedaGiocatoreEntry.COLUMN_BONTA_PGP + " NUMBER)";

    public static final String SQL_DELETE_ENTRIES_SCHEDA_GIOCATORE =
            "DROP TABLE IF EXISTS " + SchedaGiocatoreEntry.TABLE_NAME_SCHEDA_GIOCATORE;
}
