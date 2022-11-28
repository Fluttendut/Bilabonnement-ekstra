package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.User;

import java.util.ArrayList;
import java.util.List;

public class LogginginRepository
{
    private List<User> users = new ArrayList<>();

    public int loggedin(User user)
    {

        users.add(new User(1, "clbo", "1234"));
        users.add(new User(2, "benjamin", "1234"));
        for (User i : users)
        {
            if (user.getUsername().equals(i.getUsername()) && user.getPassword().equals(i.getPassword()))
            {
                return i.getId();
            }
        }
        return -1;

    }


}


