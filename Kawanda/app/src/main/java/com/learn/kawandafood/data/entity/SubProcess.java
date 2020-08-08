package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SubProcess {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name ="process_id")
    public int process_id;

    @ColumnInfo(name ="name")
    public  String name;

    @ColumnInfo(name ="checked")
    public  boolean checked;

    public SubProcess() {
    }

    public SubProcess(int id, int process_id, String name, boolean checked) {
        this.id = id;
        this.process_id = process_id;
        this.name = name;
        this.checked = checked;
    }
}
