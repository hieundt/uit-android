package com.example.airsense.view.graph;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mobileproject.Dashboard;
import com.example.airsense.R;
//import com.example.mobileproject.api.ExportdataAPI;


import com.example.airsense.data.apiclient.TokenManager;
import com.google.android.material.button.MaterialButton;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;


import androidx.fragment.app.Fragment;

public class GraphFragment extends Fragment {
    private TokenManager tokenManager = TokenManager.getInstance(getContext());
    View view;
    public static long last_time;
    public static int axis_x_format;
    public static int mode;
    Paint paint;
    int attribute_id;
    Button show_btn;
    Map<Date, Float> data;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    Handler ui_handler = new Handler();
    Spinner attribute_spinner, timeframe_spinner;
    Thread background_Thread;
    private ConstraintLayout backgroundLayout;


    String getQueryAttribute() {
        String template = "[{\"id\":\"%s\",\"name\":\"%s\"}]";
        switch (attribute_id) {
            case 0:
                return String.format(template, "5zI6XqkQVSfdgOrZ1MyWEf", "temperature");
            case 1:
                return String.format(template, "5zI6XqkQVSfdgOrZ1MyWEf", "humidity");
            case 2:
                return String.format(template, "5zI6XqkQVSfdgOrZ1MyWEf", "rainfall");
            case 3:
                return String.format(template, "5zI6XqkQVSfdgOrZ1MyWEf", "windSpeed");


        }
        return null;
    }


    @Nullable
    @Override
    public View getView() {
        return graph;
    }

//    @Override
//    public void onResume() {
//        show_btn.setVisibility(View.VISIBLE);
//        graph.setVisibility(View.VISIBLE);
//        attribute_spinner.setVisibility(View.VISIBLE);
//        timeframe_spinner.setVisibility(View.VISIBLE);
//        super.onResume();
//
//    }
//
//    @Override
//    public void onStart() {
//        show_btn.setVisibility(View.GONE);
//        graph.setVisibility(View.GONE);
//        attribute_spinner.setVisibility(View.GONE);
//        timeframe_spinner.setVisibility(View.GONE);
//        super.onStart();
//    }
//
//    @Override
//    public void onPause() {
//        show_btn.setVisibility(View.GONE);
//        graph.setVisibility(View.GONE);
//        attribute_spinner.setVisibility(View.GONE);
//        timeframe_spinner.setVisibility(View.GONE);
//        super.onPause();
//    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chart, container, false);
        show_btn = view.findViewById(R.id.btn_show);
        backgroundLayout = view.findViewById(R.id.background4);
        timeframe_spinner = view.findViewById(R.id.spinner_timeframe);

        timeframe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.contains("day")) {
                    GraphFragment.last_time = Long.parseLong(item.split(" ")[0]) * 86400 * 1000;
                    GraphFragment.axis_x_format = 0;
                } else if (item.contains("week")) {
                    GraphFragment.last_time = Long.parseLong(item.split(" ")[0]) * 86400 * 7 * 1000;
                    GraphFragment.axis_x_format = 1;
                } else if (item.contains("month")) {
                    GraphFragment.last_time = Long.parseLong(item.split(" ")[0]) * 86400 * 30 * 1000;
                    GraphFragment.axis_x_format = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayList<String> timeframe = new ArrayList<>();
        timeframe.add("1 day");
        timeframe.add("1 week");
        timeframe.add("1 month");

        ArrayAdapter<String> adapter = new ArrayAdapter(
                view.getContext(),
                R.layout.spinner_item,
                timeframe
        );
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        timeframe_spinner.setAdapter(adapter);
        timeframe_spinner.setSelection(0);


        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));


        graph = view.findViewById(R.id.idGraphView);
        graph.setTitleColor(R.color.green_primary);
        graph.setCursorMode(true);
        graph.setTitleColor(R.color.green_primary);
        graph.setTitleTextSize(30);

        graph.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Dashboard.viewPager.setUserInputEnabled(true);
