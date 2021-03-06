package com.denis.minutodereflexao;

<<<<<<< HEAD
=======
import android.content.ContentValues;
>>>>>>> versao2
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;
import android.util.Log;
<<<<<<< HEAD
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
=======

import java.util.Objects;

>>>>>>> versao2
import static com.denis.minutodereflexao.DbHelper.DATABASE_NAME;
import static com.denis.minutodereflexao.DbHelper.DATABASE_PATH;

public class DbAccess {

<<<<<<< HEAD
    // Constantes para schema da tabela MENSAGEM
    public static final String TABELA_MENSAGEM = "mensagem";
=======
>>>>>>> versao2
    public static final String COLUNA_ID = BaseColumns._ID;
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_TEXTO = "texto";
    public static final String COLUNA_AUTOR = "autor";
<<<<<<< HEAD
    public static final String[] PROJECTION_TODAS_COLUNAS = {COLUNA_ID, COLUNA_TITULO, COLUNA_TEXTO, COLUNA_AUTOR};
=======
    public static final String COLUNA_FAVORITO = "favorito";
    // Constantes para schema da tabela MENSAGEM
    private static final String TABELA_MENSAGEM = "mensagem";
    private static final String[] PROJECTION_TODAS_COLUNAS = {COLUNA_ID, COLUNA_TITULO, COLUNA_TEXTO, COLUNA_AUTOR, COLUNA_FAVORITO};
>>>>>>> versao2

