package com.appsino.bingluo.databingtest.db;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by Answer on 2018/7/11.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    //数据库名称
    public static final String NAME = "AppDataBase";
    //数据库版本号
    public static final int VERSION = 2;
    @Migration(version = VERSION,database = AppDatabase.class)
    public static class Migration2UserData extends AlterTableMigration<UserData>{

        public Migration2UserData(Class<UserData> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            super.onPreMigrate();
            addColumn(SQLiteType.TEXT,"sex");
        }
    }
}