//                    return true;
//                }
//                if (Dashboard.viewPager.isUserInputEnabled())
//                    Dashboard.viewPager.setUserInputEnabled(false);

                return false;
            }

        });


        DefaultLabelFormatter custom_formatter = new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis((long) value);
                    if (GraphFragment.axis_x_format == 0)
                        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)).concat(":00");
                    else
                        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)).concat("/" + calendar.get(Calendar.MONTH));
                }
                DecimalFormat df = new DecimalFormat("0.00");
                if (attribute_id == 0)
                    return df.format(value).concat(" ℃");
                else if (attribute_id == 1) {
                    df = new DecimalFormat("0");

                    return df.format(value).concat(" %");
                } else if (attribute_id == 2) {
                    df = new DecimalFormat("0.00");
                    return df.format(value).concat(" mm");
                } else {
                    df = new DecimalFormat("0.00");
                    return df.format(value).concat(" km/h");
                }
            }
        };


        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Show", String.valueOf(GraphFragment.last_time));
                if (GraphFragment.mode == 0) {
                    Map<String, String> query = new HashMap<>();
                    query.put("attributeRefs", getQueryAttribute());
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));

                    long to_timestamp = calendar.getTimeInMillis();
                    long from_timestamp = to_timestamp - GraphFragment.last_time;
                    query.put("fromTimestamp", String.valueOf(from_timestamp));
                    query.put("toTimestamp", String.valueOf(to_timestamp));
                    Thread data_thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                Log.d("interrupt","false");
                                ExportDataApi export_data = new ExportDataApi("https://uiot.ixxc.dev/api/master/asset/datapoint/export", "GET", query, tokenManager.getAccessToken());
                                data = export_data.GetData();
                                int stop = 0;
                                SortedSet<Date> keys = new TreeSet<>(data.keySet());
                                DataPoint[] datapoints_temp = new DataPoint[keys.size()];
                                if (data.isEmpty()) {
                                    ui_handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), "Empty data", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                                int count = 0;
                                for (Date key : keys) {
                                    datapoints_temp[count] = new DataPoint(key, data.get(key));
                                    count++;
                                }

                                if (attribute_id == 0)
                                    graph.setTitle("Temperature");
                                else if (attribute_id == 1)
                                    graph.setTitle("Humidity");
                                else if (attribute_id == 2)
                                    graph.setTitle("Rainfall");
                                else if (attribute_id == 3)
                                    graph.setTitle("Wind speed");
                                series = new LineGraphSeries<>(datapoints_temp);
                                ui_handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        graph.removeAllSeries();
                                        series.setCustomPaint(paint);
                                        series.setDrawDataPoints(true);
                                        series.setDataPointsRadius(10);
                                        graph.getViewport().setYAxisBoundsManual(true);
                                        if (attribute_id == 0) {
                                            graph.getViewport().setMinY(0);
                                            graph.getViewport().setMaxY(35);
                                        } else if (attribute_id == 1) {
                                            graph.getViewport().setMinY(0);
                                            graph.getViewport().setMaxY(100);
                                        } else if (attribute_id == 2) {
                                            graph.getViewport().setMinY(0);
                                            graph.getViewport().setMaxY(10);
                                        } else if (attribute_id == 3) {
                                            graph.getViewport().setMinY(0);
                                            graph.getViewport().setMaxY(7);
                                        }
                                        graph.getViewport().setXAxisBoundsManual(true);
                                        graph.addSeries(series);
                                        graph.setTitleTextSize(50);

                                        graph.getGridLabelRenderer().setLabelFormatter(custom_formatter);

                                        if (GraphFragment.axis_x_format == 0 || GraphFragment.axis_x_format == 1) {
                                            graph.getViewport().setMinX(datapoints_temp[0].getX());
                                            graph.setCursorMode(true);
                                            graph.getViewport().setScrollable(false);
                                            graph.getViewport().setMaxX(datapoints_temp[datapoints_temp.length - 1].getX());
                                        } else {
                                            graph.getViewport().setMinX(datapoints_temp[0].getX());
                                            graph.getViewport().setMaxX(datapoints_temp[(datapoints_temp.length / 10)].getX());
                                            graph.setCursorMode(false);
                                            graph.getViewport().setScrollable(true);
                                        }


                                    }
                                });


                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    });
                    data_thread.start();


                }


            }
        });


        attribute_spinner = view.findViewById(R.id.spinner_attribute);
        attribute_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Check if the parent view is not null and is an instance of TextView
                if (parent.getChildAt(0) instanceof TextView) {
                    // Set the text size
                    ((TextView) parent.getChildAt(0)).setTextSize(20);
                }
                attribute_id = position;
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> attribute_ArrayList = new ArrayList<>();
        attribute_ArrayList.add("Temperature");
        attribute_ArrayList.add("Humidity");
        attribute_ArrayList.add("Rainfall");
        attribute_ArrayList.add("Wind speed");
        ArrayAdapter<String> attribute_adapter = new ArrayAdapter<>(view.getContext(),
                R.layout.spinner_item, // Sử dụng tệp layout tùy chỉnh ở đây
                attribute_ArrayList
        );
        attribute_adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        attribute_spinner.setAdapter(attribute_adapter);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if(hourOfDay >= 5 && hourOfDay <= 17)
        {
        } else {
            backgroundLayout.setBackgroundResource(R.drawable.custom_button_rounded);
            show_btn.setBackgroundResource(R.drawable.custom_button_rounded);
            attribute_spinner.setBackgroundResource(R.drawable.custom_spinner);
            timeframe_spinner.setBackgroundResource(R.drawable.custom_spinner);
        }
        return view;
    }
}