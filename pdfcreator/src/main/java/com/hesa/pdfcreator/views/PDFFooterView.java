package com.hesa.pdfcreator.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.hesa.pdfcreator.views.basic.PDFCustomView;
import com.hesa.pdfcreator.views.basic.PDFVerticalView;
import com.hesa.pdfcreator.views.basic.PDFView;

import java.io.Serializable;

public class PDFFooterView extends PDFVerticalView implements Serializable {

    public PDFFooterView(Context context) {
        super(context);

        PDFCustomView emptySpaceView = new PDFCustomView(context, new View(context), 0, 0, 1);
        this.addView(emptySpaceView);
    }

    @Override
    public PDFFooterView addView(PDFView viewToAdd) {
        super.addView(viewToAdd);
        return this;
    }

    @Override
    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }
}
