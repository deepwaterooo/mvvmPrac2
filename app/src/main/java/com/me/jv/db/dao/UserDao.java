package com.me.jv.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.me.jv.db.beans.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * 用户数据操作
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();

    @Update
    Completable update(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(User user);

    @Query("DELETE FROM user")
    Completable deleteAll();
}
