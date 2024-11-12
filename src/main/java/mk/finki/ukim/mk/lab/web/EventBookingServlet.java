package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
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

@WebServlet(name = "eventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingRepository bookingRepository;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingRepository bookingRepository) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookingRepository = bookingRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String attendeeName = "Filip Lichovski";
        String attendeeAddress = req.getRemoteAddr();
        Long numberOfTickets = Long.parseLong(req.getParameter("numTickets"));

        EventBooking booking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        bookingRepository.addBooking(booking);

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        context.setVariable("eventName", eventName);
        context.setVariable("numTickets", numberOfTickets);
        context.setVariable("attendeeName", attendeeName);
        context.setVariable("attendeeAddress", attendeeAddress);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
