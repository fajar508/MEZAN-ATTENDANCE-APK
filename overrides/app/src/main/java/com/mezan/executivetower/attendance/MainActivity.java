
package com.mezan.executivetower.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.*;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends Activity {

    int navy = Color.rgb(18, 35, 58);
    int blue = Color.rgb(38, 105, 210);
    int green = Color.rgb(30, 145, 95);
    int red = Color.rgb(200, 65, 65);
    int orange = Color.rgb(220, 135, 35);
    int ink = Color.rgb(35, 45, 60);
    int muted = Color.rgb(105, 115, 130);
    int bgColor = Color.rgb(245, 247, 251);

    LinearLayout page;
    String[] names = new String[17];
    String[] statuses = new String[17];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 17; i++) {
            names[i] = String.format(Locale.US, "اسٹاف ممبر %02d", i + 1);
            statuses[i] = "ریکارڈ موجود نہیں";
        }

        showHome();
    }

    GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(10, 10, 10, 10);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    LinearLayout column() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(12, 14, 12, 20);
        l.setBackgroundColor(bgColor);
        return l;
    }

    void heading(String title, String subtitle) {
        LinearLayout card = column();
        card.setBackground(shape(navy, 26));
        card.addView(text(title, 24, Color.WHITE, true));
        card.addView(text(subtitle, 14,
                Color.rgb(220, 230, 245), false));
        page.addView(card);
    }

    Button button(String title, int color) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setBackground(shape(color, 20));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        -1, -2);
        p.setMargins(4, 6, 4, 6);
        b.setLayoutParams(p);
        b.setMinHeight(60);
        return b;
    }

    void setup(String title, String subtitle) {
        page = column();
        heading(title, subtitle);
        page.addView(text(
                new SimpleDateFormat(
                        "EEEE, dd MMMM yyyy",
                        Locale.ENGLISH).format(new Date()),
                14, muted, false));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(false);
        scroll.addView(page);
        setContentView(scroll);
    }

    LinearLayout statCard(String label, String value) {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setGravity(Gravity.CENTER);
        c.setBackground(shape(Color.WHITE, 20));
        c.addView(text(label, 12, muted, false));
        c.addView(text(value, 23, ink, true));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(0, 90, 1);
        p.setMargins(4, 5, 4, 10);
        c.setLayoutParams(p);
        return c;
    }

    void showHome() {
        setup("MEZAN EXECUTIVE TOWER",
                "ATTENDANCE MANAGEMENT SYSTEM");

        LinearLayout stats = new LinearLayout(this);
        stats.addView(statCard("TOTAL STAFF", "17"));
        stats.addView(statCard("PRESENT", "—"));
        stats.addView(statCard("ON LEAVE", "—"));
        page.addView(stats);

        page.addView(text("MAIN MENU", 17, navy, true));

        Button daily = button("📋  Daily Attendance", blue);
        Button staff = button("👥  Staff Status List", green);
        Button leaves = button("📝  Leaves & Short Leaves", orange);
        Button reports = button("📊  Monthly / Annual Reports", blue);
        Button holidays = button("📅  Holidays & Sundays", green);
        Button admin = button("⚙  Admin Dashboard", navy);

        page.addView(daily);
        page.addView(staff);
        page.addView(leaves);
        page.addView(reports);
        page.addView(holidays);
        page.addView(admin);
        page.addView(text("MEZAN ATTENDANCE APP",
                13, muted, true));

        daily.setOnClickListener(v -> showDaily());
        staff.setOnClickListener(v -> showStaff());
        admin.setOnClickListener(v -> showAdmin());
        leaves.setOnClickListener(v -> showMessage("Leave Records"));
        reports.setOnClickListener(v -> showMessage("Reports"));
        holidays.setOnClickListener(v -> showMessage("Holidays"));
    }

    void showAdmin() {
        setup("ADMIN DASHBOARD",
                "MEZAN EXECUTIVE TOWER");

        page.addView(text("CONTROL CENTER",
                17, navy, true));

        Button staff = button("👥  All Staff & Status", green);
        Button daily = button("📋  Daily Attendance", blue);
        Button leaves = button("📝  Leave Records", orange);
        Button reports = button("📊  Reports", blue);
        Button back = button("←  Back to Home", navy);

        page.addView(staff);
        page.addView(daily);
        page.addView(leaves);
        page.addView(reports);
        page.addView(back);

        staff.setOnClickListener(v -> showStaff());
        daily.setOnClickListener(v -> showDaily());
        leaves.setOnClickListener(v -> showMessage("Leave Records"));
        reports.setOnClickListener(v -> showMessage("Reports"));
        back.setOnClickListener(v -> showHome());
    }

    void showStaff() {
        setup("STAFF STATUS LIST",
                "All 17 Staff Members");

        page.addView(text(
                "ریکارڈ کے بغیر کسی کو حاضر یا چھٹی پر ظاہر نہیں کیا گیا۔",
                15, ink, true));

        for (int i = 0; i < names.length; i++) {
            final int index = i;

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);
            row.setPadding(8, 8, 8, 8);
            row.setBackground(shape(Color.WHITE, 18));

            LinearLayout.LayoutParams rp =
                    new LinearLayout.LayoutParams(-1, -2);
            rp.setMargins(2, 5, 2, 5);
            row.setLayoutParams(rp);

            row.addView(text(names[i], 17, ink, true));
            row.addView(text(statuses[i], 14, muted, false));

            Button change = button("اسٹیٹس منتخب کریں", blue);
            row.addView(change);

            change.setOnClickListener(v -> {
                String[] options = {
                        "حاضر",
                        "غیر حاضر",
                        "چھٹی",
                        "شارٹ لیو",
                        "ریکارڈ موجود نہیں"
                };

                new AlertDialog.Builder(this)
                        .setTitle(names[index])
                        .setItems(options, (dialog, which) -> {
                            statuses[index] = options[which];
                            showStaff();
                        })
                        .show();
            });

            page.addView(row);
        }

        Button back = button("←  Back to Admin", navy);
        page.addView(back);
        back.setOnClickListener(v -> showAdmin());
    }

    void showDaily() {
        setup("DAILY ATTENDANCE",
                "Staff Attendance");

        page.addView(text(
                "ملازم منتخب کریں اور اس کا اسٹیٹس درج کریں۔",
                15, ink, false));

        for (int i = 0; i < names.length; i++) {
            final int index = i;
            Button b = button(names[i] + " — " + statuses[i], blue);
            page.addView(b);

            b.setOnClickListener(v -> {
                String[] options = {
                        "حاضر", "غیر حاضر", "چھٹی",
                        "شارٹ لیو", "ریکارڈ موجود نہیں"
                };

                new AlertDialog.Builder(this)
                        .setTitle(names[index])
                        .setItems(options, (dialog, which) -> {
                            statuses[index] = options[which];
                            showDaily();
                        })
                        .show();
            });
        }

        Button back = button("←  Back to Home", navy);
        page.addView(back);
        back.setOnClickListener(v -> showHome());
    }

    void showMessage(String title) {
        Toast.makeText(this,
                title + " ابھی فعال نہیں ہے",
                Toast.LENGTH_SHORT).show();
    }
    }
