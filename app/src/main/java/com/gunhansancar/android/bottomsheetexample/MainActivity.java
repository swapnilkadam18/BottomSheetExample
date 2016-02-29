package com.gunhansancar.android.bottomsheetexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gunhansancar.android.bottomsheetexample.adapter.SampleSheetAdapter;
import com.gunhansancar.android.bottomsheetexample.model.SampleModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior<View> behavior;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomSheet();
    }

    @OnClick(R.id.showButton)
    void onShowButtonClick() {
        //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        createDialog();
    }

    private boolean dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            return true;
        }

        return false;
    }

    private void createDialog() {
        if (dismissDialog()) {
            return;
        }

        List<SampleModel> list = new ArrayList<>();
        list.add(new SampleModel(R.string.share, R.mipmap.ic_launcher));
        list.add(new SampleModel(R.string.upload, R.mipmap.ic_launcher));
        list.add(new SampleModel(R.string.copy, R.mipmap.ic_launcher));
        list.add(new SampleModel(R.string.print, R.mipmap.ic_launcher));

        SampleSheetAdapter adapter = new SampleSheetAdapter(list);
        adapter.setOnItemClickListener(new SampleSheetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SampleSheetAdapter.ItemHolder item, int position) {
                //dismissDialog();
            }
        });

        View view = getLayoutInflater().inflate(R.layout.sheet_main, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }

    private void initBottomSheet() {
        View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });
    }
}
