package com.juhezi.bookshelf.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by qiaoyunrui on 16-8-16.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {

}
