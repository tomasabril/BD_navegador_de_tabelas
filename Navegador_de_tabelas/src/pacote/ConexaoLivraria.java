/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote;

import java.sql.*;

/**
 *
 * @author Tomás Abril
 */
public class ConexaoLivraria {

    public static Connection getConnectionLivraria() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/livraria1013343";
            String usuario = "root";
            String senha = "a123";
            Connection con = DriverManager.getConnection(url, usuario, senha);

            return con;

        } catch (Exception e1) {
            System.out.println("Erro conexao: " + e1);
            return null;
        }

    }
}
