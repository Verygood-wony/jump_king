// JFrame Ŭ���� (���θ޼ҵ带 ����)
package ������Ʈ;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	Image img = new ImageIcon(GameFrame.class.getResource("../�̹���/������.png")).getImage();
	public static final int WIDTH_SIZE = 1280;	// â ����ũ�� ����
	public static final int HEIGHT_SIZE = 720;	// â ����ũ�� ����
	public static GameScreen screen = new GameScreen();
	public static GameStartScreen startscreen;
	public static GameCursor cursor = new GameCursor();
	
	public GameFrame() {
		setTitle("201611466 ������ ����");			// ����
		setResizable(false);					// �������� â ũ������ �Ұ�
		setIconImage(img);						// �۾�ǥ���� ������ �̹���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// â ����� ���α׷� ��ü�� ����
		add(screen);
		setSize(WIDTH_SIZE, HEIGHT_SIZE);		// â ũ��(1280X720)
		setLocationRelativeTo(null);			// â�� ȭ�� ���߾� ǥ��
		setUndecorated(true);					// ������ ��� ����(���� ����ǥ���� ����)
		setBackground(new Color(0, 0, 0, 0));	// ���� �� ���� ����(r,g,b,������)
		setVisible(true);						// ȭ��ǥ��
		setCursor(cursor.default_cursor);		// ���콺 Ŀ�� ����
		startscreen = new GameStartScreen();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
	}
}