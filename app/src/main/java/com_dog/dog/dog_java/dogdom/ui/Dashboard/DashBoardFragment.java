package com_dog.dog.dog_java.dogdom.ui.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import com_dog.dog.dog_java.dogdom.Modules.ItemHouse;
import com_dog.dog.dog_java.dogdom.R;

public class DashBoardFragment extends Fragment {

    private RecyclerView rvMain;
    //ArrayList for items months in recyclerView
    ArrayList<ItemHouse> arrayHouses;
    //Adapter for Months RecyclerView
    private AdapterRvMain adapterRvHouse;
    //searcher editText
    private EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        rvMain = view.findViewById(R.id.recyclerViewMain);
        searchEditText = view.findViewById(R.id.search_edit_text);
        buildRecyclerViewLegend();
        setDataHouses();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        return view;
    }

    private void buildRecyclerViewLegend() {
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void filter(String text) {
        ArrayList<ItemHouse> filteredList = new ArrayList<>();

        for (ItemHouse item : arrayHouses) {
            if (item.getStreet().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapterRvHouse.filterList(filteredList);
    }

    private void setDataHouses(){
        arrayHouses = new ArrayList<>();
        arrayHouses.add(new ItemHouse(R.drawable.img_exp_house, "Дом 1", "ул. Гагарина"));
        arrayHouses.add(new ItemHouse(R.drawable.img_exp_house, "Дом 2", "ул. Пушкина"));
        arrayHouses.add(new ItemHouse(R.drawable.img_exp_house, "Дом 3", "ул. Темина"));
        arrayHouses.add(new ItemHouse(R.drawable.img_exp_house, "Дом 4", "ул. Димина"));
        arrayHouses.add(new ItemHouse(R.drawable.img_exp_house, "Дом 4", "ул. Данила"));
        adapterRvHouse = new AdapterRvMain(arrayHouses, getContext());
        rvMain.setAdapter(adapterRvHouse);
    }

}