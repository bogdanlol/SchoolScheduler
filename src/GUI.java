import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import org.pdfbox.*;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class GUI {

	private JFrame frame;
	private Connections con=new Connections();
	private JTextField idStudField;
	private JTextField numeStudField;
	private JTextField codFormatieStudField;
	private JTextField subgrupaStudField;
	private JTextField codDiscField;
	private JTextField denumireDiscField;
	private JTextField anDiscField;
	private JTextField semestruDiscField;
	private JTextField idProfField;
	private JTextField numeProfField;
	private JTextField titluProfField;
	private JTextField nrCursField;
	private JTextField nrSeminarField;
	private JTextField nrLabField;
	private JTextField nrProjField;
	private JTextField codSalaField;
	private JTextField numeSalaField;
	private JTextField capacitateSalaField;
	private JTextField tipSalaField;
	private JTextField grupaStudField;
	private JTextField anStudField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 884, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 13, 871, 552);
		frame.getContentPane().add(tabbedPane);
		
		
		JPanel TabStudenti = new JPanel();
		tabbedPane.addTab("Studenti", null, TabStudenti, null);
		TabStudenti.setLayout(null);
		
		JScrollPane scrollPaneStud = new JScrollPane();
		scrollPaneStud.setBounds(204, 33, 476, 449);
		TabStudenti.add(scrollPaneStud);
		
		AfisareStudenti AfisareStud = new AfisareStudenti();
		scrollPaneStud.setColumnHeaderView(AfisareStud);
		FlowLayout flowLayout = (FlowLayout) AfisareStud.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		
		JButton btnInserareStud = new JButton("Inserare");
		btnInserareStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("INSERT INTO student VALUES(NULL,"+"'"+codFormatieStudField.getText()+"'"+",'"+numeStudField.getText()+"'"+",'"+grupaStudField.getText()+"'"+",'"+subgrupaStudField.getText()+"'"+",'"+anStudField.getText()+"')");
				con.Update("INSERT INTO student VALUES(NULL,"+"'"+codFormatieStudField.getText()+"'"+",'"+numeStudField.getText()+"'"+",'"+grupaStudField.getText()+"'"+",'"+subgrupaStudField.getText()+"'"+",'"+anStudField.getText()+"')");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
			}
		});
		btnInserareStud.setBounds(40, 272, 128, 23);
		TabStudenti.add(btnInserareStud);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document document = new Document();
					try {
			    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("stud.pdf"));
			    document.open();
			    PdfContentByte contentByte = writer.getDirectContent();
			    PdfTemplate template = contentByte.createTemplate(10000, 10000);
			    Graphics2D g2 = template.createGraphics(AfisareStud.getWidth(), AfisareStud.getHeight());
			    g2.scale(0.8, 1);	
			    AfisareStud.printAll(g2);
			    
			    g2.dispose();
			    AfisareStud.addNotify();
			    AfisareStud.setSize(5000, 5000);
			    AfisareStud.validate();
			    contentByte.addTemplate(template, 5, 60);
			} catch (Exception e1) {
			    e1.printStackTrace();
			}
			finally{
			    if(document.isOpen()){
			        document.close();
			    }
			}

		    }
			
		});
		btnPdf.setBounds(40, 402, 128, 23);
		TabStudenti.add(btnPdf);
		
		
		
		JButton btnModificareStud = new JButton("Modificare");
		btnModificareStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("UPDATE student SET "+"codFormatie="+"'"+codFormatieStudField.getText()+"'"+","+"nume_st="+"'"+numeStudField.getText()+"'"+","+"grupa="+"'"+grupaStudField.getText()+"',"+"subgrupa="+"'"+subgrupaStudField.getText()+"' " + "WHERE id_student="+"'"+idStudField.getText()+"'");
				con.Update("UPDATE student SET "+"codFormatie="+"'"+codFormatieStudField.getText()+"'"+","+"nume_st="+"'"+numeStudField.getText()+"'"+","+"grupa="+"'"+grupaStudField.getText()+"',"+"subgrupa="+"'"+subgrupaStudField.getText()+"' " + "WHERE id_student="+"'"+idStudField.getText()+"'");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
			}
		});
		btnModificareStud.setBounds(40, 306, 128, 23);
		TabStudenti.add(btnModificareStud);
		
		JButton btnStergereStud = new JButton("Stergere");
		btnStergereStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DELETE FROM student WHERE id_student="+"'"+idStudField.getText()+"'");
				con.Update("DELETE FROM student WHERE id_student="+"'"+idStudField.getText()+"'");			
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
			}
		});
		btnStergereStud.setBounds(40, 340, 128, 23);
		TabStudenti.add(btnStergereStud);
		
		idStudField = new JTextField();
		idStudField.setBounds(97, 44, 86, 20);
		TabStudenti.add(idStudField);
		idStudField.setColumns(10);
		
		numeStudField = new JTextField();
		numeStudField.setBounds(97, 106, 86, 20);
		TabStudenti.add(numeStudField);
		numeStudField.setColumns(10);
		
		codFormatieStudField = new JTextField();
		codFormatieStudField.setBounds(97, 75, 86, 20);
		TabStudenti.add(codFormatieStudField);
		codFormatieStudField.setColumns(10);
		
		subgrupaStudField = new JTextField();
		subgrupaStudField.setBounds(97, 168, 86, 20);
		TabStudenti.add(subgrupaStudField);
		subgrupaStudField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(25, 50, 62, 14);
		TabStudenti.add(lblId);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(25, 112, 62, 14);
		TabStudenti.add(lblNume);
		
		JLabel lblCodFormatie = new JLabel("Cod Formatie");
		lblCodFormatie.setBounds(25, 81, 73, 14);
		TabStudenti.add(lblCodFormatie);
		
		JLabel lblSubgrupa = new JLabel("Subgrupa");
		lblSubgrupa.setBounds(25, 174, 62, 14);
		TabStudenti.add(lblSubgrupa);
		
		JPanel TabDiscipline = new JPanel();
		tabbedPane.addTab("Discipline", null, TabDiscipline, null);
		TabDiscipline.setLayout(null);
		
		JScrollPane scrollPaneDisc = new JScrollPane();
		scrollPaneDisc.setBounds(203, 31, 479, 445);
		TabDiscipline.add(scrollPaneDisc);
		
		AfisareDiscipline AfisareDisc = new AfisareDiscipline();
		scrollPaneDisc.setColumnHeaderView(AfisareDisc);
		FlowLayout flowLayoutDisc = (FlowLayout) AfisareDisc.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
	
		
		
		JLabel labelCodDisc = new JLabel("Cod");
		labelCodDisc.setBounds(23, 40, 62, 14);
		TabDiscipline.add(labelCodDisc);
		
		codDiscField = new JTextField();
		codDiscField.setColumns(10);
		codDiscField.setBounds(95, 34, 86, 20);
		TabDiscipline.add(codDiscField);
		
		JLabel labelNumeDisc = new JLabel("Denumire");
		labelNumeDisc.setBounds(23, 68, 62, 14);
		TabDiscipline.add(labelNumeDisc);
		
		denumireDiscField = new JTextField();
		denumireDiscField.setColumns(10);
		denumireDiscField.setBounds(95, 62, 86, 20);
		TabDiscipline.add(denumireDiscField);
		
		JLabel labelAnDisc = new JLabel("Anul");
		labelAnDisc.setBounds(23, 93, 62, 14);
		TabDiscipline.add(labelAnDisc);
		
		anDiscField = new JTextField();
		anDiscField.setColumns(10);
		anDiscField.setBounds(95, 87, 86, 20);
		TabDiscipline.add(anDiscField);
		
		JLabel labelSemestruDisc = new JLabel("Semestru");
		labelSemestruDisc.setBounds(23, 124, 62, 14);
		TabDiscipline.add(labelSemestruDisc);
		
		semestruDiscField = new JTextField();
		semestruDiscField.setColumns(10);
		semestruDiscField.setBounds(95, 118, 86, 20);
		TabDiscipline.add(semestruDiscField);
		
		JButton buttonInserareDisc = new JButton("Inserare");
		buttonInserareDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("INSERT INTO discipline VALUES(NULL,"+"'"+denumireDiscField.getText()+"'"+",'"+anDiscField.getText()+"'"+",'"+semestruDiscField.getText()+"'"+",'"+nrCursField.getText()+"'"+",'"+nrSeminarField.getText()+"'"+",'"+nrLabField.getText()+"'"+",'"+nrProjField.getText()+"')");
				con.Update("INSERT INTO discipline VALUES(NULL,"+"'"+denumireDiscField.getText()+"'"+",'"+anDiscField.getText()+"'"+",'"+semestruDiscField.getText()+"'"+",'"+nrCursField.getText()+"'"+",'"+nrSeminarField.getText()+"'"+",'"+nrLabField.getText()+"'"+",'"+nrProjField.getText()+"')");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
		});
		buttonInserareDisc.setBounds(37, 321, 128, 23);
		TabDiscipline.add(buttonInserareDisc);
		
		JButton buttonModificareDisc = new JButton("Modificare");
		buttonModificareDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("UPDATE discipline SET "+"numeDisc"+"="+"'"+denumireDiscField.getText()+"'"+","+"anDisc"+"="+"'"+anDiscField.getText()+"'"+","+"semestruDisc"+"="+"'"+semestruDiscField.getText()+"'"+","+"NrCurs"+"="+"'"+nrCursField.getText()+"'"+","+"NrSeminar"+"="+"'"+nrSeminarField.getText()+"'"+","+"NrLab"+"="+"'"+nrLabField.getText()+"'"+","+"NrProj"+"="+"'"+nrProjField.getText()+"' " + "WHERE codDisc="+"'"+codDiscField.getText()+"'");
				con.Update("UPDATE discipline SET "+"numeDisc"+"="+"'"+denumireDiscField.getText()+"'"+","+"anDisc"+"="+"'"+anDiscField.getText()+"'"+","+"semestruDisc"+"="+"'"+semestruDiscField.getText()+"'"+","+"NrCurs"+"="+"'"+nrCursField.getText()+"'"+","+"NrSeminar"+"="+"'"+nrSeminarField.getText()+"'"+","+"NrLab"+"="+"'"+nrLabField.getText()+"'"+","+"NrProj"+"="+"'"+nrProjField.getText()+"' " + "WHERE codDisc="+"'"+codDiscField.getText()+"'");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
		});
		buttonModificareDisc.setBounds(37, 355, 128, 23);
		TabDiscipline.add(buttonModificareDisc);
		
		JButton buttonStergereDisc = new JButton("Stergere");
		buttonStergereDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DELETE FROM discipline WHERE codDisc="+"'"+codDiscField.getText()+"'");
				con.Update("DELETE FROM discipline WHERE codDisc="+"'"+codDiscField.getText()+"'");			
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
		});
		buttonStergereDisc.setBounds(37, 389, 128, 23);
		TabDiscipline.add(buttonStergereDisc);
		
		JLabel lblNrCursDisc = new JLabel("Nr. Cursuri");
		lblNrCursDisc.setBounds(23, 155, 62, 14);
		TabDiscipline.add(lblNrCursDisc);
		
		nrCursField = new JTextField();
		nrCursField.setBounds(95, 149, 86, 20);
		TabDiscipline.add(nrCursField);
		nrCursField.setColumns(10);
		
		JLabel labelNrSeminar = new JLabel("Nr. Seminar");
		labelNrSeminar.setBounds(23, 190, 69, 14);
		TabDiscipline.add(labelNrSeminar);
		
		nrSeminarField = new JTextField();
		nrSeminarField.setColumns(10);
		nrSeminarField.setBounds(95, 184, 86, 20);
		TabDiscipline.add(nrSeminarField);
		
		JLabel lblNrLab = new JLabel("Nr. Lab");
		lblNrLab.setBounds(23, 221, 62, 14);
		TabDiscipline.add(lblNrLab);
		
		nrLabField = new JTextField();
		nrLabField.setColumns(10);
		nrLabField.setBounds(95, 215, 86, 20);
		TabDiscipline.add(nrLabField);
		
		JLabel lblNrProiect = new JLabel("Nr. Proiect");
		lblNrProiect.setBounds(23, 252, 62, 14);
		TabDiscipline.add(lblNrProiect);
		
		nrProjField = new JTextField();
		nrProjField.setColumns(10);
		nrProjField.setBounds(95, 246, 86, 20);
		TabDiscipline.add(nrProjField);
		
		JPanel TabProfesori = new JPanel();
		tabbedPane.addTab("Profesori", null, TabProfesori, null);
		TabProfesori.setLayout(null);
		
		JScrollPane scrollPaneProf = new JScrollPane();
		scrollPaneProf.setBounds(188, 36, 491, 449);
		TabProfesori.add(scrollPaneProf);
		
		AfisareProfesori AfisareProf = new AfisareProfesori();
		scrollPaneProf.setColumnHeaderView(AfisareProf);
		FlowLayout flowLayoutProf = (FlowLayout) AfisareProf.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		
		
		JLabel idProfLabel = new JLabel("ID");
		idProfLabel.setBounds(20, 42, 62, 14);
		TabProfesori.add(idProfLabel);
		
		idProfField = new JTextField();
		idProfField.setColumns(10);
		idProfField.setBounds(92, 36, 86, 20);
		TabProfesori.add(idProfField);
		
		JLabel numeProfLabel = new JLabel("Nume");
		numeProfLabel.setBounds(20, 79, 62, 14);
		TabProfesori.add(numeProfLabel);
		
		numeProfField = new JTextField();
		numeProfField.setColumns(10);
		numeProfField.setBounds(92, 73, 86, 20);
		TabProfesori.add(numeProfField);
		
		JLabel titluProfLabel = new JLabel("Titlu");
		titluProfLabel.setBounds(20, 122, 62, 14);
		TabProfesori.add(titluProfLabel);
		
		titluProfField = new JTextField();
		titluProfField.setColumns(10);
		titluProfField.setBounds(92, 116, 86, 20);
		TabProfesori.add(titluProfField);
		
		JButton btnInserareProf = new JButton("Inserare");
		btnInserareProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("INSERT INTO profesor VALUES(NULL,"+"'"+numeProfField.getText()+"'"+",'"+titluProfField.getText()+"')");
				con.Update("INSERT INTO profesor VALUES(NULL,"+"'"+numeProfField.getText()+"'"+",'"+titluProfField.getText()+"')");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);	
			}
		});
		btnInserareProf.setBounds(35, 269, 128, 23);
		TabProfesori.add(btnInserareProf);
		
		JButton btnModificareProf = new JButton("Modificare");
		btnModificareProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("UPDATE profesor SET "+"nume_prof="+"'"+numeProfField.getText()+"'"+","+"titlu="+"'"+titluProfField.getText()+"' " + "WHERE id_prof="+"'"+idProfField.getText()+"'");
				con.Update("UPDATE profesor SET "+"nume_prof="+"'"+numeProfField.getText()+"'"+","+"titlu="+"'"+titluProfField.getText()+"' " + "WHERE id_prof="+"'"+idProfField.getText()+"'");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
		});
		btnModificareProf.setBounds(35, 303, 128, 23);
		TabProfesori.add(btnModificareProf);
		
		JButton btnStergereProf = new JButton("Stergere");
		btnStergereProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("DELETE FROM profesor WHERE id_prof="+"'"+idProfField.getText()+"'");
				con.Update("DELETE FROM profesor WHERE id_prof="+"'"+idProfField.getText()+"'");			
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
			
		});
		btnStergereProf.setBounds(35, 337, 128, 23);
		TabProfesori.add(btnStergereProf);
		
		
		JPanel TabSali = new JPanel();
		tabbedPane.addTab("Sali", null, TabSali, null);
		TabSali.setLayout(null);
		
		JScrollPane scrollPaneSali = new JScrollPane();
		scrollPaneSali.setBounds(203, 31, 479, 445);
		TabSali.add(scrollPaneSali);
		
		AfisareSali AfisareSali = new AfisareSali();
		scrollPaneSali.setColumnHeaderView(AfisareSali);
		FlowLayout flowLayoutSali = (FlowLayout) AfisareSali.getLayout();
		
		JButton btnInserareSali = new JButton("Inserare");
		btnInserareSali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("INSERT INTO sali VALUES(NULL,"+"'"+numeSalaField.getText()+"'"+",'"+capacitateSalaField.getText()+"',"+"'"+tipSalaField.getText()+"')");
				con.Update("INSERT INTO sali VALUES(NULL,"+"'"+numeSalaField.getText()+"'"+",'"+capacitateSalaField.getText()+"',"+"'"+tipSalaField.getText()+"')");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);	
				
			}
		});
		btnInserareSali.setBounds(44, 331, 128, 23);
		TabSali.add(btnInserareSali);
		
		JButton btnModificareSali = new JButton("Modificare");
		btnModificareSali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("UPDATE sali SET "+"numeSala="+"'"+numeSalaField.getText()+"'"+","+"capacitateSala="+"'"+capacitateSalaField.getText()+"',"+"tip="+"'"+tipSalaField.getText()+"' " + "WHERE codSala="+"'"+codSalaField.getText()+"'");
				con.Update("UPDATE sali SET "+"numeSala="+"'"+numeSalaField.getText()+"'"+","+"capacitateSala="+"'"+capacitateSalaField.getText()+"',"+"tip="+"'"+tipSalaField.getText()+"' " + "WHERE codSala="+"'"+codSalaField.getText()+"'");
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
				
			}
		});
		btnModificareSali.setBounds(44, 365, 128, 23);
		TabSali.add(btnModificareSali);
		
		JButton btnStergereSali = new JButton("Stergere");
		btnStergereSali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("DELETE FROM sali WHERE codSala="+"'"+codSalaField.getText()+"'");
				con.Update("DELETE FROM sali WHERE codSala="+"'"+codSalaField.getText()+"'");			
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
			}
		});
		btnStergereSali.setBounds(44, 399, 128, 23);
		TabSali.add(btnStergereSali);
		
		JLabel lblCodSala = new JLabel("Cod sala");
		lblCodSala.setBounds(20, 67, 66, 14);
		TabSali.add(lblCodSala);
		
		codSalaField = new JTextField();
		codSalaField.setBounds(96, 61, 86, 20);
		TabSali.add(codSalaField);
		codSalaField.setColumns(10);
		
		numeSalaField = new JTextField();
		numeSalaField.setBounds(96, 92, 86, 20);
		TabSali.add(numeSalaField);
		numeSalaField.setColumns(10);
		
		JLabel lblNumeSala = new JLabel("Nume");
		lblNumeSala.setBounds(20, 98, 46, 14);
		TabSali.add(lblNumeSala);
		
		capacitateSalaField = new JTextField();
		capacitateSalaField.setBounds(96, 123, 86, 20);
		TabSali.add(capacitateSalaField);
		capacitateSalaField.setColumns(10);
		
		JLabel lblCapacitateSali = new JLabel("Capacitate");
		lblCapacitateSali.setBounds(20, 129, 66, 14);
		TabSali.add(lblCapacitateSali);
		
		tipSalaField = new JTextField();
		tipSalaField.setBounds(96, 154, 86, 20);
		TabSali.add(tipSalaField);
		tipSalaField.setColumns(10);
		
		JLabel lblTip = new JLabel("Tip");
		lblTip.setBounds(20, 160, 46, 14);
		TabSali.add(lblTip);
		
		JPanel TabAdaugare = new JPanel();
		tabbedPane.addTab("Interactivitate", null, TabAdaugare, null);
		TabAdaugare.setLayout(null);
		
		JLabel lblZi = new JLabel("Zi");
		lblZi.setBounds(44, 86, 26, 16);
		TabAdaugare.add(lblZi);
		
		JLabel lblModul = new JLabel("Modul");
		lblModul.setBounds(112, 86, 56, 16);
		TabAdaugare.add(lblModul);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(210, 86, 56, 16);
		TabAdaugare.add(lblDisciplina);
		
		JLabel lblProfesor = new JLabel("Profesor");
		lblProfesor.setBounds(439, 86, 56, 16);
		TabAdaugare.add(lblProfesor);
		
		JLabel lblFormatie = new JLabel("Formatie");
		lblFormatie.setBounds(575, 86, 56, 16);
		TabAdaugare.add(lblFormatie);
		
		JComboBox comboZi = new JComboBox();
		comboZi.setBounds(28, 115, 56, 22);
		TabAdaugare.add(comboZi);
			
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select denZi from zi ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboZi.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		
		JComboBox comboModul = new JComboBox();
		comboModul.setBounds(96, 115, 102, 22);
		TabAdaugare.add(comboModul);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select oraModul from modul ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboModul.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		
		JComboBox comboDisciplina = new JComboBox();
		comboDisciplina.setBounds(210, 115, 64, 22);
		TabAdaugare.add(comboDisciplina);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select numeDisc from discipline ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboDisciplina.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		
		JComboBox comboProfesor = new JComboBox();
		comboProfesor.setBounds(390, 115, 161, 22);
		TabAdaugare.add(comboProfesor);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select nume_prof from profesor ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboProfesor.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		
		JComboBox comboFormatie = new JComboBox();
		comboFormatie.setBounds(575, 115, 123, 22);
		TabAdaugare.add(comboFormatie);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select fSubgrupa from formatii ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboFormatie.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		JButton btnInsert = new JButton("Insert");
		
		btnInsert.setBounds(339, 213, 97, 25);
		TabAdaugare.add(btnInsert);
		
		JLabel lblTip_1 = new JLabel("Tip");
		lblTip_1.setBounds(316, 86, 56, 16);
		TabAdaugare.add(lblTip_1);
		
		JComboBox comboTip = new JComboBox();
		comboTip.setBounds(286, 115, 92, 22);
		TabAdaugare.add(comboTip);
		comboTip.addItem("L");
		comboTip.addItem("S");
		comboTip.addItem("P");
		comboTip.addItem("C");
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(740, 86, 56, 16);
		TabAdaugare.add(lblSala);
		
		JComboBox comboSala = new JComboBox();
		comboSala.setBounds(725, 115, 86, 22);
		TabAdaugare.add(comboSala);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select numeSala from sali ");
		
			while (rs.next()){
				a =rs.getString(1);
				comboSala.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a =Integer.parseInt(con.Str("select max(codProgram) from program"))+1;
				con.Update("INSERT INTO `program`(`codZi`, `codM`, `codDisc`, `codProfesor`, `codFormatie`,`codSala`,`tipModul`)"+
				" select DISTINCT zi.codZi,modul.codModul,discipline.codDisc,profesor.id_prof,formatii.codF,sali.codSala,"+"'"+comboTip.getSelectedItem().toString()+"' "+
				" from zi,modul,discipline,profesor,formatii,sali,program"+
				" where zi.denZi="+"'"+comboZi.getSelectedItem().toString()+"'"+" and modul.oraModul="+"'"
				+comboModul.getSelectedItem().toString()+"'"+" and discipline.numeDisc="+"'"
				+comboDisciplina.getSelectedItem().toString()+"'"+" and profesor.nume_prof="+"'"
				+comboProfesor.getSelectedItem().toString()+"'"+" and formatii.fSubgrupa="+"'"
				+comboFormatie.getSelectedItem().toString()+"'"+" and sali.numeSala="+"'"
				+comboSala.getSelectedItem().toString()+"'");
			System.out.println("INSERT INTO `program`(`codZi`, `codM`, `codDisc`, `codProfesor`, `codFormatie`,`codSala`,`tipModul`)"+
					" select DISTINCT zi.codZi,modul.codModul,discipline.codDisc,profesor.id_prof,formatii.codF,sali.codSala,"+"'"+comboTip.getSelectedItem().toString()+"' "+
					" from zi,modul,discipline,profesor,formatii,sali,program"+
					" where zi.denZi="+"'"+comboZi.getSelectedItem().toString()+"'"+" and modul.oraModul="+"'"
					+comboModul.getSelectedItem().toString()+"'"+" and discipline.numeDisc="+"'"
					+comboDisciplina.getSelectedItem().toString()+"'"+" and profesor.nume_prof="+"'"
					+comboProfesor.getSelectedItem().toString()+"'"+" and formatii.fSubgrupa="+"'"
					+comboFormatie.getSelectedItem().toString()+"'"+" and sali.numeSala="+"'"
					+comboSala.getSelectedItem().toString()+"'");
			
			}
		});
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		
		JLabel lblGrupaStud = new JLabel("Grupa");
		lblGrupaStud.setBounds(25, 140, 62, 14);
		TabStudenti.add(lblGrupaStud);
		
		grupaStudField = new JTextField();
		grupaStudField.setColumns(10);
		grupaStudField.setBounds(97, 137, 86, 20);
		TabStudenti.add(grupaStudField);
		
		JLabel lblAnStud = new JLabel("An");
		lblAnStud.setBounds(25, 205, 62, 14);
		TabStudenti.add(lblAnStud);
		
		anStudField = new JTextField();
		anStudField.setColumns(10);
		anStudField.setBounds(97, 199, 86, 20);
		TabStudenti.add(anStudField);
		
		JPanel TabOrar = new JPanel();
		tabbedPane.addTab("Orar", null, TabOrar, null);
		
		JScrollPane scrollPane = new JScrollPane();
		TabOrar.add(scrollPane);
		AfisareOrar afisOrar = new AfisareOrar();
		scrollPane.setViewportView(afisOrar);
		afisOrar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel AfisarePtProf = new JPanel();
		tabbedPane.addTab("AfisarePtProfesori", null, AfisarePtProf, null);
		AfisarePtProf.setLayout(new MigLayout("", "[866px]", "[522px][]"));
		
		JLabel lblProfesori = new JLabel("Profesori");
		AfisarePtProf.add(lblProfesori, "flowx,cell 0 0,alignx left,aligny top");
		
		JComboBox ProfBox = new JComboBox();
		AfisarePtProf.add(ProfBox, "cell 0 0,alignx left,aligny top");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select nume_prof from profesor ");
		
			while (rs.next()){
				a =rs.getString(1);
				ProfBox.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		JButton btnAfisProf = new JButton("Afisare");
		
		AfisarePtProf.add(btnAfisProf, "cell 0 0,alignx center,aligny top");
		
		
		
	

		
		btnAfisProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfisarePtProf.remove(AfisarePtProf.getComponentAt(400,400));
				AfisarePtProf.updateUI();
				AfisareInFnctProfesor afisPRF = new AfisareInFnctProfesor(ProfBox.getSelectedItem().toString());
				AfisarePtProf.add(afisPRF, "cell 0 0,alignx left,aligny top");
				AfisarePtProf.updateUI();
				
				
			}
		});
	


		JPanel AfisareSala = new JPanel();
		tabbedPane.addTab("AfisarePeSala", null, AfisareSala, null);
		AfisareSala.setLayout(new MigLayout("", "[140px][][][][][][]", "[22px][25px]"));
		
		JLabel lblSali = new JLabel("Sali");
		AfisareSala.add(lblSali, "cell 0 0,alignx left,aligny center");
		
		JComboBox SalaBox = new JComboBox();
		AfisareSala.add(SalaBox, "cell 0 0,alignx right,aligny top");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");
			String a="";
			Statement st =connection.createStatement();
			
			ResultSet rs = st.executeQuery("select numeSala from Sali ");
		
			while (rs.next()){
				a =rs.getString(1);
				SalaBox.addItem(a);
			}
				
				connection.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}
		JButton btnAfisareSali = new JButton("Afisare");
		btnAfisareSali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfisareSala.remove(AfisareSala.getComponentAt(400,400));
				AfisareSala.updateUI();
				AfisareInFnctSala afisSala = new AfisareInFnctSala(SalaBox.getSelectedItem().toString());
				AfisareSala.add(afisSala, "cell 3 0 1 2,alignx left,aligny top");
				AfisareSala.updateUI();
			}
		});
		AfisareSala.add(btnAfisareSali, "cell 1 0,alignx left,aligny top");
		
		
		
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				GUI refresh = new GUI();
				refresh.frame.setVisible(true);
			}
		});
		btnRefresh.setBounds(403, 578, 89, 23);
		frame.getContentPane().add(btnRefresh);
		btnRefresh.setBounds(328, 573, 89, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
