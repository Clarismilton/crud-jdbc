package br.crud.clarismilton.gui;

//Importação da Classe que define Layout dos paineis

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.crud.clarismilton.entidades.Pessoa;
import br.crud.clarismilton.servico.PessoaService;

public class PessoaView extends JFrame implements ActionListener, ListSelectionListener{
	
	private JPanel jpUm;
	private JPanel jpDois;
	private JPanel jpTres;
	private JPanel jpQuatro;
	private JPanel jpPrincipal;
	
	private JLabel jlbPessoa;
	private JTextField jtfId;
	private JTextField jtfNome;
	
	private JLabel jlbProfissao;
	private JTextField jtfProfissao;
	
	private JButton jbtSalvar;
	private JButton jbtCancelar;
	private JButton jbtLimpar;
	private JButton jbtAlterar;
	private JButton jbtExcluir;
	
	private JTable jTabelaPessoa;
	private JScrollPane jspPessoa;
	
	public PessoaView(){
		
		//Chamando construtor da Classe mï¿½e (JFrame) passando o tï¿½tulo da janela como parï¿½metro
		
		super("Cadastro de Pessoas");
		
		//Pegando o Container de JFrame para nele lanï¿½ar os componentes grï¿½ficos na janela.
		
		Container c = this.getContentPane();
		
		//Instanciando os Objetos Grï¿½ficos
		
		jpUm = new JPanel(new FlowLayout());
		jpDois = new JPanel(new FlowLayout());
		jpTres = new JPanel(new FlowLayout());
		jpQuatro = new JPanel(new BorderLayout());
		jpPrincipal = new JPanel(new BorderLayout());
		
		this.jlbPessoa = new JLabel("Nome da Pessoa");
		this.jtfId = new JTextField(3);
		this.jtfId.setEditable(false);
		this.jtfNome = new JTextField(30);
		
		this.jlbProfissao =  new JLabel("Profissao");
		this.jtfProfissao = new JTextField(20);
		
		this.atualizaDadosTabela();
		this.definirTamanhoColunas();
		
		this.jspPessoa = new JScrollPane(jTabelaPessoa);
		this.jspPessoa.setViewportView(jTabelaPessoa);
		this.jTabelaPessoa.getSelectionModel().addListSelectionListener(this);
		
		this.jbtSalvar = new JButton("Salvar");
		this.jbtSalvar.setIcon(new ImageIcon("imagens/btnGravar.png"));
		this.jbtSalvar.addActionListener(this);
		this.jbtLimpar = new JButton("Limpar");
		this.jbtLimpar.setIcon(new ImageIcon("imagens/btnLimpar.png"));
		this.jbtLimpar.addActionListener(this);
		this.jbtCancelar = new JButton("Sair");
		this.jbtCancelar.setIcon(new ImageIcon("imagens/btnFechar.png"));
		this.jbtCancelar.addActionListener(this);
		this.jbtAlterar = new JButton("Alterar");
		this.jbtAlterar.setIcon(new ImageIcon("imagens/btnAlterar.png"));
		this.jbtAlterar.addActionListener(this);
		this.jbtExcluir = new JButton("Excluir");
		this.jbtExcluir.setIcon(new ImageIcon("imagens/btnExcluir.png"));
		this.jbtExcluir.addActionListener(this);
		
		this.jpUm.add(jlbPessoa, FlowLayout.LEFT);
		this.jpUm.add(jtfId);
		this.jpUm.add(jtfNome);
		
		this.jpDois.add(jlbProfissao);
		this.jpDois.add(jtfProfissao);
		
		this.jpTres.add(jbtSalvar, FlowLayout.LEFT);
		this.jpTres.add(jbtAlterar);
		this.jpTres.add(jbtLimpar);
		this.jpTres.add(jbtExcluir);
		this.jpTres.add(jbtCancelar);
		
		this.jpQuatro.add(jpDois, BorderLayout.NORTH);
		this.jpQuatro.add(jspPessoa, BorderLayout.CENTER);
		
		this.jpPrincipal.add(jpUm, BorderLayout.NORTH);
		this.jpPrincipal.add(jpQuatro, BorderLayout.CENTER);
		this.jpPrincipal.add(jpTres, BorderLayout.SOUTH);
		
		c.add(jpPrincipal);
		
		this.setVisible(true);
		this.setSize(600, 700);
		this.centerContainer(this);
		
		//this.CoresAlternadas(null);

	}

    public static void centerContainer(Container container) {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int componentWidth = container.getWidth();
        int componentHeight = container.getHeight();
        container.setBounds((screenSize.width-componentWidth)/2, (screenSize.height-componentHeight)/2, componentWidth, componentHeight);
    }
    
