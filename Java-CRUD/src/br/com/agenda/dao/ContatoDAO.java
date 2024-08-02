package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {


    /*
    ============
        CRUD
    ============
    C: CREATE - OK - INSERT
    R: READ   - OK - SELECT
    U: UPDATE - OK - UPDATE
    D: DELETE -  - DELETE
     */

    public void save(Contato contato){

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES(?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            //Criar uma conexao com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();

            //Criamos uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Executar a query
            pstm.execute();

            System.out.println("Contato salvo com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {

            //Fechar as conexões
            try{
                if (pstm!=null){
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void deleteById(int id){

        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try{

            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public List<Contato> getContatos(){

        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco.
        ResultSet rset = null;

        try{
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()){

                Contato contato = new Contato();

                //Recuperar o id
                contato.setId(rset.getInt("id"));
                //Recuperar o nome
                contato.setNome(rset.getString("nome"));
                //Recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                //recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (rset!=null){
                    conn.close();
                }

                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        return contatos;
    }

    public void update(Contato contato){
        String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? "+
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            //Criar conexao com o banco
            conn = ConnectionFactory.createConnectionToMySql();

            //Criar a classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para ataualizar
            pstm.setString(1,contato.getNome());
            pstm.setInt(2,contato.getIdade());
            pstm.setDate(3,new Date(contato.getDataCadastro().getTime()));

            //Qual p id do registro que deseja atualizar?
            pstm.setInt(4, contato.getId());

            pstm.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (pstm!=null){
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
