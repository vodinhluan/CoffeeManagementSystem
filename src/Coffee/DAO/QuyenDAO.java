package Coffee.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Coffee.DAO.MyConnect;
import Coffee.DTO.Quyen;

public class QuyenDAO {
	 public ArrayList<Quyen> getListQuyen() {
	        try {
	            String sql = "SELECT * FROM phanquyen";
	            Statement st = MyConnect.conn.createStatement();
	            ResultSet rs = st.executeQuery(sql);
	            ArrayList<Quyen> dspq = new ArrayList<>();
	            while (rs.next()) {
	                Quyen phanQuyen = new Quyen();
	                phanQuyen.setQuyen(rs.getString(1));
	                phanQuyen.setNhapHang(rs.getInt(2));
	                phanQuyen.setQlSanPham(rs.getInt(3));
	                phanQuyen.setQlNhanVien(rs.getInt(4));
	                phanQuyen.setQlKhachHang(rs.getInt(5));
	                phanQuyen.setThongKe(rs.getInt(6));
	                dspq.add(phanQuyen);
	            }
	            return dspq;
	        } catch (Exception e) {
	        }
	        return null;
	    }

	    public Quyen getQuyen(String quyen) {
	        try {
	            String sql = "SELECT * FROM phanquyen WHERE quyen='" + quyen + "'";
	            Statement st = MyConnect.conn.createStatement();
	            ResultSet rs = st.executeQuery(sql);
	            if (rs.next()) {
	                Quyen phanQuyen = new Quyen();
	                phanQuyen.setQuyen(quyen);
	                phanQuyen.setNhapHang(rs.getInt(2));
	                phanQuyen.setQlSanPham(rs.getInt(3));
	                phanQuyen.setQlNhanVien(rs.getInt(4));
	                phanQuyen.setQlKhachHang(rs.getInt(5));
	                phanQuyen.setThongKe(rs.getInt(6));
	                return phanQuyen;
	            }
	        } catch (Exception e) {
	        }
	        return null;
	    }

	    public boolean suaQuyen(Quyen phanQuyen) {
	        try {
	            String sql = "UPDATE phanquyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE Quyen=?";
	            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
	            pre.setInt(1, phanQuyen.getNhapHang());
	            pre.setInt(2, phanQuyen.getQlSanPham());
	            pre.setInt(3, phanQuyen.getQlNhanVien());
	            pre.setInt(4, phanQuyen.getQlKhachHang());
	            pre.setInt(5, phanQuyen.getThongKe());
	            pre.setString(6, phanQuyen.getQuyen());
	            return pre.executeUpdate() > 0;
	        } catch (Exception e) {
	        }
	        return false;
	    }

	    public boolean themQuyen(Quyen phanQuyen) {
	        try {
	            String sql = "INSERT INTO phanquyen VALUES (?,?,?,?,?,?)";
	            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
	            pre.setString(1, phanQuyen.getQuyen());
	            pre.setInt(2, phanQuyen.getNhapHang());
	            pre.setInt(3, phanQuyen.getQlSanPham());
	            pre.setInt(4, phanQuyen.getQlNhanVien());
	            pre.setInt(5, phanQuyen.getQlKhachHang());
	            pre.setInt(6, phanQuyen.getThongKe());
	            return pre.executeUpdate() > 0;
	        } catch (Exception e) {
	        }
	        return false;
	    }

	    public boolean xoaQuyen(String phanQuyen) {
	        try {
	            String sql1 = "UPDATE TaiKhoan SET Quyen='Default' WHERE Quyen='" + phanQuyen + "'";
	            Statement st1 = MyConnect.conn.createStatement();
	            st1.executeUpdate(sql1);
	            String sql = "DELETE FROM phanquyen WHERE Quyen='" + phanQuyen + "'";
	            Statement st = MyConnect.conn.createStatement();
	            return st.executeUpdate(sql) > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
