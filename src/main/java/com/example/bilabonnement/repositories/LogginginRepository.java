package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.User;
import java.util.ArrayList;
import java.util.List;

public class LogginginRepository
{
    private List<User> users = new ArrayList<>();

    public int loggedin(User user)
    {

        return 1;
        /*
        users.add(new User(1, "Hino", "1234"));
        users.add(new User(2, "svend", "asdf"));
        for (User i : users)
        {
            if (user.getUsername().equals(i.getUsername()) && user.getPassword().equals(i.getPassword()))
            {
                return i.getId();
            }
        }
        return -1;

         */

    }



}


