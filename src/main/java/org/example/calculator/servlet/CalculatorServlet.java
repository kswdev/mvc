package org.example.calculator.servlet;

import org.example.calculator.normal.Calculator;
import org.example.calculator.normal.interface_version.number.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * GenericServlet을 상속받는다면
 * destroy, getServletConfig, getServletInfo 등 메소드는 삭제해도 된다
 */
//@WebServlet("/calculate")
public class CalculatorServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    private ServletConfig config;
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init");
        this.config = config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(req.getParameter("operand1"));
        String operator = req.getParameter("operator");
        int operand2 = Integer.parseInt(req.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        PrintWriter writer = res.getWriter();
        writer.println(result);
    }

    @Override
    public void destroy() {
        //resource release
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}
