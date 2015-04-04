package com.example.markuss.asignaturas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Button btnLimpiar;
    private Button btnAñadir;
    private EditText asigText;
    private EditText profText;
    private TimePicker iniTime;
    private TimePicker finTime;
    private CheckBox checkLun;
    private CheckBox checkMar;
    private CheckBox checkMier;
    private CheckBox checkJue;
    private CheckBox checkVie;
    private CheckBox checkSab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asigText = (EditText) findViewById(R.id.asigText);
        profText = (EditText)findViewById(R.id.profText);
        btnAñadir = (Button) findViewById(R.id.btnAñadir);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        iniTime = (TimePicker)findViewById(R.id.iniTime);
        finTime = (TimePicker)findViewById(R.id.finTime);
        checkLun= (CheckBox)findViewById(R.id.checkLun);
        checkMar= (CheckBox)findViewById(R.id.checkMar);
        checkMier= (CheckBox)findViewById(R.id.checkMier);
        checkJue= (CheckBox)findViewById(R.id.checkJue);
        checkVie= (CheckBox)findViewById(R.id.checkVie);
        checkSab= (CheckBox)findViewById(R.id.checkSab);


        iniTime.setIs24HourView(true);
        finTime.setIs24HourView(true);



        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctrlAdd();
            }
        });


        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctrlClear();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean nameValidate(String asig, String prof) {
        String error1 = "-El nombre de la asignatura (" + asig + ") no es valido.\n\t";
        String error2 = "-El nombre del profesor (" + prof + ") no es valido.\n";
        String error = "";
        int con = 0;

        if (asig.length() == 0) {
            error += error1;
        } else {
            while (con < asig.length()) {

                int code = Character.valueOf(asig.charAt(con));
                con++;

                if ((code < 65 || code > 90) && (code < 97 || code > 122) && (code != 32)) {

                    error += error1;
                    con = asig.length();

                }
            }
        }

        con=0;

        if (prof.length() == 0) {
            error += error2;
        } else {
            while (con < prof.length()) {

                int code = Character.valueOf(prof.charAt(con));
                con++;

                if ((code < 65 || code > 90) && (code < 97 || code > 122) && (code != 32)) {

                    error += error2;
                    con = prof.length();

                }
            }
        }

        if (error.length() == 0) {
            return true;
        } else {
            String out = "Error al procesar los nombres. \n\nDetalles:\n\t" + error;
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            out, Toast.LENGTH_LONG);
            toast1.show();

            return false;
        }

    }

    public void ctrlAdd() {

        Toast toast;
        String asig = asigText.getText().toString();
        String prof = profText.getText().toString();
        String fin, ini;
        int iniHour = iniTime.getCurrentHour();
        int iniMin=iniTime.getCurrentMinute();
         ini= padTime(iniHour,iniMin);


        int finHour = finTime.getCurrentHour();
        int finMin = finTime.getCurrentMinute();
        fin=padTime(finHour,finMin);


        String dias="\nDias:\n";
        boolean valName = nameValidate(asig, prof);
        boolean valHour = hourValidate(iniHour,iniMin,finHour,finMin);
        boolean valDay = dayValidate();

        if (valName && valHour && valDay) {
            if(checkLun.isChecked())
                dias+="\tLunes\n";
            if(checkMar.isChecked())
                dias+="\tMartes\n";
            if(checkMier.isChecked())
                dias+="\tMiercoles\n";
            if(checkJue.isChecked())
                dias+="\tJueves\n";
            if(checkVie.isChecked())
                dias+="\tViernes\n";
            if(checkSab.isChecked())
                dias+="\tSabado\n";
            String out = "Asignatura: " + asig + "\nProfesor: "+ prof + "\nHora: " + ini + "-" + fin + dias;

            ctrlClear();
            toast =
                    Toast.makeText(getApplicationContext(),
                            "Asignatura agregada!!!." + out, Toast.LENGTH_SHORT);





        }
        else{
            toast =
                    Toast.makeText(getApplicationContext(),
                            "Arregle los datos.", Toast.LENGTH_SHORT);
        }
        toast.show();


    }



    public void ctrlClear(){
        asigText.setText("");
        profText.setText("");
        checkLun.setChecked(false);
        checkMar.setChecked(false);
        checkMier.setChecked(false);
        checkJue.setChecked(false);
        checkVie.setChecked(false);
        checkSab.setChecked(false);

    }


    public boolean hourValidate(int ih, int im, int fh, int fm){
        int nIni = (ih*100)+im;
        int nFin = (fh*100)+fm;

        if(nIni>=nFin){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "El horario propuesto no es valido.\n La hora de fin tiene que ser despues de la de inicio", Toast.LENGTH_LONG);
            toast1.show();
            return false;
        }
        else{
            return true;
        }


    }

    public int toNumber(String text){

        int num=0;

        num=Integer.parseInt(text.substring(0,2));
        num*=100;
        num+= Integer.parseInt(text.substring(3,5));

        return num;
    }

    public boolean dayValidate(){
        boolean lun = checkLun.isChecked();
        boolean mar = checkMar.isChecked();
        boolean mier = checkMier.isChecked();
        boolean jue = checkJue.isChecked();
        boolean vie = checkVie.isChecked();
        boolean sab = checkSab.isChecked();
        if(!lun&&!mar&&!mier&&!jue&&!vie&&!sab){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Al menos un dia debe ser seleccionado.", Toast.LENGTH_LONG);
            toast1.show();
            return false;
        }
        else return true;
    }


    public void saveData(){
        //Todo
        /*Aqui es donde tienes que llamar el metodo insert de la base de datos que hizo Wesley
        donde esta la funcion add comentada.

        Cuando hagas esto me avisas para validar lo de que si ya existe un choque.
         */
    }

    public String padTime(int hour, int min){
        String h="";
        String m="";
        if(min<9){
            m=":0"+min;
        }
        else
            m=":"+min;


        if(hour<9){
            h="0"+hour+m;
        }
        else
            h=hour+m;

        return h;
    }
}
