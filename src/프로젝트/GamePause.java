// 게임도중 esc를 눌렀을 때를 담당하는 클래스(일시정지)
package 프로젝트;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class GamePause extends JDialog{
	ImageIcon pause_resumebuttonImg = new ImageIcon(GameFrame.class.getResource("../이미지/Pause버튼/계속하기.png"));
	ImageIcon pause_exitbuttonImg1 = new ImageIcon(GameFrame.class.getResource("../이미지/Pause버튼/나가기.png"));
	ImageIcon pause_resumebuttonImg_clicked = new ImageIcon(GameFrame.class.getResource("../이미지/Pause버튼/계속하기눌림.png"));
	ImageIcon pause_exitbuttonImg1_clicked = new ImageIcon(GameFrame.class.getResource("../이미지/Pause버튼/나가기눌림.png"));
	JButton pause_resumebutton = new JButton(pause_resumebuttonImg);	// 계속하기버튼
	JButton pause_exitbutton = new JButton(pause_exitbuttonImg1);		// 나가기 버튼
	
	private GameMusic btnmusic;
	
	public GamePause() {
		this.setSize(GameFrame.WIDTH_SIZE, GameFrame.HEIGHT_SIZE);						// 창 크기(209X150)
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 50));
		this.setAlwaysOnTop(true);					// 항상 가장 위에 표시
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
				btnmusic = new GameMusic("버튼음.mp3", false);
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
				btnmusic = new GameMusic("버튼음.mp3", false);
				btnmusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_exitbutton.setIcon(pause_exitbuttonImg1);
				pause_exitbutton.setCursor(GameFrame.cursor.default_cursor);
			}
			@Override
			public void mousePressed(MouseEvent e) {		// 게임에서 나가기
				pause_exitbutton.setCursor(GameFrame.cursor.clicked_cursor);
				dispose();
				GameScreen.isGameStart = false;				// 게임 실행 아니요로 표시
				GamePlayer.isPause = false;					// 이 다이얼로그를 띄웠는가 아니요 표시
				setFocusable(false);						// 키 포커스 false
				GameStage.music.close();					// 음악종료
				GameFrame.startscreen.mainScreen();			// 메인화면 불러오기
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){											// ESC눌렀을 때
					if(GamePlayer.isPause && GameScreen.isGameStart==true) {		// 나가기창이 켜져있고 게임 진행중이라면
						GamePlayer.isPause = false;								// 나가기창 변수를 false
						dispose();												// 창 닫기
					}
				}
			}
		});
		
		this.setFocusable(true);

		this.add(pause_resumebutton);
		this.add(pause_exitbutton);
	}
}
