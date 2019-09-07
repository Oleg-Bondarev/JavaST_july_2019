package by.training.flowers.controller;

import by.training.flowers.entity.AbstractFlower;
import by.training.flowers.entity.ArtificialFlower;
import by.training.flowers.entity.WildFlower;
import by.training.flowers.service.factory.FlowerBuilderFactory;
import by.training.flowers.service.factory.UnknownParserTypeException;
import by.training.flowers.service.parser.AbstractFlowerParser;
import by.training.flowers.service.parser.ParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represent servlet.
 * */
public class WebServlet extends HttpServlet {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * @param request -request.
     * @param response -response.
     * @throws ServletException -some servlet exception.
     * @throws IOException -I/O exception.
     * */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * @param request -request.
     * @param response -response.
     * @throws ServletException -some servlet exception.
     * @throws IOException -I/O exception.
     * */
    @Override
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse  response)
        throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Request processing.
     * @param request -request.
     * @param response -response.
     * @throws ServletException -some servlet exception.
     * @throws IOException -I/O exception.
     * */
    public void processRequest(final HttpServletRequest request,
                               final HttpServletResponse response)
            throws ServletException, IOException {
        String parserType = request.getParameter("parserType");
        String xmlPath = request.getParameter("xmlText");
        Set<AbstractFlower> flowers = new LinkedHashSet<>();
        Set<AbstractFlower> wildFlowers = new LinkedHashSet<>();
        Set<AbstractFlower> artificialFlower = new LinkedHashSet<>();
        try {
            AbstractFlowerParser parser = FlowerBuilderFactory.getInstance()
                    .createFlowerBuilder(parserType);
            parser.buildFlowerSet(xmlPath);
            flowers = parser.getFlowersSet();
            for (AbstractFlower flower : flowers) {
                if (flower instanceof WildFlower) {
                    wildFlowers.add(flower);
                }
                if (flower instanceof ArtificialFlower) {
                    artificialFlower.add(flower);
                }
            }
        } catch (UnknownParserTypeException | ParserException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        request.setAttribute("wild", wildFlowers);
        request.setAttribute("artificial", artificialFlower);
        request.getRequestDispatcher("/jsp/result.jsp")
                .forward(request, response);
    }

}
