package Coffee.GUI;

import Main.main;

import Coffee.BUS.LoginBUS;
import Coffee.DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {
        this.setTitle("Đăng nhập");
        this.setSize(440, 624);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        views();
        addEvents();
    }



    private JLabel btnExit, btnLogin, btnForgot;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JPanel pnMain;
    private JCheckBox ckbRemember;

    private void views() {
        Container con = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBackground(Color.BLUE);
        

        btnLogin = new JLabel("DANG NHAP");
        btnLogin.setForeground(Color.RED);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // thay doi bieu tuong mouse
        btnLogin.setBounds(24, 513, 392, 55);

        

        Font fontTXT = new Font("", Font.BOLD, 18);
        txtUser = new JTextField();
        txtUser.setBackground(Color.YELLOW);
        txtUser.setBorder(BorderFactory.createEmptyBorder());
        txtUser.setForeground(Color.WHITE);
        txtUser.setFont(fontTXT);
        txtUser.setHorizontalAlignment(JTextField.LEFT);
        txtUser.setBounds(36, 302, 370, 50);

        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('•');
        txtPassword.setBackground(new Color(0, 0, 0, 0f));
        txtPassword.setBorder(BorderFactory.createEmptyBorder());
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setFont(fontTXT);
        txtPassword.setHorizontalAlignment(JTextField.LEFT);
        txtPassword.setBounds(36, 401, 370, 50);



        pnMain.add(txtUser);
        pnMain.add(txtPassword);
        pnMain.add(btnLogin);

        con.add(pnMain);
    }

    private void addEvents() {
        moveFrame();
        
        txtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.requestFocus();
            }
        });
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyDangNhap();
            }
        });
       
        btnLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyDangNhap();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //btnLogin.setIcon(new ImageIcon("image/LoginUI/btn-login--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //.setIcon(new ImageIcon("image/LoginUI/btn-login.png"));
            }
        });
    }

    int xMouse, yMouse;

    private void moveFrame() {
        pnMain.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Move(x, y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void xuLyThoat() {
        System.exit(0);
    }

   

    private void xuLyDangNhap() {
        LoginBUS LoginBUS = new LoginBUS();
        TaiKhoan tk = LoginBUS.getTaiKhoanDangNhap(txtUser.getText(),
                txtPassword.getText());
        if (tk != null) {
            this.dispose();
            HomeGUI gui = new HomeGUI();
            this.dispose();
            gui.showWindow();
        }
    }

    public void showWindow() {
       //Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        //this.setIconImage(icon);
        this.setVisible(true);
    }

}
