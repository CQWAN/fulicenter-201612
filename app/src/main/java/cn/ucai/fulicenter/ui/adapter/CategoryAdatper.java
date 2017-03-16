package cn.ucai.fulicenter.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.utils.ImageLoader;

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
        CategoryGroupBean group = getGroup(groupPosition);
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_category_group, null);
            groupHolder = new GroupHolder();
            groupHolder.ivCategoryGroup = (ImageView) convertView.findViewById(R.id.ivGroupImage);
            groupHolder.tvCategoryGroup = (TextView) convertView.findViewById(R.id.tvGroupText);
            groupHolder.ivIsExpand = (ImageView) convertView.findViewById(R.id.ivIsExpand);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        ImageLoader.downloadImg(context,groupHolder.ivCategoryGroup,group.getImageUrl());
        groupHolder.tvCategoryGroup.setText(group.getName());
        if (isExpanded) {
            groupHolder.ivIsExpand.setImageResource(R.mipmap.expand_off);
        } else {
            groupHolder.ivIsExpand.setImageResource(R.mipmap.expand_on);
        }
        return convertView;
    }
    class GroupHolder{
        ImageView ivCategoryGroup;
        TextView tvCategoryGroup;
        ImageView ivIsExpand;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CategoryChildBean child = categoryChildList.get(groupPosition).get(childPosition);
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_category_child, null);
            childHolder = new ChildHolder();
            childHolder.ivCategoryChild = (ImageView) convertView.findViewById(R.id.ivChildImage);
            childHolder.tvCategoryChild = (TextView) convertView.findViewById(R.id.tvChildText);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        ImageLoader.downloadImg(context,childHolder.ivCategoryChild,child.getImageUrl());
        childHolder.tvCategoryChild.setText(child.getName());
        return convertView;
    }
    class ChildHolder{
        ImageView ivCategoryChild;
        TextView tvCategoryChild;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
