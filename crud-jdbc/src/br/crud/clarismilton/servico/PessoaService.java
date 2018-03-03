package br.crud.clarismilton.servico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.crud.clarismilton.entidades.Pessoa;
import br.crud.clarismilton.persistencia.PessoaDAO;

public class PessoaService {
	
	PessoaDAO dao;
	
	public PessoaService() {
		dao = new PessoaDAO();
	}
	
	public void inserir(Pessoa pessoa) throws SQLException{
		dao.inserir(pessoa);
	}
	
	public void alterar(Pessoa pessoa) throws SQLException{
		dao.alterar(pessoa);
	}
	
	public void excluir(Pessoa pessoa) throws SQLException{
		dao.excluir(pessoa);
	}
	
	public ArrayList<Pessoa> listar() throws SQLException{
		ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		ResultSet rset = this.dao.listar();
		
		while (rset.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rset.getInt("ID"));
			pessoa.setNome(rset.getString("NOME"));
			pessoa.setProfissao(rset.getString("Profissao"));
			
			listaPessoa.add(pessoa);
		}
		return listaPessoa;
	}

}
