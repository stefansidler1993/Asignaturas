package com.example.markuss.asignaturas;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class SplashScreen extends ActionBarActivity {
    public static final int segundos =5;
    public static final int milisegundos = segundos*1000;
    private ProgressBar pbprogreso;
    private static final int delay = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pbprogreso = (ProgressBar) findViewById(R.id.progressBar);
        pbprogreso.setMax(maximo_progreso());
        MostrarSplash();

    }

    public void MostrarSplash()
    {

        new CountDownTimer(milisegundos, 1000)
        {

            public void onTick(long milUntilFinished)
            {
                pbprogreso.setProgress(establecer_progreso(milUntilFinished));
            }

            public void onFinish()
            {
                Intent nuevofrom = new Intent(SplashScreen.this,Nav_Menu_Main.class);
                startActivity(nuevofrom);
                finish();
            }

        }.start();

    }

    public int establecer_progreso(long milisegundos)
    {
        return (int)((milisegundos-milisegundos)/1000);

    }

    public int maximo_progreso()
    {
        return segundos-delay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
}
