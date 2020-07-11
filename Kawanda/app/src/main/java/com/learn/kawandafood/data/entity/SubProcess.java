package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SubProcess {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name ="process_id")
    int process_id;

    @ColumnInfo(name ="name")
    String name;
}
