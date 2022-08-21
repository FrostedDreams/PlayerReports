package me.frosteddreams.playerreports.reports;

import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Report {

    private final UUID reporter;
    private final UUID target;
    private final String reportReason;
    private final String timeStamp;

    public Report(UUID reporter, UUID target, String reportReason) {
        this.reporter = reporter;
        this.target = target;
        this.reportReason = reportReason;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        this.timeStamp = sdf.format(cal.getTime());
    }

    public String getReporterName() {
        return Bukkit.getPlayer(reporter).getName();
    }

    public String getTargetName() {
        return Bukkit.getPlayer(target).getName();
    }

    public String getReportReason() {
        return reportReason;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}