import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Puzzle_201921252 extends JFrame implements ActionListener{
    JButton b1,b2;
    JButton[][] pan=new JButton[4][4];
    int[][] panCount=new int [4][4];
    int brow=0;
    int bcol=0;
    //0~15 난수발생
    public void getRand()
    {
        int[] com=new int[16];
        int numb=0; //난수 발생시 저장한 변수
        boolean bCheck=false; //중복 여부 확인
        for(int i=0;i<16;i++)
        {
            //난수발생
            bCheck=true;
            while(bCheck)
            {
                numb=(int)(Math.random()*16);
                bCheck=false;
                for(int j=0;j<i;j++)
                {
                    if(numb==com[j])
                    {
                        bCheck=true;
                        break;
                    }
                }
            }
            com[i]=numb;
            
            panCount[i/4][i%4]=numb;
            
            if(numb==15)
            {
                brow=i/4;
                bcol=i%4;
            }
        }
    }
    //배치함수
    public void display()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(i==brow && j==bcol)
                {
                    pan[i][j].setText("");
                    pan[i][j].setEnabled(false);
                }
                else
                {
                pan[i][j].setText(String.valueOf(panCount[i][j]+1));
                pan[i][j].setEnabled(true);
                }
            }
        }
    }
    //버튼클릭
    public Puzzle_201921252()
    {
        b1=new JButton("시작");
        b2=new JButton("종료");
        JPanel p=new JPanel();
        p.add(b1);p.add(b2);
        add("South",p);
        
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(4,4,5,5));
        int k=1;
        for(int i=0; i<4; i++)
        {
            for(int j=0;j<4;j++)
            {
                pan[i][j]=new JButton(String.valueOf(k));
                pan[i][j].setFont(new Font("굴림체",Font.BOLD,50));
                p2.add(pan[i][j]);
                pan[i][j].addActionListener(this);
                k++;
            }
        }
        add("Center", p2);
        
        getRand();
        display();
        
        setSize(500,400);
        setVisible(true);
        
        b1.addActionListener(this);
    }
    public static void main(String[] args)
    {
        new Puzzle_201921252();
    }
  
    //종료
    public boolean isEnd(){
        int k=0;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(panCount[i][j]!=k)
                    return false;
                k++;
            }
        }return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            getRand();
            display();
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(e.getSource()==pan[i][j])
                {
                    panCount[brow][bcol]=panCount[i][j];
                    panCount[i][j]=15;
                    brow=i;
                    bcol=j;
                    display();
                    if(isEnd())
                    {
                        JOptionPane.showMessageDialog(this, "Game Over!!");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
