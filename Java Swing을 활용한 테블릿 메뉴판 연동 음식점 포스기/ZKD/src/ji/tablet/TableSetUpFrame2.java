package ji.tablet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TableSetUpFrame2 extends JFrame {

   TableSetController2 tableSetController2;
   ButtonGroup bg1;
   JButton pass;
   JButton table_SetUp_Bnt;
   private JPanel contentPane;
   private JTextField nickNameTextField ;
   JLabel result;
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               TableSetUpFrame2 frame = new TableSetUpFrame2();
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
   public TableSetUpFrame2() {
      init();
   }
   public TableSetUpFrame2(TableSetController2 tableSetController2) {
      this.tableSetController2=tableSetController2;
      init();
      setVisible(true);
   }
   void init() {
      setBounds(400, 200, 612, 210);
      setTitle("닉네임/컨셉 재설정");
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel nickNameSetUpPanel = new JPanel();
      contentPane.add(nickNameSetUpPanel, BorderLayout.NORTH);
      nickNameSetUpPanel.setLayout(new BorderLayout(0, 0));
      
      JLabel youLab = new JLabel("당신의 ");
      nickNameSetUpPanel.add(youLab, BorderLayout.WEST);
      
      JLabel areLab = new JLabel(" 입니다.");
      nickNameSetUpPanel.add(areLab, BorderLayout.EAST);
      
      JPanel panel_5 = new JPanel();
      nickNameSetUpPanel.add(panel_5, BorderLayout.CENTER);
      panel_5.setLayout(new BorderLayout(0, 0));
      
      JLabel nickNamelab = new JLabel("닉네임은 ");
      panel_5.add(nickNamelab, BorderLayout.WEST);
      
      nickNameTextField = new JTextField("GUEST");
      panel_5.add(nickNameTextField, BorderLayout.CENTER);
      nickNameTextField.setColumns(10);
      
      JPanel panel = new JPanel();
      nickNameSetUpPanel.add(panel, BorderLayout.SOUTH);
      panel.setLayout(new GridLayout(0, 2, 1, 0));

      JLabel nickN_regex = new JLabel("           * 한글로 5글자 이하로 입력해주세요.");
      nickN_regex.setHorizontalAlignment(SwingConstants.LEFT);
      panel.add(nickN_regex);
      
      JPanel cenceptSepUpPanel = new JPanel();
      contentPane.add(cenceptSepUpPanel, BorderLayout.CENTER);
      cenceptSepUpPanel.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_3 = new JPanel();
      cenceptSepUpPanel.add(panel_3, BorderLayout.NORTH);
      
      JLabel concept = new JLabel("오늘의 음주 컨셉을 선택해주세요 !");
      panel_3.add(concept);
      
      JPanel conceptBntPanel = new JPanel();
      cenceptSepUpPanel.add(conceptBntPanel, BorderLayout.CENTER);
      
      JToggleButton conceptBnt1 = new JToggleButton("방해받기 싫음"); //눌려져있는 버튼 생성하기//true는 눌려있다.
      conceptBnt1.setBounds(20, 210, 70, 30);
      conceptBntPanel.add(conceptBnt1);
      JToggleButton conceptBnt2 = new JToggleButton("가볍게 한잔만");
      conceptBnt2.setBounds(90, 210, 70, 30);
      conceptBntPanel.add(conceptBnt2);
      JToggleButton conceptBnt3 = new JToggleButton("달리고싶은 날");
      conceptBnt3.setBounds(160, 210, 70, 30);
      conceptBntPanel.add(conceptBnt3);
      bg1 = new ButtonGroup(); //버튼을 묶어주는 것 - 토글1을 눌렀을때 토글2가 풀렸으면 좋겠어서
      bg1.add(conceptBnt1);
      bg1.add(conceptBnt2);
      bg1.add(conceptBnt3);
      
      
      JPanel panel_2 = new JPanel();
      contentPane.add(panel_2, BorderLayout.SOUTH);
      panel_2.setLayout(new BorderLayout(0, 0));
      
      //확인버튼 누르면 닉넥임과 컨셉이 DB에 자료 넣고 테이블태블릿에 뜨게 하기
      table_SetUp_Bnt = new JButton("확인");
//      table_SetUp_Bnt.setEnabled(false);
      table_SetUp_Bnt.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            String strNN = nickNameTextField.getText();

            if (Pattern.matches("[ㄱ-힣]{1,6}", strNN)) {
            String tT_concept= "";
      
            if(conceptBnt1.isSelected())tT_concept+=conceptBnt1.getText();
            else if(conceptBnt2.isSelected())tT_concept+=conceptBnt2.getText();
            else if(conceptBnt3.isSelected())tT_concept+=conceptBnt3.getText();
            tableSetController2.setNickNameNConcept(strNN, tT_concept ,tableSetController2.clientName);
            tableSetController2.refreshTable(strNN, tT_concept);
            
            dispose();//확인 버튼 누르면 창 닫기
            } else if(!Pattern.matches("[ㄱ-힣]{1,6}", strNN)){
               result.setText("형식에 맞지않습니다.");
               result.setForeground(Color.RED);
            }
         }
      });
      panel_2.add(table_SetUp_Bnt);
      
      result = new JLabel(" ");
      result.setHorizontalAlignment(SwingConstants.LEFT);
      panel.add(result);
      
      //건너뛰기 버튼 누르면 창이 꺼지게 만들기
      pass = new JButton("건너뛰기");
//      pass.setEnabled(false);
      panel_2.add(pass, BorderLayout.EAST);
      
      pass.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) 
         
         {    
            tableSetController2.tableMaincontroller.openBtn();

            tableSetController2.setNickNameNConcept("GUEST", "   ", tableSetController2.clientName);
            tableSetController2.refreshTable("GUEST", "   ");
            dispose();// 건너뛰기 버튼 누르면 창 닫기
         }
      });
   }

}