package works;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/headers")
public class HeaderInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve various headers
        String userAgent = request.getHeader("User-Agent");
        String host = request.getHeader("Host");
        String acceptLanguage = request.getHeader("Accept-Language");
        String acceptEncoding = request.getHeader("Accept-Encoding");
        String connection = request.getHeader("Connection");

        // Generate HTML output
        out.println("<html><body>");
        out.println("<h2>Request Header Information</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Header</th><th>Value</th></tr>");
        out.println("<tr><td>User-Agent</td><td>" + (userAgent != null ? userAgent : "Not Available") + "</td></tr>");
        out.println("<tr><td>Host</td><td>" + (host != null ? host : "Not Available") + "</td></tr>");
        out.println("<tr><td>Accept-Language</td><td>" + (acceptLanguage != null ? acceptLanguage : "Not Available") + "</td></tr>");
        out.println("<tr><td>Accept-Encoding</td><td>" + (acceptEncoding != null ? acceptEncoding : "Not Available") + "</td></tr>");
        out.println("<tr><td>Connection</td><td>" + (connection != null ? connection : "Not Available") + "</td></tr>");
        out.println("</table>");
        out.println("</body></html>");
    }
}