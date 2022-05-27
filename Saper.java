package projekt;

import java.util.Random;

public class Saper{

int pole[][];
int odkryte[][];
int szerokosc=16;
int wysokosc=16;
int ileMin=15;

    public Saper(int szerokosc,int wysokosc,int ileMin)//konstruktor
    {
    this.szerokosc=szerokosc;
    this.wysokosc=wysokosc;
    this.ileMin=ileMin;
    pole=new int[szerokosc][wysokosc];	
    odkryte=new int[szerokosc][wysokosc];
    }
    
    
    void WypelnijPole()
	{
		int x,y;
		int m=0;
		
		CzyscTablice(pole);
		CzyscTablice(odkryte);
		
		Random r=new Random();
		
		while (m<ileMin)
		{
		do
		{
		x=r.nextInt(szerokosc);	//losowe umieszanie miny
		y=r.nextInt(wysokosc);
		}
		while(pole[x][y]==-1);
		pole[x][y]=-1;			//mina
		m++;					//zwieksz licznik min
		}
		
		for(x=0;x<szerokosc;x++)
		{
		for(y=0;y<wysokosc;y++)
		{
		if (pole[x][y]!=-1)
		{
		m=0;	//wypelnianie pol sasiadujacych z minami
		if(x-1>=0 && y-1>=0 && pole[x-1][y-1]==-1)m++;
		if(y-1>=0 && pole[x][y-1]==-1)m++;
		if(x+1<szerokosc && y-1>=0 && pole[x+1][y-1]==-1)m++;
		if(x-1>=0 && pole[x-1][y]==-1)m++;
		if(x+1<szerokosc && pole[x+1][y]==-1)m++;
		if(x-1>=0 && y+1<wysokosc && pole[x-1][y+1]==-1)m++;
		if(y+1<wysokosc && pole[x][y+1]==-1)m++;
		if(x+1<szerokosc && y+1<wysokosc && pole[x+1][y+1]==-1)m++;
		if(m!=0)
		pole[x][y]=m;
		}
		}
		}
	}
	
	void OdkryjPoleRek(int x,int y)
	{		
		if (x<0 || x>=szerokosc || y<0 || y>=wysokosc)
		return;
		if(odkryte[x][y]!=0)
		return;
		odkryte[x][y]=1;
		
		if(pole[x][y]==0)
		{
		OdkryjPoleRek(x-1,y-1);	//rekurencyjna funkcja odkrywania pol
		OdkryjPoleRek(x,y-1);
		OdkryjPoleRek(x+1,y-1);
		OdkryjPoleRek(x-1,y);
		OdkryjPoleRek(x+1,y);
		OdkryjPoleRek(x-1,y+1);
		OdkryjPoleRek(x,y+1);
		OdkryjPoleRek(x+1,y+1);
		}
	}
			
	int OdkryjPole(int x,int y)
	{
	int licznik=0;
	if(pole[x][y]==-1)
	return -1;
	else
	{
	OdkryjPoleRek(x,y);			//odkrywanie pol, jesli trafimy na mine
	for(x=0;x<szerokosc;x++)	//to zwroci -1
	for(y=0;y<wysokosc;y++)		//jezeli odkryjemy wszystkie miny - zwroci ileMin
	if(odkryte[x][y]==0) licznik++;
	}
	return licznik;
	}
	
	void CzyscTablice(int T[][])
	{
	for(int x=0;x<szerokosc;x++)
	for(int y=0;y<wysokosc;y++)
	T[x][y]=0;
	}
	

}