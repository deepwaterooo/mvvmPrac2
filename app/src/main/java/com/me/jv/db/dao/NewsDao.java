package com.me.jv.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.me.jv.db.beans.News;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    Flowable<List<News>> getAll();

    // 由于读取速率可能 远大于 观察者处理速率，故使用背压 Flowable 模式，这是为了防止表中数据过多，读取速率远大于接收数据，从而导致内存溢出的问题，
    // Completable就是操作完成的回调，可以感知操作成功或失败， onComplete和onError。
    // @Query("SELECT * FROM digitalcoin WHERE id LIKE :id LIMIT 1") // like ?
    // // Image queryById(String id);
    // Flowable<News> queryById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // void insertAll(News... digitalCoins);
    Completable insertAll(List<News> news);

    // @Delete
    // void delete(News digitalCoin);
    @Query("DELETE FROM news")
    Completable deleteAll();
}
