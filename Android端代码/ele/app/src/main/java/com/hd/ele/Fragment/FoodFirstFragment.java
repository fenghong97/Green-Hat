package com.hd.ele.Fragment;

import android.os.Bundle;

import com.hd.ele.Adapter.FoodAdapter;
import com.hd.ele.Adapter.TypeAdapter;
import com.hd.ele.FoodListActivity;
import com.hd.ele.R;
import com.hd.ele.View.ListContainer;
import com.shizhefei.fragment.LazyFragment;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class FoodFirstFragment extends LazyFragment {

    private ListContainer listContainer;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_food_first);
        listContainer = (ListContainer) findViewById(R.id.listcontainer);
        listContainer.setAddClick((FoodListActivity) getActivity());
    }

    public FoodAdapter getFoodAdapter() {
        return listContainer.foodAdapter;
    }

    public TypeAdapter getTypeAdapter() {
        return listContainer.typeAdapter;
    }

}
