package com.appsino.bingluo.databingtest.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Answer on 2018/7/11.
 */
@Table(database = AppDatabase.class)
public class UserData extends BaseModel{
    @PrimaryKey(autoincrement = true)
    public long id;
    /**
     * 姓名
     */
    @Column
    public String name;
    @Column
    public String sex;


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
