package com.tecylab.SaleSystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@RequestMapping("/keyvault/api")
public class KeyVaultController {

    @Value("${azure-sql-url}")
    private String sqlUrl;

    @Value("${azure-sql-user}")
    private String sqlUser;

    @Value("${azure-sql-password}")
    private String sqlPassword;

    @GetMapping("/check")
    public String check() {
        return "Secreto cargado: " + sqlUrl + ", " + sqlUser + ", " + sqlPassword;
    }

    @GetMapping("/db/test")
    public String testDbConnection() {
        try (Connection connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword)) {
            return "✔ Conexión exitosa a SQL Server!";
        } catch (Exception e) {
            return "❌ Error conectando: " + e.getMessage();
        }
    }
}

