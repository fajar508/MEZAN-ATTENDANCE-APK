package com.mezan.executivetower.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private int blue = Color.rgb(20, 92, 160);
    private int dark = Color.rgb(25, 35, 45);
    private int light = Color.rgb(245, 248, 250);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(16, 16, 16, 16);

        if (bold) {
            t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }

        return t;
    }

    private Button menuButton(String title) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextSize(16);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setBackgroundColor(blue);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        58
                );

        p.setMargins(20, 8, 20, 8);
        b.setLayoutParams(p);

        return b;
    }

    private LinearLayout baseLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(light);
        layout.setPadding(0, 20, 0, 20);
        return layout;
    }

    private void showHome() {

        LinearLayout layout = baseLayout();

        TextView title = text(
                "MEZAN EXECUTIVE TOWER",
                25,
                blue,
                true
        );
        layout.addView(title);

        TextView subtitle = text(
                "ATTENDANCE MANAGEMENT SYSTEM",
                17,
                dark,
                true
        );
        layout.addView(subtitle);

        String date = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy",
                Locale.ENGLISH
        ).format(new Date());

        layout.addView(
                text(date, 16, Color.DKGRAY, false)
        );

        layout.addView(
                text("SYSTEM READY", 18, Color.rgb(0, 130, 70), true)
        );

        Button daily = menuButton("📋 Daily Attendance");
        Button staff = menuButton("👥 Staff Management");
        Button leaves = menuButton("📝 Leaves & Short Leaves");
        Button reports = menuButton("📊 Monthly / Annual Reports");
        Button holidays = menuButton("📅 Holidays & Sundays");
        Button admin = menuButton("⚙ Admin Dashboard");

        layout.addView(daily);
        layout.addView(staff);
        layout.addView(leaves);
        layout.addView(reports);
        layout.addView(holidays);
        layout.addView(admin);

        daily.setOnClickListener(v -> showDailyAttendance());
        staff.setOnClickListener(v -> showMessage("Staff Management"));
        leaves.setOnClickListener(v -> showMessage("Leaves & Short Leaves"));
        reports.setOnClickListener(v -> showMessage("Monthly / Annual Reports"));
        holidays.setOnClickListener(v -> showMessage("Holidays & Sundays"));
        admin.setOnClickListener(v -> showMessage("Admin Dashboard"));

        TextView footer = text(
                "MEZAN ATTENDANCE APP",
                13,
                Color.GRAY,
                false
        );

        LinearLayout.LayoutParams fp =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        fp.setMargins(0, 25, 0, 0);
        footer.setLayoutParams(fp);

        layout.addView(footer);

        setContentView(layout);
    }

    private void showDailyAttendance() {

        LinearLayout layout = baseLayout();

        TextView heading = text(
                "DAILY ATTENDANCE",
                24,
                blue,
                true
        );
        layout.addView(heading);

        String date = new SimpleDateFormat(
                "dd MMMM yyyy",
                Locale.ENGLISH
        ).format(new Date());

        layout.addView(
                text("Date: " + date, 17, dark, true)
        );

        layout.addView(
                text(
                        "Staff attendance will be recorded here.",
                        16,
                        Color.DKGRAY,
                        false
                )
        );

        String[] options = {
                "✅ Present",
                "❌ Absent",
                "📝 Leave",
                "⏱ Short Leave",
                "📷 Selfie Attendance",
                "📍 Location Verification"
        };

        for (String option : options) {
            Button b = menuButton(option);

            b.setOnClickListener(v ->
                    Toast.makeText(
                            MainActivity.this,
                            option + " — Module Ready",
                            Toast.LENGTH_SHORT
                    ).show()
            );

            layout.addView(b);
        }

        Button back = menuButton("← Back to Home");

        back.setOnClickListener(v -> showHome());

        layout.addView(back);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);

        setContentView(scroll);
    }

    private void showMessage(String title) {

        Toast.makeText(
                this,
                title + " — اگلے مرحلے میں تیار کیا جائے گا",
                Toast.LENGTH_SHORT
        ).show();
    }
    }
