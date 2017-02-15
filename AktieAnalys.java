import java.awt.Dimension;		//Hur skapa en färdig textruta
import java.awt.Graphics;
import java.awt.GridLayout;		//Frågor: Hur göra knappen mindre
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField; 


public class AktieAnalys extends JFrame implements ActionListener  {
	
	protected JTextField ticker1 = new JTextField(15);
	protected JTextField ticker2 = new JTextField(15);
	protected JTextField startDatum = new JTextField(15);
	protected JTextField slutDatum = new JTextField(15);
	protected JTextArea resultatField = new JTextArea();
	protected JScrollPane scrollPane = new JScrollPane(resultatField);
	//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	private JLabel tic1 = new JLabel ("Ticker 1: ", JLabel.RIGHT);
	private JLabel tic2 = new JLabel ("Ticker 2: ", JLabel.RIGHT);
	private JLabel start = new JLabel ("Startdatum: ", JLabel.RIGHT);
	private JLabel slut = new JLabel ("Slutdatum: ", JLabel.RIGHT);
	
	private JPanel p = new JPanel();
	private JPanel p2 = new JPanel();
	private JLabel resultat = new JLabel();
	private JButton btn = new JButton("do query");
	
	
	private JRadioButton r1 = new JRadioButton("EUR");
	private JRadioButton r2 = new JRadioButton("USD");
	private JRadioButton r3 = new JRadioButton("SEK");
	
	private String urlString = "";
	private String urlString2 = "";
	private String urlString3="";
	
