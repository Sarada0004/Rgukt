import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.Applet;
import javax.swing.*;

public class Projectt implements ActionListener
{
   Frame f1;
   MenuBar mb;
   Menu m1,m2,m3;
   Label l1,l2,l3;
   TextField t1,t2;
   Button b1,b2,b3,b4;
   MenuItem nw,op,sv,svs,ext,en,dec;
   TextArea ta;
   FileDialog fd;
   String name,path,n;
   int count=0;
    Frame f2;
    public Projectt() 
    {
    f1=new Frame("Cryptoanalysis");
    f1.setSize(500,500);
	
	 mb=new MenuBar();
    m1=new Menu("File");
    m2=new Menu("Security");
    ta=new TextArea();
    f1.add(ta);
   
    nw=new MenuItem("New");
    op=new MenuItem("Open");
    sv=new MenuItem("Save");
    svs=new MenuItem("Save As");
    ext=new MenuItem("Exit");
    en=new MenuItem("encrpyt");
    dec=new MenuItem("decrypt");
   
    
    fd=new FileDialog(f1,"Save As",FileDialog.SAVE);
   
    nw.addActionListener(this);
    op.addActionListener(this);
    sv.addActionListener(this);
    svs.addActionListener(this);
    ext.addActionListener(this);
    en.addActionListener(this);
    dec.addActionListener(this);
    
    m1.add(nw); 
    m1.add(op); 
    m1.add(sv);
    m1.add(svs);
    m1.add(ext); 
   
    m2.add(en);
    m2.add(dec);
   
    GridLayout gl=new GridLayout(3,0);
    f2=new Frame("encrpyt-decrypt");
    f2.setSize(300,200);
    f2.setLayout(gl);
   
	
    Label l1=new Label("enter password");
    TextField tf=new TextField(10);
    Panel p=new Panel();
    Button b1=new Button("ok");
    Button b2=new Button("Close");
	
	en.setEnabled(false);
	dec.setEnabled(false);
	b1.addActionListener(new ActionListener()
    {
    public void actionPerformed(ActionEvent ae1)
    {
		f2.setVisible(false);
    }
    });
	b2.addActionListener(new ActionListener()
    {
    public void actionPerformed(ActionEvent ae1)
    {
    f2.setVisible(false);
    f2.dispose();
    }
    });
	
	p.add(b1);
    p.add(b2);
    f2.add(l1);
	f2.add(tf);
    f2.add(p);
	mb.add(m1);
    mb.add(m2);
    f1.setMenuBar(mb);
    f1.setVisible(true);
	
    }
	
