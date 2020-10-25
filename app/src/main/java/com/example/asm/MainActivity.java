package com.example.asm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private TextView name;
    private TextView type;
    private TextView averagePrice;
    private TextView notes;
    private TextView reporter;
    private boolean isCheck = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        name = findViewById(R.id.edtName);
        type = findViewById(R.id.edtType);
        averagePrice = findViewById(R.id.edtAve);
        notes = findViewById(R.id.edtNotes);
        reporter = findViewById(R.id.edtReporter);
        // calendar
        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        // spinner
        Spinner serviceSpinner = (Spinner) findViewById(R.id.serviceSpinner);
        Spinner cleanSpinner = (Spinner) findViewById(R.id.cleanSpinner);
        Spinner foodSpinner = (Spinner) findViewById(R.id.foodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brew_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(adapter);
        cleanSpinner.setAdapter(adapter);
        foodSpinner.setAdapter(adapter);
        // re
//        CustomSpinner<String> adapter = new CustomSpinner(
//                this,
//                R.layout.view_spinner_item,
//                Arrays.asList(getResources().getStringArray(R.array.my_array))
//        );
//        spinner.setAdapter(adapter);

        showErr();
    }

    private void showErr() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isCheck && editable.toString().length() != 0) {
                    name.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
            }
        });
        type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isCheck && editable.toString().length() != 0) {
                    type.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
            }
        });
        averagePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isCheck && editable.toString().length() != 0) {
                    averagePrice.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
            }
        });
        reporter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isCheck && editable.toString().length() != 0) {
                    reporter.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        validation();
        return true;
    }




    private void validation() {
        if (name.getText().length() == 0 || type.length() == 0 || averagePrice.length() == 0 || notes.length() == 0 || reporter.length() == 0) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("chua nhap")
                    .setPositiveButton("oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setTitle("please validate");
            if (name.getText().length() == 0) {
                name.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_error_24,0);
            }
            if (type.getText().length() == 0) {
                type.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_error_24,0);
            }
            if (averagePrice.getText().length() == 0) {
                averagePrice.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_error_24,0);
            }
            if (reporter.getText().length() == 0) {
                reporter.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_error_24,0);
            }
                isCheck = true;
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("nha hang da duoc them ")
                    .setPositiveButton("oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setTitle("Add sucess");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}