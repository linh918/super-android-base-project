package com.bkdev.translation.database;

import io.realm.Realm;

/**
 * Created by VIT3-SV1 on 7/31/2017.
 */

abstract class RealmHelper  {
    protected Realm realm;
    RealmHelper(){
        realm=Realm.getDefaultInstance();
    }
}
