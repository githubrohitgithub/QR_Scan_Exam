package com.rohit.onlne_exams.teacher.Activities;

import static com.rohit.onlne_exams.teacher.Activities.StudentResultActivity.resultData;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hesa.pdfcreator.activity.PDFCreatorActivity;
import com.hesa.pdfcreator.utils.PDFUtil;
import com.hesa.pdfcreator.views.PDFBody;
import com.hesa.pdfcreator.views.PDFFooterView;
import com.hesa.pdfcreator.views.PDFHeaderView;
import com.hesa.pdfcreator.views.basic.PDFHorizontalView;
import com.hesa.pdfcreator.views.basic.PDFImageView;
import com.hesa.pdfcreator.views.basic.PDFLineSeparatorView;
import com.hesa.pdfcreator.views.basic.PDFTextView;
import com.rohit.onlne_exams.R;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Pdf_Creater extends PDFCreatorActivity {


    String sub_code,set_code,regno,correct,wrong,total,attempted,result;

    Context context;

    String stringDate;

    String directory;
    String  pdf_saved_file_named;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stringDate = DateFormat.getDateTimeInstance().format(new Date());

        context=this;

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }





        sub_code=getIntent().getStringExtra("sub_code");
        set_code=getIntent().getStringExtra("set_code");
        regno=getIntent().getStringExtra("regno");
        correct=getIntent().getStringExtra("correct");
        wrong=getIntent().getStringExtra("wrong");
        total=getIntent().getStringExtra("total");
        attempted=getIntent().getStringExtra("attempted");
        result=getIntent().getStringExtra("result");




        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(Calendar.getInstance().getTime());

        directory = getApplicationContext().getExternalFilesDir("").toString();

        pdf_saved_file_named=regno+" Report "+formattedDate;


        File file = new File(directory+pdf_saved_file_named);



            if(file.exists()){

                Toast.makeText(context, "Pdf already created", Toast.LENGTH_SHORT).show();


            }else{

                createPDF(pdf_saved_file_named, new PDFUtil.PDFUtilListener() {
                    @Override
                    public void pdfGenerationSuccess(File savedPDFFile) {
                        Toast.makeText(context, "PDF Created and saved in Download/", Toast.LENGTH_LONG).show();






                        moveFile(directory+"/temp/",pdf_saved_file_named+".pdf", Environment.getExternalStorageDirectory()+"/Download/");








                    }

                    @Override
                    public void pdfGenerationFailure(Exception exception) {
                        Toast.makeText(context, "PDF NOT Created", Toast.LENGTH_SHORT).show();
                    }
                });

            }





    }

    private void moveFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();


        } catch (Exception fnfe1) {

            Toast.makeText(context, fnfe1.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected PDFHeaderView getHeaderView(int pageIndex) {
        PDFHeaderView headerView = new PDFHeaderView(getApplicationContext());

        PDFHorizontalView horizontalView = new PDFHorizontalView(getApplicationContext());
        horizontalView.getView().setGravity(Gravity.CENTER_HORIZONTAL);



        PDFImageView imageView = new PDFImageView(getApplicationContext());
        LinearLayout.LayoutParams imageLayoutParam = new LinearLayout.LayoutParams(
                130,
                130, 0);
        imageView.setImageScale(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.drawable.logo);
//        imageLayoutParam.setMargins(0, 0, 20, 0);
        imageView.setLayout(imageLayoutParam);
        horizontalView.addView(imageView);
        headerView.addView(horizontalView);
        headerView.getView().setGravity(Gravity.CENTER_HORIZONTAL);

        return headerView;
    }

    @Override
    protected PDFBody getBodyViews() {
        PDFBody pdfBody = new PDFBody();


        PDFTextView pdfCompanyNameView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.HEADER);
        pdfCompanyNameView.setText(regno+" Report"+"\n");
        pdfCompanyNameView.getView().setTypeface(pdfCompanyNameView.getView().getTypeface(), Typeface.BOLD);
        pdfBody.addView(pdfCompanyNameView);
        pdfCompanyNameView.getView().setGravity(Gravity.CENTER_HORIZONTAL);


        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView1);


        PDFTextView space2 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H4);
        space2.setText(" ");
        pdfBody.addView(space2);
        space2.getView().setGravity(Gravity.CENTER_HORIZONTAL);








        PDFHorizontalView row3 = new PDFHorizontalView(getApplicationContext());
        PDFTextView Tbbi = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H2);
        Tbbi.setText("Subject Code\n"+"Set Code\n"+"Reg No.\n"+"Correct\n"+"Wrong\n"+"Total\n"+"Attempted\n"+"Result\n");
        Tbbi.setLayout(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1));
        Tbbi.getView().setGravity(Gravity.CENTER_VERTICAL);
        Tbbi.getView().setTypeface(Tbbi.getView().getTypeface(), Typeface.BOLD);
        row3.addView(Tbbi);
        pdfBody.addView(row3);


        PDFTextView bbi = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H2);
        bbi.setText(sub_code+"\n"+set_code+"\n"+regno+"\n"+correct+"\n"+wrong+"\n"+total+"\n"+attempted+"\n"+result+"\n");
        bbi.setLayout(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1));
        bbi.getView().setGravity(Gravity.CENTER_VERTICAL);
//        bbi.getView().setTypeface(bbi.getView().getTypeface(), Typeface.BOLD);
        bbi.setLayout(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1));
        bbi.getView().setGravity(Gravity.END);
        row3.addView(bbi);



        PDFTextView space9 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H4);
        space9.setText(" ");
        pdfBody.addView(space9);
        space9.getView().setGravity(Gravity.CENTER_HORIZONTAL);








        PDFLineSeparatorView lineSeparatorView3 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView3);




        PDFTextView space = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H4);
        space.setText(" ");
        pdfBody.addView(space);
        space.getView().setGravity(Gravity.CENTER_HORIZONTAL);






        return pdfBody;
    }

    @Override
    protected PDFFooterView getFooterView(int forPage) {
        return null;
    }


    @Override
    protected void onNextClicked(final File savedPDFFile) {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        String stringFile = Environment.getExternalStorageDirectory()+"/Download/"+pdf_saved_file_named+".pdf";
        //String stringFile = getApplicationContext().getExternalFilesDir("").toString()+"/temp/"+pdf_saved_file_named+".pdf";



        File file = new File(stringFile);
        if (!file.exists()){
            Toast.makeText(this, "File doesn't exists", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("application/pdf");
        intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));
        startActivity(Intent.createChooser(intentShare, "Share the file ..."));


    }
}




