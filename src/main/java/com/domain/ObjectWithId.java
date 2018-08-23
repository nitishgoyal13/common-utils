package com.domain;


import com.collections.CollectionUtils;

/**
 * User: Nitish Goyal
 * Date: 23/08/18
 * Time: 10:51 PM
 */
public interface ObjectWithId {

    CollectionUtils.Transformer<ObjectWithId, String> ID_TRANSFORMER = t -> (t == null) ? null : t.getId();

    String ID_FIELD = "_id";

    String getId();

    void setId(String id);
}