	public AktieAnalys () {
		setLayout(new GridLayout(4,3)); 
		add(p);
		add(btn);
		add(p2);
		//btn.insets=new Inset(0,10,10,0);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(r1);
	    group.add(r2);
	    group.add(r3);
	    
		r1.setHorizontalAlignment(r1.CENTER);
		r2.setHorizontalAlignment(r1.CENTER);
		r3.setHorizontalAlignment(r1.CENTER);
		
		p.setLayout(new GridLayout(4,3));
		p.add(tic1);
		p.add(ticker1);
		p.add(r1);
		p.add(tic2);
		p.add(ticker2);
		p.add(r2);
		p.add(start);
		p.add(startDatum);
		p.add(r3);
		p.add(slut);
		p.add(slutDatum);
		
		//p2.add(resultatField);
		p2.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400,100));
		
		
		//p2.add(slutDatum);
		
		//p2.add(r3);
			
		btn.addActionListener(this);
		//r1.addActionListener(this);
		//r2.addActionListener(this);
		//r3.addActionListener(this);
		
		pack();
		setLocationRelativeTo(null);
		//btn.setPreferredSize(new Dimension(1, 2));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}


	
	
	
	
	public void actionPerformed (ActionEvent e) {
		
		try{
		String tic1 = ticker1.getText();
		
		String tic2 = ticker2.getText();
		String start = startDatum.getText();
		String slut = slutDatum.getText();
		
		System.out.println(tic1);
		System.out.println(tic2);
		System.out.println(start);
		System.out.println(slut);
		
		boolean a = r1.isSelected();
		System.out.println(a);
		boolean b = r2.isSelected();
		boolean c = r3.isSelected();
		
		resultat.setText("test");
		
		String[] parts = start.split("\\.");				//Fungerar kanske utan att fixa till 2 decimaler? FUNGERAR
		int month = Integer.parseInt(parts[1]);
		month--;
		if (month < 10){
			String.valueOf(month);
			String.format("%02d", month);
		}
		else{
		String.valueOf(month);
		}
		//int day=Integer.parseInt(parts[0]);
		//String.format("%02d", parts[0]);
		
		
		String[] parts2 = slut.split("\\.");
		int month2 = Integer.parseInt(parts2[1]);
		month2--;
//		if (month2 < 10){
//		String.valueOf(month2);
//		String.format("%02d", month2);
//		}
//		else{
//		String.valueOf(month2);	
//		}
		urlString = "http://ichart.finance.yahoo.com/table.csv?s="+tic1+"&a="+month+"&b="+parts[0]+"&c="+parts[2]+"&d="+month2+"&e="+parts2[0]+"&f="+parts2[2]+"&g=d&ignore=.csv";
		System.out.println(urlString);
		
		urlString2 = "http://ichart.finance.yahoo.com/table.csv?s="+tic2+"&a="+month+"&b="+parts[0]+"&c="+parts[2]+"&d="+month2+"&e="+parts2[0]+"&f="+parts2[2]+"&g=d&ignore=.csv";
		System.out.println("test");
		if (a==true){
			urlString3= "http://ichart.finance.yahoo.com/table.csv?s=EUR=X&a="+month+"&b="+parts[0]+"&c="+parts[2]+"&d="+month2+"&e="+parts2[0]+"&f="+parts2[2]+"&g=d&ignore=.csv";
		}
		
		if (b==true) {
			urlString3=null;
			System.out.println("test2");
		}
		if (c==true){
			urlString3= "http://ichart.finance.yahoo.com/table.csv?s=SEK=X&a="+month+"&b="+parts[0]+"&c="+parts[2]+"&d="+month2+"&e="+parts2[0]+"&f="+parts2[2]+"&g=d&ignore=.csv";
		}
		System.out.println(urlString3);
		
		Request(urlString,urlString2,urlString3);
		}
		catch (Exception a){
			JOptionPane.showMessageDialog(null,"Check all inputs! ");
		}
		//aapl
		//fb
		//19.03.2014
		//26.03.2014
}	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AktieAnalys a = new AktieAnalys();
	}



	public void Request(String a, String b,String c) {
	ArrayList<String> aktiedata = new ArrayList<String>();
	ArrayList<String> aktiedata2 = new ArrayList<String>();
	ArrayList<String> kursdata = new ArrayList<String>();
	String line;
	try {
			URL url = new URL(a); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(url.openStream()));
			line = buf.readLine(); // första med data
			line = buf.readLine();
			//aktiedata.add(line); 
				while (line != null) { 
//				if (b==1){
				aktiedata.add(line); 
//				}
//				else if (b==2){
//				aktiedata2.add(line);
				
			line = buf.readLine();
	}
			
			
			
	URL url2 = new URL(b); 
	BufferedReader buf2 = new BufferedReader(new InputStreamReader(url2.openStream()));
	line = buf2.readLine(); // första med data
	line = buf2.readLine();
	//aktiedata2.add(line); 
		while (line != null) { 

		aktiedata2.add(line); 

		
	line = buf2.readLine();
		}
		
		if (c==null){
			System.out.println("test1");
			kursdata=null;
		}
		
		else{
		URL url3 = new URL(c); 
		BufferedReader buf3 = new BufferedReader(new InputStreamReader(url3.openStream()));
		line = buf3.readLine(); // första med data
		line = buf3.readLine();
		//aktiedata2.add(line); 
			while (line != null) { 

			kursdata.add(line); 

			
		line = buf3.readLine();
		
			}
		}
		
}



	
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to request data: Check tickers and dates! ");
			
		}
	appendResult(aktiedata,aktiedata2,kursdata); // 
	}
	
	protected void appendResult(ArrayList <String> aktiedata, ArrayList<String> aktiedata2, ArrayList<String> kursdata) {
		StringBuffer buf = new StringBuffer();
		String tic1 = ticker1.getText();
		String tic2 = ticker2.getText();
		DecimalFormat df = new DecimalFormat("#.00");
		for (int i = 0; i < aktiedata.size()|| i<aktiedata2.size(); i++) {
			double dd4=1;
			if (kursdata==null){
				dd4=1;
			}
			else{
				String[] d = kursdata.get(i).split(",");
				dd4=Double.parseDouble(d[4]);	
			}
			
			
			
			String[] x = aktiedata.get(i).split(",");
			//System.out.println(x[4]);
			double xx4=Double.parseDouble(x[4]);
			//System.out.println(xx4);
			xx4=dd4*xx4;
			String x4 = df.format(xx4);
				
			//x[4] = String.format("%.2f", x4);
			String[] y = aktiedata2.get(i).split(","); //Detta orsakar problem + y[4]
			
			double yy4=Double.parseDouble(y[4]);
			yy4=yy4*dd4;
			String y4 = df.format(yy4);
			//y[4] = String.format("%.2f", y[4]);
			buf.append(x[0] + " stock: " + tic1+ " close: "+ (x4)+ " || stock: "+tic2 +" close: "+y4+"\n");
		}

	
		resultatField.setText(buf.toString()); //Borde läggas till scrollpane
}
		public void paintComponent(Graphics g) {
			
		}
	}
	
