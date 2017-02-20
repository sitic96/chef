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
        ViewSwitcher nameSwitcher = (ViewSwitcher) findViewById(R.id.name_switcher);
        ViewSwitcher lastNameSwitcher = (ViewSwitcher) findViewById(R.id.lastName_switcher);
        ViewSwitcher buttonSwitcher = (ViewSwitcher) findViewById(R.id.button_switcher);
        nameSwitcher.showNext();
        lastNameSwitcher.showNext();
        buttonSwitcher.showNext();
        TextView nameTextView = (TextView) nameSwitcher.findViewById(R.id.label_name);
        TextView editNameTextView = (TextView) nameSwitcher.findViewById(R.id.edit_name);
        TextView lastNameTextView = (TextView) lastNameSwitcher.findViewById(R.id.label_secondName);
        TextView editLastNameTextView = (TextView) lastNameSwitcher.findViewById(R.id.edit_lastName);
        lastNameTextView.setText(editLastNameTextView.getText());
        nameTextView.setText(editNameTextView.getText());
    }
}
