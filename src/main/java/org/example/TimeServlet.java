package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String timezoneParam = request.getParameter("timezone");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'UTC'X");

        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            int hoursOffset = Integer.parseInt(timezoneParam.replace("UTC", "").trim());
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + (hoursOffset >= 0 ? "+" : "") + hoursOffset));
        } else {
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        String currentTime = dateFormat.format(new Date());

        String htmlResponse = "<html><body><p>" + currentTime + "</p></body></html>";
        response.getWriter().write(htmlResponse);
    }
}