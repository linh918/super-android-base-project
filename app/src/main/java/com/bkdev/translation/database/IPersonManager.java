package com.bkdev.translation.database;

import com.bkdev.translation.model.person.Person;

import java.util.List;

/**
 * Created by VIT3-SV1 on 7/31/2017.
 */

public interface IPersonManager {
    int createPerson(Person person);

    List<Person> getPersons();

    int deletePerson(String name);
}
