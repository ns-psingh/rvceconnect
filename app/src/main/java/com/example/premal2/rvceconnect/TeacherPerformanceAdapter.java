package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

public class TeacherPerformanceAdapter extends BaseAdapter
{

    Context c;
    ArrayList<SectionCardsForTeacherPerformance> sections;


    public TeacherPerformanceAdapter(Context c,ArrayList<SectionCardsForTeacherPerformance> sections)
    {
        this.c=c;
        this.sections=sections;
    }

    @Override
    public int getCount()
    {

        return sections.size();
    }

    @Override
    public Object getItem(int i)
    {
        return sections.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view ==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.card_for_teacher_home,viewGroup,false);
        }

        final SectionCardsForTeacherPerformance s= (SectionCardsForTeacherPerformance) this.getItem(i);

        TextView nameTxt= (TextView) view.findViewById(R.id.SectionNameTxt);
        TextView propTxt= (TextView) view.findViewById(R.id.subjectnameTxt);

        //BIND
        nameTxt.setText(s.getSectionname());
        propTxt.setText(s.getSubjectname());


        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                View prompt =LayoutInflater.from(c).inflate(R.layout.performance_graph,null);









                AnyChartView anyChartView = prompt.findViewById(R.id.anychart_performance);
                anyChartView.setProgressBar(prompt.findViewById(R.id.progress_bar));


                AlertDialog.Builder builder;

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                {
                    builder= new AlertDialog.Builder(c,android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(c);
                }




                int Average=0;
                int Count_S=0;
                int Count_A=0;
                int COunt_B=0;
                int COunt_C=0;
                int Count_D=0;
                int Count_E=0;
                int COunt_F=0;


//                int[] student_marks=s.getStudentmarks();

                for(int i=0;i<s.getStudentmarks().size();i++)
                {
                    int marks=s.getStudentmarks().get(i);

                    if(marks>=45)
                    {
                        Count_S++;
                    }
                    else if(marks>=40)
                    {
                        Count_A++;
                    }
                    else if(marks>=35)
                    {
                        COunt_B++;
                    }
                    else if(marks>=30)
                    {
                        COunt_C++;
                    }
                    else if(marks>=25)
                    {
                        Count_D++;
                    }
                    else if(marks>=20)
                    {
                        Count_E++;
                    }
                    else
                    {
                        COunt_F++;
                    }

                }
                Pie pie = AnyChart.pie();














                List<DataEntry> data = new ArrayList<>();
                data.add(new ValueDataEntry("S", Count_S));
                data.add(new ValueDataEntry("A", Count_A));
                data.add(new ValueDataEntry("B", COunt_B));
                data.add(new ValueDataEntry("C", COunt_C));
                data.add(new ValueDataEntry("D", Count_D));
                data.add(new ValueDataEntry("E", Count_E));
                data.add(new ValueDataEntry("F", COunt_F));



                pie.data(data);

                pie.title("SCORE DISTRIBUTION OF LAST QUIZ/TEST");

                pie.labels().position("outside");

                pie.legend().title().enabled(true);
                pie.legend().title()
                        .text("GRADES")
                        .padding(0d, 0d, 10d, 0d);

                pie.legend()
                        .position("center-bottom")
                        .itemsLayout(LegendLayout.HORIZONTAL)
                        .align(Align.CENTER);

                pie.animation(true);

                anyChartView.setChart(pie);

                builder.setView(prompt);


                builder.setTitle("PERFORMANCE");



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

        return view;


    }
}
