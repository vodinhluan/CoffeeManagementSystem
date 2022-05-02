package Coffee.BUS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import Coffee.DTO.TaiKhoan;
import Coffee.BUS.QuyenBUS;
import Coffee.DAO.LoginDAO;

public class LoginBUS {


    private final static int EMPTY_ERROR = 1;
    private final static int WRONG_ERROR = 2;
    public static TaiKhoan taiKhoanLogin = null;

    public TaiKhoan getTaiKhoanDangNhap(String user, String password) {
		if (kiemTraDangNhap(user, password) == EMPTY_ERROR) {
			JOptionPane.showMessageDialog(null, "Khong duoc de trong thong tin");
        }
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        LoginDAO dangNhapDAO = new LoginDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);
        taiKhoanLogin = account;

        if (account == null) {
			JOptionPane.showMessageDialog(null, "Sai thong tin dang nhap");
        } else {
            QuyenBUS phanQuyenBUS = new QuyenBUS();
            phanQuyenBUS.kiemTraQuyen(account.getQuyen());
			JOptionPane.showMessageDialog(null, "Dang Nhap Thanh Cong");
//	            new MyDialog("Vì tình hình dịch Covid phức tạp, cửa hàng chỉ thực hiện bán mang về!", MyDialog.INFO_DIALOG);
        }
        return account;
	}

  
    private int kiemTraDangNhap(String user, String password) {
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        int result = 0;

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        LoginDAO dangNhapDAO = new LoginDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);

        if (user.length() <= 0 || password.length() <= 0) {
            result = EMPTY_ERROR;
        } else if (account == null) {
            result = WRONG_ERROR;
        }
        return result;
    }

}

 






