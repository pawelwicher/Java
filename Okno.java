package projekt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Okno extends JFrame
{


	 Saper s=new Saper(16,16,25);
	 boolean koniec=false;
	 JButton button[][]=new JButton[s.szerokosc][s.wysokosc]; 	//tablica przyciskow
	 JTextField text[][]=new JTextField[s.szerokosc][s.wysokosc];
	 JTextField t=new JTextField();
	JMenuBar menu;
	JMenu menu1;
	JMenuItem poz1,poz2;

	
	public Okno()
	{	
	setLayout(null);
	
	this.setTitle("SAPER");
	this.setResizable(false);		//ustawienia okienka
	this.setSize(327,390);
	this.setLocation(300,200);
	this.setBackground(new Color(100,100,100));
	
	
	menu=new JMenuBar();
	menu1=new JMenu("Gra");			//tworzenie menu
	poz1=new JMenuItem("Nowa gra");
 	poz2=new JMenuItem("Koniec");
 	
 	menu1.add(poz1);
 	menu1.addSeparator();  	//dodanie menu
 	menu1.add(poz2);
 	menu.add(menu1);
 	setJMenuBar(menu);
 	
 	t.setSize(320,20);
	t.setLocation(0,1);
	t.setHorizontalAlignment(JTextField.CENTER);
	t.setEditable(false);
 	add(t);
	
	for(int i=0;i<s.szerokosc;i++)
	for(int j=0;j<s.wysokosc;j++)
	{
	button[i][j]=new JButton();
	text[i][j]=new JTextField();
	text[i][j].setSize(20,20);
	text[i][j].setLocation(0+20*i,21+20*j);				//dynamiczny tworzenie
	text[i][j].setHorizontalAlignment(JTextField.CENTER);	//i umieszczanie buttonow
	text[i][j].setEditable(false);							//oraz textfieldow
	text[i][j].setVisible(false);
	button[i][j].setSize(20,20);
	button[i][j].setLocation(0+20*i,21+20*j);
	add(text[i][j]);
	add(button[i][j]);
	
	button[i][j].addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent evt){
	for(int i=0;i<s.szerokosc;i++)
	for(int j=0;j<s.wysokosc;j++)				//dodwanie sluchaczy dla przyciskow
    if(evt.getSource()==button[i][j])			//gdy nacisiniemy prawy klawisz myszy
	FunkcjaSterujaca(i,j);}});					//wykona sie FunkcjaSterujaca do ktorej
												//zostana przekazane i oraz j
	
	button[i][j].addMouseListener(new MouseAdapter(){
	public void mousePressed(MouseEvent evt){
	for(int i=0;i<s.szerokosc;i++)				//gdy prawy - FunkcjaPrawyKlawisz
	for(int j=0;j<s.wysokosc;j++)
    if(evt.getSource()==button[i][j])
    if(evt.isMetaDown()==true)
	FunkcjaPrawyKlawisz(i,j);}});
	}
	
	
	poz1.addActionListener(new ActionListener()
	{public void actionPerformed(ActionEvent evt)	//Nowa gra
	{poz1Action(evt);}});
	
	poz2.addActionListener(new ActionListener()
	{public void actionPerformed(ActionEvent evt)	//zamkniecie okna
	{System.exit(0);}});
	
	addWindowListener(new WindowAdapter()
	{public void windowClosing(WindowEvent evt)		//zamkniecie okna
	{System.exit(0);}});
	
	}
	
	
	void FunkcjaSterujaca(int i,int j)
	{
	    button[i][j].setVisible(false);		//odkrycie pola
	    text[i][j].setVisible(true);
	    
	    if(s.OdkryjPole(i,j)==-1)		//jesli trafiono na mine...
	    {
	    	koniec=true;				//...to koniec
	    	t.setText("Przegra³eœ, spróbuj jeszcze raz."); //komunikat
		   	for(int p=0;p<s.szerokosc;p++)
				for(int q=0;q<s.wysokosc;q++)
				{
				button[p][q].setVisible(false);
				text[p][q].setVisible(true);		//schowanie wszystkich buttonow
				if(s.pole[p][q]==-1)				//wyswietlenie min
					text[p][q].setBackground(Color.red);
				}
		}
		else							//...w przeciwnym razie
		{
		    for(int p=0;p<s.szerokosc;p++)
				for(int q=0;q<s.wysokosc;q++)
				if(s.odkryte[p][q]==1)
					{
					button[p][q].setVisible(false);
					text[p][q].setVisible(true);
					}
			if(s.OdkryjPole(i,j)==s.ileMin)		//okrywanie pustych pol
			{									//a jezeli funkcja OdkryjPole
				t.setText("Wygra³eœ, gratulacje !!!");//zwroci liczbe ileMin
				for(int p=0;p<s.szerokosc;p++)			//to wygrana
					for(int q=0;q<s.wysokosc;q++)
					{
					button[p][q].setVisible(false);		//schowanie wszystkich buttonow
					text[p][q].setVisible(true);
					if(s.pole[p][q]==-1)				//wyswietlenie min
						text[p][q].setBackground(Color.red);
					}
			}
		}

	}
	
	void FunkcjaPrawyKlawisz(int i,int j)		//zaznaczanie min
	{
		if(koniec==false)
			if(button[i][j].getBackground()==Color.red)
			{
			button[i][j].setEnabled(true);
			button[i][j].setBackground(null);
			}
			else
			{
			button[i][j].setEnabled(false);
			button[i][j].setBackground(Color.red);
			}
	}
	
	void NowaGra()
	{
		koniec=false;
		t.setText("");
		for(int i=0;i<s.szerokosc;i++)
		for(int j=0;j<s.wysokosc;j++)
			text[i][j].setText("");			//resetowanie wszystkich ustawien
		
		s.WypelnijPole();				//tworzenie nowej planszy
		for(int i=0;i<s.szerokosc;i++)
			for(int j=0;j<s.wysokosc;j++)
			{
			if(s.pole[i][j]!=0)
				text[i][j].setText(""+s.pole[i][j]);	//przypisywanie ilosci min
			if(s.pole[i][j]==-1)
				text[i][j].setText("");
			text[i][j].setVisible(false);
			text[i][j].setBackground(null);
			button[i][j].setBackground(null);
			button[i][j].setVisible(true);
			button[i][j].setEnabled(true);
			}
	}
	
	void poz1Action(ActionEvent evt)
	{
		NowaGra();
	}	
		
	public static void main(String args[])
	{
		Okno okno=new Okno();
		okno.show();
		okno.NowaGra();
	}
}



