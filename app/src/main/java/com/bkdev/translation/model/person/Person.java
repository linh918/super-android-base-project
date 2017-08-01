package com.bkdev.translation.model.person;

import android.support.annotation.CallSuper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by VIT3-SV1 on 7/31/2017.
 */
@Data

@EqualsAndHashCode(callSuper = false)
public class Person extends RealmObject  {
    @PrimaryKey
    private String name;

    private String className;

}
