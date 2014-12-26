package com.topicos.suertediaria;

import java.util.Random;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class SuerteDiaria extends Activity implements DatePickerDialog.OnDateSetListener
{
	private DatePickerDialog calendario;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suerte_diaria);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.suerte_diaria, menu);
		return true;
	}
	
	/**
	 * Este metodo se ejecuta al hacer click sobre
	 * el boton "Probar la suerte de tu fecha de nacimiento
	 * 
	 * @param view
	 */
	public void lanzarSuerteBirthday(View view)
	{
		calendario = new DatePickerDialog(view.getContext(), this, 1993, 31, 1);
		calendario.show();
	}
	
	/**
	 * Este metodo se ejecuta cuando se hace click sobre
	 * el boton: "Probar suerte con este numero"
	 * @param view
	 */
	public void probarNumero(View view)
	{
		EditText num = (EditText)findViewById(R.id.numSuerte);
		try
		{
			Integer.parseInt(num.getText()+"");
			Random rnd = new Random();
			if(rnd.nextBoolean())
			{
				Toast.makeText(view.getContext(), "Es de buena suerte", Toast.LENGTH_SHORT).show();
			} 
			else
			{
				Toast.makeText(view.getContext(), "Es de mala suerte", Toast.LENGTH_SHORT).show();
			}
		} 
		catch(Exception err)
		{
			Toast.makeText(view.getContext(), "Pon un numero valido", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
	{
		Intent i = new Intent(this, Suerte.class);
		
		i.putExtra("dia", dayOfMonth);
		i.putExtra("mes", monthOfYear);
		i.putExtra("anio", year);
		
		startActivity(i);
	}

}
