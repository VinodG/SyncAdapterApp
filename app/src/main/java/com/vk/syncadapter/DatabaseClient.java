package com.vk.syncadapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.vk.syncadapter.ArticleContract.DB_NAME;
import static com.vk.syncadapter.ArticleContract.DB_VERSION;

public class DatabaseClient extends SQLiteOpenHelper {
    private static volatile DatabaseClient instance;
    private final SQLiteDatabase db;


    private DatabaseClient(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    /**
     * We use a Singleton to prevent leaking the SQLiteDatabase or Context.
     * @return {@link DatabaseClient}
     */
    public static DatabaseClient getInstance(Context c) {
        if (instance == null) {
            synchronized (DatabaseClient.class) {
                if (instance == null) {
                    instance = new DatabaseClient(c);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create any SQLite tables here
        createArticlesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Update any SQLite tables here
        db.execSQL("DROP TABLE IF EXISTS [" + ArticleContract.Articles.NAME + "];");
        onCreate(db);
    }

    /**
     * Provide access to our database.
     */
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * Creates our 'articles' SQLite database table.
     * @param db {@link SQLiteDatabase}
     */
    private void createArticlesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE [" + ArticleContract.Articles.NAME + "] ([" +
                ArticleContract.Articles.COL_ID + "] TEXT UNIQUE PRIMARY KEY,[" +
                ArticleContract.Articles.COL_TITLE + "] TEXT NOT NULL,[" +
                ArticleContract.Articles.COL_CONTENT + "] TEXT,[" +
                ArticleContract.Articles.COL_LINK + "] TEXT);");
    }
}
