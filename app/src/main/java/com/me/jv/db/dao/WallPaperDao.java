package com.me.jv.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.me.jv.db.beans.WallPaper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * 壁纸数据操作
 * @author llw
 */
@Dao
public interface WallPaperDao {

    @Query("SELECT * FROM wallpaper")
    Flowable<List<WallPaper>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<WallPaper> wallPapers);

    @Query("DELETE FROM wallpaper")
    Completable deleteAll();
}