package com.demo.wpq.mydemo.listview_and_recyclerview.linked_recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/1/18 11:35
 */
public class LinkedRVRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LinkedRVRightBaseBean> data;

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_header;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            tv_header = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView sdv_icon;
        private TextView tv_content;

        public ContentViewHolder(View itemView) {
            super(itemView);

            sdv_icon = (SimpleDraweeView) itemView.findViewById(R.id.sdv_icon);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public LinkedRVRightAdapter(List<LinkedRVRightBaseBean> data) {
        this.data = data;
    }

    public List<LinkedRVRightBaseBean> getData() {
        return data == null ? new ArrayList<LinkedRVRightBaseBean>() : data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LinkedRVRightBaseBean.TYPE_HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linked_recyclerview_right_header, parent, false));
            case LinkedRVRightBaseBean.TYPE_CONTENT:
                return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linked_recyclerview_right_content, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LinkedRVRightBaseBean rightBean = data.get(position);
        switch (getItemViewType(position)) {
            case LinkedRVRightBaseBean.TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                LinkedRVRightHeaderBean headerBean = (LinkedRVRightHeaderBean) rightBean;
                headerViewHolder.tv_header.setText(headerBean.header);
                break;
            case LinkedRVRightBaseBean.TYPE_CONTENT:
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                LinkedRVRightContentBean contentBean = (LinkedRVRightContentBean) rightBean;
                contentViewHolder.sdv_icon.setImageURI("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=0cdaf1de26738bd4c421b53799b0e0eb/3b292df5e0fe99256a5fe7533ca85edf8db17166.jpg");
                contentViewHolder.tv_content.setText(contentBean.content);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null && !data.isEmpty()) {
            return data.get(position).type;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == LinkedRVRightHeaderBean.TYPE_HEADER ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    public String getHeaderForPosition(int position) {
        if (data == null) {
            return "";
        }

        switch (getItemViewType(position)) {
            case LinkedRVRightBaseBean.TYPE_HEADER:
                return ((LinkedRVRightHeaderBean) data.get(position)).header;
            case LinkedRVRightBaseBean.TYPE_CONTENT:
                return ((LinkedRVRightContentBean) data.get(position)).headerForChild;
        }

        return "";
    }

    public int getPositionForHeader(String header) {
        if (data == null || header == null) {
            return 0;
        }

        for (int i = 0; i < data.size(); i++) {
            LinkedRVRightBaseBean baseBean = data.get(i);
            if (baseBean instanceof LinkedRVRightHeaderBean) {
                LinkedRVRightHeaderBean headerBean = (LinkedRVRightHeaderBean) baseBean;
                if (headerBean.header.equals(header)) {
                    return i;
                }
            }
        }

        return 0;
    }
}
