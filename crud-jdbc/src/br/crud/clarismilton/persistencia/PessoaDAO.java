package br.crud.clarismilton.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.crud.clarismilton.conexao.ConnectionFactory;
import br.crud.clarismilton.entidades.Pessoa;

public class PessoaDAO {
	
	Connection conexao;
	
	public PessoaDAO() {
		conexao = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Pessoa pessoa) throws SQLException{
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into pessoa (nome, profissao) values (?, ?)");
		PreparedStatement statement = this.conexao.prepareStatement(sql.toString());
		int i = 1;
		statement.setString(i++,pessoa.getNome());
		statement.setString(i++,pessoa.getProfissao());
		
		statement.executeUpdate();	
		
	}
	
	public void alterar(Pessoa pessoa) throws SQLException{
		
		StringBuffer sql = new StringBuffer();
		sql.append("update pessoa set nome = ?, profissao = ? where id = ?");
		PreparedStatement statement = this.conexao.prepareStatement(sql.toString());
		int i = 1;
		statement.setString(i++,pessoa.getNome());
		statement.setString(i++,pessoa.getProfissao());
		statement.setInt(i++,pessoa.getId());
		
		statement.executeUpdate();	
		
	}

	public void excluir(Pessoa pessoa) throws SQLException{

		StringBuffer sql = new StringBuffer();
		
		sql.append("delete from pessoa where id = ?");
		PreparedStatement statement = this.conexao.prepareStatement(sql.toString());
		int i = 1;
		statement.setInt(i++,pessoa.getId());
		
		statement.executeUpdate();
		
	}
	
	public ResultSet listar() throws SQLException{
		StringBuffer sql= new StringBuffer();
		sql.append("select * from pessoa");
		
		PreparedStatement statement = this.conexao.prepareStatement(sql.toString());
		
		ResultSet rset = statement.executeQuery();
		
		return rset;
		
	}

}