	public static void main(String[] args) {
		PessoaView gui = new PessoaView();
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		
	PessoaService service = new PessoaService();
		
		if((JButton) evento.getSource() == jbtSalvar) {
			try{
				
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(this.jtfNome.getText());
				pessoa.setProfissao(this.jtfProfissao.getText());
				service.inserir(pessoa);			
				
				//service.inserir(getDadosPessoa("incluir")); //Estï¿½ dando erro ao salvar um registro.
				
				JOptionPane.showMessageDialog(null, "Pessoa Cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar pessoa! Tente novamente \n"
						+ "Se o problema peristir entre em contato com o administrador do sistema. "
						+ "\n\n Erro: \n" + e.getMessage(), 
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			this.atualizaDadosTabela();
			
			// Nï¿½o consegui atualizar a tabela depois de salvar um novo registro entï¿½o utilizei este recurso:
			
			this.dispose();
			PessoaView gui = new PessoaView();
			
		}else if((JButton) evento.getSource() == jbtLimpar) {
			this.jtfProfissao.setText("");
			this.jtfNome.setText("");
			this.jtfId.setText("");
			this.jtfNome.requestFocus();
		}else if((JButton) evento.getSource() == jbtAlterar) {
			try{
				service.alterar(getDadosPessoa("alterar"));
				JOptionPane.showMessageDialog(null, "Pessoa Alterado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar pessoa! Tente novamente \n"
						+ "Se o problema persistir entre em contato com o administrador do sistema. ", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}
			
			this.atualizaDadosTabela();
			
			// Nï¿½o consegui atualizar a tabela depois de salvar um novo registro entï¿½o utilizei este recurso:
			
			this.dispose();
			PessoaView gui = new PessoaView();
			
		}else if((JButton) evento.getSource() == jbtExcluir) {
			try{
				service.excluir(getDadosPessoa("excluir"));
				JOptionPane.showMessageDialog(null, "Pessoa Excluï¿½do com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao Excluir o pessoa! Tente novamente \n"
						+ "Se o problema persistir entre em contato com o administrador do sistema. ", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}
			
			this.atualizaDadosTabela();
			
			// Nï¿½o consegui atualizar a tabela depois de salvar um novo registro entï¿½o utilizei este recurso:
			
			this.dispose();
			PessoaView gui = new PessoaView();			
			
		}else if((JButton) evento.getSource() == jbtCancelar) {
			this.dispose();
		}
		
	}

	public Pessoa getDadosPessoa(String operacao) {
		Pessoa pessoa = new Pessoa();
		if(this.jtfId.equals("") && operacao.equals("alterar")) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado!",
					"ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}else {
			pessoa.setId(Integer.parseInt(jtfId.getText()));
			if(this.jtfNome.equals("")) {
				JOptionPane.showMessageDialog(null, "O Campo 'Nome' nï¿½o pode ficar em branco:",
						"ATENï¿½ï¿½O", JOptionPane.WARNING_MESSAGE);
				this.jtfNome.setFocusable(true);
				return null;
			}else {
				pessoa.setNome(this.jtfNome.getText());
				pessoa.setProfissao(this.jtfProfissao.getText());
				return pessoa;
			}
		}
	}
		
	private String[] getColunas() {
		String[] colunas = new String[3];
		colunas[0] = "Id";
		colunas[1] = "Nome";
		colunas[2] = "Profissï¿½o";
		return colunas;
 	}
	
	private void atualizaDadosTabela() {
		this.jTabelaPessoa = new  JTable();
		this.jTabelaPessoa.getTableHeader().setReorderingAllowed(true);
		this.jTabelaPessoa.setModel(new DefaultTableModel(new Object[][] {}, getColunas()));
		this.jTabelaPessoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel dtm = (DefaultTableModel)jTabelaPessoa.getModel();
		
		this.jspPessoa = new JScrollPane(this.jTabelaPessoa);

		Pessoa pessoa = new Pessoa();
		
		PessoaService service = new PessoaService();
				
		ArrayList<Pessoa> listaPessoa;
		
		
		try {
			listaPessoa = service.listar();
			Iterator<Pessoa> it = listaPessoa.iterator();
			 while (it.hasNext()) {
				 pessoa = it.next();
				 dtm.addRow(new Object[] {pessoa.getId(), pessoa.getNome(), pessoa.getProfissao()});
			 }
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar pessoas! Tente novamente \n"
					+ "Se o problema persistir entre em contato com o administrador do sistema. "
					+ "\n\n Erro: \n" + e.getMessage(), 
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void definirTamanhoColunas() {

		DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
		rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
		rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
		
		TableColumnModel modeloDaColuna = this.jTabelaPessoa.getColumnModel();  

	    modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);  
	    modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda); 
	    modeloDaColuna.getColumn(2).setCellRenderer(rendererEsquerda);

	    modeloDaColuna.getColumn(0).setMaxWidth(50);  
	    modeloDaColuna.getColumn(1).setMaxWidth(260);
	    modeloDaColuna.getColumn(2).setMaxWidth(260);

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		DefaultTableModel dtm = (DefaultTableModel)jTabelaPessoa.getModel();
		
		this.jtfId.setText(dtm.getValueAt(this.jTabelaPessoa.getSelectedRow(),0).toString());
		this.jtfNome.setText(dtm.getValueAt(this.jTabelaPessoa.getSelectedRow(),1).toString());
		this.jtfProfissao.setText(dtm.getValueAt(this.jTabelaPessoa.getSelectedRow(),2).toString());	
	}

	
	//Tentando colocar cores alternadas na JTable
	
	private JComponent CoresAlternadas(DefaultTableModel defaultTableModel) {
		JTable jTabelaPessoa = new JTable(defaultTableModel) {
			@Override
			public Component prepareRenderer (TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer,  row, column);
				component.setBackground(Color.GREEN);
				return component;
			}
		};
		return new JScrollPane(jTabelaPessoa);
	}

	
}
