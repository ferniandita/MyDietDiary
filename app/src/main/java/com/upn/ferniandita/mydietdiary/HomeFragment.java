package com.upn.ferniandita.mydietdiary;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class HomeFragment extends Fragment{
    TextView tv_welcome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_home, container, false);

        tv_welcome = (TextView)mView.findViewById(R.id.tv_welcome);

        String welcome = "Aplikasi android My Diet Diary hadir untuk memudahkan manusia dalam " +
                        "mengetahui informasi mengenai berat badan ideal dan informasi diet " +
                        "yang bisa diakses dimanapun, kapanpun, dan dalam kondisi apapun.";
        tv_welcome.setText(welcome);
        return mView;
    }
}
