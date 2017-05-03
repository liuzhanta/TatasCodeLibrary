package com.tata.android.adapter.base.inject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * 
 * 名称：AbstractBaseAdapter
 * 
 * 描述：@param <D>
 * 
 * 修改时间：2014-1-20 下午4:02:24
 * 
 * @author lzt
 * 
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter {
    
    private Context context;
    public LayoutInflater inflater;
    public List<T> mData;
    
    public AbstractBaseAdapter(Context context, List<T> mData2) {
        
        this.context = context;
        this.mData = mData2;
        inflater = LayoutInflater.from(context);
    }
    
    public void setListData(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    
    public List<T> getData() {
        return mData;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData == null ? 0 : mData.size();
    }
    
    @Override
    public T getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = null;
        int type = getItemViewType(position);
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, onGenerateLayoutId(type), null);
            holder = getViewHolderInstance(type);
            // bind itemView by Reflection
            if (isBindViewByReflect) {
                ViewInjectorByReflect.injectView(holder, view);
            } else {
                onViewBinded(view, holder);
            }
            view.setTag(holder);
        } else {
            holder = (BaseViewHolder) view.getTag();
        }
        // bind item data
        onDataBinded(view, getItem(position), position, holder);
        return view;
    }
    
    public abstract void onViewBinded(View view, BaseViewHolder holder);
    
    private boolean isBindViewByReflect = false;
    
    public boolean isBindViewByReflect() {
        return isBindViewByReflect;
    }
    
    public void setBindViewByReflect(boolean isBindViewByReflect) {
        this.isBindViewByReflect = isBindViewByReflect;
    }
    
    /**
     * 得到Item的布局
     * 
     * @param itemViewType
     * @return
     * @modifiedTime 下午4:07:10
     * @author lzt
     */
    public abstract int onGenerateLayoutId(int itemViewType);
    
    /**
     * 得到Item的视图容器
     * 
     * @param itemViewType
     * @return
     * @modifiedTime 下午4:08:18
     * @author lzt
     */
    public abstract BaseViewHolder getViewHolderInstance(int itemViewType);
    
    /**
     * 绑定数据
     * 
     * @param view TODO
     * @param data 每一行的对象数据
     * @param position 位置
     * @param holder TODO
     * @modifiedTime 下午4:10:08
     * @author lzt
     */
    public abstract void onDataBinded(View view, T data, int position, BaseViewHolder holder);
    
    public Context getContext() {
        return context;
    }
}
