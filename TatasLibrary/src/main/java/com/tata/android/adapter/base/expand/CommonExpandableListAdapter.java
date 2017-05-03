package com.tata.android.adapter.base.expand;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.tata.android.adapter.base.ViewHolder;

import java.util.List;

/**
 * Desc: an Enhanced ExpandableListAdapter
 * Author: Terry
 * Date: 16/3/31 下午8:14
 */
public abstract class CommonExpandableListAdapter<T extends GenericGroupData, V> extends BaseExpandableListAdapter {

    protected LayoutInflater mInflater;

    protected Context mContext;

    protected List<T> mData;

    protected Resources mResources = null;

    public CommonExpandableListAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * Return group view ID for an XML layout resource to load (e.g., R.layout.group_page)
     */
    protected abstract int getGroupLayoutId();

    /**
     * @param holder
     * @param groupData
     * @param isExpanded
     * @param position
     */
    protected abstract void convertGroupView(ViewHolder holder, T groupData, boolean isExpanded, int position);

    /**
     * Return child view ID for an XML layout resource to load (e.g., R.layout.child_page)
     */
    protected abstract int getChildLayoutId();

    /**
     * @param viewHolder
     * @param childData
     * @param position
     */
    protected abstract void convertChildView(ViewHolder viewHolder, V childData, int position);

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, convertView, parent, getGroupLayoutId(), groupPosition);
        convertGroupView(viewHolder, getGroup(groupPosition), isExpanded,groupPosition);
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, convertView, parent, getChildLayoutId(), groupPosition);
        convertChildView(viewHolder, getChild(groupPosition, childPosition), childPosition);
        return viewHolder.getConvertView();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public V getChild(int groupPosition, int childPosition) {
        return (V) mData.get(groupPosition).getChildData().get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).childData == null ? 0 : mData.get(groupPosition).childData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public T getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    public Resources getResources() {
        if (mResources == null) {
            mResources = mContext.getResources();
        }
        return mResources;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
}
