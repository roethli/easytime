package ch.roethli.timeclock.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Component;

public class TimeGUI extends JFrame {

	private JPanel contentPane;
	private ArrayList<Time> timelist = new ArrayList<Time>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		   try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (InstantiationException e) {
		        e.printStackTrace();
		    } catch (IllegalAccessException e) {
		        e.printStackTrace();
		    } catch (UnsupportedLookAndFeelException e) {
		        e.printStackTrace();
		    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeGUI frame = new TimeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimeGUI() {
		setTitle("EasyTime");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimeGUI.class.getResource("/ch/roethli/timeclock/java/clock_red.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 353);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmExportieren = new JMenuItem("Exportieren");
		mntmExportieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//Delimiter used in CSV file
				final String COMMA_DELIMITER = ",";
				final String NEW_LINE_SEPARATOR = "\n";
				
				//CSV file header
				final String FILE_HEADER = "datum,zeit";

				
					
					//Create new students objects
				
					FileWriter fileWriter = null;
							
					try {
						fileWriter = new FileWriter("zeiten.xml");

						//Write the CSV file header
						fileWriter.append(FILE_HEADER.toString());
						
						//Add a new line separator after the header
						fileWriter.append(NEW_LINE_SEPARATOR);
						
						//Write a new student object list to the CSV file
						for (Time t : timelist) {
							fileWriter.append(String.valueOf(t.getCurrentdate()));
							fileWriter.append(COMMA_DELIMITER);
							fileWriter.append(String.valueOf(t.getCurrenttime()));
							fileWriter.append(COMMA_DELIMITER);
							fileWriter.append(NEW_LINE_SEPARATOR);
							fileWriter.append(NEW_LINE_SEPARATOR);
						}

						
						
						System.out.println("CSV file was created successfully !!!");
						
					} catch (Exception e1) {
						System.out.println("Error in CsvFileWriter !!!");
						e1.printStackTrace();
					} finally {
						
						try {
							fileWriter.flush();
							fileWriter.close();
						} catch (IOException e1) {
							System.out.println("Error while flushing/closing fileWriter !!!");
			                e1.printStackTrace();
						}
						
					}
				}
				
		
			
		});
		mnDatei.add(mntmExportieren);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TimeGUI.class.getResource("/ch/roethli/timeclock/java/clock_red.png")));
		lblNewLabel.setBounds(501, 0, 123, 80);
		contentPane.add(lblNewLabel);
		
		final JLabel lblDate = new JLabel("");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.WHITE);
		lblDate.setOpaque(true);
		lblDate.setBackground(Color.GRAY);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDate.setBounds(20, 64, 239, 27);
		contentPane.add(lblDate);
		
		final JLabel lblTime = new JLabel("");
		lblTime.setOpaque(true);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTime.setBackground(Color.GRAY);
		lblTime.setBounds(20, 92, 239, 27);
		contentPane.add(lblTime);
		

		final DefaultListModel listModel = new DefaultListModel();
		
		JLabel lblEasytimeZeiterfassung = new JLabel("Tages\u00FCbersicht");
		lblEasytimeZeiterfassung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEasytimeZeiterfassung.setBounds(280, 36, 117, 27);
		contentPane.add(lblEasytimeZeiterfassung);
		
		JLabel lblLetzteRegistrierung = new JLabel("Letzte Registrierung");
		lblLetzteRegistrierung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLetzteRegistrierung.setBounds(32, 36, 187, 27);
		contentPane.add(lblLetzteRegistrierung);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TimeGUI.class.getResource("/ch/roethli/timeclock/java/gui_title_bg.PNG")));
		label.setBounds(20, 36, 239, 27);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		
		lblNewLabel_1.setIcon(new ImageIcon(TimeGUI.class.getResource("/ch/roethli/timeclock/java/gui_title_bg.PNG")));
		lblNewLabel_1.setBounds(269, 36, 327, 27);
		contentPane.add(lblNewLabel_1);
		final JList list = new JList(listModel);
		
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Tahoma", Font.BOLD, 13));
		list.setBackground(Color.GRAY);
		list.setBounds(269, 64, 327, 217);
		contentPane.add(list);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		addPopup(list, popupMenu);
		
		JMenuItem mntmLschenMan = new JMenuItem("L\u00F6schen man!");
		mntmLschenMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listModel.remove(list.getSelectedIndex());
				
				for(int i=0;i<timelist.size();i++) { 
							      
							    if(listModel.get(i).equals(list) ) 
							    		{  
							    	  listModel.remove(i);
							        i--; 
							      
							       
						    } 
							} 

				
			}
		});
		mntmLschenMan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				popupMenu.setVisible(true);
				
			}
		});
		popupMenu.add(mntmLschenMan);
		

list.addMouseListener(new MouseAdapter() {


      public void mouseReleased(MouseEvent evt) {


         if (evt.isPopupTrigger()) {


            showMenu(evt);


     }


       }


  });




		
		JButton btnEintragen = new JButton("Eintragen");
		btnEintragen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timelist.add(new Time());
				listModel.removeAllElements();
				for(Time t : timelist)
				{
					
					lblTime.setText(t.getCurrenttime());
					lblDate.setText(t.getCurrentdate());
					listModel.addElement("Eingetragen: "+t.getCurrenttime());
					System.out.println(t.getCurrenttime());
					
				}
			}
		});
		btnEintragen.setIcon(new ImageIcon(TimeGUI.class.getResource("/ch/roethli/timeclock/java/okay.png")));
		btnEintragen.setSelectedIcon(new ImageIcon(TimeGUI.class.getResource("/ch/roethli/timeclock/java/okay.png")));
		btnEintragen.setBounds(20, 125, 107, 23);
		contentPane.add(btnEintragen);
		
		
		
		
		
		
		
		
	}
	
	public void showMenu(MouseEvent evt){


	

		}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
