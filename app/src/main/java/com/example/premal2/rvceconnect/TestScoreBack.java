package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Anchor;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

public class TestScoreBack extends Fragment
{


    static int[] test1_score=new int[]{0,0,0,0,0,0,0};
    static int[] test2_score=new int[]{0,0,0,0,0,0,0};
    static int[] test3_score=new int[]{0,0,0,0,0,0,0};
    static int[] quiz1_score=new int[]{0,0,0,0,0,0,0};
    static int[] quiz2_score=new int[]{0,0,0,0,0,0,0};
    static int[] quiz3_score=new int[]{0,0,0,0,0,0,0};
    static double[] studentsgpa=new double[]{0.0,0.0,0.0,0.0};
    static double[] toppersgpa=new double[]{0.0,0.0,0.0,0.0};
    static double[] averagesgpa=new double[]{0.0,0.0,0.0,0.0};
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        /*inflate the testscore backend*/
        final View testscorebackview=inflater.inflate(R.layout.testscore_back, container, false);

        /*colors of the buttons*/
        int[] button_color=new int[]{Color.BLUE,Color.MAGENTA,Color.GREEN,Color.RED,Color.BLUE,Color.MAGENTA};

        /*names of the subjects*/
        final String[] subjects=new String[]{"MATHS","DBMS","MCES","PSQ","CCN","IPRE","SE"};


        final int number_of_subjects =subjects.length;


        /*scores of test1*/
    //    final int[]  test1_score=new int[]{50,40,45,48,46,43,45};

        /*scores of test2 */
    //    final int[] test2_score =new int[]{0,0,0,0,0,0,0};


        /*scores of test3*/
    //    final int[] test3_score =new int[]{0,0,0,0,0,0,0};

        /*score of quiz 1*/
     //   final int[] quiz1_score =new int[]{10,10,8,9,10,9,5};


        /*score of quiz2*/
      //  final int[] quiz2_score =new int[]{0,0,0,0,0,0,0};


        /*score of quiz3 */
      //  final int[] quiz3_score =new int[]{0,0,0,0,0,0,0};




        /*initialise the student sgpa into an array */
        //static double[] studentsgpa=new double[]{9.4,9.2,9.44,9.46};
       // static double[] toppersgpa =new double[]{10,9.92,9.8,9.84};
    //    static double[] averagesgpa =new double[]{8,9.2,9.3,8.5};
        /*the color array that will hold the color options */





        final int number_of_semesters=studentsgpa.length;







        /*a vertical linear layout that will hold all the buttons */
        LinearLayout super_parent_layout=new LinearLayout(testscorebackview.getContext());
        super_parent_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        super_parent_layout.setOrientation(LinearLayout.VERTICAL);





        /*creating the six different buttons */
        final Button quiz1=new Button(testscorebackview.getContext());
        Button quiz2=new Button(testscorebackview.getContext());
        Button quiz3=new Button(testscorebackview.getContext());

        Button test1=new Button(testscorebackview.getContext());
        Button test2=new Button(testscorebackview.getContext());
        Button test3=new Button(testscorebackview.getContext());

        Button student_comparision_analysis=new Button(testscorebackview.getContext());



        ViewGroup.MarginLayoutParams params=new ViewGroup.MarginLayoutParams(super_parent_layout.getLayoutParams());
        params.setMargins(0,0,0,40);

        quiz1.setText("QUIZ 1");
        quiz1.setTextColor(Color.WHITE);
        quiz1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        quiz1.setBackgroundColor(button_color[0]);

        quiz2.setText("QUIZ 2");
        quiz2.setTextColor(Color.WHITE);
        quiz2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        quiz2.setBackgroundColor(button_color[0]);

        quiz3.setText("QUIZ 3");
        quiz3.setTextColor(Color.WHITE);
        quiz3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        quiz3.setBackgroundColor(button_color[0]);


        test1.setText("TEST 1");
        test1.setTextColor(Color.WHITE);
        test1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        test1.setBackgroundColor(button_color[1]);

        test2.setText("TEST 2");
        test2.setTextColor(Color.WHITE);
        test2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        test2.setBackgroundColor(button_color[1]);

        test3.setText("TEST 3");
        test3.setTextColor(Color.WHITE);
        test3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        test3.setBackgroundColor(button_color[1]);


        student_comparision_analysis.setText("SEMESTER WISE PERFORMANCE");
        student_comparision_analysis.setTextColor(Color.WHITE);
        student_comparision_analysis.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        student_comparision_analysis.setBackgroundColor(button_color[0]);






        test1.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);




                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],test1_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tTEST 1 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(50);


                anyChartView.setChart(cartesian);




                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                     builder = new AlertDialog.Builder(testscorebackview.getContext());
                  }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });


        test2.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);




                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],test2_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tTEST 2 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(50);
                anyChartView.setChart(cartesian);




                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(testscorebackview.getContext());
                }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });


        test3.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);



                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],test3_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tTEST 3 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(50);
                anyChartView.setChart(cartesian);




                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(testscorebackview.getContext());
                }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });


        quiz1.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);



                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],quiz1_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tQUIZ 1 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(10);



                anyChartView.setChart(cartesian);




















                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(testscorebackview.getContext());
                }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });

        quiz2.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);



                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],quiz2_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tQUIZ 2 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(10);
                anyChartView.setChart(cartesian);




                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(testscorebackview.getContext());
                }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });

        quiz3.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                View prompt =inflater.inflate(R.layout.performance_graph,null);



                AnyChartView anyChartView=prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();
                for(int i=0;i<number_of_subjects;i++)
                {

                    data.add(new ValueDataEntry(subjects[i],quiz3_score[i]));

                }

                Column column = cartesian.column(data);





                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(com.anychart.enums.Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(4d)
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);
                cartesian.title("\t\tQUIZ 3 PERFORMANCE");


                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title("subjects");
                cartesian.yAxis(0).title("marks");


                cartesian.yScale().maximum(10);
                anyChartView.setChart(cartesian);




                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(testscorebackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(testscorebackview.getContext());
                }



                builder.setView(prompt);




                builder.setNegativeButton("BACK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });


                builder.show();



            }
        });

       student_comparision_analysis.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {


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


                   for (int i = 0; i < number_of_semesters; i++)
                   {
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

                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                   {
                       builder = new AlertDialog.Builder(testscorebackview.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                   }
                   else
                   {
                       builder = new AlertDialog.Builder(testscorebackview.getContext());
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



        super_parent_layout.addView(quiz1,params);
        super_parent_layout.addView(test1,params);
        super_parent_layout.addView(quiz2,params);
        super_parent_layout.addView(test2,params);
        super_parent_layout.addView(quiz3,params);
        super_parent_layout.addView(test3,params);
        super_parent_layout.addView(student_comparision_analysis,params);



        RelativeLayout rl = (RelativeLayout)testscorebackview.findViewById(R.id.testscore_back_layout);
        rl.addView(super_parent_layout);


        return testscorebackview;

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
