package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;


public class TranscriptsBackEnd extends Fragment
{

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        /*initialise the student sgpa into an array */
        final double[] studentsgpa=new double[]{9.4,9.2,9.44,9.46};
        final double[] toppersgpa =new double[]{10,9.92,9.8,9.84};
        final double[] averagesgpa =new double[]{8,9.2,9.3,8.5};
        /*the color array that will hold the color options */
        int[] button_color=new int[]{Color.BLUE,Color.MAGENTA,Color.GREEN,Color.RED,Color.BLUE,Color.MAGENTA};




        final int number_of_semesters=studentsgpa.length;


        /*inflate the transcripts backend xml file */
        final View transcriptBackendView= inflater.inflate(R.layout.transcripts_layout_back, container, false);



        /*get the id of the linear layout in backend.xml*/
        LinearLayout superparentlayout=transcriptBackendView.findViewById(R.id.transcripts_back_layout);


        /*create a button to display the progress of the student*/
        Button studentSgpaAnalysis = new Button(transcriptBackendView.getContext());



        /*Bottom margin to be added to each Button*/
        ViewGroup.MarginLayoutParams params=new ViewGroup.MarginLayoutParams(superparentlayout.getLayoutParams());
        params.setMargins(0,0,0,40);


        /*set the Text for student cgpa*/
        studentSgpaAnalysis.setText("SEMESTER WISE PERFORMANCE");
        studentSgpaAnalysis.setTextColor(Color.WHITE);
        studentSgpaAnalysis.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        studentSgpaAnalysis.setBackgroundColor(button_color[0]);




        studentSgpaAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*inflate the performance graph*/
                View prompt = inflater.inflate(R.layout.performance_graph, null);

                try {
                    /*initalise anychartView*/
                    AnyChartView anyChartView = prompt.findViewById(R.id.anychart_performance);
                    APIlib.getInstance().setActiveAnyChartView(anyChartView);

                    /*set progress bar*/
                    anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));



                    Cartesian cartesian = AnyChart.line();

                    cartesian.animation(true);

                    cartesian.padding(10d, 20d, 5d, 20d);


                    cartesian.crosshair().enabled(true);
                    cartesian.crosshair()
                            .yLabel(true)
                            // TODO ystroke
                            .yStroke((Stroke) null, null, null, (String) null, (String) null);

                    cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

                    cartesian.title("SEMESTER-WISE GRADE POINT AVERAGE");

                    cartesian.yAxis(0).title("SGPA");
                    cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

                    List<DataEntry> seriesData = new ArrayList<>();


                    for (int i = 0; i < number_of_semesters; i++) {
                        int sem = i + 1;
                        seriesData.add(new CustomDataEntry("" + sem, studentsgpa[i], averagesgpa[i], toppersgpa[i]));

                    }

                    Set set = Set.instantiate();
                    set.data(seriesData);


                    Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
                    Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
                    Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

                    Line series1 = cartesian.line(series1Mapping);
                    series1.name("MY SGPA");
                    series1.hovered().markers().enabled(true);
                    series1.hovered().markers()
                            .type(MarkerType.CIRCLE)
                            .size(4d);
                    series1.tooltip()
                            .position("right")
                            .anchor(com.anychart.enums.Anchor.LEFT_CENTER)
                            .offsetX(5d)
                            .offsetY(5d);

                    Line series2 = cartesian.line(series2Mapping);
                    series2.name("AVERAGE SGPA");
                    series2.hovered().markers().enabled(true);
                    series2.hovered().markers()
                            .type(MarkerType.CIRCLE)
                            .size(4d);
                    series2.tooltip()
                            .position("right")
                            .anchor(com.anychart.enums.Anchor.LEFT_CENTER)
                            .offsetX(5d)
                            .offsetY(5d);

                    Line series3 = cartesian.line(series3Mapping);
                    series3.name("Topper\\'s SGPA");
                    series3.hovered().markers().enabled(true);
                    series3.hovered().markers()
                            .type(MarkerType.CIRCLE)
                            .size(4d);
                    series3.tooltip()
                            .position("right")
                            .anchor(com.anychart.enums.Anchor.LEFT_CENTER)
                            .offsetX(5d)
                            .offsetY(5d);

                    cartesian.legend().enabled(true);
                    cartesian.legend().fontSize(13d);
                    cartesian.legend().padding(0d, 0d, 10d, 0d);


                    anyChartView.setChart(cartesian);


                    Log.d("e","works so far");
                  /*  GraphView graph = (GraphView) transcriptBackendView.findViewById(R.id.graph);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]
                            {
                                    new DataPoint(0, 1),
                                    new DataPoint(1, 5),
                                    new DataPoint(2, 3),
                                    new DataPoint(3, 2),
                                    new DataPoint(4, 6)
                            });
                    graph.addSeries(series); */



                    AlertDialog.Builder builder;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(transcriptBackendView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    }
                    else
                        {
                        builder = new AlertDialog.Builder(transcriptBackendView.getContext());
                    }


                    builder.setView(prompt);


                    builder.setNegativeButton("BACK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });


                    builder.show();


                }
                catch (Exception e)
                {
                    Log.d("e","doesnt work");

                    e.printStackTrace();
                }
            }


        });

        superparentlayout.addView(studentSgpaAnalysis);

        return  transcriptBackendView;

    }


    public class CustomDataEntry extends ValueDataEntry
    {

        CustomDataEntry(String x, Number value, Number value2, Number value3)
        {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }



}
