package com.upn.ferniandita.mydietdiary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Stefanus on 26/05/2017.
 */

public class AboutFragment extends Fragment{

    TextView tv_tentangKami;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_about, container, false);

        tv_tentangKami = (TextView)mView.findViewById(R.id.tv_tentangKami);

        String tentangKami = "Aplikasi ini dibuat oleh: \nTheresia Liana (123150006) \nFerniandita NM (123150007) \nMarella PM (123150019) \nThareq Aziz (123150113)";
        tv_tentangKami.setText(tentangKami);
        return mView;
    }
}
