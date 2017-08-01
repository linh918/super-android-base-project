package com.bkdev.translation.database;

import com.bkdev.translation.model.person.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by VIT3-SV1 on 7/31/2017.
 */

public class PersonManager extends  RealmHelper implements IPersonManager {



    @Override
    public int createPerson(Person person) {
        int i=0;
        realm.beginTransaction();
        if(realm.where(Person.class).equalTo("name",person.getName()).findFirst()!=null){
            realm.commitTransaction();
            return i;
        }
        realm.copyToRealmOrUpdate(person);
        i=1;
        realm.commitTransaction();
        return  i;

    }

    public int create(Person person){
        int i=0;
        realm.beginTransaction();
        Person realmObj = realm.createObject(Person.class,"name");
        realmObj.setName(person.getName());
        realmObj.setClassName(person.getClassName());
        i=1;
        realm.commitTransaction();
        return  i;

    }


    @Override
    public List<Person> getPersons() {
        RealmResults<Person> realmResults = realm.where(Person.class).findAll().sort("name");
        List<Person> persons= new ArrayList<>();
        for (Person person: realmResults) {
            persons.add(person);
        }
        return persons;
    }

    @Override
    public int  deletePerson(String name) {
        int i=0;
        realm.beginTransaction();
        Person person = realm.where(Person.class).equalTo("name",name).findFirst();
        person.deleteFromRealm();
        i=1;
        realm.commitTransaction();
        return i;

    }
}
