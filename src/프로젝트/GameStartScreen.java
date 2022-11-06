// 프로그램 실행시 학번 나오는 파트
package 프로젝트;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GameStartScreen implements ActionListener{
	private Timer timer;
	public static GameMusic music;
	public static boolean intro = false;
	public static Image introImage;
	public static Color color;
	public static int i = 0;
	
	public GameStartScreen() {			// 시작 로고 메소드
		timer = new Timer(0, this);		// 0ms마다 이벤트 실행
		introImage = new ImageIcon(GameFrame.class.getResource("../이미지/시작로고.png")).getImage();
		intro = true;
		music = new GameMusic("게임시작음악.mp3", false);
		music.start();
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		color = new Color(i, i, i, i);			// 색상 및 투명도 지정(r,g,b,투명도)	
		if(i==255) {							// 색은 255까지
			if(music.isend) {					// 음악이 끝날때까지 진행
				timer.stop();					// 타이머 종료
				intro = false;					// 인트로 변수 false
				mainScreen();					// 아래에 있는 mainScreen메소드 호출
			}
		}
		else {
			i++;
		}
	}
	
	public void mainScreen() {	// 메인 화면으로 넘어감
		GameScreen.background = new ImageIcon(GameFrame.class.getResource("../이미지/메인화면/배경화면타이틀.png")).getImage();
		GameFrame.screen.rankdraw = true;
		try {
			GameFrame.screen.file.fileRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music = new GameMusic("메인화면음악.mp3", true);
		music.start();
		
		GameScreen.startbutton.setVisible(true);	// 버튼 표시
		GameScreen.helpbutton.setVisible(true);
		GameScreen.exitbutton.setVisible(true);
	}
}
