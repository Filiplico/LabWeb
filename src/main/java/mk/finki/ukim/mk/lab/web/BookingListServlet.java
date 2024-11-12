package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookingListServlet", urlPatterns = "/allBookings")
public class BookingListServlet extends HttpServlet {

    private final EventBookingRepository bookingRepository;
    private final SpringTemplateEngine springTemplateEngine;

    public BookingListServlet(EventBookingRepository bookingRepository, SpringTemplateEngine springTemplateEngine) {
        this.bookingRepository = bookingRepository;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        String searchName = req.getParameter("searchName");
        List<EventBooking> bookings;

        if (searchName != null && !searchName.isEmpty()) {
            bookings = bookingRepository.searchByEventName(searchName);
            context.setVariable("searchName", searchName);
        } else {
            bookings = bookingRepository.findAll();
        }

        context.setVariable("bookings", bookings);
        springTemplateEngine.process("allBookings.html", context, resp.getWriter());
    }
}
