package com.android.shjman.testfromsquirrel;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.android.shjman.testfromsquirrel.models.State;
import com.android.shjman.testfromsquirrel.utils.DbOpenHelper;

public class App extends Application {
    private static final String VACUUM = "VACUUM";
    private static App self;
    private State state = new State();//TODO can make one method for create and don't  make heavier ! https://drive.google.com/drive/folders/18gQ5r7xuYPUELjZ2x8muuuDulOwNwJuS
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        dbOpenHelper = new DbOpenHelper(this);
        db = dbOpenHelper.getWritableDatabase();
        db.execSQL(VACUUM);
    }

    public static App getInstance() {
        return self;
    }

    public State getState() {
        return state;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
