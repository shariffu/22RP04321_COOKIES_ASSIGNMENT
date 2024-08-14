package works;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/status")
public class StatusCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameter to determine response code
        String code = request.getParameter("code");

        if (code == null) {
            out.println("<html><body>");
            out.println("<h2>Welcome to Status Code Generator</h2>");
            out.println("<form action='status' method='get'>");
            out.println("Enter status code (404, 500, etc.): <input type='text' name='code'>");
            out.println("<input type='submit' value='Generate Status Code'>");
            out.println("</form>");
            out.println("</body></html>");
            return;
        }

        try {
            int statusCode = Integer.parseInt(code);

            // Set the status code and generate appropriate response
            switch (statusCode) {
                case 404:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested resource was not found.");
                    break;
                case 500:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred.");
                    break;
                case 403:
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access to the resource is forbidden.");
                    break;
                case 400:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The request could not be understood by the server due to malformed syntax.");
                    break;
                default:
                    out.println("<html><body>");
                    out.println("<h2>Invalid Status Code</h2>");
                    out.println("<p>The status code '" + statusCode + "' is not supported. Please enter one of the following: 400, 403, 404, 500.</p>");
                    out.println("</body></html>");
                    break;
            }
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h2>Invalid Input</h2>");
            out.println("<p>Please enter a valid number for the status code.</p>");
            out.println("</body></html>");
        }
    }
}
