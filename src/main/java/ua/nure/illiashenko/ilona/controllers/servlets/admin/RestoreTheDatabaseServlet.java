package ua.nure.illiashenko.ilona.controllers.servlets.admin;

import ua.nure.illiashenko.ilona.services.AdminService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.ADMIN_SERVICE;

@WebServlet("/database/restore")
public class RestoreTheDatabaseServlet extends HttpServlet {

    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute(ADMIN_SERVICE);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        adminService.restoreDatabase();
    }
}