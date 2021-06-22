package com.example.usercollegeapp.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.usercollegeapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class PdfViewerActivity extends AppCompatActivity {
private    String pdfUri;
private PDFView pdfView;
private ProgressBar progressPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        pdfUri =  getIntent().getStringExtra("pdfUri");
        pdfView = findViewById(R.id.pdfView);
       progressPdf = findViewById(R.id.progreesPdf);

       //loading pdf
        loadFile(pdfUri);




    }

    private void loadFile(String pdfUri) {
        FileLoader.with(this)
                .load(pdfUri) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory("test4", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        File loadedFile = response.getBody();
                        progressPdf.setVisibility(View.GONE);
                        // do something with the file
                        pdfView.fromFile(loadedFile)
                                .password(null)
                                .defaultPage(0)
                                .enableSwipe(true)
                                .swipeHorizontal(true)
                                .enableDoubletap(true)
                                .spacing(5)
                                .load();
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(PdfViewerActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressPdf.setVisibility(View.GONE);
                    }
                });

    }


}
        