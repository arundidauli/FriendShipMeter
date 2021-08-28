package com.love2knot.friendshipmeter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.love2knot.friendshipmeter.R;
import com.love2knot.friendshipmeter.interfaces.MyItemClickListener;
import com.love2knot.friendshipmeter.model.Lang;
import com.love2knot.friendshipmeter.utils.Languages;
import com.love2knot.friendshipmeter.utils.Util;


import java.util.ArrayList;

/**
 * Created by Arun Kumar on 14/8/20.
 * Copyright (c) 2020 wingshieldtechnologies.com All rights reserved.
 */
public class BottomSheetSelect extends BottomSheetDialogFragment implements MyItemClickListener {

    private final TextView textView;

    LangAdapter langAdapter;


    public BottomSheetSelect(TextView textView) {
        this.textView = textView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_set_select, container, false);

        ListView listView = view.findViewById(R.id.std_list);

        langAdapter = new LangAdapter(requireActivity(), Languages.getLanguage(), this);

        listView.setAdapter(langAdapter);


        return view;



    }

    @Override
    public void clicked(String name, String id) {
        textView.setText(name);
        Util.getInstance(requireActivity()).SetValue("lang",id);
        dismiss();
    }


    static class LangAdapter extends ArrayAdapter<Lang> {

        private final MyItemClickListener itemClickListener;

        public LangAdapter(@NonNull Context context, ArrayList<Lang> arrayList, MyItemClickListener itemClickListener) {

            super(context, 0, arrayList);
            this.itemClickListener = itemClickListener;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View currentItemView = convertView;
            if (currentItemView == null) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
            }
            Lang currentNumberPosition = getItem(position);

            TextView txtName = currentItemView.findViewById(R.id.txtName);
            txtName.setText(currentNumberPosition.getLanName());
            txtName.setOnClickListener(v -> {
                itemClickListener.clicked(currentNumberPosition.getLanName(), currentNumberPosition.getLang());
            });

            return currentItemView;
        }


    }



}