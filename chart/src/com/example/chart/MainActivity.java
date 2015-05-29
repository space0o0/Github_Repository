package com.example.chart;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends ActionBarActivity implements
		OnSeekBarChangeListener ,OnChartValueSelectedListener,OnChartGestureListener{
	private LineChart mChart;
	private TextView x,y;
	
	private Typeface tf;
	ArrayList<String> xVals;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		init();
		mChart.setOnChartGestureListener(this);
		mChart.setOnChartValueSelectedListener(this);
		initChart();
	}
	public void init(){
		mChart = (LineChart) findViewById(R.id.lineChart);
		x=(TextView) findViewById(R.id.textView1);
		y=(TextView) findViewById(R.id.textView2);
	}

	public void initChart() {
		mChart.setDescription("this is desctiption!");
		mChart.setHighlightEnabled(true);
		mChart.setTouchEnabled(true);
		mChart.setDragEnabled(true);
		mChart.setScaleEnabled(true);
		mChart.setDrawGridBackground(false);
		mChart.getAxisRight().setEnabled(false);
		mChart.setPinchZoom(true);
	
		tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
		XAxis x = mChart.getXAxis();
		x.setTypeface(tf);
		x.setEnabled(false);

		//X轴
		XAxis xAxis=mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(tf);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        //Y轴
        YAxis yAxis=mChart.getAxisLeft();
        yAxis.setTypeface(tf);
        yAxis.setLabelCount(6);
        yAxis.setStartAtZero(false);
		yAxis.setEnabled(true);
		
		// add data
		setData(50, 25);

		/*mChart.getLegend().setEnabled(true);
		
		Legend l=mChart.getLegend();
		l.setForm(LegendForm.LINE);*/

		mChart.animateXY(2000, 2000);

		// dont forget to refresh the drawing
		mChart.invalidate();
	}

	private void setData(int count, float range) {
		ArrayList<LineDataSet> datas = new ArrayList<LineDataSet>();
		xVals = new ArrayList<String>();
		for (int i = 1; i < count+1; i++) {
			xVals.add(i + "");
		}

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult);// + (float)
															// ((mult *
															// 0.1) / 10);
			yVals1.add(new Entry(val, i));
		}

		// create a dataset and give it a type
		LineDataSet set1 = new LineDataSet(yVals1, "yvals1");
		
		set1.setDrawCubic(true);
		set1.setCubicIntensity(0.2f);
		// set1.setDrawFilled(true);
		set1.setDrawCircles(false);
		set1.setLineWidth(2f);
		set1.setCircleSize(5f);
		set1.setHighLightColor(Color.rgb(244, 117, 117));
		set1.setColor(Color.rgb(0, 0, 0));// 线条颜色
		set1.setFillColor(ColorTemplate.getHoloBlue());

		ArrayList<Entry> yVals2 = new ArrayList<Entry>();

		for (int i = 0; i < count; i++) {
			float mult = range;
			float val = (float) (Math.random() * mult) + 40;// + (float)
																// ((mult *
																// 0.1) / 10);
			yVals2.add(new Entry(val, i));
		}

		LineDataSet set2 = new LineDataSet(yVals2, "yvals2");
		set2.setDrawCubic(true);
		set2.setCubicIntensity(0.2f);
		// set1.setDrawFilled(true);
		set2.setDrawCircles(false);
		set2.setLineWidth(2f);
		set2.setCircleSize(5f);
		set2.setHighLightColor(Color.rgb(244, 117, 117));
		set2.setColor(Color.rgb(0, 0, 255));// 线条颜色
		set2.setFillColor(ColorTemplate.getHoloBlue());

		datas.add(set1);
		datas.add(set2);
		// create a data object with the datasets
		LineData data = new LineData(xVals, datas);
		
		data.setValueTypeface(tf);
		data.setValueTextSize(9f);
		data.setDrawValues(false);
		
		
		
		// set data
		mChart.setData(data);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onValueSelected(Entry arg0, int arg1, Highlight arg2) {
		// TODO Auto-generated method stub
		Log.i("select value", arg0.toString()+"------------"+arg1);
		x.setText(xVals.get(arg0.getXIndex()));
		y.setText(arg0.getVal()+"");
		Log.i("arg0", arg0.getData()+"///"+arg0.getVal()+"///"+arg0.getXIndex());
	}

	@Override
	public void onChartDoubleTapped(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartLongPressed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartScale(MotionEvent arg0, float arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartSingleTapped(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartTranslate(MotionEvent arg0, float arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
