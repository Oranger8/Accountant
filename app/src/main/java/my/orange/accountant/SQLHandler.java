package my.orange.accountant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLHandler extends SQLiteOpenHelper {

    private static String DB_PATH;
    private static String DB_NAME = "data.db";
    private SQLiteDatabase db;
    private final Context mContext;

    public SQLHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        DB_PATH = context.getDatabasePath(DB_NAME).toString();
    }

    public void createDatabase() {
        boolean dbExists = checkDataBase();
        if (!dbExists) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Can't copy database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {}
        if (checkDB != null) checkDB.close();
        return checkDB != null;
    }

    public void copyDatabase() throws IOException {
        InputStream in = mContext.getAssets().open(DB_NAME);
        OutputStream out = new FileOutputStream(DB_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
        out.flush();
        out.close();
        in.close();
    }

    public void openDatabase(int flag) {
        db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public synchronized void close() {
        if (db != null) db.close();
        super.close();
    }

    protected String[] getTargets() {
        return null;
    }
}
