package my.orange.accountant;

import android.content.Context;
import android.view.View;

public class Cell extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    private String name;

    public Cell(Context context) {
        super(context);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