    private final static String LOG_TAG = "DbAccess";
    private static DbAccess instance;
    private DbHelper mDbHelper;
    private SQLiteDatabase mDatabase;
    private Cursor mCursor;

<<<<<<< HEAD
    /**
     * Construtor da classe
     *
     * @param context
     */
    private DbAccess(Context context) {
        Log.i(LOG_TAG, "Executa contrutor DbAccess");
=======
    private DbAccess(Context context) {
        Log.i(LOG_TAG, "Executa construtor DbAccess");
>>>>>>> versao2
        mDbHelper = new DbHelper(context);
    }

    /**
<<<<<<< HEAD
     * Retorna uma instância de DbAccess.
     *
=======
>>>>>>> versao2
     * @param context Contexto da chamada da função
     * @return Instância de DbAccess
     */
    public static DbAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DbAccess(context);
        }
        Log.i(LOG_TAG, "getInstance");
        return instance;
    }

    /**
<<<<<<< HEAD
     * Abre uma conexão com o banco de dados
=======
     * Abre uma conexão de leitura com o banco de dados
>>>>>>> versao2
     */
    public void openRead() {
        Log.i(LOG_TAG, "getReadableDatabase()");
        mDatabase = mDbHelper.getReadableDatabase();
    }

    /**
<<<<<<< HEAD
     * Abre uma conexão com o banco de dados
=======
     * Abre uma conexão de escrita com o banco de dados
>>>>>>> versao2
     */
    public void openWrite() {
        Log.i(LOG_TAG, "getWritableDatabase()");
        mDatabase = mDbHelper.getWritableDatabase();
    }

    /**
     * Fecha a conexão com o banco de dados
     */
    public void close() {
        if (mDatabase != null) {
            Log.i(LOG_TAG, "closeDatabase");
            mDatabase.close();
        }
    }

    /**
<<<<<<< HEAD
     * Consulta todos os dados das colunas especificadas em strProjetion
     *
     * @return Um cursor com o resultado
     */
    public Cursor getTodasMensagens() {

        try {
            Log.i(LOG_TAG, "Query Todas as mensagens");
            mCursor = mDatabase.query(TABELA_MENSAGEM, //Nome da tabela
                    PROJECTION_TODAS_COLUNAS, // campos para pesquisa. NULL = *
                    null, // Criterios de pesquisa WHERE
                    null, // argumentos do WHERE
                    null, // Group By
                    null, // Having
                    null); // Order By
        } catch (Exception e) {
            Log.i(LOG_TAG, e.getMessage());
        } finally {
=======
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        String mPath;
        SQLiteDatabase checkDB = null;
        try {
            mPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);
            if (checkDB != null) {
                Log.i(LOG_TAG, "Banco de dados EXISTE: " + mPath);
            }
        } catch (SQLiteException e) {
            Log.i(LOG_TAG, "Banco de dados NÃO existe. " + e.getMessage());
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    public Integer atualizaCampoFavorito(int id, String strValor) {
        // Valores a atualizar
        ContentValues valores = new ContentValues();
        valores.put(COLUNA_FAVORITO, strValor);
        // Qual linha a alterar (cláusula WHERE)
        String strSelection = COLUNA_ID + " = ?";
        String[] strSelectionArgs = {Objects.toString(id)};
        // Registrar quantidade de linhas atualizadas após a atualização
        int linhasAtualizadas = 0;

        try {
            Log.i(LOG_TAG, "Submetendo update: (" + TABELA_MENSAGEM + ", " + valores + ", " + strSelection + ", " + Objects.toString(id) + ")");
            linhasAtualizadas = mDatabase.update(TABELA_MENSAGEM, //Nome da tabela
                    valores, //Valores a atualizar
                    strSelection, //WHERE
                    strSelectionArgs); // argumentos do WHERE
        } catch (Exception e) {
            Log.i(LOG_TAG, "Registrou a seguinte exceção:");
            Log.i(LOG_TAG, e.getMessage());
        }
        Log.i(LOG_TAG, "Qtde. de registros atualizados: " + linhasAtualizadas);
        return linhasAtualizadas;
    }

    public Cursor obtemListaMsgsFavoritas() {
        String strSelection = COLUNA_FAVORITO + " = 1";
        String[] strProjection = {COLUNA_ID, COLUNA_TITULO};

        try {
            Log.i(LOG_TAG, "Query Mensagens Favoritas iniciou");
            mCursor = mDatabase.query(TABELA_MENSAGEM, //Nome da tabela
                    strProjection, // Retorna o ID e titulo da mensagem
                    strSelection, // Critérios de pesquisa WHERE
                    null, // argumentos do WHERE
                    null, // Group By
                    null, // Having
                    null, // Order By
                    null); // Limit
        } catch (Exception e) {
            Log.i(LOG_TAG, "Registrou a seguinte exceção:");
            Log.i(LOG_TAG, e.getMessage());
        } finally {
            Log.i(LOG_TAG, "Query Mensagens Favoritas terminou");
>>>>>>> versao2
            return mCursor;
        }
    }

    /**
<<<<<<< HEAD
     * Consulta aleatória na tabela. Returna 1 resultado.
     *
     * @return Um cursor com o resultado
     */
    public Cursor getMensagemAleatoria() {

        String strOrderBy = "RANDOM()";
        String strLimit = "1";

=======
     * @return Um cursor com o resultado aleatorio
     */
    public Cursor getMsgAleatoria() {
        String strOrderBy = "RANDOM()";
        String strLimit = "1";
>>>>>>> versao2
        try {
            Log.i(LOG_TAG, "Query Mensagem Aleatoria iniciou");
            mCursor = mDatabase.query(TABELA_MENSAGEM, //Nome da tabela
                    PROJECTION_TODAS_COLUNAS, // campos para pesquisa. NULL = *
<<<<<<< HEAD
                    null, // Criterios de pesquisa WHERE
=======
                    null, // Critérios de pesquisa WHERE
>>>>>>> versao2
                    null, // argumentos do WHERE
                    null, // Group By
                    null, // Having
                    strOrderBy, // Order By
                    strLimit); // Limit
        } catch (Exception e) {
<<<<<<< HEAD
=======
            Log.i(LOG_TAG, "Registrou a seguinte exceção:");
>>>>>>> versao2
            Log.i(LOG_TAG, e.getMessage());
        } finally {
            Log.i(LOG_TAG, "Query Mensagem Aleatoria terminou");
            return mCursor;
        }
    }

    /**
<<<<<<< HEAD
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        String mPath;
        SQLiteDatabase checkDB = null;
        try {
            mPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);
            if (checkDB != null) {
                Log.i(LOG_TAG, "Banco de dados EXISTE: " + mPath);
            }
        } catch (SQLiteException e) {
            Log.i(LOG_TAG, "Banco de dados NÃO existe. " + e.getMessage());
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copia Banco de Dados da pasta assets/databases
     */
=======
     * @return Um cursor com uma mensagem cujo ID foi informado
     * como parametro
     */
    public Cursor obtemMsgFavorita(String intId) {
        // Qual mensagem a buscar (cláusula WHERE)
        String strSelection = COLUNA_ID + " = ?";
        String[] strSelectionArgs = {Objects.toString(intId)};
        try {
            Log.i(LOG_TAG, "Query Mensagem Favorita buscando mensagem com ID: " + intId);
            mCursor = mDatabase.query(TABELA_MENSAGEM, //Nome da tabela
                    PROJECTION_TODAS_COLUNAS, // campos para pesquisa. NULL = *
                    strSelection, // Critérios de pesquisa WHERE
                    strSelectionArgs, // argumentos do WHERE
                    null, // Group By
                    null, // Having
                    null, // Order By
                    null); // Limit
        } catch (Exception e) {
            Log.i(LOG_TAG, "Registrou a seguinte exceção:");
            Log.i(LOG_TAG, e.getMessage());
        } finally {
            Log.i(LOG_TAG, "Query Obter Mensagem Favorita terminou");
            return mCursor;
        }
    }

/*
     // Copia Banco de Dados da pasta assets/databases
>>>>>>> versao2
    public void copiaDatabase(Context context) {
        try {
            InputStream mInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream mOutput = new FileOutputStream(outFileName);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0) {
                mOutput.write(mBuffer, 0, mLength);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
<<<<<<< HEAD
            Log.i(LOG_TAG, "Cópia do arquivo concluída");
=======
            Log.i(LOG_TAG, "Copia do arquivo concluida");
>>>>>>> versao2
        } catch (IOException e) {
            Log.i(LOG_TAG, "Erro copiando o arquivo de banco de dados: " + e.getCause());
            throw new Error("Ocorreu um erro copiando o arquivo de banco de dados: " + e.getMessage());
        }
    }
<<<<<<< HEAD
=======
*/
>>>>>>> versao2

}
