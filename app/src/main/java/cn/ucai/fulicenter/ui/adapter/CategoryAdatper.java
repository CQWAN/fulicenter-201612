package cn.ucai.fulicenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;

/**
 * Created by LPP on 2017/3/16.
 */

public class CategoryAdatper extends BaseExpandableListAdapter {
    Context context;
    List<CategoryGroupBean> categoryGroupList;
    List<List<CategoryChildBean>> categoryChildList;

    public CategoryAdatper(Context context, List<CategoryGroupBean> categoryGroupList, List<List<CategoryChildBean>> categoryChildList) {
        this.context = context;
        this.categoryGroupList = categoryGroupList;
        this.categoryChildList = categoryChildList;
    }

    @Override
    public int getGroupCount() {
        return categoryGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryChildList.get(groupPosition).size();
    }

    @Override
    public CategoryGroupBean getGroup(int groupPosition) {
        return categoryGroupList.get(groupPosition);
    }

    @Override
    public CategoryChildBean getChild(int groupPosition, int childPosition) {
        return categoryChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
