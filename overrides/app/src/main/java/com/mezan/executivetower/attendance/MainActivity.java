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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private TextView text(String value, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(12), dp(10), dp(12), dp(10));

        if (bold) {
            t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }

        return t;
    }

    private Button menuButton(String title) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setMinHeight(dp(55));
        b.setPadding(dp(10), dp(8), dp(10), dp(8));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(dp(16), dp(6), dp(16), dp(6));
        b.setLayoutParams(p);

        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(10, 45, 70));

        ScrollView scroll = new ScrollView(this);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setBackgroundColor(Color.rgb(245, 248, 250));
        main.setPadding(0, dp(18), 0, dp(25));

        TextView title = text(
                "MEZAN EXECUTIVE TOWER",
                24,
                Color.WHITE,
                true
        );
        title.setBackgroundColor(Color.rgb(10, 70, 105));
        title.setPadding(dp(10), dp(24), dp(10), dp(24));
        main.addView(title);

        TextView subtitle = text(
                "ATTENDANCE MANAGEMENT SYSTEM",
                16,
                Color.rgb(10, 70, 105),
                true
        );
        subtitle.setPadding(dp(10), dp(18), dp(10), dp(8));
        main.addView(subtitle);

        String today = new SimpleDateFormat(
                "EEEE, dd MMMM yyyy",
                Locale.getDefault()
        ).format(new Date());

        TextView date = text(
                today,
                15,
                Color.DKGRAY,
                false
        );
        main.addView(date);

        TextView status = text(
                "System Ready",
                17,
                Color.rgb(20, 120, 70),
                true
        );
        status.setPadding(dp(10), dp(20), dp(10), dp(15));
        main.addView(status);

        Button attendance = menuButton("📋  Daily Attendance");
        main.addView(attendance);

        Button staff = menuButton("👥  Staff Management");
        main.addView(staff);

        Button leaves = menuButton("📝  Leaves & Short Leaves");
        main.addView(leaves);

        Button reports = menuButton("📊  Monthly / Annual Reports");
        main.addView(reports);

        Button holidays = menuButton("📅  Holidays & Sundays");
        main.addView(holidays);

        Button admin = menuButton("⚙️  Admin Dashboard");
        main.addView(admin);

        TextView footer = text(
                "MEZAN ATTENDANCE APP - NATIVE BUILD 14",
                13,
                Color.GRAY,
                true
        );
        footer.setPadding(dp(10), dp(30), dp(10), dp(5));
        main.addView(footer);

        scroll.addView(main);
        setContentView(scroll);
    }
          }
