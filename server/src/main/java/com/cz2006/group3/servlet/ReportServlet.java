package com.cz2006.group3.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;


import com.cz2006.group3.bean.*;



/**
 * This class handles GET request from the client side who is requesting for a yearly/monthly report.
 */
@WebServlet(urlPatterns = "/report")
public class ReportServlet extends AbstractServlet {
    // private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ArrayList<Double> unitExp = new ArrayList<>(); unitExp.add(1.00); unitExp.add(2.00);
//        HashMap<String, Double> cateExp = new HashMap<>(); cateExp.put("food", 2.00); cateExp.put("drink", 1.00);
//         ReceiptData r = new ReceiptData(1, "HSGBKJ123", "each a cup", 123456, LocalDateTime.now(), 1.00, "food", "bubble tea 50% sugar");
//         ArrayList<ReceiptData> rs = new ArrayList<>(); rs.add(r);
//         ReportData report = new ReportData(3.00, unitExp, cateExp, rs);
//         ReportModel reportmodel = new ReportModel(-1, "first report", report);
         int uid = req.getIntHeader("uid");
        // System.out.println(req.getParameter("year")+" "+req.getParameter("month"));
         Integer year = null, month = null;
        if (!req.getParameter("year").equals("null"))
            year = Integer.parseInt(req.getParameter("year"));
        if (!req.getParameter("month").equals("null"))
            month = Integer.parseInt(req.getParameter("month"));

        LocalDateTime start, end;
        if (month == null){
            System.out.println("User " + uid + " resquests for " + year + " report");
            start = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            end = start.plusYears(1);
        }else{
            System.out.println("User " + uid + " resquests for " + month + "/" + year+" report");
            start = LocalDateTime.of(year, month, 1, 0, 0, 0);
            end = start.plusMonths(1);
        }
        ReportData r = null;
        int errorCode = -1;
        String errorMsg = "";
        try {
            r = DBConnector.getReport(uid, start, end);
            errorCode = 0;
        }catch (SQLException e){
            errorMsg += "Getting Report Failed";
            e.printStackTrace();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write(new ReportModel(errorCode, errorMsg, r).toString());
        pw.flush();
    }

}
