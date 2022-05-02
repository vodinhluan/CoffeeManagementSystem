package Coffee.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Coffee.DAO.QuyenDAO;
import Coffee.DTO.Quyen;

public class QuyenBUS {
    public static Quyen quyenTK = null;
    private QuyenDAO QuyenDAO = new QuyenDAO();
    private ArrayList<Quyen> listQuyen = null;

    public void docDanhSachQuyen() {
        this.listQuyen = QuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String quyen) {
        quyenTK = QuyenDAO.getQuyen(quyen);
    }

    public ArrayList<Quyen> getListQuyen() {
        if (listQuyen == null)
            docDanhSachQuyen();
        return this.listQuyen;
    }

    public boolean suaQuyen(String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
        Quyen phanQuyen = new Quyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = QuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
			JOptionPane.showMessageDialog(null, "Sua thanh cong!");
        } else {
			JOptionPane.showMessageDialog(null, "Sua that bai");
        }
        return flag;
    }

    public boolean themQuyen(String tenQuyen) {
        if (tenQuyen == null || tenQuyen.trim().equals("")) {
            return false;
        }

        if (kiemTonTaiTraQuyen(tenQuyen)) {
			JOptionPane.showMessageDialog(null, "Them that bai. Quyen da ton tai.");
            return false;
        }

        Quyen Quyen = new Quyen(tenQuyen, 0, 0, 0, 0, 0);
        boolean flag = QuyenDAO.themQuyen(Quyen);
        if (flag) {
			JOptionPane.showMessageDialog(null, "Them thanh cong! Hay dieu chinh quyen.");
        } else {
			JOptionPane.showMessageDialog(null, "Them that bai! Quyen da ton tai.");
        }
        return flag;
    }

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (Quyen q : listQuyen) {
            if (q.getQuyen().equalsIgnoreCase(tenQuyen))
                return true;
        }
        return false;
    }

    public boolean xoaQuyen(String tenQuyen) {
        boolean flag = QuyenDAO.xoaQuyen(tenQuyen);
        if (flag) {
			JOptionPane.showMessageDialog(null, "Xoa thanh cong");
        } else {
			JOptionPane.showMessageDialog(null, "Xoa that bai");

        }
        return flag;
    }
}
