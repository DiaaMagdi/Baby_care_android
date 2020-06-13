package com.example.babyinformation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babyinformation.fragments.PageFragmentOneSitter;
import com.example.babyinformation.fragments.PageFragmentTwoSitter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class PopUpBabySitter extends BottomSheetDialogFragment {

    private ViewPager pagerSitter;
    private PagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pop_up_baby_sitter , container , false);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmentOneSitter());
        list.add(new PageFragmentTwoSitter());

        pagerSitter = view.findViewById(R.id.pagerSitter);
        pagerAdapter = new SlidePagerAdapter(getChildFragmentManager(),list);
        pagerSitter.setAdapter(pagerAdapter);

        return view;
    }

}