package model;

import fabricaConexao.FabricaConexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JogadaDAO {
    public ResultSet relatorioJogadas() throws SQLException {
        Connection c = FabricaConexao.getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt
                .executeQuery("select * from view_jogadas");
        return rs;
    }
}
