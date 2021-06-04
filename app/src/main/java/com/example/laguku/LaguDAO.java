package com.example.laguku;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LaguDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLagu(Lagu lagu);


    @Query("SELECT * FROM tLagu")
    Lagu[] readDataLagu();

    @Update
    int updateLagu(Lagu lagu);

    @Delete
    void hapusLagu(Lagu lagu);


}
