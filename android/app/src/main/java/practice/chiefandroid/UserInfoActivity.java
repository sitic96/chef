package practice.chiefandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void TextViewClicked(View view) {
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.my_switcher);
        switcher.showNext(); //or switcher.showPrevious();
        TextView myTV = (TextView) switcher.findViewById(R.id.clickable_text_view);
        TextView newVal = (TextView) switcher.findViewById(R.id.hidden_edit_view) ;
        myTV.setText(newVal.getText());
    }
}
