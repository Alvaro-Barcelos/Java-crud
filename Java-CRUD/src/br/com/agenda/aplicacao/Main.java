package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Ricardo damiao");
        contato.setIdade(41);
        contato.setDataCadastro(new Date());

        //contatoDAO.save(contato);

        //Atualizar o contato.
        Contato c1 = new Contato();
        c1.setNome("Ricardo Damiao Nunes");
        c1.setIdade(49);
        c1.setDataCadastro(new Date());
        c1.setId(6); //Numero que esta no banco de dados

        //contatoDAO.update(c1);

        //Deletar o contato pelo seu número de ID
        //contatoDAO.deleteById(3);


        //Visualização dos registros do banco de dados TODOS

        for (Contato c : contatoDAO.getContatos()){

            System.out.println("=============================");
            System.out.println("Contato: " + c.getNome());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Data de Cadastro: " + c.getDataCadastro());
        }
    }
}
