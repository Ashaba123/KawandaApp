package com.learn.kawandafood.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.learn.kawandafood.data.entity.SubProcess;

import java.util.List;

public class ProcessWithSubProcesses {
    @Embedded
    public Process process;
    @Relation(
            parentColumn = "id",
            entityColumn = "process_id"
    )
    public List<SubProcess> subProcesses;
}
