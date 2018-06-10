package com.pos.lejapinh.apps.popularmovies.data;

import android.content.Context;
import android.os.AsyncTask;

import com.pos.lejapinh.apps.popularmovies.data.dao.DatabaseContext;
import com.pos.lejapinh.apps.popularmovies.data.dao.UserDao;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.User;

import java.util.List;

public class UserClient implements UserDao {

    private static UserClient userClient;
    private Context context;
    private DatabaseContext db;
    private List<User> list;
    private User user;

    public static UserClient getInstance(Context context) {
        userClient = new UserClient(context);

        return userClient;
    }

    private UserClient(Context context) {
        this.context = context;
        db = DatabaseClient.getInstance(context);
    }

    @Override
    public List<User> getAll() {
        Runnable getAll = new Runnable() {
            @Override
            public void run() {
                list = db.userDAO().getAll();
            }
        };

        AsyncTask.execute(getAll);

        return list;
    }

    @Override
    public List<User> loadAllByIds(final int[] userIds) {
        Runnable getAllByIds = new Runnable() {
            @Override
            public void run() {
                list = db.userDAO().loadAllByIds(userIds);
            }
        };

        AsyncTask.execute(getAllByIds);

        return list;
    }

    @Override
    public User findByEmail(final String email) {
        Runnable getAllByIds = new Runnable() {
            @Override
            public void run() {
                user = db.userDAO().findByEmail(email);
            }
        };

        AsyncTask.execute(getAllByIds);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void insertAll(final User... users) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.userDAO().insertAll(users);
            }
        };

        AsyncTask.execute(insert);
    }

    @Override
    public void delete(final User user) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.userDAO().delete(user);
            }
        };

        AsyncTask.execute(insert);
    }

    @Override
    public void insert(final User user) {
        Runnable insert = new Runnable() {
            @Override
            public void run() {
                db.userDAO().insert(user);
            }
        };

        AsyncTask.execute(insert);
    }
}
