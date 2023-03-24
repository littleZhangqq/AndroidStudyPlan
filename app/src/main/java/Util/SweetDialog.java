package Util;


import android.app.Dialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;

public class SweetDialog extends Dialog {
    private final SparseArray<View> views;

    public SweetDialog(Context context, int theme) {
        super(context, theme);
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    public SweetDialog setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }
}

