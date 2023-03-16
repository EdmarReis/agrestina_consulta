
import java.awt.EventQueue;
import javax.swing.JFrame;
import persistence.DAO;
import telas.LoginConsulta;

public class PrincipalConsulta extends JFrame{

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginConsulta window = new LoginConsulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
