package com.pos.lejapinh.apps.popularmovies.data;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.pos.lejapinh.apps.popularmovies.data.dao.DatabaseContext;


public class DatabaseClient extends Application {
    private static DatabaseContext databaseContext;
    public static DatabaseContext getInstance(Context context){
        if (databaseContext == null){
            databaseContext = Room.databaseBuilder(context,
                    DatabaseContext.class, "popular_movies_db")
                    .build();
        }

        return databaseContext;
    }
}
