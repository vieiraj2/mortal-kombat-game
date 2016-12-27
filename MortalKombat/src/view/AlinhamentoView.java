package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.AlinhamentoListener;
import model.dao.AlinhamentoDAO;
import model.entity.Alinhamento;

import static view.AlinhamentoView.CodigoAli;
import static view.AlinhamentoView.NomeAli;
import static view.AlinhamentoView.table_ViewAli;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlinhamentoView extends JFrame {
	
	private static AlinhamentoDAO daoAli;
	private static AcessoBD dbman;

	private JPanel bg_Ali;
	public static JTextField CodigoAli;
	public static JTextField NomeAli;
	public static JComboBox comboBox_SelectAli;
	public static JTable table_ViewAli;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlinhamentoView frame = new AlinhamentoView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlinhamentoView() throws SQLException {
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de alinhamentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		bg_Ali = new JPanel();
		bg_Ali.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Ali);
		bg_Ali.setLayout(null);
		
		AlinhamentoListener al = new AlinhamentoListener();
		
		JLabel logo_ali = new JLabel("");
		logo_ali.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_ali.setBounds(199, 32, 226, 39);
		bg_Ali.add(logo_ali);
		
		JLabel lblCrudAlinhamento = new JLabel("CRUD - Alinhamento");
		lblCrudAlinhamento.setForeground(Color.WHITE);
		lblCrudAlinhamento.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudAlinhamento.setBounds(239, 73, 286, 21);
		bg_Ali.add(lblCrudAlinhamento);
		
		JButton btnCarregarAlinhamentos = new JButton("Carregar alinhamentos");
		btnCarregarAlinhamentos.addActionListener(al);
		btnCarregarAlinhamentos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarAlinhamentos.setBounds(340, 220, 350, 25);
		bg_Ali.add(btnCarregarAlinhamentos);
		
		dbman = new AcessoBD();
		daoAli = new AlinhamentoDAO(dbman.getCon());
		
		List<Alinhamento> listal = daoAli.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (Alinhamento ali : listal) {
			vetor.addElement(ali.getNomeAlinhamento());
		}
		
		comboBox_SelectAli = new JComboBox(vetor);
		comboBox_SelectAli.setBounds(80, 255, 248, 22);
		bg_Ali.add(comboBox_SelectAli);
		
		JLabel label_1 = new JLabel("Selecione:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(12, 261, 68, 16);
		bg_Ali.add(label_1);
		
		JLabel label_2 = new JLabel("C\u00F3digo:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(12, 293, 56, 16);
		bg_Ali.add(label_2);
		
		CodigoAli = new JTextField();
		CodigoAli.setEnabled(false);
		CodigoAli.setColumns(10);
		CodigoAli.setBounds(80, 290, 45, 22);
		bg_Ali.add(CodigoAli);
		
		NomeAli = new JTextField();
		NomeAli.setColumns(10);
		NomeAli.setBounds(80, 323, 248, 22);
		bg_Ali.add(NomeAli);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(12, 326, 56, 16);
		bg_Ali.add(label_3);
		
		JButton btnCadastrarAli = new JButton("Cadastrar");
		btnCadastrarAli.addActionListener(al);
		btnCadastrarAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		btnCadastrarAli.setBounds(80, 372, 128, 25);
		bg_Ali.add(btnCadastrarAli);
		
		JButton btnEditarAli = new JButton("Editar");
		btnEditarAli.addActionListener(al);
		btnEditarAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		btnEditarAli.setBounds(210, 372, 118, 25);
		bg_Ali.add(btnEditarAli);
		
		JButton btnRemoverAli = new JButton("Remover");
		btnRemoverAli.addActionListener(al);
		btnRemoverAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		btnRemoverAli.setBounds(80, 399, 128, 25);
		bg_Ali.add(btnRemoverAli);
		
		JButton btnLimparAli = new JButton("Limpar");
		btnLimparAli.addActionListener(al);
		btnLimparAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		btnLimparAli.setBounds(210, 399, 118, 25);
		bg_Ali.add(btnLimparAli);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 258, 350, 169);
		bg_Ali.add(scrollPane);
		
		table_ViewAli = new JTable();
		scrollPane.setViewportView(table_ViewAli);
		
		JLabel label_ali = new JLabel("");
		label_ali.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		label_ali.setBounds(63, 0, 200, 200);
		bg_Ali.add(label_ali);
		
		JLabel bg_alinhamento = new JLabel("");
		bg_alinhamento.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bg_alinhamento.setBounds(0, 0, 736, 553);
		bg_Ali.add(bg_alinhamento);
	}
}
