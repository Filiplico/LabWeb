package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "EventSearchServlet",
        urlPatterns = {"/searchEvents"}
)
public class EventSearchServlet extends HttpServlet {
    private final EventRepository eventRepository;
    private final SpringTemplateEngine templateEngine;

    public EventSearchServlet(EventRepository eventRepository, SpringTemplateEngine templateEngine) {
        this.eventRepository = eventRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText");

        List<Event> events = eventRepository.searchEvents(searchText);

        context.setVariable("events", events);

        templateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
