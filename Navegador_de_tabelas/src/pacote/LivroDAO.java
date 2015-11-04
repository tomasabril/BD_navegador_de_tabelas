/*
 * Professor que fez esse arquivo
 */
package pacote;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.sql.DatabaseMetaData;

/**
 *
 * @author Tomás Abril
 */
public class LivroDAO {

    //essa classe lida com o banco de dados
    private Connection con;
    private Statement stmt1;

    public LivroDAO() {
        try {
            con = ConexaoLivraria.getConnectionLivraria();

            this.stmt1 = this.con.createStatement();

        } catch (Exception e1) {
            System.out.println("Erro LivroDAO ..: " + e1);
        }
    }

    public List listByTitulo(String titulo) {
        return null;
    }

    public byte[] getCapaByte(int id) {
        try {
            ResultSet rs = this.stmt1.executeQuery("select capa from livros where livro_id = " + id);
            if (rs.next()) {
                byte img[] = rs.getBytes(1);
                return img;
            } else {
                return null;
            }
        } catch (SQLException sql1) {
            System.out.println("Erro LivroDAO: " + sql1);
            return null;
        }
    }

    public ArrayList<LivroTO> listAll() {
        try {
            ResultSet rs = this.stmt1.executeQuery("select * from livros order by livro_id");
            ArrayList<LivroTO> lista = new ArrayList<LivroTO>();
            LivroTO res = null;
            while (rs.next()) {
                res = new LivroTO();
                res.setLivro_id(rs.getInt("livro_id"));
                res.setTitulo(rs.getString("titulo"));
                res.setAutor(rs.getString("autor"));
                res.setPreco(rs.getDouble("preco"));
                res.setAno(rs.getInt("ano"));
                res.setDescricao(rs.getString("descricao"));
                res.setEditora(rs.getString("editora"));
                res.setEstoque(rs.getInt("estoque"));
                res.setReserva(rs.getInt("reserva"));
                res.setGenero_id(rs.getInt("genero_id"));
                lista.add(res);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            return null;
        }
    }

    public ArrayList<LivroTO> listtables() {

        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String[] types = null;
        try {
            DatabaseMetaData databaseMetaData = con.getMetaData();

            ResultSet result = databaseMetaData.getTables(
                    catalog, schemaPattern, tableNamePattern, types);

            ArrayList<LivroTO> listat = new ArrayList<LivroTO>();

            LivroTO resulta = null;
            while (result.next()) {
                resulta = new LivroTO();
                resulta.setTableNamePattern(result.getString("TABLE_NAME"));

                listat.add(resulta);
            }
            return listat;
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            return null;
        }
    }

    public boolean update(LivroTO livro) {
        String sql = "update livros set titulo = '" + livro.getTitulo() + "', "
                + "autor = '" + livro.getAutor() + "', editora = '" + livro.getEditora() + "', "
                + "genero_id = " + livro.getGenero_id() + ", estoque = " + livro.getEstoque() + ", "
                + "reserva = " + livro.getReserva() + ", preco = " + livro.getPreco() + ", "
                + "descricao = '" + livro.getDescricao() + "', ano = " + livro.getAno()
                + " where livro_id = " + livro.getLivro_id();
        System.out.println("Comando: " + sql);
        try {
            int n = this.stmt1.executeUpdate(sql);
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sql3) {
            System.out.println("Erro: " + sql3);
            return false;
        }
    }

    public boolean insert(LivroTO livro) {
        return true;
    }

    public boolean delete(LivroTO livro) {
        String sql = "delete from livros where livro_id = " + livro.getLivro_id();
        System.out.println("Comando: " + sql);
        try {
            int n = this.stmt1.executeUpdate(sql);
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sql3) {
            System.out.println("Erro: " + sql3);
            return false;
        }
    }

    public LivroTO find(String Titulo) {
        try {
            ResultSet rs = this.stmt1.executeQuery("select * from livros where Titulo = " + "\"" + Titulo + "\"");
            if (rs.next()) {
                LivroTO res = new LivroTO();
                res.setLivro_id(rs.getInt("livro_id"));
                res.setTitulo(rs.getString("titulo"));
                res.setAutor(rs.getString("autor"));
                res.setPreco(rs.getDouble("preco"));
                res.setAno(rs.getInt("ano"));
                res.setDescricao(rs.getString("descricao"));
                res.setEditora(rs.getString("editora"));
                res.setEstoque(rs.getInt("estoque"));
                res.setReserva(rs.getInt("reserva"));
                res.setGenero_id(rs.getInt("genero_id"));
                return res;
            } else {
                return null;
            }
        } catch (SQLException sql1) {
            System.out.println("Erro LivroDAO: " + sql1);
            return null;
        }
    }

    public LivroTO mostratabela() {
        try {

            DatabaseMetaData meta = con.getMetaData();
            try (ResultSet res = meta.getTables(null, null, null,
                    new String[]{"TABLE"})) {
                System.out.println("List of tables: ");
                while (res.next()) {
                    System.out.println(
                            "   " + res.getString("TABLE_CAT")
                            + ", " + res.getString("TABLE_SCHEM")
                            + ", " + res.getString("TABLE_NAME")
                            + ", " + res.getString("TABLE_TYPE")
                            + ", " + res.getString("REMARKS"));
                }
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void finalize() {
        try {
            this.con.close();
        } catch (Exception e2) {
            System.out.println("Erro LivroDAO: " + e2);
        }
    }

}
