// ���� ���� Ŭ����(����ȭ���� ����)
package ������Ʈ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class GameHelp extends JDialog{
	private Image img = new ImageIcon(GameFrame.class.getResource("../����/1page.png")).getImage();
	private ImageIcon closebuttonImg = new ImageIcon(GameFrame.class.getResource("../����/�ݱ�1.png"));			// ����ȭ���� ���۹�ư �⺻�̹���
	private ImageIcon closebuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../����/�ݱ�2.png"));			// ����ȭ���� ���۹�ư �⺻�̹���
	private JButton closebutton = new JButton(closebuttonImg);
	private GameMusic btnmusic;
	
	public GameHelp() {
		this.setSize(GameFrame.WIDTH_SIZE, GameFrame.HEIGHT_SIZE);						// â ũ��
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(225, 224, 140, 100));
		this.setAlwaysOnTop(true);							// �׻� ���� ���� ǥ��
		this.setCursor(GameFrame.cursor.default_cursor);	// ���콺 Ŀ�� ����
		closebutton.setBounds(1200, 10, 64, 65);
		closebutton.setContentAreaFilled(false);
		closebutton.setBorderPainted(false);
		closebutton.setFocusPainted(false);
		closebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closebutton.setIcon(closebuttonImgClicked);
				closebutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("��ư��.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closebutton.setIcon(closebuttonImg);
				closebutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				closebutton.setCursor(GameFrame.cursor.clicked_cursor);
				setFocusable(false);
				dispose();
			}
		});
		add(closebutton);
		this.setVisible(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){		// ESC������ ��
					if(GameScreen.isGameStart==false) {		// ���� �������� �ƴ϶��
						setFocusable(false);
						dispose();							// â �ݱ�
					}
				}
			}
		});
		
		this.setFocusable(true);							// Ű�Է� ��Ŀ��
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 390, 110, null);
	}
}
