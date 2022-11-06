// ���ӵ��� esc�� ������ ���� ����ϴ� Ŭ����(�Ͻ�����)
package ������Ʈ;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class GamePause extends JDialog{
	ImageIcon pause_resumebuttonImg = new ImageIcon(GameFrame.class.getResource("../�̹���/Pause��ư/����ϱ�.png"));
	ImageIcon pause_exitbuttonImg1 = new ImageIcon(GameFrame.class.getResource("../�̹���/Pause��ư/������.png"));
	ImageIcon pause_resumebuttonImg_clicked = new ImageIcon(GameFrame.class.getResource("../�̹���/Pause��ư/����ϱⴭ��.png"));
	ImageIcon pause_exitbuttonImg1_clicked = new ImageIcon(GameFrame.class.getResource("../�̹���/Pause��ư/�����ⴭ��.png"));
	JButton pause_resumebutton = new JButton(pause_resumebuttonImg);	// ����ϱ��ư
	JButton pause_exitbutton = new JButton(pause_exitbuttonImg1);		// ������ ��ư
	
	private GameMusic btnmusic;
	
	public GamePause() {
		this.setSize(GameFrame.WIDTH_SIZE, GameFrame.HEIGHT_SIZE);						// â ũ��(209X150)
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 50));
		this.setAlwaysOnTop(true);					// �׻� ���� ���� ǥ��
		this.setCursor(GameFrame.cursor.default_cursor);
		this.setVisible(true);
		
		pause_resumebutton.setBounds(537, 278, 210, 68);
		pause_resumebutton.setContentAreaFilled(false);
		pause_resumebutton.setBorderPainted(false);
		pause_resumebutton.setFocusPainted(false);
		pause_resumebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pause_resumebutton.setIcon(pause_resumebuttonImg_clicked);
				pause_resumebutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("��ư��.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_resumebutton.setIcon(pause_resumebuttonImg);
				pause_resumebutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pause_resumebutton.setCursor(GameFrame.cursor.clicked_cursor);
				GamePlayer.isPause = false;
				dispose();
			}
		});
		
		pause_exitbutton.setBounds(537, 378, 210, 68);
		pause_exitbutton.setContentAreaFilled(false);
		pause_exitbutton.setBorderPainted(false);
		pause_exitbutton.setFocusPainted(false);
		pause_exitbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pause_exitbutton.setIcon(pause_exitbuttonImg1_clicked);
				pause_exitbutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("��ư��.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_exitbutton.setIcon(pause_exitbuttonImg1);
				pause_exitbutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {		// ���ӿ��� ������
				pause_exitbutton.setCursor(GameFrame.cursor.clicked_cursor);
				dispose();
				GameScreen.isGameStart = false;				// ���� ���� �ƴϿ�� ǥ��
				GamePlayer.isPause = false;					// �� ���̾�α׸� ����°� �ƴϿ� ǥ��
				setFocusable(false);						// Ű ��Ŀ�� false
				GameStage.music.close();					// ��������
				GameFrame.startscreen.mainScreen();			// ����ȭ�� �ҷ�����
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){											// ESC������ ��
					if(GamePlayer.isPause && GameScreen.isGameStart==true) {		// ������â�� �����ְ� ���� �������̶��
						GamePlayer.isPause = false;								// ������â ������ false
						dispose();												// â �ݱ�
					}
				}
			}
		});
		
		this.setFocusable(true);

		this.add(pause_resumebutton);
		this.add(pause_exitbutton);
	}
}