	public void actionPerformed(ActionEvent e)
    {
    try
    {
    //String str=e.getActionCommand();
    MenuItem mi=(MenuItem)e.getSource();
   
    if(mi==nw)
    {
    ta.setText("");
    }
	 else if(mi==op)
    {
     fd=new FileDialog(f1,"Open",FileDialog.LOAD);
     fd.setVisible(true);
     name=fd.getFile();
     path=fd.getDirectory();
     n=path+name+"";
         File f=new File(path,name);
           FileInputStream fis=new FileInputStream(f);
           int ch;
           ta.setText("");
           while((ch=fis.read())!=-1)
            {
               ta.appendText((char)ch+"");
            }
			en.setEnabled(true);
           fis.close();
           f1.setTitle(fd.getFile()+"-Notepad");
		   
           
      }
	
	 else if(mi==sv) 
    {
    fd=new FileDialog(f1,"Save",FileDialog.SAVE);
         fd.setVisible(true);
         name=fd.getFile();
         path=fd.getDirectory();
         String strr=ta.getText();
         File f=new File(path,name);
     //f.createNewFile();
     String str1=ta.getText();
     FileOutputStream fos=new FileOutputStream(f);
       char arr[];
     arr=strr.toCharArray();
     for(int i=0;i<arr.length;i++)
     { 
      fos.write(arr[i]);
     }
       fos.close();
	   en.setEnabled(true);
    }
	else if(mi==svs)
    {
    fd=new FileDialog(f1,"Save As",FileDialog.SAVE);
    fd.setVisible(true);
    name=fd.getFile();
    path=fd.getDirectory();
    File f=new File(path,name);
    String str1=ta.getText();
    FileOutputStream fos=new FileOutputStream(f);
    char arr[];
    arr=str1.toCharArray();
    for(int i=0;i<arr.length;i++)
    { 
      fos.write(arr[i]);
    }
      fos.close();
		en.setEnabled(true);
   
   
    }
	else if(mi==ext)
    {
       f1.setVisible(false);
           f1.dispose();           //release the memory
           System.exit(1);
    }
    if(mi==en)
    {
		String str=JOptionPane.showInputDialog(ta,"Enter the key");
		String enstr=ta.getText();
		ta.setText(" ");
		ta.setText(this.encryption(enstr,str));
		dec.setEnabled(true);
    }
    if(mi==dec)
    {
		ta.setText(" ");
		 fd=new FileDialog(f1,"decrypt",FileDialog.LOAD);
     fd.setVisible(true);
     name=fd.getFile();
     path=fd.getDirectory();
     n=path+name+"";
         File f=new File(path,name);
           FileInputStream fis=new FileInputStream(f);
           int ch;
           ta.setText("");
           while((ch=fis.read())!=-1)
            {
               ta.appendText((char)ch+"");
            }
			en.setEnabled(true);
           fis.close();
		String dstr=JOptionPane.showInputDialog(ta,"Enter the key");
		String decstr=ta.getText();
		ta.setText(this.decryption(decstr,dstr));
    }
    }
    catch(Exception ex)
    {
   
    }
         
    }
    public String encryption(String s, String c)
    {
		
		int l=s.length();
		
		char hash[]=new char[26];
		char hashC[]=new char[26];
		char ch='a';
		char CH='A';
		String newk="";
		for(int i=0;i<26;i++)
		{
			hash[i]=ch;
			hashC[i]=CH;
			ch++;
			CH++;
		}
		int kl=c.length();
		int r=l/kl;
		int rem=l%kl;
		for(int i=1;i<=r;i++)
		{
			
			newk=newk+c;
		}
		int j=0;
		while(rem!=0)
		{
			newk=newk+Character.toString(c.charAt(j));
			j++;
			rem--;
		}
		String fi="";
		char a[],b[];
		a=s.toCharArray();
		b=newk.toCharArray();
		for(int i=0;i<l;i++)
		{
			
			if(a[i]>=97 && a[i]<=122)
			{
				int v=a[i]-b[i];
			v=v%25;
			if(v>=0)
			{
				fi=fi+Character.toString(hash[v]);
			}
			else
				{
					v=v+25;
					
					fi=fi+Character.toString(hash[v]);
			
				}
				
				
			}
			else if(a[i]>=65 && a[i]<=90)
			{
				int v=a[i]-(b[i]-32);
			v=v%25;
			if(v>=0)
			{
				fi=fi+Character.toString(hashC[v]);
			}
			else
				{
					v=v+25;
					fi=fi+Character.toString(hashC[v]);
			
				}
				
				
			}
			else
			{
				fi=fi+Character.toString(a[i]);
			}
			
			
			
			
	}
	return fi;
	}
	 public String decryption(String s, String c)
    {
		
		
		int l=s.length();
		
		char hash[]=new char[26];
		char hashC[]=new char[26];
		char ch='a';
		char CH='A';
		String newk="";
		for(int i=0;i<26;i++)
		{
			hash[i]=ch;
			hashC[i]=CH;
			ch++;
			CH++;
		}
		int kl=c.length();
		int r=l/kl;
		int rem=l%kl;
		for(int i=1;i<=r;i++)
		{
			
			newk=newk+c;
		}
		int j=0;
		while(rem!=0)
		{
			newk=newk+Character.toString(c.charAt(j));
			j++;
			rem--;
		}
		String fi="";
		char a[], b[];
		a=s.toCharArray();
		b=newk.toCharArray();
		for(int i=0;i<l;i++)
		{
			
			if(a[i]>=97 && a[i]<=122)
			{
				int v=a[i]+b[i]-'a'-'a';
			v=v%25;
			if(v>=0)
			{
				fi=fi+Character.toString(hash[v]);
			}
			else
				{
					v=v+25;
					
					fi=fi+Character.toString(hash[v]);
			
				}
				
				
			}
			else if(a[i]>=65 && a[i]<=90)
			{
				int v=a[i]+(b[i]-32)-'A'-'A';
			v=v%25;
			if(v>=0)
			{
				fi=fi+Character.toString(hashC[v]);
			}
			else
				{
					v=v+25;
					fi=fi+Character.toString(hashC[v]);
			
				}
				
				
			}
			else
			{
				fi=fi+Character.toString(a[i]);
			}
			
			
			
			
	}
	return fi;
	}
    public static void main(String args[])
    {
    Projectt pj=new Projectt();
    }
}
