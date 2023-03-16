package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import persistence.DAO;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.text.ParseException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.Choice;
import java.awt.Color;

public class CadastroConsulta extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField email;
	private JTextField endereco;
	private JTextField produto;
	private JTextField preco;
	private JTextField descricao;
	private JTextField codigo;
	private JComboBox unidade;
	private JFormattedTextField cpf;
	private JFormattedTextField celular;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraCelular;
	private JTable tblProdutos;
	private JTextField txtIdCliente;
	private JTable tblClientes;
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	JLabel lblFoto = new JLabel("Foto");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConsulta frame = new CadastroConsulta();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public CadastroConsulta() {
		conexao = DAO.conector();
		try {
			MaskFormatter mascaraCpf = new MaskFormatter ("###.###.###-##");
			MaskFormatter mascaraCelular = new MaskFormatter ("(##) #####-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 521);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ajuda");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Clientes");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Produtos");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Sistema");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sobre o Sistema");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sair");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			}
		});
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 638, 459);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Produtos", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(6, 11, 61, 16);
		panel_1.add(lblProduto);
		
		//Evento para preencher a tabela digitando em tempo real
		produto = new JTextField();
		produto.setEnabled(false);
		produto.setEditable(false);
		produto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//pesquisarProduto();
			}
		});
		
		produto.setColumns(10);
		produto.setBounds(103, 6, 307, 26);
		panel_1.add(produto);
		
		JLabel lblQuantidade = new JLabel("Código");
		lblQuantidade.setBounds(6, 52, 95, 16);
		panel_1.add(lblQuantidade);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(6, 95, 61, 16);
		panel_1.add(lblUnidade);
		
		JLabel lblPreco = new JLabel("Preco");
		lblPreco.setBounds(6, 136, 61, 16);
		panel_1.add(lblPreco);
		
		preco = new JTextField();
		preco.setEnabled(false);
		preco.setEditable(false);
		preco.setColumns(10);
		preco.setBounds(103, 131, 307, 26);
		panel_1.add(preco);
		
		JLabel lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(6, 180, 95, 16);
		panel_1.add(lblDescricao);
		
		JButton btnLimparProduto = new JButton("");
		btnLimparProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaTelaProduto();
			}
		});
		btnLimparProduto.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/limpar-limpo-2.png")));
		btnLimparProduto.setBounds(11, 227, 73, 70);
		panel_1.add(btnLimparProduto);
		
		JButton btnPesquisarProduto = new JButton("");
		btnPesquisarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPesquisarProduto.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/arquivo-de-documento.png")));
		btnPesquisarProduto.setBounds(538, 227, 73, 70);
		panel_1.add(btnPesquisarProduto);
		
		descricao = new JTextField();
		descricao.setEnabled(false);
		descricao.setEditable(false);
		descricao.setColumns(10);
		descricao.setBounds(103, 175, 307, 26);
		panel_1.add(descricao);
		
		codigo = new JTextField();
		codigo.addKeyListener(new KeyAdapter() {
			
			
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProduto();
			}
		});
		codigo.setColumns(10);
		codigo.setBounds(103, 47, 307, 26);
		panel_1.add(codigo);
		
		unidade = new JComboBox();
		unidade.setEnabled(false);
		unidade.setModel(new DefaultComboBoxModel(new String[] {"Kilo", "Unidade", "Saco", "Gramas", "Pote", "Duzia", "Litro", "ml"}));
		unidade.setBounds(103, 91, 307, 27);
		panel_1.add(unidade);
		
		//evento mouse clicked, quando seleciona linha da tabela, preenche os campos automaticamente
		tblProdutos = new JTable();
		tblProdutos.addMouseListener(new MouseAdapter() {
	
		});
		
		tblProdutos.setBounds(16, 309, 595, 98);
		panel_1.add(tblProdutos);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(383, 44, 1, 1);
		panel_1.add(desktopPane);
		
		//JLabel lblFoto = new JLabel("Foto");
		lblFoto.setForeground(Color.BLACK);
		lblFoto.setBackground(Color.RED);
		lblFoto.setBounds(422, 11, 189, 185);
		panel_1.add(lblFoto);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Clientes", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(6, 11, 61, 16);
		panel.add(lblNome);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(6, 52, 61, 16);
		panel.add(lblCelular);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(6, 95, 61, 16);
		panel.add(lblEmail);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setBounds(6, 136, 61, 16);
		panel.add(lblEndereco);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(6, 180, 61, 16);
		panel.add(lblCpf);
		try {
			cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cpf.setBounds(103, 175, 508, 26);
		panel.add(cpf);
		try {
			celular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		celular.setBounds(103, 47, 508, 26);
		panel.add(celular);
		
		nome = new JTextField();
		nome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		nome.setBounds(103, 6, 346, 26);
		panel.add(nome);
		nome.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(103, 90, 508, 26);
		panel.add(email);
		
		endereco = new JTextField();
		endereco.setColumns(10);
		endereco.setBounds(103, 131, 508, 26);
		panel.add(endereco);
		
		JButton btnSalvarCliente = new JButton("");
		btnSalvarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		btnSalvarCliente.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/salvar-2.png")));
		btnSalvarCliente.setBounds(145, 227, 73, 70);
		panel.add(btnSalvarCliente);
		
		JButton btnLimparCliente = new JButton("");
		btnLimparCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLimparCliente.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/limpar-limpo-2.png")));
		btnLimparCliente.setBounds(11, 227, 73, 70);
		panel.add(btnLimparCliente);
		
		JButton btnExcluirCliente = new JButton("");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluirCliente.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/lixo.png")));
		btnExcluirCliente.setBounds(410, 227, 73, 70);
		panel.add(btnExcluirCliente);
		
		JButton btnAlterarCliente = new JButton("");
		btnAlterarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterarCliente.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/lapis.png")));
		btnAlterarCliente.setBounds(277, 227, 73, 70);
		panel.add(btnAlterarCliente);
		
		JButton btnPesquisarCliente = new JButton("");
		btnPesquisarCliente.setIcon(new ImageIcon(CadastroConsulta.class.getResource("/Imagens/arquivo-de-documento.png")));
		btnPesquisarCliente.setBounds(538, 227, 73, 70);
		panel.add(btnPesquisarCliente);
		
		JLabel lblIdCliente = new JLabel("Id");
		lblIdCliente.setBounds(461, 11, 61, 16);
		panel.add(lblIdCliente);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setEnabled(false);
		txtIdCliente.setEditable(false);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBounds(481, 6, 130, 26);
		panel.add(txtIdCliente);
		
		tblClientes = new JTable();
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		tblClientes.setBounds(6, 304, 605, 103);
		panel.add(tblClientes);
	}

	//metodo para preencher automaticamente a jtable com like do que for digitado no campo produto - funciona junto com o evento de key do campo produto
	private void pesquisarProduto() {
		String sql = "select * from produtos where codigo = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, codigo.getText());
			rs = pst.executeQuery();
			
			if(rs.first()) {
				//JOptionPane.showMessageDialog(null, "Entrou aqui");
				produto.setText(rs.getString("nome"));
				preco.setText(rs.getString("preco"));
				descricao.setText(rs.getString("descricao"));
				unidade.setSelectedItem(rs.getString("unidade"));
				
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs.getString("foto")).getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));
				lblFoto.setIcon(imageIcon);
				
				
			}else {
				//JOptionPane.showMessageDialog(null, "Nao entrou ");
				limpaTelaProdutoMantemCodigo();
				produto.setText(null);
			}
			//tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	private void limpaTelaProduto() {
		produto.setText(null);
		preco.setText(null);
		codigo.setText(null);
		descricao.setText(null);
		lblFoto.setIcon(null);
	}
	
	private void limpaTelaProdutoMantemCodigo() {
		produto.setText(null);
		preco.setText(null);
		descricao.setText(null);
		lblFoto.setIcon(null);
	}

	

}
