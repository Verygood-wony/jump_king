// 게임 도움말 클래스(메인화면의 도움말)
package 프로젝트;

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
	private Image img = new ImageIcon(GameFrame.class.getResource("../도움말/1page.png")).getImage();
	private ImageIcon closebuttonImg = new ImageIcon(GameFrame.class.getResource("../도움말/닫기1.png"));			// 메인화면의 시작버튼 기본이미지
	private ImageIcon closebuttonImgClicked = new ImageIcon(GameFrame.class.getResource("../도움말/닫기2.png"));			// 메인화면의 시작버튼 기본이미지
	private JButton closebutton = new JButton(closebuttonImg);
	private GameMusic btnmusic;
	
	public GameHelp() {
		this.setSize(GameFrame.WIDTH_SIZE, GameFrame.HEIGHT_SIZE);						// 창 크기
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(225, 224, 140, 100));
		this.setAlwaysOnTop(true);							// 항상 가장 위에 표시
		this.setCursor(GameFrame.cursor.default_cursor);	// 마우스 커서 설정
		closebutton.setBounds(1200, 10, 64, 65);
		closebutton.setContentAreaFilled(false);
		closebutton.setBorderPainted(false);
		closebutton.setFocusPainted(false);
		closebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closebutton.setIcon(closebuttonImgClicked);
				closebutton.setCursor(GameFrame.cursor.click_able_cursor);
				btnmusic = new GameMusic("버튼음.mp3", false);
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
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){		// ESC눌렀을 때
					if(GameScreen.isGameStart==false) {		// 게임 진행중이 아니라면
						setFocusable(false);
						dispose();							// 창 닫기
					}
				}
			}
		});
		
		this.setFocusable(true);							// 키입력 포커스
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 390, 110, null);
	}
}
