package works;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/quadratic")
public class QuadraticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Quadratic Equation Solver</h2>");
        out.println("<form method='post' action='quadratic'>");
        out.println("a: <input type='text' name='a'><br>");
        out.println("b: <input type='text' name='b'><br>");
        out.println("c: <input type='text' name='c'><br>");
        out.println("<input type='submit' value='Calculate Roots'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process the form submission
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get coefficients from the form
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));

            // Calculate the discriminant
            double discriminant = b * b - 4 * a * c;
            double root1, root2;

            // Display the roots based on the discriminant
            out.println("<html><body>");
            out.println("<h2>Quadratic Equation Solver</h2>");
            out.println("<p>Equation: " + a + "x<sup>2</sup> + " + b + "x + " + c + " = 0</p>");

            if (discriminant > 0) {
                // Two real and distinct roots
                root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                out.println("<p>Root 1: " + root1 + "</p>");
                out.println("<p>Root 2: " + root2 + "</p>");
            } else if (discriminant == 0) {
                // One real root
                root1 = -b / (2 * a);
                out.println("<p>Root: " + root1 + "</p>");
            } else {
                // Complex roots
                double realPart = -b / (2 * a);
                double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                out.println("<p>Root 1: " + realPart + " + " + imaginaryPart + "i</p>");
                out.println("<p>Root 2: " + realPart + " - " + imaginaryPart + "i</p>");
            }
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h2>Error</h2>");
            out.println("<p>Invalid input. Please enter valid numbers for coefficients.</p>");
            out.println("</body></html>");
        }
    }
}