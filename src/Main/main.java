package Main;

import Coffee.DAO.MyConnect;
import Coffee.GUI.LoginGUI;

public class main {
	public static void main(String[] args) {
        new MyConnect();

        LoginGUI login = new LoginGUI();
        login.showWindow();
    }
}
