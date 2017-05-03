package com.tata.android.adapter.base.expand;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:通用的组数据Model,配合 {@link CommonExpandableListAdapter}
 *
 * Author: Terry
 *
 * Date:2016-03-31
 */
public class GenericGroupData<T> implements Serializable {

    protected List<T> childData;

    public List<T> getChildData() {
        return childData;
    }

    public void setChildData(List<T> mChildData) {
        this.childData = mChildData;
    }
}
