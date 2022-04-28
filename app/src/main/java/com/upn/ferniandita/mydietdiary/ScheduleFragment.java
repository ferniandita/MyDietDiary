package com.upn.ferniandita.mydietdiary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class ScheduleFragment extends Fragment {

    Button btn_diet1, btn_diet2, btn_diet3, btn_diet4, btn_diet5;
    TextView tv_diet1, tv_diet2, tv_diet3, tv_diet4, tv_diet5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_schedule, container, false);

        final String diet1 = "Dalam diet ini, Anda diharuskan untuk mengurangi konsumsi garam atau sama sekali tidak makan garam. " +
                "Sebab garam dipercaya mampu mengikat cairan dan lemak tubuh sehingga dapat menyebabkan kegemukan. " +
                "Lalu Anda pun juga diharuskan untuk mengonsumsi semua makanan dengan cara direbus atau dikukus karena proses penggorengan membuat makanan kaya akan lemak.";
        final String diet2 = "Dalam diet Paleo Anda disarankan untuk memperbanyak mengonsumsi makanan laut segar, sayuran hijau, serta zaitun yang menjadi main point di dalamnya. " +
                "Hal ini disebabkan karena zaitun dinilai merupakan tumbuhan yang super sehat.";
        final String diet3 = "Diet ini menyarankan Anda untuk mengonsumsi makanan yang tinggi akan alkaline, suatu zat alami yang bermanfaat untuk kesehatan tubuh. " +
                "Sebab makanan tinggi alkaline akan membuat pH tubuh menjadi seimbang dan terkendali sehingga berbagai macam gangguan kesehatan termasuk kegemukan dapat Anda hindari. " +
                "Jika Anda bingung dengan makanan mana yang tinggi alkaline, pilihlah makanan alami yang tumbuh dari pohon atau tanah langsung. Serta olahlah dengan cara yang sehat.";
        final String diet4 = "Prinsip dari pola makan food combining adalah Anda tidak boleh mengonsumsi karbohidrat dan protein dalam waktu yang bersamaan. " +
                "Sebab kedua hal tersebut sulit untuk dicerna secara bersamaan. Dan jika tidak tercerna dengan baik, maka sisa makanan ini akan menumpuk dan menimbulkan penyakit. " +
                "Sebaliknya dalam food combining, Anda diperbolehkan untuk makan karbohidrat dengan sayuran atau sayuran dengan protein. " +
                "Dalam food combining Anda diperbolehkan untuk makan sebanyak yang Anda mau.";
        final String diet5 = "Dalam clean eating, poin utamanya adalah Anda wajib mengonsumsi makanan yang segar, organik, dan kaya nutrisi. " +
                "Hindari segala macam junk food dan makanan nol nutrisi yang ribet dalam proses memasaknya. Sebab proses memasak yang ribet akan menyebabkan vitamin di dalamnya berkurang.";

        btn_diet1 = (Button) mView.findViewById(R.id.btn_diet1);
        btn_diet2 = (Button) mView.findViewById(R.id.btn_diet2);
        btn_diet3 = (Button) mView.findViewById(R.id.btn_diet3);
        btn_diet4 = (Button) mView.findViewById(R.id.btn_diet4);
        btn_diet5 = (Button) mView.findViewById(R.id.btn_diet5);
        tv_diet1 = (TextView) mView.findViewById(R.id.tv_diet1);
        tv_diet2 = (TextView) mView.findViewById(R.id.tv_diet2);
        tv_diet3 = (TextView) mView.findViewById(R.id.tv_diet3);
        tv_diet4 = (TextView) mView.findViewById(R.id.tv_diet4);
        tv_diet5 = (TextView) mView.findViewById(R.id.tv_diet5);

        tv_diet1.setText("");
        tv_diet2.setText("");
        tv_diet3.setText("");
        tv_diet4.setText("");
        tv_diet5.setText("");

        btn_diet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_diet1.setText(diet1);
                tv_diet2.setText("");
                tv_diet3.setText("");
                tv_diet4.setText("");
                tv_diet5.setText("");
            }
        });
        btn_diet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_diet1.setText("");
                tv_diet2.setText(diet2);
                tv_diet3.setText("");
                tv_diet4.setText("");
                tv_diet5.setText("");
            }
        });
        btn_diet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_diet1.setText("");
                tv_diet2.setText("");
                tv_diet3.setText(diet3);
                tv_diet4.setText("");
                tv_diet5.setText("");
            }
        });
        btn_diet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_diet1.setText("");
                tv_diet2.setText("");
                tv_diet3.setText("");
                tv_diet4.setText(diet4);
                tv_diet5.setText("");
            }
        });
        btn_diet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_diet1.setText("");
                tv_diet2.setText("");
                tv_diet3.setText("");
                tv_diet4.setText("");
                tv_diet5.setText(diet5);
            }
        });
        return mView;
    }
}
