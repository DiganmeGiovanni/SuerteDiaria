package com.topicos.suertediaria;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class Suerte extends Activity
{
	int dia;
	int mes;
	int anio;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_birthday_suerte);
			
			// OBTENEMOS LOS VALORES ENVIADOS DESDE LA ACTIVIDAD ANTERIOR
			dia = getIntent().getExtras().getInt("dia");
			mes = getIntent().getExtras().getInt("mes");
			anio = getIntent().getExtras().getInt("anio");
			
			configurarTabs();
			
			// CALCULAMOS Y MOSTRAMOS SU SUERTE:
			TextView horoscopo = (TextView) findViewById(R.id.horoscopo);
			TextView pronostico= (TextView) findViewById(R.id.pronostico);
			TextView edad= (TextView) findViewById(R.id.edad);
			TextView cicloFisico = (TextView) findViewById(R.id.ciclo_fisico_valor);
			TextView cicloEmocional = (TextView) findViewById(R.id.ciclo_emocional_valor);
			TextView cicloIntelectual = (TextView) findViewById(R.id.ciclo_intelectual_valor);
			
			horoscopo.setText(calculaHoroscopo());
			pronostico.setText(pronostica());
			edad.setText(calculaEdad());
			cicloFisico.setText(cicloFisico(calcularDiasVividos()));
			cicloEmocional.setText(cicloEmocional(calcularDiasVividos()));
			cicloIntelectual.setText(cicloIntelectual(calcularDiasVividos()));
		}
		
		public String calculaHoroscopo()
		{
			
			String horoscopo = "Acuario";
			
			mes++;
			if((dia>=21 && mes == 3) || (dia<=20 && mes==4))
			{
				horoscopo = "Aries";
			}
			else if((dia>=21 && mes == 4) || (dia<=20 && mes==5))
			{
				horoscopo = "Tauro";
			}
			else if((dia>=21 && mes == 5) || (dia<=20 && mes==6))
			{
				horoscopo = "Geminis";
			}
			else if((dia>=21 && mes == 6) || (dia<=20 && mes==7))
			{
				horoscopo = "Cancer";
			}
			else if((dia>=21 && mes == 7) || (dia<=20 && mes==8))
			{
				horoscopo = "Leo";
			}
			else if((dia>=21 && mes == 8) || (dia<=20 && mes==9))
			{
				horoscopo = "Virgo";
			}
			else if((dia>=21 && mes == 9) || (dia<=20 && mes==10))
			{
				horoscopo = "Libra";
			}
			else if((dia>=21 && mes == 10) || (dia<=20 && mes==11))
			{
				horoscopo = "Escorpion";
			}
			else if((dia>=21 && mes == 11) || (dia<=20 && mes==12))
			{
				horoscopo = "Sagitario";
			}
			else if((dia>=21 && mes == 12) || (dia<=20 && mes==1))
			{
				horoscopo = "Capricornio";
			}
			else if((dia>=21 && mes == 1) || (dia<=20 && mes==2))
			{
				horoscopo = "Acuario";
			}
			else if((dia>=21 && mes == 2) || (dia<=20 && mes==3))
			{
				horoscopo = "Piscis";
			}
			mes--;
			return horoscopo;
		}
		
		/**
		 * Hace un pronostico sobre tu dia basado en tu horoscopo
		 * @param horoscopo
		 */
		private String pronostica()
		{
			Random rnd = new Random();
			int suerte = rnd.nextInt(5);
			
			switch(suerte)
			{
				case 0: return "Hoy es un buen dia para hacer negocios";
						
				case 1: return "Hoy sera un mal dia para ir a clases";
						
				case 2: return "Hoy te ganaras la loteria";
				
				case 3: return "Hoy recibiras malas noticias pero luego te ira bien";
				
				case 4: return "Hoy seras muy productivo";
				
				case 5: return "Hoy pasaras topicos de programacion";
				
				default: return "Tendras muy mala suerte hoy";
			}
			
			
		}
		
		private String calculaEdad()
		{
			int edad;
			if(mes > 5)
			{
				edad = (2013-anio)-1;
			}
			else
			{
				edad = (2013-anio);
			}	
			
			return edad + "";
		}
		
		private int calcularDiasVividos()
		{
			int anios = 2012-anio;
			int dias = (4*30) + (anios*365);
			
			return dias;
		}
		
		private String cicloFisico(int diasVividos)
		{
			return Math.sin((2*Math.PI*diasVividos)/23) + "";
		}
		
		private String cicloEmocional(int diasVividos)
		{
			
			
			return Math.sin((2*Math.PI*diasVividos)/28) + "";
		}
		
		private String cicloIntelectual(int diasVividos)
		{
			return Math.sin((2*Math.PI*diasVividos)/33) + "";
		}
		
		private void configurarTabs()
		{
			TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
			tabHost.setup();
			
			final TabWidget tabWidget = tabHost.getTabWidget();
			final FrameLayout tabContent = tabHost.getTabContentView();
			
			// Get the original tab textviews and remove them from the viewgroup.
			TextView[] originalTextViews = new TextView[tabWidget.getTabCount()];
			for (int index = 0; index < tabWidget.getTabCount(); index++) {
				originalTextViews[index] = (TextView) tabWidget.getChildTabViewAt(index);
			}
			tabWidget.removeAllViews();
			
			// Ensure that all tab content childs are not visible at startup.
			for (int index = 0; index < tabContent.getChildCount(); index++) {
				tabContent.getChildAt(index).setVisibility(View.GONE);
			}
			
			// Create the tabspec based on the textview childs in the xml file.
			// Or create simple tabspec instances in any other way...
			for (int index = 0; index < originalTextViews.length; index++) {
				final TextView tabWidgetTextView = originalTextViews[index];
				final View tabContentView = tabContent.getChildAt(index);
				TabSpec tabSpec = tabHost.newTabSpec((String) tabWidgetTextView.getTag());
				tabSpec.setContent(new TabContentFactory() {
					@Override
					public View createTabContent(String tag) {
						return tabContentView;
					}
				});
				if (tabWidgetTextView.getBackground() == null) {
					tabSpec.setIndicator(tabWidgetTextView.getText());
				} else {
					tabSpec.setIndicator(tabWidgetTextView.getText(), tabWidgetTextView.getBackground());
				}
				tabHost.addTab(tabSpec);
			}
		}
}
