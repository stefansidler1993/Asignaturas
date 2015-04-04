package com.example.markuss.asignaturas;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by markuss on 3/4/15.
 */
public class ver_materias extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lay_vermaterias, container, false);

        TextView tv = (TextView) rootView.findViewById(R.id.verMateriaTV);
        tv.setText("Ver Materias");
        return rootView;

    }



}
