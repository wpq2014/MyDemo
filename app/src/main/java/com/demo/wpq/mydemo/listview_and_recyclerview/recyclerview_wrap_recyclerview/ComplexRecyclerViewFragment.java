package com.demo.wpq.mydemo.listview_and_recyclerview.recyclerview_wrap_recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wpq.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wpq
 * @version 1.0
 */
public class ComplexRecyclerViewFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private List<List<String>> mList = new ArrayList<>();
    private ComplexRecyclerViewAdapter mAdapter;

    public static ComplexRecyclerViewFragment newInstance() {
        Bundle bundle = new Bundle();
        ComplexRecyclerViewFragment fragment = new ComplexRecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complex_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int size = 0;
        for (int i = 0; i < 15; i++) {
            List<String> list = new ArrayList<>();
            for (int j = size; j < size + 6; j++) {
                list.add("item" + j);
            }
            size += list.size();
            mList.add(list);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new ComplexRecyclerViewAdapter(getActivity(), mList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
